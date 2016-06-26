package main;

/**
 * Created by wang on 2016/06/13.
 */
public class Main0613 {
    public static void main(String[] args) {

        long s = System.currentTimeMillis();
        for(int i = 0;i<1000000;i++) {
            int a = 1;
            int b = (int)(Math.random()*2);
            if (a == b) {
                //System.out.print("1");
            }
        }
        long e = System.currentTimeMillis();

        long s2 = System.currentTimeMillis();
        for (int i = 0;i<1000000;i++){
            int a = 1;
            int b = (int)(Math.random()*2);
            int c = a;
            a = b;
            b = c;
            //System.out.print("1");
        }
        long e2 = System.currentTimeMillis();

        System.out.println();
        System.out.println(e-s);
        System.out.println(e2-s2);
    }
}
