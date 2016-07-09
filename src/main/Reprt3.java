package main;
import com.company.*;
/**
 * Created by Main on 2016/06/28.
 */
public class Reprt3 {
    public static void main(String[] Args){

    }
    public static void cg_method(double[][] a, double[] x, double[] b) {
        int n = b.length;
        double[] p = new double[n];
        double[] r = new double[n];
        int i, loop;
        double[] ax = vector_x_matrix(a, x);
        for(i = 0; i < n; i++) {
            p[i] = b[i] - ax[i];
            r[i] = p[i];
        }
        for(loop = 1; loop< 1000; loop++){
            double alpha, beta;
            double[] ap = vector_x_matrix(a, p);
            alpha = dot_product(p, r)/dot_product(p, ap);
            for(i = 0; i < n; i++){
                x[i] += alpha*p[i];
                r[i] -= alpha*ap[i];
            }
            double e = vector_norm2(r) / vector_norm2(b);
            System.out.printf("反復回数 : %d\t 相対誤差 : %g\n", loop, e);
            if(1.0e-10 > e) break;
            beta= -dot_product(r, ap)/dot_product(p, ap);
            for(i = 0; i < n; i++){
                p[i] = r[i] + beta*p[i];
            }
        }
    }

    static double[] vector_x_matrix(double[][] a, double[] x) {
        int i, j;
        double vxm;
        double[] y = new double[x.length];
        for(i = 0; i < x.length; i++){
            vxm = 0;
            for(j = 0; j < x.length; j++){
                vxm += a[i][j]*x[j];
            }
            y[i] = vxm;
        }
        return y;
    }

    static double dot_product(double[] x, double[] y) {
        int i;
        double dot_p = 0;
        for(i = 0; i < x.length; i++){
            dot_p += x[i]*y[i];
        }
        return dot_p;
    }

    static double vector_norm2(double[] x) {
        double res = 0;
        for ( int i = 0; i < x.length; i++) {
            res+= x[i] * x[i];
        }
        return Math.sqrt(res);
    }
}
