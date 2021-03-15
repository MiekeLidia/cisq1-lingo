package nl.hu.cisq1.lingo.domain;

import nl.hu.cisq1.lingo.words.domain.Mark;

import java.util.ArrayList;
import java.util.List;

public class Round {
    private String wordToGuess;
    private int attemptNumber;
    private List<Feedback> feedbackHistory = new ArrayList<>();
    List<Mark> marks = new ArrayList<>();
    private List<String> hintsHistory = new ArrayList<>();


    public Round(String wordToGuess, int attemptNumber) {
        this.wordToGuess = wordToGuess;
        this.attemptNumber = attemptNumber;
    }

   public void guess(String attempt) {
//        checkt of een poging nog wel mag
       if (attemptNumber == 5) {
           System.out.println("mag niet meer raden! 5 attempts made");
       } else {
           //genereert feedback en voegt het toe aan de history
           marks = giveMarks(attempt);
           Feedback feedback = new Feedback(attempt, marks);
           feedbackHistory.add(feedback);
           attemptNumber += 1;
       }
   }

    public List<Mark> giveMarks(String attempt){
        marks.clear();
        String[] lettersAttempt = attempt.split("");
        String[] lettersWordToGuess = this.wordToGuess.split("");
        for (int i = 0; i < lettersAttempt.length; i++) {
            if (lettersAttempt[i].equals(lettersWordToGuess[i])){
                marks.add(Mark.CORRECT);
            }else {
                marks.add(Mark.ABSENT);
            }
        }
        return marks;
    }


    public String giveHint(){
//      gebruik feedback history om de laatste hint te genereren
        if (attemptNumber == 0){
            List<String> hintPunt = new ArrayList<>();
            String firstLetter = wordToGuess.split("")[0];
            hintPunt.add(firstLetter);
            for (int i = 1; i < getCurrentWordLength(); i++) {
                hintPunt.add(".");
            }
            String hint = String.join("", hintPunt);
            hintsHistory.add(hint);
            return hint;
        }else {
            int sizeFH = feedbackHistory.size();
            Feedback feedback = getFeedbackHistory().get(sizeFH - 1);
            int sizeHH = getHintsHistory().size();
            return feedback.giveHint(hintsHistory.get(sizeHH - 1));
        }
    }

    public List<Feedback> getFeedbackHistory(){
       return feedbackHistory;
    }

    public int getAttempts(){
        return attemptNumber;
    }

    public int getCurrentWordLength(){
        return wordToGuess.length();
    }

    public List<String> getHintsHistory() {
        return hintsHistory;
    }

    public String getLastHint(){
        return hintsHistory.get(hintsHistory.size()-1);
    }
}
