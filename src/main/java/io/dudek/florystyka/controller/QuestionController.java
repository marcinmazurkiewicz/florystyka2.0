package io.dudek.florystyka.controller;

import io.dudek.florystyka.db.QuestionRepository;
import io.dudek.florystyka.domain.Question;
import io.dudek.florystyka.domain.SolutionDTO;
import io.dudek.florystyka.domain.TestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Controller
public class QuestionController {

    @Autowired
    private QuestionRepository repository;

    //Generate one question
    @RequestMapping(value = "/single", method = RequestMethod.GET)
    public String viewQuestion(Model model) {
        Random random = new Random();
        Integer count = repository.countQuestionByIdIsGreaterThanEqual(0);
        Question question = repository.findQuestionById(random.nextInt(count + 1));
//        Question question = repository.findQuestionById(random.nextInt(repository.getMaxId()) + 1);
        model.addAttribute("question", question);
        model.addAttribute("solution", new SolutionDTO());
        return "single";
    }

//    //Get single question by ID - for test only!
//    @RequestMapping(value = "/single/ph", method = RequestMethod.GET)
//    public String viewQuestionByID(@RequestParam("id") Integer id, Model model) {
//        Question question = repository.findQuestionById(id);
//        model.addAttribute("question", question);
//        model.addAttribute("solution", new SolutionDTO());
//        return "single";
//    }
//
    //Generate 40 unique questions and start test (with count down time)
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String viewTest(Model model) {
        List<Question> questions;
        Random random = new Random();
        Set<Integer> ids = new HashSet<>();
        int max = repository.countQuestionByIdIsGreaterThanEqual(0);
        while(ids.size() != 40) {
            ids.add(random.nextInt(max) + 1);
        }
        questions = repository.findTestQuestions(ids);
        model.addAttribute("questions", questions);
        model.addAttribute("solutions", new TestDTO());
        return "test";
    }



}

