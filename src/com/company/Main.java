package com.company;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        testInverse();
    }

    public static void testInverse() {
        double[][] a = {
                {1, 1, 1, 2},
                {1, 5, 5, 33},
                {1, 5, 14, 0},
                {5, 3, 1, 0}
        };
        Matrix A = new Matrix(a);
        C.p(A.multi(A.inverse()));
        C.p(A.inverse().multi(A));
    }


    public static void testCholesky() {

        double[][] a = {
                {1, 1, 1},
                {1, 5, 5},
                {1, 5, 14}
        };
        double[][] l = GaussianElimination.cholesky_decomposition(a);
        Matrix L = new Matrix(l);
        System.out.println(L);
    }

    public static void testLU() {

        double[][] a = {
                {1, 1, 1},
                {1, 5, 5},
                {1, 5, 14}
        };
        LUResult res = GaussianElimination.lu_decomposition(a);
        Matrix L = new Matrix(res.L);
        Matrix U = new Matrix(res.U);
        System.out.print(L.multi(U));
    }

    public static void pivot() {
        int n = 500;
        int iter = 100;
        double[] res = new double[iter];
        double A[][] = new double[n][n];
        for (int i = 0; i < iter; i++) {
            double b[] = new double[n];
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    A[j][k] = Math.random();
                }
                b[j] = Math.random();
            }
            Matrix Amtx = new Matrix(A);
            Vector bvct = new Vector(b);
            Vector xvct;
            try {
                //xvct = GaussianElimination.algorithmToVector(A, b);
                xvct = GaussianElimination.algorithmWithPivotToVector(A, b);
            } catch (Exception e) {
                xvct = new Vector(n);
            }
            System.out.println(bvct.sub(Amtx.multi(xvct)).getNorm2());
        }
    }
}
