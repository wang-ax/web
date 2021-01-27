package org.example.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.example.model.Result;
import org.example.model.Weight;
import org.example.util.Index;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
//loadOnStartup：必须是一个整数，表示servlet应该被载入的顺序
//如果该元素不存在或者这个数为负数时，容器会当该Servlet被请求时，再加载
//当值为0或者大于0时，表示容器在应用启动时就夹在并初始化这个Servlet
@WebServlet(value = "/search",loadOnStartup = 1)
public class SearchServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
       //初始化工作：先构建正排索引，再根据正排索引建立倒排索引
        Index.buildForwardIndex();
        Index.buildInvertedIndex();
        System.out.println("init complete!");
    }

    //重写doGet方法
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");//ajax请求，响应json格式
        //构造返回给前端的内容：
        //使用对象，之后再序列化为json字符串
        Map<String,Object> map = new HashMap<>();
        //解析请求数据
        String query = req.getParameter("query");//query是发送的请求数据
        List<Result> results = new ArrayList<>();//结果存在List中
        try{
            //根据搜索内容处理搜索业务
            //校验请求数据，搜索内容
            if (query == null || query.trim().length() == 0){
                //输出的请求的字符串没有意义
                map.put("ok",false);
                map.put("msg","搜索内容为空");
            }else {
                //1.根据搜索内容，进行分词，遍历每个分词
                for (Term t: ToAnalysis.parse(query).getTerms()){
                    String fenci = t.getName();//搜索的分词
                    //如果分词是没有意义的分词就跳过，不执行
                    //TODO 定义一个数组，包括没有意义的关键词  if(isValid(fenci) continue;
                    //2.每一个分词，在倒排中查找对应的文档（一个分词对应多个文档）
                    List<Weight> weights = Index.get(fenci);
                    //3.遍历多个文档，一个文档转换为Result（不同分词可能存在相同文档，需要合并）
                    for (Weight w :weights){
                        //先转换weight为result
                        Result r = new Result();
                        r.setId(w.getDoc().getId());
                        r.setTitle(w.getDoc().getTitle());
                        r.setWeight(w.getWeight());
                        r.setUrl(w.getDoc().getUrl());
                        //文档内容超过60个长度，超过部分就隐藏为...
                        String content = w.getDoc().getContent();
                        r.setDesc(content.length()<=60 ? content :content.substring(0,60)+"...");
                        //TODO  暂时不做合并涉及其他的代码，需要在List<Result>
                        // 找到已有的，判断docId相同，就已有的权重
                        //不存在，直接放进去
                        results.add(r);
                    }
                }
                //4.合并完成后，对List<Result>进行排序：根据权重降序排序
                results.sort(new Comparator<Result>() {
                    @Override
                    public int compare(Result o1, Result o2) {
                        return o1.getWeight()-o2.getWeight();
                    }
                });
                map.put("ok",true);
                map.put("data",results);
            }

        }catch (Exception e){
            e.printStackTrace();
            map.put("ok",false);
            map.put("msg","未知错误");
        }
        PrintWriter pw = resp.getWriter();
        //设置响应体的内容：map对象序列化为json对象
        pw.println(new ObjectMapper().writeValueAsString(map));//对象转为json字符串

    }
}
