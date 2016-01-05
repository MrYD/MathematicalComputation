package main;

import com.company.*;

import java.awt.*;
import java.util.Timer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by Main on 2016/01/06.
 */
public class MainWinter {
    private static int n= 10;
    private static double range= 100;
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 100; i++) {
            Vector X = Vector.generate(n, range);
            Matrix A = Matrix.generate(n, n, range);
            Vector B = A.multi(X);
            System.out.println("<GaussianElimination>");
            analysisGaussianEliminationWithPivot(A,B,X);
            System.out.println("<SOR>");
            analysisSOR(A,B,X);
        }
    }
    public static void analysisGaussianEliminationWithPivot(Matrix A,Vector B,Vector X){
       analysis(X,()->{
            try {
                return Algorithm.gaussianEliminationWithPivot(A.toDoubleArray(),B.toDoubleArray());
            } catch (Exception e) {
                e.printStackTrace();
                return new double[0];
            }
        });
    }

    public static void analysisSOR(Matrix A,Vector B,Vector X){
        analysis(X,()->Algorithm.SOR(A.toDoubleArray(),B.toDoubleArray(),
                new double[n],
                10E-14,100,1,
                Algorithm.ConvergenceTestMethod.soutaizansa,
                NormType.Two));
    }
    public static void analysisGauss_Seidel(Matrix A,Vector B,Vector X){
        analysis(X,()->Algorithm.Gauss_Seidel(A.toDoubleArray(),B.toDoubleArray(),
                new double[n],
                10E-14,100,
                Algorithm.ConvergenceTestMethod.soutaizansa,
                NormType.Two));
    }
    public static void analysis(Vector X,Supplier<double[]> function){
        long start = System.nanoTime();
        double[] r = function.get();
        long end = System.nanoTime();
        System.out.println("Time:" + (end - start) / 1000000f + "ms");
        Vector R = new Vector(r);
        System.out.println("Norm2:" + X.sub(R).getNorm2());
    }
}
