package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            String[] data = inputAndCheckData(scanner);
            checkData(data);
            checkMobileNumber(data);
            checkGender(data);
            checkFIO(data);
        } catch (IllegalArgumentException e){
            System.out.println(ANSI_RED + "Дата рождения введена не корректно, попробуйте снов!" + ANSI_RESET);
        } catch (ArithmeticException e){
            System.out.println(ANSI_RED + "Номер телефона введен не корректно, попробуйте снова!" + ANSI_RESET);
        } catch (NullPointerException e){
            System.out.println(ANSI_RED + "Пол введен не корректно, попробуйте снова!");
        } catch (RuntimeException e){
            System.out.println(ANSI_RED + "ФИО введены не корректно, ФИО не может содержать цифры или состоять из пробела!" + ANSI_RESET);
        }

    }
    public static String[] inputAndCheckData(Scanner scanner){
        while (true) {
            System.out.print("Введите данные через пробел следующего формата:\n" +
                    "фамилия имя отчество - строки\n" +
                    "датарождения - строка формата dd.mm.yyyy\n" +
                    "номертелефона - целое беззнаковое число без форматирования\n" +
                    "пол - символ латиницей f или m.\n" +
                    "Пример: Иванов Иван Иванович 12.12.2012 89200000000 m\n" +
                    "Введите ваши данные: ");
            String data = scanner.nextLine();
            String[] array = data.split(" ");
            if(array.length == 6){
                return array;
            } else {
                System.out.println(ANSI_RED +
                        "Данные были введены некорректно, попробуйте еще раз!" +
                        ANSI_RESET);
            }
        }
    }
    public static void checkFIO(String[] data){
        for (int i = 0; i < 3; i++) {
            if(data[i].matches(".*\\d.*")){
                throw new RuntimeException();
            }
            if (data[i].isEmpty()){
                throw new RuntimeException();
            }
        }
    }
    public static void checkData(String[] data){
        String birthData = data[3];
        if (birthData.length() != 10){
            throw new IllegalArgumentException();
        }
        String[] arrayData = birthData.split("\\.");
        if(arrayData.length != 3){
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < arrayData.length - 1; i++) {
            if(arrayData[i].length() != 2){
                throw new IllegalArgumentException();
            }
        }
        if(arrayData[2].length() != 4){
            throw new IllegalArgumentException();
        }

    }
    public static void checkMobileNumber(String[] data){
        if(data[4].length() != 11){
            throw new ArithmeticException();
        }
        long number = Long.parseLong(data[4]);
    }
    public static void checkGender(String[] data){
        if(!(data[5].equals("m") || data[5].equals("f"))){
            throw new NullPointerException();
        }
    }
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
}