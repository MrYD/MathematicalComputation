package com.company;

/**
 * Created by Main on 2015/11/09.
 */
public class HilbertMatrix extends Matrix {
    public HilbertMatrix(int n) {
        super(n, n);
        foreach((i, j, a) -> set(i, j, 1.0 / (i + j - 1.0)));
    }
}