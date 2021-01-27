import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

import java.util.List;

/**
 * ClassName PACKAGE_NAME
 * Description TODO  分词
 * Author 30712
 * Date 2021-01-25
 * Time 11:31
 */
//使用现成呢个的分词库 ansj
public class TestAnsj {
    public static void main(String[] args) {
        String str = "小明毕业于清华大学计算机专业," +
                "后来去蓝翔技校和新东方深造," +
                "擅长使用计算机控制挖掘机炒菜";
        List<Term> terms = ToAnalysis.parse(str).getTerms();//List包裹分词
        for (Term term : terms) {
            System.out.print(term.getName() + "/");//分词的内容
            //System.out.println(term.getNatureStr());
            //term.getNatureSrt()//分词的词性
        }
    }
    //Result result = ToAnalysis.parse(str); //封装的分词结果对象，包含一个terms列表
    // System.out.println(result);
    // List<Term> terms = result.getTerms(); //term列表，元素就是拆分出来的词以及词性

}
