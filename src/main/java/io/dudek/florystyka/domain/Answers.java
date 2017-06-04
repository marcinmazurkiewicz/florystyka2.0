package io.dudek.florystyka.domain;

public class Answers {
    private Answer answerA;
    private Answer answerB;
    private Answer answerC;
    private Answer answerD;

    public Answers() {
        answerA = new Answer();
        answerB = new Answer();
        answerC = new Answer();
        answerD = new Answer();

    };

    public Answers(Answer answerA, Answer answerB, Answer answerC, Answer answerD) {
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
    }

    public String getAnswerA() {
        return answerA.getContent();
    }

    public void setAnswerA(String answerA) {
        this.answerA.setValue(AnswerType.A);
        this.answerA.setContent(answerA);
    }

    public String getAnswerB() {
        return answerB.getContent();
    }

    public void setAnswerB(String answerB) {
        this.answerB.setValue(AnswerType.B);
        this.answerB.setContent(answerB);
    }

    public String getAnswerC() {
        return answerC.getContent();
    }

    public void setAnswerC(String answerC) {
        this.answerC.setValue(AnswerType.C);
        this.answerC.setContent(answerC);
    }

    public String getAnswerD() {
        return answerD.getContent();
    }

    public void setAnswerD(String answerD) {
        this.answerD.setValue(AnswerType.D);
        this.answerD.setContent(answerD);
    }

    @Override
    public String toString() {
        return "Answers [answerA=" + answerA.getContent() + ", answerB=" + answerB.getContent() + ", answerC=" + answerC.getContent() + ", answerD=" + answerD.getContent()
                + "]";
    }

}
