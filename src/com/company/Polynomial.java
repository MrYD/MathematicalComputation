package com.company;

/**
 * Created by Main on 2016/10/10.
 */
public class Polynomial {
    public double[] vector;

    public int getDimenion(){
        for (int i = vector.length - 1; i > 0 ; i--) {
            if(vector[i] != 0)
                return i + 1;
        }
        return 1;
    }
    public Polynomial(int max){
        vector = new double[max];
    }

    public double y(double x){
        double res = 0;
        for (int i = vector.length - 1; i >= 0; i--) {
            res += vector[i] * Math.pow(x,i);
        }
        return res;
    }

    public Polynomial(double[] vec){
        vector = vec;
    }

    public void Update(int max){
        if(vector.length < max) {
            double[] nvec = new double[max];
            for (int i = 0; i < vector.length; i++) {
                nvec[i] = vector[i];
                vector = nvec;
            }
        }
    }

    public Polynomial Add(Polynomial b){
        int d = b.getDimenion();
        Polynomial res = new Polynomial(Math.max(getDimenion(),d));
        for (int i = 0; i < d; i++) {
            if(i < vector.length)
                res.vector[i] = vector[i] + b.vector[i];
            else
                res.vector[i] = b.vector[i];
        }
        return res;
    }

    public Polynomial Add(double b){
        int d = getDimenion();
        Polynomial res = new Polynomial(d);
        for (int i = 0; i < d; i++) {
            res.vector[i] = vector[i];
        }
        res.vector[0] += b;
        return res;
    }

    public Polynomial Sub(Polynomial b){
        int d = b.getDimenion();
        Polynomial res = new Polynomial(Math.max(getDimenion(),d));
        for (int i = 0; i < d; i++) {
            if(i < vector.length)
                res.vector[i] = vector[i] - b.vector[i];
            else
                res.vector[i] = -b.vector[i];
        }
        return res;
    }

    public Polynomial Sub(double b){
        int d = getDimenion();
        Polynomial res = new Polynomial(getDimenion());
        for (int i = 0; i < d; i++) {
            res.vector[i] = vector[i];
        }
        res.vector[0] -= b;
        return res;
    }

    public Polynomial Multi(double b){
        int d = getDimenion();
        Polynomial res = new Polynomial(d);
        for (int i = 0; i < d; i++) {
            res.vector[i] = vector[i] * b;
        }
        return res;
    }

    public Polynomial Multi(Polynomial b){
        int bd =  b.getDimenion();
        int d = getDimenion();
        Polynomial res = new Polynomial(bd + d);

        for (int i = 0; i < bd; i++) {
            for (int j = 0; j < d; j++) {
                res.vector[i + j] += b.vector[i] * vector[j];
            }
        }
        return res;
    }

    @Override
    public String toString() {
        String res = "";
        for (int i = vector.length - 1; i > 0 ; i--) {
            if(vector[i] > 0){
                if(vector[i] == 1)
                    res += " +x^"+i;
                else
                    res += " +"+vector[i]+"x^"+i;
            }

            if(vector[i] < 0){
                if(vector[i] == -1)
                    res += " -x^"+i;
                else
                    res += " "+vector[i]+"x^"+i;
            }
        }
        if(vector[0] > 0)
            res += " +"+vector[0];
        if(vector[0] < 0)
            res += " "+vector[0];
        if(res.charAt(1) == '+'){
            res = res.substring(2);
        }
        else {
            res = res.substring(1);
        }
        return res;
    }
}
