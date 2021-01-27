import org.example.model.DocInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName PACKAGE_NAME
 * Description TODO  List的api的使用
 * Author 30712
 * Date 2021-01-26
 * Time 9:21
 */
public class TestArrayList {
    static List<DocInfo> list = new ArrayList<>();

    public  static  void init(){
        DocInfo doc1 = new DocInfo();
        doc1.setId(1);
        doc1.setTitle("A");
        DocInfo doc2 = new DocInfo();
        doc2.setId(2);
        doc2.setTitle("B");
        DocInfo doc3 = new DocInfo();
        doc3.setId(3);
        doc3.setTitle("C");
        DocInfo doc4 = new DocInfo();
        doc4.setId(4);
        doc4.setTitle("D");

        DocInfo dup = new DocInfo();
        dup.setId(1);

        list.add(doc4);
        list.add(doc2);
        list.add(doc3);
        list.add(doc1);
        list.add(dup);
    }
    public static void main(String[] args) {
        init();
        DocInfo doc = new DocInfo();
        doc.setId(1);

        //要满足业务需求，使用以下方法
        // 一定要根据业务重写hashCode和equals方法

        //判断list中是不是包含doc对象
        System.out.println(list.contains(doc));//true
        //indexOf：第一次出现的索引位置
        System.out.println(list.indexOf(doc));//3
        //lastIndexOf:最后匹配到的元素的索引位置
        System.out.println(list.lastIndexOf(doc));//4

    }
}
