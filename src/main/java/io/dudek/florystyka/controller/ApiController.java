package io.dudek.florystyka.controller;

import io.dudek.florystyka.dao.QuestionDAO;
import io.dudek.florystyka.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Random;

@Controller
public class ApiController {

    @Autowired
    QuestionDAO questDAO;

    @RequestMapping(value = "/api", method = RequestMethod.GET)
    public String viewQuestion(Model model) {
        Random random = new Random();
        Question question = questDAO.findQuestionById(random.nextInt(questDAO.getMaxId()) + 1);

//		ObjectMapper mapper = new ObjectMapper();
//		String jsonInString = mapper.writeValueAsString(question);
//		model.addAttribute("question", question);
//		model.addAttribute("solution", new SolutionDTO());
        return "single";
    }


}