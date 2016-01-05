package com.company;

import com.sun.org.apache.xpath.internal.functions.FunctionMultiArgs;
import javafx.concurrent.Task;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by Main on 15/09/16.
 */

public class Matrix {
    public Complex[][] field;
    public Matrix(int column, int row) {
        field = new Complex[column][];
        for (int i = 0; i < field.length; i++) {
            field[i] = new Complex[row];
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = new Complex();
            }
        }
    }

    public Matrix(double[][] field) {
        this.field = new Complex[field.length][];
        for (int i = 0; i < field.length; i++) {
            this.field[i] = new Complex[field[i].length];
            for (int j = 0; j < field[i].length; j++) {
                this.field[i][j] = new Complex(field[i][j],0);
            }
        }
    }
    public Matrix(Complex[][] field) {
        this.field = new Complex[field.length][];
        for (int i = 0; i < field.length; i++) {
            this.field[i] = new Complex[field[i].length];
            for (int j = 0; j < field[i].length; j++) {
                this.field[i][j] = field[i][j].clone();
            }
        }
    }

    public int getColumnLength() {

        return field.length;
    }


    public int getRowLength() {

        return field[0].length;
    }

    public Complex[][] toComplexArray() {
        Complex[][] array = new Complex[getColumnLength()][getRowLength()];
        foreach((i, j, num) -> {
            array[i - 1][j - 1] = num;
        });
        return array;
    }

    public double[][] toDoubleArray(){
        double[][] array = new double[getColumnLength()][getRowLength()];
        foreach((i, j, num) -> {
            array[i - 1][j - 1] = num.real;
        });
        return array;
    }

    public Matrix getPartial(int i, int j, int columnlength, int rowlength) {
        Matrix mtx = new Matrix(columnlength, rowlength);
        mtx.foreach((mi, mj, num) -> {
            mtx.set(mi, mj, this.get(i + mi - 1, j + mj - 1));
        });
        return mtx;
    }

    public Matrix transpose() {
        Matrix mtx = new Matrix(getRowLength(), getColumnLength());
        mtx.foreach((i, j, num) -> {
            mtx.set(i, j, this.get(j, i));
        });
        return mtx;
    }

    public Vector getRow(int num) {
        return new Vector(field[num - 1]);
    }

    public Vector getColumn(int num) {
        Vector v = new Vector(getColumnLength());
        for (int i = 1; i <= getColumnLength(); i++) {
            v.set(i, get(i, num));
        }
        return v;
    }

    public Complex get(int column, int row) {

        return field[column - 1][row - 1];
    }

    public void set(int column, int row, Complex num) {

        field[column - 1][row - 1] = num;
    }
    public void set(int column, int row, double num) {

        field[column - 1][row - 1] = new Complex(num,0);
    }

    @Override
    public Matrix clone() {
        Matrix matrix = new Matrix(getColumnLength(), getRowLength());
        this.foreach(matrix::set);
        return matrix;
    }

    public void foreach(TriConsumer<Integer, Integer, Complex> func) {
        for (int i = 1; i <= getColumnLength(); i++) {
            for (int j = 1; j <= getRowLength(); j++) {
                func.apply(i, j, get(i, j));
            }
        }
    }

    public Matrix add(Matrix matrix) {
        Matrix mat = new Matrix(getColumnLength(), getRowLength());
        mat.foreach((i, j, num) -> {
            mat.set(i, j, get(i, j).add(num));
        });
        return mat;
    }

    public Matrix sub(Matrix matrix) {
        Matrix mat = new Matrix(getColumnLength(), getRowLength());
        mat.foreach((i, j, num) -> {
            mat.set(i, j, get(i, j).sub(num));
        });
        return mat;
    }

    public Matrix multi(double x) {
        Matrix mat = new Matrix(getColumnLength(), getRowLength());
        mat.foreach((i, j, num) -> {
            mat.set(i, j, get(i, j).multiply(x));
        });
        return mat;
    }

    public Matrix multi(Matrix matrix) {
        Matrix mat = new Matrix(matrix.getColumnLength(), getRowLength());
        mat.foreach((i, j, num) -> {
            Complex x = new Complex();
            for (int k = 1; k <= getRowLength(); k++) {
                x = x.add(this.get(i, k).multiply(matrix.get(k, j)));
            }
            mat.set(i, j, x);
        });
        return mat;
    }

    public Matrix swapColumn(int column1, int column2) {
        Matrix res = clone();
        res.field[column1] = field[column2].clone();
        res.field[column2] = field[column1].clone();
        return res;
    }

    public Matrix swapRow(int row1, int row2) {
        Matrix res = clone();
        for (int i = 0; i < getRowLength(); i++) {
            res.field[i][row1] = field[i][row2];
            res.field[i][row2] = field[i][row1];
        }
        return res;
    }

    @Override
    public String toString() {
        String str = "{\n";
        for (int i = 1; i <= getColumnLength(); i++) {
            str += "\t{";
            for (int j = 1; j <= getRowLength(); j++) {
                if (j != getRowLength()) str += get(i, j) + ", ";
                else str += get(i, j);
            }
            if (i != getColumnLength()) str += "},\n";
            else str += "}\n";
        }
        str += "}";
        return str;
    }

    public Matrix inverse(){
        return new Matrix(Algorithm.inverse_matrix(toDoubleArray()));
    }

    public double getNorm1() {
        return transpose().getNormInf();
    }

    public double getNormInf() {
        double max = 0;
        double norm = 0;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                norm += field[i][j].magnitude();
            }
            if (norm > max) max = norm;
            norm = 0;
        }
        return max;
    }

    public Vector multi(Vector vct) {
        Complex[] res = new Complex[getColumnLength()];
        for (int i = 0; i < getColumnLength(); i++) {
            res[i] = new Complex();
            for (int k = 0; k < getRowLength(); k++) {
                res[i] = res[i].add(field[i][k].multiply(vct.field[k]));
            }
        }
        return new Vector(res);
    }
    public static Matrix generate(int columnlength, int rowlength, double range){
        Matrix matrix = new Matrix(new double[columnlength][rowlength]);
        matrix.foreach((i,j,c)->{
            matrix.set(i,j,(Math.random() - 0.5) * 2.0 * range);
        });
        return matrix;
    }
}
