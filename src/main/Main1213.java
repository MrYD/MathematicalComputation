package main;

import com.company.*;

/**
 * Created by Main on 2015/12/13.
 */
public class Main1213 {
    public static void main(String[] args) {
        Matrix A = new Matrix(new Complex[][]{
                {new Complex(2, 0), new Complex(3, -4)},
                {new Complex(4, 1), new Complex(0, -3)}
        });
        System.out.println(A.getNorm1());
        System.out.println(A.getNormInf());
    }
}
