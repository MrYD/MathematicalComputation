package main;
import com.company.*;
import java.util.Random;
import java.util.stream.Stream;

/**
 * Created by wang on 2016/05/12.
 */
public class Main0512 {
    public static void main(String[] args){

        Random rm = new Random();
        int l = 10000;
        String str = "";
        String str2 = "";
        int x[] = new int[l];
        int x2[] = new int[l];

        for (int n = 1; n <= 10000 ;n ++){
            for (int i = 0;i< l;i++) {
                x[i] = rm.nextInt();
            }
            x2 = x.clone();
            long s = System.nanoTime();
            Sort.GnormSort(x);
            long e = System.nanoTime();
            long s2 = System.nanoTime();
            Sort.GnormSort2(x2);
            long e2 = System.nanoTime();
            System.out.println(n + ","  + (e - s) +"," + (e2 - s2));
        }
    }
}
