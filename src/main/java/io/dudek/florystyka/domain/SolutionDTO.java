package io.dudek.florystyka.domain;

public class SolutionDTO {

    private Integer questionID;
    private AnswerType answer;

    public SolutionDTO() {}

    public SolutionDTO(AnswerType answer, Integer questionID) {
        this.answer = answer;
        this.questionID = questionID;
    }
    public AnswerType getAnswer() {
        return answer;
    }
    public void setAnswer(AnswerType answer) {
        this.answer = answer;
    }
    public Integer getQuestionID() {
        return questionID;
    }
    public void setQuestionID(Integer questionID) {
        this.questionID = questionID;
    }

    @Override
    public String toString() {
        return "SolutionDTO [answer=" + answer + ", questionID=" + questionID + "]";
    }
}

