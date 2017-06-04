//package io.dudek.florystyka.controller;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import io.dudek.florystyka.dao.QuestionDAOImpl;
//import io.dudek.florystyka.domain.Question;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Random;
//import java.util.Set;
//
//@Controller
//@RequestMapping(value = "/api")
//public class ApiController {
//
//    @Autowired
//    QuestionDAOImpl questDAO;
//
//    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
//    public
//    @ResponseBody
//    String getTest() {
//        Random random = new Random();
//        int max = questDAO.getMaxId();
//        Set<Integer> ids = new HashSet<>();
//        while(ids.size() != 40) {
//            ids.add(random.nextInt(max) + 1);
//        }
//        List<Question> result = questDAO.generateQuestionsList(ids);
//        Gson gson = new GsonBuilder().create();
//        String json = gson.toJson(result);
//        return json;
//    }
//
//    @RequestMapping(value = "/single", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
//    public
//    @ResponseBody
//    String getSingle() {
//        Random random = new Random();
//        int max = questDAO.getMaxId();
//        int id = random.nextInt(max) + 1;
//        Question result = questDAO.findQuestionById(id);
//        Gson gson = new GsonBuilder().create();
//        String json = gson.toJson(result);
//        return json;
//    }
//
//
//}