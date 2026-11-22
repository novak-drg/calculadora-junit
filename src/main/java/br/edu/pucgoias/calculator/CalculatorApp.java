package br.edu.pucgoias.calculator;

import java.io.Console;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Scanner;

/**
 * Console front end: reads expressions such as "2 + 3" and prints the result.
 */
public class CalculatorApp {

    private static final Locale PT_BR = Locale.forLanguageTag("pt-BR");

    private final Calculadora calculator = new Calculadora();
    private final DecimalFormat numberFormat =
        new DecimalFormat("#.######", DecimalFormatSymbols.getInstance(PT_BR));

    public static void main(String[] args) {
        // A real console reports its own code page; piped or redirected streams get UTF-8.
        Console console = System.console();
        Charset charset = console != null ? console.charset() : StandardCharsets.UTF_8;

        new CalculatorApp().run(
            new Scanner(System.in, charset),
            new PrintStream(System.out, true, charset));
    }

    void run(Scanner input, PrintStream output) {
        output.println("Calculadora - digite uma expressão (ex.: 2 + 3) ou 'sair' para encerrar.");

        while (input.hasNextLine()) {
            String line = input.nextLine().trim();
            if (line.equalsIgnoreCase("sair")) {
                break;
            }
            if (!line.isEmpty()) {
                output.println(evaluate(line));
            }
        }

        output.println("Até logo!");
    }

    String evaluate(String expression) {
        String[] parts = expression.trim().split("\\s+");
        if (parts.length != 3) {
            return "Expressão inválida. Use o formato: número operador número";
        }

        try {
            double left = parseNumber(parts[0]);
            double right = parseNumber(parts[2]);
            return "= " + numberFormat.format(apply(parts[1], left, right));
        } catch (NumberFormatException e) {
            return "Número inválido. Use apenas números, por exemplo: 2,5 + 1";
        } catch (ArithmeticException | IllegalArgumentException e) {
            return "Erro: " + e.getMessage();
        }
    }

    private double apply(String operator, double left, double right) {
        return switch (operator) {
            case "+" -> calculator.add(left, right);
            case "-" -> calculator.subtract(left, right);
            case "*", "x" -> calculator.multiply(left, right);
            case "/" -> calculator.divide(left, right);
            default -> throw new IllegalArgumentException("Operador desconhecido: " + operator);
        };
    }

    // Accepts both "2.5" and the Brazilian "2,5".
    private double parseNumber(String text) {
        return Double.parseDouble(text.replace(',', '.'));
    }
}
