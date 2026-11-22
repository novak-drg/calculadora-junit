package br.edu.pucgoias.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Calculadora - testes unitários")
class CalculadoraTest {

    private static final double DELTA = 1e-9;

    private Calculadora calculator;

    @BeforeEach
    void createCalculator() {
        calculator = new Calculadora();
    }

    @Nested
    @DisplayName("Somar")
    class Add {

        @Test
        @DisplayName("soma dois números positivos")
        void addsTwoPositives() {
            assertEquals(5, calculator.add(2, 3), DELTA);
        }

        @Test
        @DisplayName("soma dois números negativos")
        void addsTwoNegatives() {
            assertEquals(-5, calculator.add(-2, -3), DELTA);
        }

        @Test
        @DisplayName("soma um positivo com um negativo")
        void addsPositiveAndNegative() {
            assertEquals(-1, calculator.add(2, -3), DELTA);
        }

        @Test
        @DisplayName("somar zero não altera o valor")
        void addingZeroKeepsValue() {
            assertEquals(7, calculator.add(7, 0), DELTA);
            assertEquals(7, calculator.add(0, 7), DELTA);
            assertEquals(0, calculator.add(0, 0), DELTA);
        }

        @ParameterizedTest(name = "{0} + {1} = {2}")
        @CsvSource({
            "1.5, 2.25, 3.75",
            "0.1, 0.2, 0.3",
            "-10, 4.5, -5.5",
            "1000000, 2000000, 3000000"
        })
        @DisplayName("soma números decimais e grandes")
        void addsDecimalsAndLargeNumbers(double a, double b, double expected) {
            assertEquals(expected, calculator.add(a, b), DELTA);
        }
    }

    @Nested
    @DisplayName("Subtrair")
    class Subtract {

        @Test
        @DisplayName("subtrai dois números positivos")
        void subtractsTwoPositives() {
            assertEquals(6, calculator.subtract(10, 4), DELTA);
        }

        @Test
        @DisplayName("resultado fica negativo quando o segundo número é maior")
        void resultIsNegativeWhenSecondIsLarger() {
            assertEquals(-6, calculator.subtract(4, 10), DELTA);
        }

        @Test
        @DisplayName("subtrai dois números negativos")
        void subtractsTwoNegatives() {
            assertEquals(1, calculator.subtract(-2, -3), DELTA);
        }

        @Test
        @DisplayName("subtrair um negativo equivale a somar")
        void subtractingNegativeIsAdding() {
            assertEquals(5, calculator.subtract(2, -3), DELTA);
        }

        @Test
        @DisplayName("subtrair zero não altera o valor")
        void subtractingZeroKeepsValue() {
            assertEquals(7, calculator.subtract(7, 0), DELTA);
        }

        @Test
        @DisplayName("zero menos um número inverte o sinal")
        void subtractingFromZeroFlipsSign() {
            assertEquals(-7, calculator.subtract(0, 7), DELTA);
            assertEquals(7, calculator.subtract(0, -7), DELTA);
        }

        @Test
        @DisplayName("um número menos ele mesmo é zero")
        void numberMinusItselfIsZero() {
            assertEquals(0, calculator.subtract(3.5, 3.5), DELTA);
        }
    }

    @Nested
    @DisplayName("Multiplicar")
    class Multiply {

        @Test
        @DisplayName("multiplica dois números positivos")
        void multipliesTwoPositives() {
            assertEquals(12, calculator.multiply(3, 4), DELTA);
        }

        @Test
        @DisplayName("positivo vezes negativo dá negativo")
        void positiveTimesNegativeIsNegative() {
            assertEquals(-12, calculator.multiply(3, -4), DELTA);
            assertEquals(-12, calculator.multiply(-3, 4), DELTA);
        }

        @Test
        @DisplayName("negativo vezes negativo dá positivo")
        void negativeTimesNegativeIsPositive() {
            assertEquals(12, calculator.multiply(-3, -4), DELTA);
        }

        @Test
        @DisplayName("qualquer número vezes zero é zero")
        void anythingTimesZeroIsZero() {
            assertEquals(0, calculator.multiply(9, 0), DELTA);
            assertEquals(0, calculator.multiply(0, -9), DELTA);
            assertEquals(0, calculator.multiply(0, 0), DELTA);
        }

        @Test
        @DisplayName("multiplicar por um não altera o valor")
        void multiplyingByOneKeepsValue() {
            assertEquals(-8.5, calculator.multiply(-8.5, 1), DELTA);
        }

        @ParameterizedTest(name = "{0} x {1} = {2}")
        @CsvSource({
            "2.5, 4, 10",
            "0.5, 0.5, 0.25",
            "-1.5, 2, -3",
            "1000, 1000, 1000000"
        })
        @DisplayName("multiplica números decimais e grandes")
        void multipliesDecimalsAndLargeNumbers(double a, double b, double expected) {
            assertEquals(expected, calculator.multiply(a, b), DELTA);
        }
    }

    @Nested
    @DisplayName("Dividir")
    class Divide {

        @Test
        @DisplayName("divide dois números positivos")
        void dividesTwoPositives() {
            assertEquals(5, calculator.divide(10, 2), DELTA);
        }

        @Test
        @DisplayName("positivo dividido por negativo dá negativo")
        void positiveByNegativeIsNegative() {
            assertEquals(-5, calculator.divide(10, -2), DELTA);
            assertEquals(-5, calculator.divide(-10, 2), DELTA);
        }

        @Test
        @DisplayName("negativo dividido por negativo dá positivo")
        void negativeByNegativeIsPositive() {
            assertEquals(5, calculator.divide(-10, -2), DELTA);
        }

        @Test
        @DisplayName("zero dividido por qualquer número é zero")
        void zeroDividedByAnythingIsZero() {
            assertEquals(0, calculator.divide(0, 5), DELTA);
        }

        @Test
        @DisplayName("divisão com resultado decimal")
        void dividesWithDecimalResult() {
            assertEquals(2.5, calculator.divide(5, 2), DELTA);
            assertEquals(0.333333333, calculator.divide(1, 3), 1e-6);
        }

        @Test
        @DisplayName("divisão por zero lança ArithmeticException")
        void divisionByZeroThrows() {
            ArithmeticException exception =
                assertThrows(ArithmeticException.class, () -> calculator.divide(10, 0));

            assertEquals("Divisão por zero não é permitida", exception.getMessage());
        }

        @Test
        @DisplayName("zero dividido por zero também lança ArithmeticException")
        void zeroByZeroThrows() {
            assertThrows(ArithmeticException.class, () -> calculator.divide(0, 0));
        }
    }
}
