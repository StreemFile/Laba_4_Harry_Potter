package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
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
        for (int i = 0; i < index; i++) {
            if(numberOfEachWord[i] == 2 || numberOfEachWord[i] == 3 || numberOfEachWord[i] == 4) {
                System.out.println("Слово " + wordsWithoutRepetition[i] + " повторюється " + numberOfEachWord[i] + " рази.");
            }
            else {
                System.out.println("Слово " + wordsWithoutRepetition[i] + " повторюється " + numberOfEachWord[i] + " раз.");
            }
        }

//        System.out.println("Меню програми:" +
//                "\n 1 - вивести всі слова в алфавітному порядку" +
//                "\n 2 - вивести слова в порядку спадання" +
//                "\n 3 - вивести слова в порядку зростання" +
//                "\n 4 - закінчити роботу програми.");

    }
}
