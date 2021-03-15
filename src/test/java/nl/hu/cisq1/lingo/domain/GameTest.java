package nl.hu.cisq1.lingo.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameTest {

//    @BeforeEach
//    public void initialize() {
//        String wordToGuess = "Pjoes";
//        String attempt = "pookj";
//        Game game = new Game(wordToGuess);
//        Round round = game.startNewRound(wordToGuess);
//    }

    @Test
    @DisplayName("stores a feedback element in feedback history")
    void startNewRound() {
        String wordToGuess = "Pjoes";
        String attempt = "pookj";
        Game game = new Game(wordToGuess);
        Round round = game.startNewRound(wordToGuess);
//        round.giveHint();
        round.guess(attempt);
        game.startNewRound(wordToGuess);
        assertEquals(new Feedback(attempt,round.giveMarks(attempt)),round.getFeedbackHistory().get(0));
    }

    @Test
    void guess() {
        String wordToGuess = "Pjoes";
        String attempt = "pookj";
        Game game = new Game(wordToGuess);
        Round round = game.startNewRound(wordToGuess);
//        round.giveHint();
        game.guess(attempt);
        assertEquals(1, round.getFeedbackHistory().size());
    }

    @Test
    void showProgress() {
        String wordToGuess = "pjoes";
        Game game = new Game(wordToGuess);
        Round round = game.startNewRound(wordToGuess);

        String attempt = "koooo";
        game.guess(attempt);

        String attempt2 = "pjoed";
        game.guess(attempt2);

        List<Feedback> feedbackHistory = round.getFeedbackHistory();
        String lastHint = round.getHintsHistory().get(round.getHintsHistory().size()-1);
        Progress progress = new Progress(0, feedbackHistory, lastHint);

        assertEquals(progress,game.showProgress());
    }

    @Test
    void isPlayerEliminated() {
        String wordToGuess = "Pjoes";
        String attempt = "pookj";
        Game game = new Game(wordToGuess);
        game.startNewRound(wordToGuess);
        game.guess(attempt);
        game.guess(attempt);
        game.guess(attempt);
        game.guess(attempt);
        game.guess(attempt);
        assertEquals(true,game.isPlayerEliminated());

    }

    @Test
    void isPlaying() {
    }

    @Test
    void provideNextWordtLength() {
        String wordToGuess = "pjoes";
        Game game = new Game(wordToGuess);
        assertEquals(6,game.provideNextWordtLength());

    }
}