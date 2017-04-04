package io.dudek.florystyka.controller;

import io.dudek.florystyka.dao.QuestionDAO;
import io.dudek.florystyka.model.Question;
import io.dudek.florystyka.model.SolutionDTO;
import io.dudek.florystyka.model.TestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SolutionController {

    @Autowired
    private QuestionDAO questDAO;

    //Send question from single question
    @RequestMapping(value = "/single", method = RequestMethod.POST)
    public String checkAnswer(@ModelAttribute("solution") SolutionDTO solution, Model model) {
        Question question = questDAO.findQuestionById(solution.getQuestionID());
        model.addAttribute("question", question);
        return "single_result";
    }

    //Generate correct answers and count points for test
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String checkTest(@ModelAttribute("solutions") TestDTO solutions, Model model) {
        List<Question> questions = new ArrayList<>();
        List<SolutionDTO> solutionList = solutions.getAnswers();
        Integer points = 0;
        int[] qid = new int[40];
        for (int i = 0; i < qid.length; i++) {
            qid[i] = solutionList.get(i).getQuestionID();
        }
        questions = questDAO.generateQuestionsList(qid);
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

