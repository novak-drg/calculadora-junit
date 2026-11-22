package br.edu.pucgoias.calculator;

/**
 * Calculator with the four basic arithmetic operations.
 */
public class Calculadora {

    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    /**
     * @throws ArithmeticException if the divisor is zero
     */
    public double divide(double dividend, double divisor) {
        if (divisor == 0) {
            throw new ArithmeticException("Divisão por zero não é permitida");
        }
        return dividend / divisor;
    }
}
