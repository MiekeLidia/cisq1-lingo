package nl.hu.cisq1.lingo;

import nl.hu.cisq1.lingo.words.domain.Mark;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import nl.hu.cisq1.lingo.domain.Round;

import java.util.ArrayList;
import java.util.List;

import static nl.hu.cisq1.lingo.words.domain.Mark.CORRECT;
import static org.junit.jupiter.api.Assertions.*;

class RoundTest {
    @Test
    @DisplayName("stores a feedback element in feedback history")
    void guess() {
        Round round = new Round("pjoes",0);
        String attempt = "koooo";
        String attempt2 = "poooo";
        round.guess(attempt);
        round.guess(attempt2);
        assertEquals(2,round.getFeedbackHistory().size());
    }


    @Test
    @DisplayName("returns the number of attempts that have been made")
    void getAttempts() {
        Round round = new Round("pjoes",0);
        String attempt = "koooo";
        String attempt2 = "poooo";
        round.guess(attempt);
        round.guess(attempt2);
        assertEquals(2,round.getAttempts());
    }

    @Test
    @DisplayName("returns the number of letters in the current word")
    void getwordLength() {
        Round round = new Round("123456789",0);
        String attempt = "koooo";
        String attempt2 = "poooo";
        round.guess(attempt);
        round.guess(attempt2);
        assertEquals(9,round.getCurrentWordLength());
    }

    @Test
    @DisplayName("returns the number of letters in the current word")
    void giveMarks() {
        Round round = new Round("pjoes",0);
        String attempt = "pjoep";
        List<Mark> marks = new ArrayList<>();
        marks.add(CORRECT); marks.add(CORRECT); marks.add(CORRECT); marks.add(CORRECT); marks.add(Mark.ABSENT);
        assertEquals(round.giveMarks(attempt),marks);
    }

    @Test
    @DisplayName("gives hint based of hinthistory")
    void giveHint() {
        Round round = new Round("pjoes",0);
        round.giveHint();
        assertEquals("p....",round.giveHint());
    }

    @Test
    @DisplayName("gives hint based of hinthistory")
    void giveHint2() {
        Round round = new Round("pjoes",0);
        round.giveHint();
        String attempt = "pjjjj";
        round.guess(attempt);
        round.giveMarks(attempt);
        assertEquals("pj...",round.giveHint());
    }

}