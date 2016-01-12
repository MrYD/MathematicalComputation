package main;

import com.company.*;

import java.awt.*;
import java.util.Timer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;


/**
 * Created by Main on 2016/01/06.
 */
public class MainWinter {
    private static int n = 100;
    private static double range;
    private static FileWriter fw;
    private static PrintWriter pw;
    public static void main(String[] args) throws Exception {
        fw = new FileWriter("/Users/Main/Desktop/test.csv", false);  //※１
        pw = new PrintWriter(new BufferedWriter(fw));
        for (range = 1; range < 1000000; range *= 2) {
            for (int j = 0;  j < 3; j++) {
                for (int i = 0; i < 1; i++) {
                    Vector X = Vector.generate(n, range);
                    Matrix A = Matrix.generate(n, n, range);
                    Vector B = A.multi(X);
                    pw.print(range);
                    pw.print(",");
                    pw.print(n);
                    System.out.println(n);
                    analysisGaussianEliminationWithPivot(A, B, X);
                    System.out.println();
                }
            }
        }
        pw.close();
    }
    public static void analysisGaussianEliminationWithPivot(Matrix A,Vector B,Vector X){
        System.out.println("GaussianElimination");
        pw.print(",");
        pw.print("GaussianElimination");
       analysis(X,()->{
            try {
                return Algorithm.gaussianEliminationWithPivot(A.toDoubleArray(),B.toDoubleArray());
            } catch (Exception e) {
                e.printStackTrace();
                pw.print(",");
                pw.print("-");
                pw.print(",");
                pw.print("-");
                pw.println();
                return new double[0];
            }
        });
    }
    public static void analysis(Vector X,Supplier<double[]> function){
        long start = System.nanoTime();
        double[] r = function.get();
        long end = System.nanoTime();
        System.out.println("Time:" + (end - start) / 1000000f + "ms");
        Vector R = new Vector(r);
        System.out.println("Norm2:" + X.sub(R).getNorm2());
        pw.print(",");
        pw.print((end - start) / 1000000f);
        pw.print(",");
        pw.print(X.sub(R).getNorm2());
        pw.println();
        pw.flush();
    }
}
