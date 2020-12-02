package dev.mazurkiewicz.florystyka.question;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v3/questions")
public class QuestionController {

    private final QuestionService service;

    public QuestionController(QuestionService service) {
        this.service = service;
    }

    @GetMapping("/random")
    public QuestionResponse getRandomQuestion() {
        return service.getRandomQuestion();
    }

//    @GetMapping("/{id}")
//    public String viewQuestionByID(@PathVariable("id") Integer id) {
//        Question dev.mazurkiewicz.florystyka.question = repository.findQuestionById(id);
//        return "single";
//    }
//
//    //Generate 40 unique questions and start test (with count down time)
//    @RequestMapping(value = "/test", method = RequestMethod.GET)
//    public String viewTest(Model model) {
//        int max = repository.countQuestionByIdIsGreaterThanEqual(0);
//        List<Question> questions = repository.findTestQuestions(QuestionDrawer.drawQuestions(max, 40));
//        model.addAttribute("questions", questions);
//        model.addAttribute("solutions", new TestDTO());
//        return "test";
//    }


}

