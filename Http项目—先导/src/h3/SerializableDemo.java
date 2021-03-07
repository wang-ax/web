package h3;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;


public class SerializableDemo {
    //序列化
    public static void main(String[] args) throws IOException,ClassNotFoundException {
        //定义两个User对象
        /*User u1 = new User(1,"小王","女");
        User u2 = new User(2,"小李","男");*/

        //把两个对象，序列化，并写入到文件中
        //序列化
        String filename = "D:\\Bit\\code\\java-code\\20210304HTTP项目1\\sessions\\users.obj";
       /*try(OutputStream outputStream = new FileOutputStream(filename)) {
           ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

           objectOutputStream.writeObject(u1);//序列化
           objectOutputStream.writeObject(u2);
           objectOutputStream.flush();
       }*/
//反序列化
        //从文件中将两个User对象读取出来
        try(InputStream inputStream =new FileInputStream(filename)){
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            User u1 = (User) objectInputStream.readObject();//反序列化
            User u2 = (User) objectInputStream.readObject();

            System.out.println(u1);
            System.out.println(u2);
        }
    }
}
