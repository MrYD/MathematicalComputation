package com.company;

/**
 * Created by Main on 2015/12/08.
 */
public class Main1208 {
    public static void main(String[] args) {
        Matrix a = new Matrix(new double[][]{
                {0, -1, 2},
                {-4, 5, 6},
                {8, 9, 10}
        });
        Vector b = new Vector(new double[]{
                3, 7, 11
        });
        System.out.println(
                new Vector(Algorithm.jacobi(a.field, b.field, new double[]{0, 0, 0}, 10E-8, 50, Algorithm.ConvergenceTestMethod.soutaigosa, NormType.Two)));
    }
}
