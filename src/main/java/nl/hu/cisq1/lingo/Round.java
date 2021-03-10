package nl.hu.cisq1.lingo;

import nl.hu.cisq1.lingo.words.domain.Mark;
import trainer.domain.Feedback;

import java.util.ArrayList;
import java.util.List;

public class Round {
    private String wordToGuess;
    private int roundNumber;
    private List<Feedback> feedbackHistory = new ArrayList<>();
    List<Mark> marks = new ArrayList<>();
    private List<String> hintsHistory = new ArrayList<>();


    public Round(String wordToGuess, int roundNumber) {
        this.wordToGuess = wordToGuess;
        this.roundNumber = roundNumber;
    }

   public void guess(String attempt) {
//        checkt of een poging nog wel mag
       if (roundNumber == 5) {
           System.out.println("mag niet meer raden! 5 attempts made");
       } else {
           //genereert feedback en voegt het toe aan de history
           marks = giveMarks(attempt);
           Feedback feedback = new Feedback(attempt, marks);
           feedbackHistory.add(feedback);
           roundNumber += 1;
       }
   }

    public List<Mark> giveMarks(String attempt){
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
        if (roundNumber == 2){
            List<String> hintPunt = new ArrayList<>();
            for (int i = 0; i < getCurrentWordLength(); i++) {
                hintPunt.add(".");
            }
            hintsHistory.add(String.join("", hintPunt));
        }
        int sizeFH = feedbackHistory.size();
        Feedback feedback = getFeedbackHistory().get(sizeFH-1);
        int sizeHH = getHintsHistory().size();
        System.out.println(sizeHH);
        return feedback.giveHint(hintsHistory.get(sizeHH-1));
    }

    public List<Feedback> getFeedbackHistory(){
       return feedbackHistory;
    }

    public int getAttempts(){
        return roundNumber-1;
    }

    public int getCurrentWordLength(){
        return wordToGuess.length();
    }

    public List<String> getHintsHistory() {
        return hintsHistory;
    }
}
