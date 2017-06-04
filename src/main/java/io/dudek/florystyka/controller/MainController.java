package io.dudek.florystyka.controller;

import io.dudek.florystyka.db.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class MainController {


    @Autowired
    private QuestionRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public String viewHome(Model model) {
//        model.addAttribute("how_many", questDAO.getMaxId());
        model.addAttribute("how_many", 100);
        return "home";
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String viewInfo(Model model) {
//        model.addAttribute("how_many", questDAO.getMaxId());
        model.addAttribute("how_many", 100);
        return "info";
    }

}
