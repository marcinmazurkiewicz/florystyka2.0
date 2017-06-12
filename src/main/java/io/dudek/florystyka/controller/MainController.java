package io.dudek.florystyka.controller;

import io.dudek.florystyka.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class MainController {

    private QuestionRepository repository;

    @Autowired
    public MainController(QuestionRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String viewHome(Model model) {
        Integer max = repository.countQuestionByIdIsGreaterThanEqual(0);
        model.addAttribute("how_many", max);
        model.addAttribute("how_many", 100);
        return "home";
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String viewInfo(Model model) {
        Integer max = repository.countQuestionByIdIsGreaterThanEqual(0);
        model.addAttribute("how_many", max);
        model.addAttribute("how_many", 100);
        return "info";
    }

}
