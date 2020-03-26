package com.tinuade.africaknow.Model;

public class Score {

    private Integer noOfRightAnswers;

    public Score(int r ,int w) {
        setNoOfRightAnswers(r);
        setNoOfWrongAnswers(w);
    }

    public Integer getNoOfRightAnswers() {
        return noOfRightAnswers;
    }

    public void setNoOfRightAnswers(Integer noOfRightAnswers) {
        this.noOfRightAnswers = noOfRightAnswers;
    }

    public Integer getNoOfWrongAnswers() {
        return noOfWrongAnswers;
    }

    public void setNoOfWrongAnswers(Integer noOfWrongAnswers) {
        this.noOfWrongAnswers = noOfWrongAnswers;
    }

    private Integer noOfWrongAnswers;
}
