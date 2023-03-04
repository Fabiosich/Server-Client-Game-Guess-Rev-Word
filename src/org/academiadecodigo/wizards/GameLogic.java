package org.academiadecodigo.wizards;

import java.util.LinkedList;

public class GameLogic {

    LinkedList<String> words = new LinkedList<>();
    // Opção 1 - Palavras do jogo.
    String reverseWord;
    Server.Client player;

    public GameLogic(Server.Client x) {
        this.player = x;
        //playRound();
    }


    public void playRound() {
        createWords();
        String secretWord = getRandomWord();
        String answer = createQuestion(secretWord);
        String reverseSecretWord = reverserWord(secretWord);

        System.out.println("Rev Solution -> " + reverseSecretWord);
        System.out.println("Answer Solution -> " + answer);
        if(compareWords(answer, reverseSecretWord)){
            System.out.println("WORDS ARE EQUAL!");
            System.out.println("YOU'VE WON! :) ");
            return;
        }

        System.out.println("WORDS ARE DIFFERENT");
        System.out.println("YOU'VE LOST! :( ");

    }
    //private String getUserInput() {
    //return }

    public void createWords() {
        words.add("car");
        words.add("bicycle");
        words.add("dog");
        words.add("jar");
        words.add("bitch");
        words.add("welcome");
        words.add("totona");
    }

    public String getRandomWord() {

        int randomIndex = (int) (Math.random() * words.size());
        String randomWord = words.get(randomIndex);
        //  System.out.println(randomWord);
        return randomWord;
    }

    public String createQuestion(String selectWord) {

        return player.askQuestion("\n" +  "So, let´s try to type the following word in reverse: " + selectWord);

    }


    public boolean compareWords(String userReverseWord, String solutionReversed) {
        return userReverseWord.equals(solutionReversed);
    }


    public String reverserWord(String sText) {
        reverseWord = new StringBuilder(sText).reverse().toString();
        return reverseWord;
    }

}
