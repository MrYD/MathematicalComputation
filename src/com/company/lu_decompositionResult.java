package com.company;

public class lu_decompositionResult {
    lu_decompositionResult(int n) {
        L = new double[n][n];
        U = new double[n][n];
    }

    public double[][] L;
    public double[][] U;
}
