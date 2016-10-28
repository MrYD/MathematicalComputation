package com.company;

/**
 * Created by Main on 2016/08/06.
 */
public class Linear {
    public static double[][] matrix_x_matrix(double[][] a, double[][] b) {
        double[][] mat = new double[a.length][b[0].length];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                for (int k = 0; k < a[0].length; k++) {
                    mat[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return mat;
    }

    public static double[] matrix_x_vector(double[][] a, double[] x) {
        int i, j;
        double vxm;
        double[] y = new double[x.length];
        for(i = 0; i < a.length; i++){
            vxm = 0;
            for(j = 0; j < a[0].length; j++){
                vxm += a[i][j]*x[j];
            }
            y[i] = vxm;
        }
        return y;
    }

    public static double[] vector_x_matrix(double[] x, double[][] a) {
        int i, j;
        double mxv;
        double[] y = new double[x.length];
        for(i = 0; i < a[0].length; i++){
            mxv = 0;
            for(j = 0; j < a.length; j++){
                mxv += a[j][i]*x[j];
            }
            y[i] = mxv;
        }
        return y;
    }

    public static double dot_product(double[] x, double[] y) {
        int i;
        double dot_p = 0;
        for(i = 0; i < x.length; i++){
            dot_p += x[i]*y[i];
        }
        return dot_p;
    }

    public static double vector_norm2(double[] x) {
        double res = 0;
        for (double i : x) {
            res += i * i;
        }
        return Math.sqrt(res);
    }

    public static double[] vector_x_value(double[] x,double a){
        double[] y = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            y[i] = x[i] * a;
        }
        return y;
    }

    public static void x(double[] x,double a){
        for (int i = 0; i < x.length; i++) {
            x[i] = x[i] * a;
        }
    }
    public static void div(double[] x,double a){
        for (int i = 0; i < x.length; i++) {
            x[i] = x[i] / a;
        }
    }


    public static void print(double[] x){
        System.out.print("{");
        for (int i = 0; i < x.length; i++) {
            System.out.print(x[i]);
            if(i != x.length - 1)
                System.out.print(", ");
        }
        System.out.println("}");
    }
    public static void print(double[] x,int d){
        System.out.print("{");
        for (int i = 0; i < x.length; i++) {
            System.out.printf("%."+d+"f",x[i]);
            if(i != x.length - 1)
                System.out.print(", ");
        }
        System.out.println("}");
    }
}
