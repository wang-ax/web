import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName PACKAGE_NAME
 * Description TODO 递归打印文件目录列表
 *   File提供的listFiles()方法只能够列出本目录中的第一级信息
 *  如果要列出所有级的信息，必须自己处理。（需要通过递归模式完成）
 * Author 30712
 * Date 2021-01-18
 * Time 9:13
 */
public class ListFile {
    public static void main(String[] args) {
        //创建一个File对象，本地的一个文件夹目录
        File dir = new File("D:\\Bit\\笔记");
        //打印该目录下的内容
        List<File> files =listDir2(dir);
        //jdk1.8 集合框架使用stream操作（效率高），可以使用lambda表达式
        files.stream()//集合的stream操作，提供更多的功能，效率也很高
                //.filter()//过滤
                //.map()  //把集合中的元素映射为另外一种类型
                //collect() //进行流操作重新返回
                .forEach(System.out::println);
    }
    public static List<File> listDir(File dir){
        List<File> list = new ArrayList<>();
        //效率低，如果是文件，返回一个list只存 放一个元素
        if (dir.isFile()){//是文件
            list.add(dir);
        }else if (dir.isDirectory()){//是文件夹
            File[] children = dir.listFiles();//将该文件夹下的子文件放入children这个数组中
            if (children != null){
                //循环遍历children
                for (File child :children){
                    List<File> files = listDir(child);//递归调用
                    list.addAll(files);
                }
            }
        }
        return list;
    }

    /**
     * 上述代码的优化（从性能上）
     * @param dir
     * @return
     */
    public static List<File> listDir2(File dir){
        List<File> list = new ArrayList<>();
        //获取目录下一级的子文件/文件夹
        File[] childern = dir.listFiles();
        if (childern != null) {
            //for循环遍历
            for (File child : childern) {
                //返回的文件列表不包含文件夹
                //在遍历过程中判断
                /*if (child.isDirectory()) {//如果子文件是文件夹
                    list.addAll(listDir2(child));//递归调用获取
                } else {
                    list.add(child);
                }*/
                //如果要包含文件夹
                list.add(child);
                if (child.isDirectory()){
                    list.addAll(listDir2(child));
                }
            }
        }
        return list;
    }
}
