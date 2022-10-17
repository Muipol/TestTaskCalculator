package com.company;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {
    static int result;
    static Scanner scanner = new Scanner(System.in);
    static Notation flagOfNotation;

    public static void main(String[] args) {
        System.out.println("Введите выражение через пробелы");
        String inputTerm = scanner.nextLine();
        inputTerm = inputTerm.trim();
        calculate(inputTerm);
        switch (flagOfNotation) {
            case ARABIC -> System.out.println(result);
            case ROMAN -> System.out.println(intToRoman(result));
        }
    }

    public static void calculate(String inputTerm) {
        String operand1, operand2;
        char operator;
        String[] elements = inputTerm.split(" ");
        if (elements.length > 3) {
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("Exception : " + e);
                System.out.println("Формат математической операции не удовлетворяет заданию - два операнда и один оператор через пробелы");
                System.exit(0);
            }
        }
        operand1 = elements[0];
        operand2 = elements[2];
        operator = elements[1].charAt(0);
        if (elements[1].length() > 1) {
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("Exception : " + e);
                System.out.println("Не верный знак операции");
                System.exit(0);
            }
        }
        int number1 = romanToInt(operand1);
        int number2 = romanToInt(operand2);
        if ((number1 * number2) > 0) {
            flagOfNotation = Notation.ROMAN;
        } else {
            try {
                number1 = Integer.parseInt(operand1);
                number2 = Integer.parseInt(operand2);
            } catch (Exception e) {
                System.out.println("Exception : " + e);
                System.out.println("Можно работать только с целыми числами");
                System.exit(0);
            }
            flagOfNotation = Notation.ARABIC;
        }

        if (number1 < 1 || number1 > 10 || number2 < 1 || number2 > 10) {
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("Exception : " + e);
                System.out.println("Можно работать с числами от 1 до 10");
                System.exit(0);
            }
        }
        switch (operator) {
            case '+':
                result = number1 + number2;
                break;
            case '-':
                result = number1 - number2;
                if (flagOfNotation == Notation.ROMAN && result <= 0)
                    try {
                        throw new IOException();
                    } catch (IOException e) {
                        System.out.println("Exception : " + e);
                        System.out.println("В римской системе есть только положительные числа");
                        System.exit(0);
                    }
                break;
            case '*':
                result = number1 * number2;
                break;
            case '/':
                try {
                    result = number1 / number2;
                } catch (ArithmeticException | InputMismatchException e) {
                    System.out.println("Exception : " + e);
                    System.out.println("Ноль в знаминателе");
                    break;
                }
                break;
            default:
                try {
                    throw new IOException();
                } catch (IOException e) {
                    System.out.println("Exception : " + e);
                    System.out.println("Не верный знак операции");
                    System.exit(0);
                }
        }
    }
    private static int romanToInt (String romanNumber){
        return switch (romanNumber) {
            case "I" -> 1;
            case "II" -> 2;
            case "III" -> 3;
            case "IV" -> 4;
            case "V" -> 5;
            case "VI" -> 6;
            case "VII" -> 7;
            case "VIII" -> 8;
            case "IX" -> 9;
            case "X" -> 10;
            default -> 0;
        };
    }
    private static String intToRoman ( int nubmer){
        String[] romanNubmers = {"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX"};
        return romanNubmers[nubmer];
    }
}
