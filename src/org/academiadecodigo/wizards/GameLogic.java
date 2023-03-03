package org.academiadecodigo.wizards;

import jdk.nashorn.internal.runtime.logging.DebugLogger;
import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

import java.io.PrintStream;
import java.net.Socket;
import java.util.LinkedList;

public class GameLogic {

    LinkedList<String> words = new LinkedList<>();
    // Opção 1 - Palavras do jogo.
    String reverseWord;

    Server.Client player;

    public GameLogic(Server.Client x) {
        this.player = x;
        createWords();
        getRandomWord();
        createQuestion();
    }


    //private String getUserInput() {
    //return }

    public void createWords() {
        words.add("crapicornio");
        words.add("merculina");
        words.add("aclitedes");
        words.add("streetsonthenights");
        words.add("carlaocaralho");
        words.add("deixatedemerdasfafonso");
        words.add("coolcoolcool");
        words.add("jogomaisdivertido");
    }

    public String getRandomWord() {

        int randomIndex = (int) (Math.random() * words.size());
        String randomWord = words.get(randomIndex);
        //  System.out.println(randomWord);
        return randomWord;
    }

    public void createQuestion() {


        String selectWord = getRandomWord();
        player.askQuestion("Type the following word in reverse " + selectWord);

    }

    public void playRound(){
        while(clientList == playerAnswersList)

    }





    /*public String reverserWord() {
        String sText = getRandomWord();
        String sReverse = new StringBuilder(sText).reverse().toString();
        reverseWord = sReverse;
        //System.out.println(reverseWord);
        return sReverse;
    }

   */ public void validateWord(String x) {

        Prompt prompt = new Prompt(System.out, new PrintStream(player.getOutputStream());
        String userWord = prompt.getUserInput();


        if(userWord.equals(reverseWord));


    }

*/
}
