package dev.mazurkiewicz.florystyka.solution;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v3/solutions")
public class SolutionController {

    private final SolutionService service;

    public SolutionController(SolutionService service) {
        this.service = service;
    }

    @PostMapping("/single")
    public SolutionResponse checkAnswer(@RequestBody SolutionRequest solution) {
        return service.checkSingleAnswer(solution);
    }
//
//    //Generate correct answers and count points for test
//    @RequestMapping(value = "/test", method = RequestMethod.POST)
//    public String checkTest(@ModelAttribute("solutions") TestDTO solutions, Model model) {
//        List<Question> questions = new ArrayList<>();
//        List<SolutionDTO> solutionList = solutions.getAnswers();
//        Integer points = 0;
//        Set<Integer> ids = new HashSet<>();
//        for (int i = 0; i <solutionList.size(); i++) {
//            ids.add(solutionList.get(i).getQuestionID());
//        }
//        questions = repository.findTestQuestions(ids);
//        for (int i = 0; i < solutionList.size(); i++) {
//            if (solutionList.get(i).getAnswer() != null
//                    && solutionList.get(i).getAnswer().equals(questions.get(i).getCorrect())) {
//                points++;
//            }
//        }
//        model.addAttribute("questions", questions);
//        model.addAttribute("solutions", solutions);
//        model.addAttribute("points", points);
//        return "test_result";
//    }

}

