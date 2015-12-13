package com.company;
/**
 * Created by Main on 2015/11/16.
 */
public class Main1116 {
    public static void main(String[] args) {
        Matrix A = new Matrix(new double[][]{{6, 1, 1, 1, 0},
                {1, 7, 1, 1, 1},
                {0, 1, 8, 1, 1},
                {0, 0, 1, 9, 1},
                {0, 0, 0, 1, 10}});
        Vector B = new Vector(new double[]{9, 11, 11, 11, 11});
        double[] resj = Algorithm.jacobi(A.toDoubleArray(), B.toDoubleArray(), new double[B.field.length], 10E-16, 100, Algorithm.ConvergenceTestMethod.gosa, NormType.Two);
        double[] resg = Algorithm.gaussianElimination(A.toDoubleArray(),B.toDoubleArray());
        System.out.println(new Vector(resj));
        System.out.println(new Vector(resg));
        System.out.println(A.multi(new Vector(resj)));
    }
}
