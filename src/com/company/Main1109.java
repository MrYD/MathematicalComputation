package com.company;

/**
 * Created by Main on 2015/11/09.
 */
public class Main1109 {
    public static void main(String[] args) {

        int n = 15;
        Matrix hil = new HilbertMatrix(n);

        //System.out.println(hil);
        double k = hil.getNormInf() * hil.inverse().getNormInf();
        C.p(k);
        Vector b = hil.multi(getElementVector(n));
        Vector x1;
        try {
            x1 = GaussianElimination.algorithmWithPivotToVector(hil.toArray(), b.toArray());
            C.p(b.sub(hil.multi(x1)).getNormInf());
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
