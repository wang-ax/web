package org.example.util;

/**
 * ClassName org.example.util
 * Description TODO
 * Author 30712
 * Date 2021-01-25
 * Time 11:23
 */

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.example.model.DocInfo;
import org.example.model.Weight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 构建索引：
 * 正排索引：从本地文件数据中读取到Java内存
 * 倒排索引：构建一个Map<String ,List<信息（Weight）>>(类似数据库的哈希索引)
 * Map键：关键词（分词来做）
 * Map值—信息：
 * （1）DocInfo对象引用或是docInfo的id，
 * （2）权重（标题对应关键词的数量*10+正文的对应关键词数量*1）——10和1是自己指定的
 * （3）关键词（是否需要）
 */
public class Index {
    //正排索引
    private static final List<DocInfo> FORWARD_INDEX = new ArrayList<>();
   //倒排索引
   private static  final Map<String,List<Weight>> INVERTED_INDEX = new HashMap<>();
    /**
     * 构建正排索引的内容：从本地raw_data.txt读取并保存
     */
    public static void buildForwardIndex(){
        try {
            FileReader fr = new FileReader(Parser.RAW_DATA);
            BufferedReader br = new BufferedReader(fr);

            int id = 0;//行号设置为docInfo的id
            String line;
            while ((line = br.readLine()) != null){
                if (line.trim().equals("")){
                    continue;
                }
                //一行对应一个DocInfo对象，类似数据库一行数据对应Java对象
                DocInfo doc = new DocInfo();
                doc.setId(++id);
                String[] parts = line.split("\3");//每一行按\3间隔符进行切分
                doc.setTitle(parts[0]);
                doc.setUrl(parts[1]);
                doc.setContent(parts[2]);
                /*if (parts[0].contains("�")){
                    System.out.println("title=================[url:"+doc.getUrl()+"]");
                }
                if (parts[1].contains("�")){
                    System.out.println("content=================[url:"+doc.getUrl()+"]");
                }*/
               // System.out.println(doc);
                //添加到正排索引
                FORWARD_INDEX.add(doc);
            }
        } catch (IOException e) {
            //初始化操作有异常让线程不捕获异常，从而结束程序
            //初始化（启动tomcat）
            throw  new RuntimeException(e);
        }
    }

    /**
     * 构建倒排索引：从Java内存中正排索引获取文档信息来构建
     */
    public static void buildInvertedIndex(){
        for (DocInfo doc :FORWARD_INDEX){
            //一个doc分别对标题和正文分词，每一个分词生成一个weight对象，需要计算权重
            /**
             *  第一次出现的关键词：要new Weight对象，之后出现要获取之前相同关键词对象，更新权重
             */
            //实现逻辑：先构造一个HashMap，保存分词（键）和weight对象（value）
            Map<String,Weight> cache = new HashMap<>();

            //title分词
            List<Term> titleFencis = ToAnalysis.parse(doc.getTitle()).getTerms();
            for (Term titleFenci : titleFencis){//标题分词并遍历处理
                /*if (titleFenci.getName().contains("�")){
                    System.out.println("title fenci ========== [url:"+doc.getUrl()+"]");
                }*/
                //分词作为键,获取标题分词键对应的weight对象
                Weight w = cache.get(titleFenci.getName());//通过键获取weight对象
                if (w == null){//如果没有，就创建一个并放到map中
                    w = new Weight();
                    w.setDoc(doc);
                    w.setKeyword(titleFenci.getName());

                    cache.put(titleFenci.getName(),w );
                }
                //标题分词,权重+10
                w.setWeight(w.getWeight()+10);
            }
            //正文的分词处理：逻辑和标题分词逻辑是一样的
            List<Term> contentFencis = ToAnalysis.parse(doc.getContent()).getTerms();
            for (Term  contentFenci:contentFencis){

                /*if (contentFenci.getName().contains("�")){
                    System.out.println("content fenci ========== [url:"+doc.getUrl()+"]");
                }*/

                Weight w = cache.get(contentFenci.getName());
                if (w == null){
                    w = new Weight();
                    w.setDoc(doc);
                    w.setKeyword(contentFenci.getName());
                    cache.put(contentFenci.getName(),w);
                }
                //正文分词,权重+1
                w.setWeight(w.getWeight()+1);
            }

            //把临时保存的map数据（keyword-weight）全部保存到倒排索引中去
            for (Map.Entry<String,Weight> e :cache.entrySet()){
                //遍历Map，得到key和value
                String  keyword = e.getKey();
                Weight  w = e.getValue();
                //更新保存到倒排索引中去
                //倒排索引中： Map<String,List<Weight>>
                // 多个文档，同一个关键词，保存在一个List中
                List<Weight> weights = INVERTED_INDEX.get(keyword); //先在倒排索引中，通过keyword获取已有的值
                if (weights == null){//如果拿不到，就创建一个，并存放进倒排索引
                    weights = new ArrayList<>();
                    INVERTED_INDEX.put(keyword,weights);
                }

                weights.add(w);//倒排索引中，添加每个分词对应的Weight对象
            }
        }
    }

    public static List<Weight> get(String keyword){

        return INVERTED_INDEX.get(keyword);
    }

    public static void main(String[] args) {
        //创建正排索引
        Index.buildForwardIndex();
        //测试正排索引
       /* FORWARD_INDEX
                .stream()
                .forEach(System.out::println);*/

        //根据正排索引创建倒排索引
        Index.buildInvertedIndex();
        //测试倒排内容是否正确
        for (Map.Entry<String,List<Weight>>  e: INVERTED_INDEX.entrySet()){
            String keyword = e.getKey();
            System.out.print(keyword+":");
            List<Weight> weights = e.getValue();
            //不校验weight中的doc对象，正排索引已经测试过了

            weights.stream()
                    .map(w -> {//map操作：把list中每一个对象准换成其他对象
                        return "（"+w.getDoc().getId()+","+w.getWeight()+"）";
                    })//转换完，会变成List<String>
                  //  .collect(Collectors.toList());//直接返回List<Stirng>
            .forEach(System.out::print);
            System.out.println();//每一个遍历完之后换行
        }
    }

}
