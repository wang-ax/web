package org.example.test;

public class Test {

    private static class P{
        protected int x = 3;//protected

        public P(){
            //super();
            System.out.println(x);
            s();//方法是具有多态性的
            System.out.println(x);
        }
        protected void s(){
            x = 4;
        }
    }

    private static class C extends P{
       protected int x = 1;

        public C(){//先调用父类的构造方法，隐藏了super();
            System.out.println(x);
        }
        protected void s(){
            x = 6;
        }
    }

    public static void main(String[] args) {
        C c = new C();
        System.out.println(c.x);
    }
}


