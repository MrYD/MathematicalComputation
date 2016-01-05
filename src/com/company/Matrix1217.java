package com.company;
/**
 * Created by Main on 2015/12/14.
 */
public class Matrix1217 extends Matrix{

    public Matrix1217(int column, int row) {
        super(column, row);
        foreach((i, j, a) -> set(i,j,Math.sqrt(2*i+j)));
    }
}
