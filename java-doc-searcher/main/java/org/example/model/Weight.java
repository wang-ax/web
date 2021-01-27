package org.example.model;

/**
 * ClassName org.example.model
 * Description TODO
 * Author 30712
 * Date 2021-01-25
 * Time 12:04
 */

/**
 * 倒排索引Map<String,List<Weight>>中，关键词对应的信息
 */
public class Weight {
    private  DocInfo doc;//文档
    private int weight =0;//权重值：通过标题和正文中，关键词的数量
    private String keyword;//关键词

    public DocInfo getDoc() {
        return doc;
    }

    public void setDoc(DocInfo doc) {
        this.doc = doc;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "Weight{" +
                "doc=" + doc +
                ", weight=" + weight +
                ", keyword='" + keyword + '\'' +
                '}';
    }
}
