package com.company;


public class Complex {

    public void main() {

    }

    public double real;
    public double imaginary;

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public Complex() {
        this.real = 0;
        this.imaginary = 0;
    }

    public Complex add(Complex x) {
        return new Complex(x.real + this.real, x.imaginary + this.imaginary);
    }

    public Complex sub(Complex x) {
        return new Complex(-x.real + this.real, -x.imaginary + this.imaginary);
    }

    public Complex multiply(double x) {
        return new Complex(x * this.real, x * this.imaginary);
    }

    public Complex divide(double x) {
        return new Complex(this.real / x, this.imaginary / x);
    }

    public double magnitude() {
        return Math.sqrt(this.real * this.real + this.imaginary * this.imaginary);
    }

    public String toString() {
        return "(" + real + "," + imaginary + ")";
    }
}

