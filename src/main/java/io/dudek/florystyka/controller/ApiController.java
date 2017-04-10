package io.dudek.florystyka.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.dudek.florystyka.dao.QuestionDAO;
import io.dudek.florystyka.model.ApiTestDTO;
import io.dudek.florystyka.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

@Controller
@RequestMapping(value = "/api")
public class ApiController {

    @Autowired
    QuestionDAO questDAO;

    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public
    @ResponseBody
    String getTest() {
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
        ApiTestDTO result = new ApiTestDTO(questDAO.generateQuestionsList(idArray));
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(result);
        return json;
    }

    @RequestMapping(value = "/single", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public
    @ResponseBody
    String getSingle() {
        Random random = new Random();
        int max = questDAO.getMaxId();
        int id = random.nextInt(max) + 1;
        Question result = questDAO.findQuestionById(id);
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(result);
        return json;
    }


}