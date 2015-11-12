package com.company;

/**
 * Created by Main on 2015/11/09.
 */
public class Main1109 {
    public static void main(String[] args){
        solve(5);
        C.p();
        solve(10);
        C.p();
        solve(15);
    }

    public static void solve(int n){
        Matrix hil = new HilbertMatrix(n);
        double k = hil.getNormInf() * hil.inverse().getNormInf();

        C.p("n = " + n);
        C.p("(1)");
        C.p(k);
        Vector b = hil.multi(getElementVector(n));
        Vector x1;

        C.p("(2)");
        try {
            x1 = GaussianElimination.algorithmWithPivotToVector(hil.toArray(), b.toArray());
            C.p(b.sub(hil.multi(x1)).getNormInf());
            C.p(getElementVector(n).sub(x1).getNormInf());
        } catch (Exception e) {
        }

        C.p("(3)");
        Vector delb = new Vector(b.getLength());
        delb.set(1, 0.001 * b.get(1));
        C.p(k * delb.getNormInf() / b.getNormInf());

        C.p("(4)");
        try {
            x1 = GaussianElimination.algorithmWithPivotToVector(hil.toArray(), b.add(delb).toArray());
            C.p(getElementVector(n).sub(x1).getNormInf());
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
