package io.dudek.florystyka.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.dudek.florystyka.repository.QuestionRepository;
import io.dudek.florystyka.domain.Question;
import io.dudek.florystyka.utils.QuestionDrawer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Random;

@Controller
@RequestMapping(value = "/api")
public class ApiController {

    @Autowired
    private QuestionRepository repository;

    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public
    @ResponseBody
    String getTest() {
        Integer max = repository.countQuestionByIdIsGreaterThanEqual(0);
        List<Question> result = repository.findTestQuestions(QuestionDrawer.drawQuestions(max, 40));
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(result);
        return json;
    }

    @RequestMapping(value = "/single", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public
    @ResponseBody
    String getSingle() {
        Random random = new Random();
        Integer max = repository.countQuestionByIdIsGreaterThanEqual(0);
        int id = random.nextInt(max) + 1;
        Question result = repository.findQuestionById(id);
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(result);
        return json;
    }


}