package nl.hu.cisq1.lingo.domain;

import java.util.ArrayList;
import java.util.List;

public class Progress {
    //Alle data teruggeven die nodig is om de huidige speeltoestadnd weer te geven
    //(score, current feedback history, current hint)
    private int score;
    private List<Feedback> feedbackHistory = new ArrayList<>();
    private String currentHint;

    public Progress(int score, List<Feedback> feedbackHistory, String currentHint) {
        this.score = score;
        this.feedbackHistory = feedbackHistory;
        this.currentHint = currentHint;
    }


    @Override
    public String toString() {
        return "Progress{" +
                "score=" + score +
                ", feedbackHistory=" + feedbackHistory +
                ", hintsHistory=" + currentHint +
                '}';
    }
}
