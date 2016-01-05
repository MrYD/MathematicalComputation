package main;

import com.company.*;

/**
 * Created by Main on 2015/12/13.
 */
public class Main1213 {
    public static void main(String[] args) throws Exception {
        //Matrix A = new Matrix1217(100,100);
        //System.out.println(A);
        //System.out.println(A.getNorm1());
        //System.out.println(A.getNormInf());

//        Vector x = new Vector(100);
//        x.foreach((i,c)-> x.set(i,i));
//        System.out.println(x);
//        System.out.println(x.getNorm2());
//        System.out.println(x.getNorm(2));
//        System.out.println(x.getNorm(3));
//        Matrix A = new Matrix(new double[][]{
//                {-1.1,2.2,3.3},
//                {4.4,-5.5,6.6},
//                {7.7,8.8,-9.9}
//        });
//        Matrix Ainv = A.inverse();
//        System.out.println(Ainv);
//
//        double sum = 0;
//        for (int i = 1; i <= 3; i++) {
//            sum += Ainv.get(i,i).real;
//        }
//        System.out.println(sum);
//        Matrix A = new HilbertMatrix(7);
//        //System.out.println(A);
//        double k = A.getNorm1() * A.inverse().getNorm1();
//        System.out.println(k);
        Vector realx = new Vector(new double[]{
                1, 1, 1
        });
//     Vector b = A.inverse().multi(realx);
//        Vector x = Algorithm.gaussianEliminationWithPivotToVector(A.toDoubleArray(),b.toDoubleArray());
//        System.out.println(A.multi(x));
//        System.out.println(x);
//        System.out.println(realx.sub(x).getNorm1()/realx.getNorm1());
//        Vector db = new Vector(new double[] {
//            b.get(1).real*0.001,0,0,0,0,0,0
//        });
//        Vector x2 = Algorithm.gaussianEliminationWithPivotToVector(A.toDoubleArray(),b.add(db).toDoubleArray());
//        System.out.println(x2);
//        System.out.println(realx.sub(x2).getNorm1()/realx.getNorm1());
//        System.out.println(A.multi(x));
//        System.out.println(A.multi(x2));
        Matrix A = new Matrix(new double[][]{
                {3,1,1},
                {-1,3,1},
                {1,1,3}
        });

    }
}
// Gauss-Seidel 法における反復行列 T は,
// T = −(D + E)^−1F
// スペクトル半径 ρ(T) > 1 ならばGauss-Seidel 法は収束しない
// スペクトル半径と最大のは固有値の絶対値

// 残差と誤差の関係
// 残差 r 誤差 e
// ||e||/||x|| <= k(A)||r||/||A||||x||
//