package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static  void printWords(String[] wordsWithoutRepetition, int[] numberOfEachWord, int index ){
        for (int i = 0; i < index; i++) {
            if (numberOfEachWord[i] == 2 || numberOfEachWord[i] == 3 || numberOfEachWord[i] == 4) {
                System.out.println("Слово " + wordsWithoutRepetition[i] + " повторюється " + numberOfEachWord[i] + " рази.");
            } else {
                System.out.println("Слово " + wordsWithoutRepetition[i] + " повторюється " + numberOfEachWord[i] + " раз.");
            }
        }
    }

    static void menu(String[] wordsWithoutRepetition, int[] numberOfEachWord, int index ) {
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
        }
        else if (menu == 2){
            for(int i = index - 1; i > 0; i--){
                for(int j = 0; j<i; j++) {
                    if(numberOfEachWord[j]>numberOfEachWord[j+1]) {
                        int tmpNumber = numberOfEachWord[j];
                        numberOfEachWord[j] = numberOfEachWord[j+1];
                        numberOfEachWord[j+1] = tmpNumber;

                        String tmpWord = wordsWithoutRepetition[j];
                        wordsWithoutRepetition[j] = wordsWithoutRepetition[j+1];
                        wordsWithoutRepetition[j+1] = tmpWord;
                    }
                }
            }
            printWords(wordsWithoutRepetition, numberOfEachWord, index);
            menu(wordsWithoutRepetition, numberOfEachWord, index);
        }
        else if (menu == 3){
            for(int i = index - 1; i > 0; i--){
                for(int j = 0; j<i; j++) {
                    if(numberOfEachWord[j]<numberOfEachWord[j+1]) {
                        int tmpNumber = numberOfEachWord[j];
                        numberOfEachWord[j] = numberOfEachWord[j+1];
                        numberOfEachWord[j+1] = tmpNumber;

                        String tmpWord = wordsWithoutRepetition[j];
                        wordsWithoutRepetition[j] = wordsWithoutRepetition[j+1];
                        wordsWithoutRepetition[j+1] = tmpWord;
                    }
                }
            }
            printWords(wordsWithoutRepetition, numberOfEachWord, index);
            menu(wordsWithoutRepetition, numberOfEachWord, index);
        }
        else if (menu == 4){
            System.out.println("Введіть слово, яке потрібно знайти: ");
            Scanner inWord = new Scanner(System.in);
            String searchedWord = inWord.nextLine();
            boolean isFound = false;
            int indexOfSearchedWord = -1;
            for (int i = 0; i < index; i++) {
                if (searchedWord.equals(wordsWithoutRepetition[i])) {
                    isFound = true;
                    indexOfSearchedWord = i;
                    break;
                }
            }
            if(isFound)
            {
                if(numberOfEachWord[indexOfSearchedWord] == 2 && numberOfEachWord[indexOfSearchedWord] == 3 && numberOfEachWord[indexOfSearchedWord] == 4) {
                        System.out.println("Слово " + searchedWord + " повторюється " + numberOfEachWord[indexOfSearchedWord] + " рази.");
                    }
                    else {
                        System.out.println("Слово " + searchedWord + " повторюється " + numberOfEachWord[indexOfSearchedWord] + " раз.");
                    }
                }
            else {
                    System.out.println("Слово " + searchedWord + " не використовувалось в тексті.");
            }
            menu(wordsWithoutRepetition, numberOfEachWord, index);
        }
    }

    static void findAndCountWords()
    {
        String harry = "Mr. and Mrs. Dursley, of number four, Privet Drive, were proud to say that they were perfectly " +
                "normal, thank you very much. They were the last people you'd expect to be involved in anything " +
                "strange or mysterious, because they just didn't hold with such nonsense.";
        harry = harry.replaceAll(",", "").replaceAll("\\.","").replaceAll(";",
                "").replaceAll(":","").replaceAll("\\?","").
                replaceAll("!","").replaceAll("-","");
        harry = harry.toLowerCase();
        String[] words = harry.split(" ");
        Arrays.sort(words);
        String[] wordsWithoutRepetition = new String[10000];
        int[] numberOfEachWord = new int[10000];
        int index = 0;
        for (int i = 0; i < words.length; i++) {
            boolean isRecorded = false;
            for (int j = 0; j < wordsWithoutRepetition.length; j++) {
                if (words[i].equals(wordsWithoutRepetition[j])) {
                    isRecorded = true;
                    break;
                }
            }
            if (!isRecorded){
                wordsWithoutRepetition[index] = words[i];
                numberOfEachWord[index] = 1;
                for (int j = 0; j < words.length; j++) {
                    if(words[i].equals(words[j]) && i != j) {
                        numberOfEachWord[index]++;
                    }
                }
                index++;
            }
        }
        printWords(wordsWithoutRepetition,numberOfEachWord,index);
        menu(wordsWithoutRepetition,numberOfEachWord,index);
    }

    public static void main(String[] args) {
        findAndCountWords();
    }
}
