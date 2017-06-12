package io.dudek.florystyka.controller;

import io.dudek.florystyka.repository.QuestionRepository;
import io.dudek.florystyka.domain.Question;
import io.dudek.florystyka.domain.SolutionDTO;
import io.dudek.florystyka.domain.TestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class SolutionController {

    private QuestionRepository repository;

    @Autowired
    public SolutionController(QuestionRepository repository) {
        this.repository = repository;
    }

    //Send question from single question
    @RequestMapping(value = "/single", method = RequestMethod.POST)
    public String checkAnswer(@ModelAttribute("solution") SolutionDTO solution, Model model) {
        Question question = repository.findQuestionById(solution.getQuestionID());
        model.addAttribute("question", question);
        return "single_result";
    }

    //Generate correct answers and count points for test
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String checkTest(@ModelAttribute("solutions") TestDTO solutions, Model model) {
        List<Question> questions = new ArrayList<>();
        List<SolutionDTO> solutionList = solutions.getAnswers();
        Integer points = 0;
        Set<Integer> ids = new HashSet<>();
        for (int i = 0; i <solutionList.size(); i++) {
            ids.add(solutionList.get(i).getQuestionID());
        }
        questions = repository.findTestQuestions(ids);
        for (int i = 0; i < solutionList.size(); i++) {
            if (solutionList.get(i).getAnswer() != null
                    && solutionList.get(i).getAnswer().equals(questions.get(i).getCorrect())) {
                points++;
            }
        }
        model.addAttribute("questions", questions);
        model.addAttribute("solutions", solutions);
        model.addAttribute("points", points);
        return "test_result";
    }

}

