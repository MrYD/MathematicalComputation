package com.company;

import java.util.function.BiConsumer;

public class Vector {
    public Complex[] field;

    public Vector(int i) {
        field = new Complex[i];
        for (int j = 0; j < field.length; j++) {
            field[j] = new Complex();
        }
    }

    public Vector(double[] array) {
        field = new Complex[array.length];
        for (int i = 0; i < array.length; i++) {
            field[i] = new Complex(array[i], 0);
        }
    }

    public Vector(Complex[] array) {
        field = new Complex[array.length];
        for (int i = 0; i < array.length; i++) {
            field[i] = array[i].clone();
        }
    }

    public int getLength() {
        return field.length;
    }

    public Complex[] toComplexArray() {
        return field.clone();
    }

    public double[] toDoubleArray() {
        double[] array = new double[field.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = field[i].real;
        }
        return array;
    }


    public Vector clone() {
        return new Vector(toComplexArray());
    }

    public Complex get(int i) {
        return field[i - 1];
    }

    public void set(int i, Complex value) {
        field[i - 1] = value;
    }

    public void set(int i, double value) {
        field[i - 1] = new Complex(value, 0);
    }

    public void foreach(BiConsumer<Integer, Complex> func) {
        for (int i = 1; i <= getLength(); i++) {
            func.accept(i, field[i - 1]);
        }
    }

    @Override
    public String toString() {
        String str = "{";
        for (int i = 0; i < field.length; i++) {
            if (i == field.length - 1) {
                str += field[i];
            } else {
                str += field[i] + ",";
            }
        }
        str += "}";
        return str;
    }

    public Vector add(Vector multi) {
        Complex[] res = new Complex[multi.getLength()];
        for (int i = 0; i < field.length; i++) {
            res[i] = field[i].add(multi.get(i + 1));
        }
        return new Vector(res);
    }

    public Vector sub(Vector multi) {
        Complex[] res = new Complex[multi.getLength()];
        for (int i = 0; i < field.length; i++) {
            res[i] = field[i].sub(multi.get(i + 1));
        }
        return new Vector(res);
    }

    public double getNorm(NormType norm) {
        switch (norm) {
            case One:
                return getNorm1();
            case Two:
                return getNorm2();
            case Inf:
                return getNormInf();
        }
        return getNorm2();
    }

    public double getNorm1() {
        double res = 0;
        for (int i = 0; i < field.length; i++) {
            res += field[i].magnitude();
        }
        return res;
    }

    public double getNorm2() {
        double res = 0;
        for (int i = 0; i < field.length; i++) {
            res += field[i].magnitude() * field[i].magnitude();
        }
        return Math.sqrt(res);
    }

    public double getNorm(int p) {
        double res = 0;
        for (int i = 0; i < field.length; i++) {
            res += Math.pow(field[i].magnitude(), p);
        }
        return Math.pow(res, 1.0 / p);
    }

    public double getNormInf() {
        double res = field[0].magnitude();
        for (int i = 0; i < field.length; i++) {
            if (field[i].magnitude() > res)
                res = field[i].magnitude();
        }
        return res;
    }

    public static Vector generate(int length , double range){
        Vector X = new Vector(new double[length]);
        X.foreach((i,c)->{
            X.set(i,(Math.random() - 0.5) * 2.0 * range);
        });
        return X;
    }
}

