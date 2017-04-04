package io.dudek.florystyka.model;

import java.util.ArrayList;
import java.util.List;

public class TestDTO {

    private List<SolutionDTO> answers;


    public TestDTO() {
        this.answers = new ArrayList<>();
        for(int i=0; i < 40; i++) {
            answers.add(new SolutionDTO());
        }
    }

    public TestDTO(List<SolutionDTO> answers) {
        this.answers = answers;
    }

    public List<SolutionDTO> getAnswers() {
        return answers;
    }


    public void setAnswers(List<SolutionDTO> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "TestDTO [answers=" + answers + "]";
    }


}