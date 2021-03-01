package trainer.domain;

import nl.hu.cisq1.lingo.words.domain.Mark;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FeedbackTest {



    @Test
    @DisplayName("word is guessed if all letters are correct")
    void wordIsGuessed(){
        List<Mark> markList = List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT);
        Feedback feedback = new Feedback("woord", markList);
        assertTrue(feedback.isWordGuessed(markList));
    }

    @Test
    @DisplayName("word is guessed if not all letters are correct")
    void wordIsNotGuessed(){
        List<Mark> markList = List.of(Mark.CORRECT, Mark.INVALID, Mark.CORRECT, Mark.ABSENT, Mark.PRESENT);
        Feedback feedback = new Feedback("woord", markList);
        assertFalse(feedback.isWordGuessed(markList));
    }

    @Test
    @DisplayName("word is guessed if at least one  letter is invalid")
    void guessIsInvalid() {
        List<Mark> markList = List.of(Mark.CORRECT, Mark.INVALID, Mark.CORRECT, Mark.ABSENT, Mark.PRESENT);
        Feedback feedback = new Feedback("woord", markList);
        assertTrue(feedback.isInvaloid(markList));
    }

    @Test
    @DisplayName("word is guessed if at least one  letter is invalid")
    void guessIsNotInvalid() {
        List<Mark> markList = List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.ABSENT, Mark.PRESENT);
        Feedback feedback = new Feedback("woord", markList);
        assertFalse(feedback.isInvaloid(markList));
    }

    @MethodSource("provideHintExamples ")
    static Stream<Arguments> provideHintExamples() {
        return Stream.of(
                Arguments.of(A, B, C),
                Arguments.of(D, E, F)
        );
    }


    @ParameterizedTest
    @MethodSource({"marks", "previousHint ", "wordToGuess"})
    @DisplayName("geef hint")
    void giveHint() {

    }



}