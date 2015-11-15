package com.company;

/**
 * Created by Main on 2015/11/09.
 */
public class Main1109 extends C {
    public static void main(String[] args){
        solve(5);
        p();
        solve(10);
        p();
        solve(15);
    }

    public static void solve(int n){
        Matrix hil = new HilbertMatrix(n);
        double k = hil.getNormInf() * hil.inverse().getNormInf();

        p("n = " + n);
        p("(1)");
        p(k);
        Vector b = hil.multi(getElementVector(n));
        Vector x1;

        p("(2)");
        try {
            x1 = GaussianElimination.algorithmWithPivotToVector(hil.toArray(), b.toArray());
            C.p(b.sub(hil.multi(x1)).getNormInf());
            C.p(getElementVector(n).sub(x1).getNormInf());
        } catch (Exception e) {
        }

        p("(3)");
        Vector delb = new Vector(b.getLength());
        delb.set(1, 0.001 * b.get(1));
        p(k * delb.getNormInf() / b.getNormInf());

        p("(4)");
        try {
            x1 = GaussianElimination.algorithmWithPivotToVector(hil.toArray(), b.add(delb).toArray());
            p(getElementVector(n).sub(x1).getNormInf());
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
