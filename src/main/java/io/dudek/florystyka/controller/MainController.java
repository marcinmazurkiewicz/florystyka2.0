package io.dudek.florystyka.controller;

import io.dudek.florystyka.dao.QuestionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {


    @Autowired
    private QuestionDAO questDAO;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String viewHome(Model model) {
        model.addAttribute("how_many", questDAO.getMaxId());
        return "home";
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String viewInfo(Model model) {
        model.addAttribute("how_many", questDAO.getMaxId());
        return "info";
    }

}
