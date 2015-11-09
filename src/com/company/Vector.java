package com.company;

import java.util.function.BiConsumer;

/**
 * Created by Main on 15/09/30.
 */
public class Vector {
    public double[] field;

    public Vector(int i) {
        field = new double[i];
    }

    public Vector(double[] array) {
        field = array.clone();
    }

    public int getLength() {
        return field.length;
    }

    public double[] toArray() {
        return field.clone();
    }

    public Vector clone() {
        return new Vector(toArray());
    }

    public double get(int i) {
        return field[i - 1];
    }

    public void set(int i, double value) {
        field[i - 1] = value;
    }

    public void foreach(BiConsumer<Integer, Double> func) {
        for (int i = 1; i <= getLength(); i++) {
            func.accept(i, field[i]);
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
        double[] res = new double[multi.getLength()];
        for (int i = 0; i < field.length; i++) {
            res[i] = field[i] + multi.get(i + 1);
        }
        return new Vector(res);
    }

    public Vector sub(Vector multi) {
        double[] res = new double[multi.getLength()];
        for (int i = 0; i < field.length; i++) {
            res[i] = field[i] - multi.get(i + 1);
        }
        return new Vector(res);
    }

    public double getNorm2() {
        double res = 0;
        for (int i = 0; i < field.length; i++) {
            res += field[i] * field[i];
        }
        return Math.sqrt(res);
    }

    public double getNormInf() {
        double res = 0;
        for (int i = 0; i < field.length; i++) {
            if (field[i] > res) res = field[i];
        }
        return res;
    }
}
