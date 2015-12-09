package com.company;

import static com.company.C.*;

/**
 * Created by Main on 2015/11/16.
 */
public class Main1116 {
    public static void main(String[] args) {
        double[][] a =
                {{6, 1, 1, 1, 0},
                        {1, 7, 1, 1, 1},
                        {0, 1, 8, 1, 1},
                        {0, 0, 1, 9, 1},
                        {0, 0, 0, 1, 10}};
        double[] b = {9, 11, 11, 11, 11};
        double[] res = Algorithm.jacobi(a, b, new double[b.length], 10E-16, 100, Algorithm.ConvergenceTestMethod.gosa, NormType.Two);
        p(new Vector(res));
    }
}
