package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static String readUsingFiles(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    static void printWords(String[] wordsWithoutRepetition, int[] numberOfEachWord, int counter) {
        for (int i = 0; i < counter; i++) {
            if (numberOfEachWord[i] == 2 || numberOfEachWord[i] == 3 || numberOfEachWord[i] == 4) {
                System.out.println("Слово " + wordsWithoutRepetition[i] + " повторюється " + numberOfEachWord[i] + " рази.");
            } else {
                System.out.println("Слово " + wordsWithoutRepetition[i] + " повторюється " + numberOfEachWord[i] + " раз.");
            }
        }
    }

    static void menu(String[] wordsWithoutRepetition, int[] numberOfEachWord, int counter) throws IOException {
        int menu = 0;
        System.out.println("Меню програми:" +
                "\n 1 - вивести всі слова в алфавітному порядку" +
                "\n 2 - вивести слова в порядку спадання" +
                "\n 3 - вивести слова в порядку зростання" +
                "\n 4 - знайти необхідне слово" +
                "\n Щоб вийти з програми введіть будь-яке інше число." +
                "\n Введіть команду: "
        );
        Scanner in = new Scanner(System.in);
        menu = in.nextInt();
        if (menu == 1) {
            findAndCountWords();
        } else if (menu == 2) {
            for (int i = counter - 1; i > 0; i--) {
                for (int j = 0; j < i; j++) {
                    if (numberOfEachWord[j] > numberOfEachWord[j + 1]) {
                        int tmpNumber = numberOfEachWord[j];
                        numberOfEachWord[j] = numberOfEachWord[j + 1];
                        numberOfEachWord[j + 1] = tmpNumber;

                        String tmpWord = wordsWithoutRepetition[j];
                        wordsWithoutRepetition[j] = wordsWithoutRepetition[j + 1];
                        wordsWithoutRepetition[j + 1] = tmpWord;
                    }
                }
            }
            printWords(wordsWithoutRepetition, numberOfEachWord, counter);
            menu(wordsWithoutRepetition, numberOfEachWord, counter);
        } else if (menu == 3) {
            for (int i = counter - 1; i > 0; i--) {
                for (int j = 0; j < i; j++) {
                    if (numberOfEachWord[j] < numberOfEachWord[j + 1]) {
                        int tmpNumber = numberOfEachWord[j];
                        numberOfEachWord[j] = numberOfEachWord[j + 1];
                        numberOfEachWord[j + 1] = tmpNumber;

                        String tmpWord = wordsWithoutRepetition[j];
                        wordsWithoutRepetition[j] = wordsWithoutRepetition[j + 1];
                        wordsWithoutRepetition[j + 1] = tmpWord;
                    }
                }
            }
            printWords(wordsWithoutRepetition, numberOfEachWord, counter);
            menu(wordsWithoutRepetition, numberOfEachWord, counter);
        } else if (menu == 4) {
            System.out.println("Введіть слово, яке потрібно знайти: ");
            Scanner inWord = new Scanner(System.in);
            String searchedWord = inWord.nextLine();
            boolean isFound = false;
            int counterOfSearchedWord = -1;
            for (int i = 0; i < counter; i++) {
                if (searchedWord.equals(wordsWithoutRepetition[i])) {
                    isFound = true;
                    counterOfSearchedWord = i;
                    break;
                }
            }
            if (isFound) {
                if (numberOfEachWord[counterOfSearchedWord] == 2 && numberOfEachWord[counterOfSearchedWord] == 3 && numberOfEachWord[counterOfSearchedWord] == 4) {
                    System.out.println("Слово " + searchedWord + " повторюється " + numberOfEachWord[counterOfSearchedWord] + " рази.");
                } else {
                    System.out.println("Слово " + searchedWord + " повторюється " + numberOfEachWord[counterOfSearchedWord] + " раз.");
                }
            } else {
                System.out.println("Слово " + searchedWord + " не використовувалось в тексті.");
            }
            menu(wordsWithoutRepetition, numberOfEachWord, counter);
        }
    }

    static void findAndCountWords() throws IOException {
        String harry = readUsingFiles("/Users/vovamv/Desktop/Учоба/Java/lesson 3/myFile.txt");
        harry = harry.toLowerCase();
        harry = harry.replaceAll("[^A-Za-z0-9']", " ");
        String[] words = harry.split(" +");
        Arrays.sort(words);
        String[] wordsWithoutRepetition = new String[10000];
        int[] numberOfEachWord = new int[10000];
        int counter = 0;
        for (int i = 0; i < words.length; i++) {
            boolean isRecorded = false;
            for (int j = 0; j < wordsWithoutRepetition.length; j++) {
                if (words[i].equals(wordsWithoutRepetition[j])) {
                    isRecorded = true;
                    break;
                }
            }
            if (!isRecorded) {
                wordsWithoutRepetition[counter] = words[i];
                numberOfEachWord[counter] = 1;
                for (int j = 0; j < words.length; j++) {
                    if (words[i].equals(words[j]) && i != j) {
                        numberOfEachWord[counter]++;
                    }
                }
                counter++;
            }
        }
        printWords(wordsWithoutRepetition, numberOfEachWord, counter);
        menu(wordsWithoutRepetition, numberOfEachWord, counter);
    }

    public static void main(String[] args) throws IOException {
        findAndCountWords();
    }
}
