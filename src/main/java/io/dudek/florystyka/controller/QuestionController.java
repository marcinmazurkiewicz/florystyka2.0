package io.dudek.florystyka.controller;

import io.dudek.florystyka.dao.QuestionDAO;
import io.dudek.florystyka.model.Question;
import io.dudek.florystyka.model.SolutionDTO;
import io.dudek.florystyka.model.TestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class QuestionController {

    @Autowired
    private QuestionDAO questDAO;

    //Generate one question
    @RequestMapping(value = "/single", method = RequestMethod.GET)
    public String viewQuestion(Model model) {
        Random random = new Random();
        Question question = questDAO.findQuestionById(random.nextInt(questDAO.getMaxId()) + 1);
        model.addAttribute("question", question);
        model.addAttribute("solution", new SolutionDTO());
        return "single";
    }

    //Get single question by ID - for dev test only!
    @RequestMapping(value = "/single/ph", method = RequestMethod.GET)
    public String viewQuestionByID(@RequestParam("id") Integer id, Model model) {
        Question question = questDAO.findQuestionById(id);
        model.addAttribute("question", question);
        model.addAttribute("solution", new SolutionDTO());
        return "single";
    }

    //Generate 40 unique questions and start test (with count out time)
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String viewTest(Model model) {
        List<Question> questions = new ArrayList<>();
        Random random = new Random();
        int max = questDAO.getMaxId();
        int[] idArray = new int[40];
        int i = 0;
        int tmp;
        boolean isOK = true;
        while (i < 40) {
            isOK = true;
            tmp = random.nextInt(max) + 1;
            for (int j = 0; j < i; j++) {
                if (tmp == idArray[j]) {
                    isOK = false;
                    break;
                }
            }
            if (isOK == true) {
                idArray[i] = tmp;
                i++;
            }
        }
        questions = questDAO.generateQuestionsList(idArray);
        model.addAttribute("questions", questions);
        model.addAttribute("solutions", new TestDTO());
        return "test";
    }



}

