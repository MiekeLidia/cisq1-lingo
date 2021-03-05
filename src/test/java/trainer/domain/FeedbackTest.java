package trainer.domain;

import nl.hu.cisq1.lingo.words.domain.Mark;
import nl.hu.cisq1.lingo.words.domain.Word;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static nl.hu.cisq1.lingo.words.domain.Mark.*;
import static org.junit.jupiter.api.Assertions.*;

class FeedbackTest {
    @Test
    @DisplayName("word is guessed if all letters are correct")
    void wordIsGuessed() {
        List<Mark> markList = List.of(CORRECT, CORRECT, CORRECT, CORRECT, CORRECT);
        Feedback feedback = new Feedback("woord", markList);
        assertTrue(feedback.isWordGuessed(markList));
    }

    @Test
    @DisplayName("word is guessed if not all letters are correct")
    void wordIsNotGuessed() {
        List<Mark> markList = List.of(CORRECT, INVALID, CORRECT, ABSENT, PRESENT);
        Feedback feedback = new Feedback("woord", markList);
        assertFalse(feedback.isWordGuessed(markList));
    }

    @Test
    @DisplayName("word is guessed if at least one  letter is invalid")
    void guessIsInvalid() {
        List<Mark> markList = List.of(CORRECT, INVALID, CORRECT, ABSENT, PRESENT);
        Feedback feedback = new Feedback("woord", markList);
        assertTrue(feedback.isInvalid(markList));
    }

    @Test
    @DisplayName("word is guessed if at least one  letter is invalid")
    void guessIsNotInvalid() {
        List<Mark> markList = List.of(CORRECT, CORRECT, CORRECT, ABSENT, PRESENT);
        Feedback feedback = new Feedback("woord", markList);
        assertFalse(feedback.isInvalid(markList));
    }

    @ParameterizedTest
    @MethodSource("provideHintExamples")
    @DisplayName("gives hint based on feedback and previous hint")
    void giveHint(String attempt, List<Mark> marks,  String previousHint, String expectedHint, Boolean exspected ) {
        // given
//        String attempt = "soort";
//        List<Mark> marks = List.of(CORRECT, ABSENT, PRESENT, ABSENT, CORRECT);
        Feedback feedback = new Feedback(attempt, marks);
//        String previousHint = "sp...";

        // when
        String hint = feedback.giveHint(previousHint);

        // then
//        String expectedHint = "sp..t";
        assertEquals(expectedHint.equals(hint), exspected);

    }

    static Stream<Arguments> provideHintExamples() {
        return Stream.of(
                Arguments.of(
                        "botet",
                        List.of(ABSENT, ABSENT, ABSENT, ABSENT, CORRECT),
                        "sp...",
                        "sp..t",
                        "True"
                ),
                Arguments.of(
                        "pjoes",
                        List.of(CORRECT, CORRECT, CORRECT, CORRECT, CORRECT),
                        ".....",
                        "pjoes",
                        "True"
                ),
                Arguments.of(
                        "pjoes",
                        List.of(ABSENT, ABSENT, ABSENT, ABSENT, ABSENT),
                        "..q..",
                        "pjoes",
                        "False"
                )
        );
    }
}