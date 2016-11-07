package main;

import javax.swing.*;
import java.util.function.Function;

/**
 * Created by Main on 2016/11/01.
 */
public class TrapezoidalRule {
    public static void main(String[] args){
        Function<Double,Double> f = (x)-> Math.pow(Math.E,Math.sin(x));
        //Function<Double,Double> f = (x)-> Math.pow(Math.E,-x*x);
        double a = 0;
        double b = 2 * Math.PI;

        for (int n : new int[]{10,100,1000,10000,100000,1000000,10000000}) {
            double h = (b-a)/n;
            double sum = 0;
            sum += h/2 * f.apply(a);
            for (int i = 1; i <= n-1; i++) {
                sum += h * f.apply(a + h * i);
            }
            sum += h/2 * f.apply(b);

            double sum2 = 0;
            for (int i = 0; i <= n-1; i++) {
                sum2 += h * f.apply(a+h*(i+0.5));
            }

            //System.out.print("T" + n + ": " + sum * 4);
            //System.out.print(",");
            System.out.println("矩形" + n + ": " +Math.abs(7.954926521012844-sum2));
            System.out.println("台形" + n + ": " +Math.abs(7.954926521012844-sum));
        }
    }
}
