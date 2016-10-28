package main;
import sun.jvm.hotspot.memory.Dictionary;

import javax.swing.event.RowSorterEvent;
import java.security.KeyPair;

import static com.company.Linear.*;
/**
 * Created by Main on 2016/08/04.
 */
class Report4 {

    public static void main(String[] args){
        double[][] a = {
                {0,2,-2,1},
                {-3,5,1,-2},
                {-2,1,0,2},
                {1,-2,-3,5}
        };
        PowerIterationResult[] arr = new PowerIterationResult[1];
        for (int i = 0; i < 1; i++) {
            //double[] x = generate_vector(4,10);
            double[] x = {3,-5,3,-5};
            //x[0] += Math.random();
            arr[i] = powerIteration(a,x,1.0E-10,100);
        }
        BubbleSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i].roop + " ");
            print(arr[i].vector0,2);
            System.out.println(arr[0].rammda);
        }
    }

    public static double[] generate_vector(int i,double x){
        double[] y = new double[i];
        for (int j = 0; j < i; j++) {
            y[j] = Math.random() * 2 * x - x;
        }
        return y;
    }

    public static PowerIterationResult powerIteration(double[][] A,double[] x,double eps,int max){
        double[] y = null;
        double ramda = 0;
        double ramda1 = 0;
        PowerIterationResult res = new PowerIterationResult(x.clone());
        for (int i = 0; i < max; i++) {
            y = matrix_x_vector(A,x);
            ramda = dot_product(y,y)/dot_product(y,x);
            if(Math.abs(ramda - ramda1) <= eps){
                return res.setResult(i+1,ramda);
            }
            ramda1 = ramda;
            double t = vector_norm2(y);
            for (int j = 0; j < x.length; j++) {
                x[j] = y[j]/t;
            }
        }
        return res;
    }

    public static void BubbleSort(PowerIterationResult[] array){
        for (int j = 0; j < array.length - 1; j++) {
            for (int i = array.length - 1; i > j; i--) {
                if(array[i].roop < array[i - 1].roop){
                    PowerIterationResult a = array[i];
                    array[i] = array[i - 1];
                    array[i - 1] = a;
                }
            }
        }
    }


    public static void Householder(double[][] A) {
        int n = A.length;
        for (int i = 0; i < n - 2; i++) {
            double[] u = new double[n];
            for (int j = i + 1; j < n; j++) {
                u[j] = A[j][i];
            }
            double s;
            if (A[i + 1][i] >= 0) {
                s = -vector_norm2(u);
            } else {
                s = vector_norm2(u);
            }
            u[i] -= s;
            double unorm = vector_norm2(u);
            for (int j = 0; j < n; j++) {
                u[j] /= unorm;
            }
            double[] f = matrix_x_vector(A, u);
            double[] g = vector_x_matrix(u, A);
            double gamma = dot_product(g, u);
            for (int j = 0; j < n; j++) {
                f[j] -= gamma * u[j];
            }
            for (int j = 0; j < n; j++) {
                g[j] -= gamma * u[j];
            }
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    A[j][k] -= 2 * u[j] * g[k] + 2 * f[j] * u[k];
                }
            }
        }
    }
}

class PowerIterationResult{
    public PowerIterationResult(double[] vector0){
        this.vector0 = vector0;
        this.roop = -99999;
        this.rammda = -99999;
        this.status =false;
    }

    public PowerIterationResult setResult(int roop,double rammda){
        this.roop = roop;
        this.rammda = rammda;
        this.status =true;
        return this;
    }

    public int roop;
    public double rammda;
    public boolean status;
    public double[] vector0;
}
