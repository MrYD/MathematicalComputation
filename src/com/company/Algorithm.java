package com.company;

import java.util.function.Consumer;

/**
 * Created by Main on 15/09/28.
 */
public class Algorithm {

    public static Matrix GaussianEliminationToMatrix(Matrix a) {
        return GaussianEliminationToMatrix(a.getPartial(1, 1, a.getColumnLength(), a.getColumnLength()).toArray(), a.getColumn(a.getRowLength()).toArray());
    }

    public static Vector GaussianEliminationToVector(Matrix a) {
        return GaussianEliminationToVector(a.getPartial(1, 1, a.getColumnLength(), a.getColumnLength()).toArray(), a.getColumn(a.getRowLength()).toArray());
    }

    public static Matrix GaussianEliminationToMatrix(double[][] a, double[] b) {
        ForwardSubstitution(a, b);
        BackwardElimination(a, b);
        return toMatrix(a, b);
    }

    public static Vector GaussianEliminationToVector(double[][] a, double[] b) {
        ForwardSubstitution(a, b);
        BackwardElimination(a, b);
        return new Vector(b);
    }

    public static Vector GaussianEliminationWithPivotToVector(double[][] a, double[] b)
            throws Exception {
        ForwardSubstitutionWithPivot(a, b);
        BackwardElimination(a, b);
        return new Vector(b);
    }

    public static Matrix toMatrix(double[][] a, double[] b) {
        int n = b.length;
        Matrix res = new Matrix(n, n + 1);
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                res.set(i + 1, j + 1, a[i][j]);
            }
        }
        for (int i = 0; i < n; i++) {
            res.set(i + 1, n + 1, b[i]);
        }
        return res;
    }

    public static void ForwardSubstitution(double[][] a, double[] b) {
        double alfa;
        int n = b.length;
        for (int k = 0; k < n - 1; k++) {
            for (int i = k + 1; i < n; i++) {
                alfa = a[i][k] / a[k][k];
                for (int j = k + 1; j < n; j++) {
                    a[i][j] -= a[k][j] * alfa;
                }
                b[i] -= alfa * b[k];
            }
        }
    }

    public static void ForwardSubstitutionWithPivot(double[][] a, double[] b)
            throws Exception {
        double alfa;
        int n = b.length;
        int l;
        double eps = 10e-18;
        double[] c = new double[a.length + 1];
        for (int k = 0; k < n - 1; k++) {
            l = k;
            for (int i = k + 1; i < n; i++) {
                if (Math.abs(a[i][k]) > Math.abs(a[l][k])) l = i;
            }
            if (Math.abs(a[l][k]) < eps) {
                throw new Exception();
            }
            if (l != k) {
                for (int i = 0; i < a.length; i++) {
                    double temp = a[l][i];
                    a[l][i] = a[k][i];
                    a[k][i] = temp;
                }
                double tmp = b[k];
                b[k] = b[l];
                b[l] = tmp;
            }
            for (int i = k + 1; i < n; i++) {
                alfa = a[i][k] / a[k][k];
                for (int j = k + 1; j < n; j++) {
                    a[i][j] -= a[k][j] * alfa;
                }
                b[i] -= alfa * b[k];
            }
        }
    }

    public static void BackwardElimination(double[][] a, double[] b) {
        int n = b.length;
        for (int k = n - 1; k >= 0; k--) {
            for (int j = k + 1; j < n; j++) {
                b[k] -= a[k][j] * b[j];
            }
            b[k] /= a[k][k];
        }
    }


    public static LUResult lu_decomposition(double[][] a) {
        int n = a.length;
        int t, i, j;
        for (t = 0; t < n - 1; t++) {
            for (i = t + 1; i < n; i++) {
                a[i][t] /= a[t][t];
                for (j = t + 1; j < n; j++)
                    a[i][j] -= a[i][t] * a[t][j];
            }
        }
        LUResult res = new LUResult(n);
        for (int k = 0; k < n; k++) {
            for (int l = 0; l < n; l++) {
                if (k < l) {
                    res.U[k][l] = a[k][l];
                    res.L[k][l] = 0;
                }
                if (k == l) {
                    res.U[k][l] = a[k][l];
                    res.L[k][l] = 1;
                }
                if (k > l) {
                    res.U[k][l] = 0;
                    res.L[k][l] = a[k][l];
                }
            }
        }
        return res;
    }

    public static double[][] cholesky_decomposition(double[][] a) {
        int n = a.length;
        double[][] l = new double[n][n];
        double s;
        for (int j = 0; j < n; j++) {
            for (int i = j; i < n; i++) {
                s = 0;
                for (int k = 0; k < j; k++) {
                    s = s + l[i][k] * l[j][k];
                }
                if (i == j) {
                    l[i][i] = Math.sqrt(a[i][i] - s);
                } else {
                    l[i][j] = (a[i][j] - s) / l[j][j];
                }
                Matrix m = new Matrix(l);
                System.out.print(m);
            }
        }
        return l;
    }

    public static double[][] inverse_matrix(double[][] mtx) {
        int n = mtx.length;
        double[][] e = new double[n][n];
        for (int i = 0; i < n; i++) {
            e[i][i] = 1;
        }
        double[][] y = new double[n][n];
        double[][] x = new double[n][n];
        LUResult res = lu_decomposition(mtx);
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                y[k][i] = e[k][i];
                for (int j = 0; j < k; j++) {
                    y[k][i] -= mtx[k][j] * y[j][i];
                }
            }
            for (int k = n - 1; k >= 0; k--) {
                x[k][i] = y[k][i];
                for (int j = k + 1; j < n; j++) {
                    x[k][i] -= mtx[k][j] * x[j][i];
                }
                x[k][i] /= mtx[k][k];
            }
        }
        return x;
    }

    enum ConvergenceTestMethod {
        gosa,
        zansa,
        soutaigosa,
        soutaizansa,
    }

    private static boolean ConvergenceTest(
            double[][] a, double[] b, double[] x1, double[] x0, double eps, ConvergenceTestMethod method, NormType type) {
        switch (method) {
            case gosa:
                if (new Vector(x1).sub(new Vector(x0)).getNorm(type) < eps)
                    return true;
                break;
            case zansa:
                if (new Vector(b).sub(new Matrix(a).multi(new Vector(x1))).getNorm(type) < eps)
                    return true;
                break;
            case soutaigosa:
                if (new Vector(x1).sub(new Vector(x0)).getNorm(type) / new Vector(x1).getNorm(type) < eps)
                    return true;
                break;
            case soutaizansa:
                if (new Vector(b).sub(new Matrix(a).multi(new Vector(x1))).getNorm(type) / new Vector(b).getNorm(type) < eps)
                    return true;
                break;
        }
        return false;
    }

    public static double[] jacobi(
            double[][] a, double[] b, double[] x, double eps, int max, ConvergenceTestMethod method, NormType type) {
        double[] lastx = new double[b.length];
        for (int m = 0; m < max; m++) {
            for (int i = 0; i < x.length; i++) {
                double ax = 0;
                for (int j = 0; j < x.length; j++) {
                    lastx[j] = x[j];
                }
                for (int j = 0; j < x.length; j++) {
                    if (i != j)
                        ax += a[i][j] * lastx[i];
                }
                x[i] = 1 / a[i][i] * (b[i] - ax);
            }
            if (ConvergenceTest(a, b, x, lastx, eps, method, type)) {
                return x;
            }
        }
        return new double[b.length];
    }


}

class LUResult {
    LUResult(int n) {
        L = new double[n][n];
        U = new double[n][n];
    }

    public double[][] L;
    public double[][] U;
}
