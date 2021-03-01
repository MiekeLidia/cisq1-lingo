package trainer.domain;

import nl.hu.cisq1.lingo.words.domain.Mark;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class Feedback {
    String attempt;
    List<Mark> marks;


    public Feedback(String attempt, List<Mark> marks) {
        this.attempt = attempt;
        this.marks = marks;
    }

    public boolean isWordGuessed(List<Mark> marks) {
        Predicate<Mark> p1 = m -> m == Mark.CORRECT;
        return marks.stream().allMatch(p1);
//        for (Mark mark : marks) {
//            if (mark != Mark.CORRECT) {
//                return false;
//            }
        }

    public boolean isInvaloid(List<Mark> marks) {
//        Predicate<Mark> p1 = m -> m == Mark.CORRECT;
//        return marks.stream().allMatch(p1);
        for (Mark mark : marks) {
            if (mark == Mark.INVALID) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return Objects.equals(attempt, feedback.attempt) &&
                Objects.equals(marks, feedback.marks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attempt, marks);
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "attempt='" + attempt + '\'' +
                ", marks=" + marks +
                '}';
    }
}
