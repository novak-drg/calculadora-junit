# Calculadora JUnit

Projeto Integrador IV-A — Análise e Desenvolvimento de Sistemas (PUC Goiás, EaD).

Projeto Java com testes automatizados em **JUnit 5** e controle de versão com **Git/GitHub**
(branch de funcionalidade, commits e merge).

## O que o projeto tem

| Arquivo | O que é |
|---|---|
| `src/main/java/.../Calculadora.java` | Classe com as quatro operações: `add` (somar), `subtract` (subtrair), `multiply` (multiplicar) e `divide` (dividir). A divisão por zero lança `ArithmeticException`. |
| `src/main/java/.../CalculatorApp.java` | Programa de console que lê expressões como `2 + 3` e mostra o resultado. |
| `src/test/java/.../CalculadoraTest.java` | **Testes unitários** de cada operação: números positivos, negativos, zero, decimais e a exceção da divisão por zero. |
| `src/test/java/.../CalculatorAppFunctionalTest.java` | **Testes funcionais**: simulam uma pessoa usando o programa de console do início ao fim. |

O código (classes, métodos e variáveis) está em inglês; os textos exibidos para quem usa
(nomes dos testes, mensagens e saída do console) estão em português.

## Requisitos

- JDK 17 ou superior

O Maven não precisa estar instalado: o projeto traz o Maven Wrapper (`mvnw`).

## Como executar os testes

```bash
# Windows
mvnw.cmd test

# Linux / macOS
./mvnw test
```

A saída lista cada teste pelo nome, agrupado por operação, e termina com o total:

```
Tests run: 37, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

## Como usar a calculadora no console

```bash
mvnw.cmd package
java -jar target/calculadora-junit-1.0.0.jar
```

```
Calculadora - digite uma expressão (ex.: 2 + 3) ou 'sair' para encerrar.
2 + 3
= 5
10 / 0
Erro: Divisão por zero não é permitida
7,5 x 2
= 15
sair
Até logo!
```

Operadores aceitos: `+`, `-`, `*` (ou `x`) e `/`. Números decimais podem usar vírgula ou ponto.

## Fluxo de versionamento

1. `main` recebe o projeto base, já configurado com JUnit 5.
2. A funcionalidade é desenvolvida na branch `feature/calculator`, com um commit por etapa
   (cada operação entra junto com os seus testes).
3. A branch é enviada ao GitHub e integrada à `main` por merge (pull request).
