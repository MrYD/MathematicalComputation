package com.company;
/**
 * Created by Main on 2015/11/09.
 */
public class Main1109{
    public static void main(String[] args){
        solve(5);
        System.out.println();
        solve(10);
        System.out.println();
        solve(15);
    }

    public static void solve(int n){
        Matrix hil = new HilbertMatrix(n);
        double k = hil.getNormInf() * hil.inverse().getNormInf();
        System.out.println("n = " + n);
        System.out.println("(1)");
        System.out.println(k);
        Vector b = hil.multi(getElementVector(n));
        Vector x1;
        System.out.println("(2)");
        try {
            x1 = Algorithm.GaussianEliminationWithPivotToVector(hil.toArray(), b.toArray());
            System.out.println(b.sub(hil.multi(x1)).getNormInf());
            System.out.println(getElementVector(n).sub(x1).getNormInf());
        } catch (Exception e) {
        }

        System.out.println("(3)");
        Vector delb = new Vector(b.getLength());
        delb.set(1, 0.001 * b.get(1));
        System.out.println(k * delb.getNormInf() / b.getNormInf());

        System.out.println("(4)");
        try {
            x1 = Algorithm.GaussianEliminationWithPivotToVector(hil.toArray(), b.add(delb).toArray());
            System.out.println(getElementVector(n).sub(x1).getNormInf());
        } catch (Exception e) {
        }
    }

    public static Vector getElementVector(int n) {
        double[] v = new double[n];
        for (int i = 0; i < v.length; i++) {
            v[i] = 1;
        }
        return new Vector(v);
    }
}
