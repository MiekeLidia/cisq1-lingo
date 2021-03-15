package nl.hu.cisq1.lingo.domain;

public class Game {
    private String wordToGuess;
    private int roundNumber;
    private String guess;
    private Round round;
    private int score;


    public Game(String wordToGuess) {
        this.wordToGuess = wordToGuess;
        score = 0;
        roundNumber = 0;
    }

    public Round startNewRound(String wordtToGuess){
        //maak nieuwe ronde aan gebaseerd op word to guess
        round = new Round(wordToGuess,roundNumber);
        return round;

    }

    public void guess(String attempt){
        //geeft raadpoging door aan huidige speelronde
        round.giveHint();
        round.guess(attempt);
    }

    public Progress showProgress(){
        //Alle data teruggeven die nodig is om de huidige speeltoestadnd weer te geven
        //(score, current feedback history, current hint)
        String currentHint = round.getHintsHistory().get(round.getHintsHistory().size()-1);
        return new Progress(this.score, round.getFeedbackHistory(), currentHint);
    }

    public boolean isPlayerEliminated(){
        return round.getFeedbackHistory().size() >= 5;
    }

    public boolean isPlaying(){
        return false;
    }

    public Integer provideNextWordtLength(){
        //op basis van ronde nieuwe woordlengte berekenen
        //handig voor als we een woord voor volgende ronde (in service) gaan aanvragen.
        int nextWordLength;
        if (wordToGuess.length() < 7){
            nextWordLength = wordToGuess.length() + 1;
        }else {
            nextWordLength = 5;
        }
        return nextWordLength;
    }





}
