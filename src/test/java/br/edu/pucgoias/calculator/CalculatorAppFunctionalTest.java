package br.edu.pucgoias.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Functional tests: drive the console program as a user would, typing lines and reading the output.
 */
@DisplayName("Calculadora - testes funcionais (uso pelo console)")
class CalculatorAppFunctionalTest {

    private static final String GREETING =
        "Calculadora - digite uma expressão (ex.: 2 + 3) ou 'sair' para encerrar.";
    private static final String FAREWELL = "Até logo!";

    @Test
    @DisplayName("sessão completa com as quatro operações")
    void fullSessionWithAllFourOperations() {
        List<String> output = runSession("2 + 3", "10 - 4", "3 * 4", "10 / 4", "sair");

        assertEquals(List.of(GREETING, "= 5", "= 6", "= 12", "= 2,5", FAREWELL), output);
    }

    @Test
    @DisplayName("divisão por zero mostra erro e a sessão continua")
    void divisionByZeroShowsErrorAndSessionContinues() {
        List<String> output = runSession("8 / 0", "8 / 2", "sair");

        assertEquals(
            List.of(GREETING, "Erro: Divisão por zero não é permitida", "= 4", FAREWELL),
            output);
    }

    @Test
    @DisplayName("aceita números negativos e decimais com vírgula")
    void acceptsNegativeAndCommaDecimalNumbers() {
        List<String> output = runSession("-2,5 + 1", "1,5 x -2", "sair");

        assertEquals(List.of(GREETING, "= -1,5", "= -3", FAREWELL), output);
    }

    @Test
    @DisplayName("entradas inválidas recebem mensagem de ajuda")
    void invalidInputsGetHelpMessages() {
        List<String> output = runSession("2 +", "dois + 3", "2 ^ 3", "sair");

        assertEquals(
            List.of(
                GREETING,
                "Expressão inválida. Use o formato: número operador número",
                "Número inválido. Use apenas números, por exemplo: 2,5 + 1",
                "Erro: Operador desconhecido: ^",
                FAREWELL),
            output);
    }

    @Test
    @DisplayName("linhas em branco são ignoradas e 'SAIR' em maiúsculas encerra")
    void blankLinesAreIgnoredAndExitIsCaseInsensitive() {
        List<String> output = runSession("", "   ", "1 + 1", "SAIR", "9 + 9");

        assertEquals(List.of(GREETING, "= 2", FAREWELL), output);
    }

    @Test
    @DisplayName("encerra normalmente quando a entrada acaba sem 'sair'")
    void endsWhenInputRunsOutWithoutExitCommand() {
        List<String> output = runSession("7 - 7");

        assertEquals(List.of(GREETING, "= 0", FAREWELL), output);
    }

    private static List<String> runSession(String... typedLines) {
        Scanner input = new Scanner(String.join("\n", typedLines) + "\n");
        ByteArrayOutputStream captured = new ByteArrayOutputStream();

        new CalculatorApp().run(input, new PrintStream(captured, true, StandardCharsets.UTF_8));

        return captured.toString(StandardCharsets.UTF_8).lines().toList();
    }
}
