package br.edu.pucgoias.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
