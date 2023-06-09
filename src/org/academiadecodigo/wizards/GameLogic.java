package org.academiadecodigo.wizards;

d
import java.util.LinkedList;

class GameLogic {
    private LinkedList<String> words = new LinkedList<>();
    private String reverseWord;
    private Server.Client player;

    public GameLogic(Server.Client x) {
        this.player = x;
        playRound();
    }

    public void playRound() {
        createWords();
        String secretWord = getRandomWord();
        String answer = createQuestion(secretWord);
        String reverseSecretWord = reverserWord(secretWord);

        System.out.println("Rev Solution -> " + reverseSecretWord);
        System.out.println("Answer Solution -> " + answer);
        if (compareWords(answer, reverseSecretWord)) {
            System.out.println("WORDS ARE EQUAL!");
            return;
        }

        System.out.println("WORDS ARE DIFFERENT");
<<<<<<< Updated upstream

=======
        System.out.println("YOU'VE LOST! :( ");
>>>>>>> Stashed changes
    }

    public void createWords() {
        words.add("car");
        words.add("bycicle");
        words.add("dog");
        words.add("jar");
        words.add("bitch");
        words.add("walcome");
        words.add("totona");
    }

    public String getRandomWord() {
        int randomIndex = (int) (Math.random() * words.size());
        String randomWord = words.get(randomIndex);
        return randomWord;
    }

    public String createQuestion(String selectWord) {
<<<<<<< Updated upstream

        return player.askQuestion("\n" +  "So, let´s try type the following word in reverse: " + selectWord);

=======
        return player.askQuestion("\nSo, let's try to type the following word in reverse: " + selectWord);
>>>>>>> Stashed changes
    }

    public boolean compareWords(String userReverseWord, String solutionReversed) {
        return userReverseWord.equals(solutionReversed);
    }

    public String reverserWord(String sText) {
        reverseWord = new StringBuilder(sText).reverse().toString();
        return reverseWord;
    }
}