package dev.mazurkiewicz.florystyka.question;

import dev.mazurkiewicz.florystyka.exception.ResourceNotFoundException;
import dev.mazurkiewicz.florystyka.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    private final QuestionRepository repository;
    private final QuestionMapper mapper;

    public QuestionService(QuestionRepository repository, QuestionMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public QuestionResponse getRandomQuestion() {
        Question question = repository.getRandomQuestions(1).get(0);
        return mapper.mapEntityToResponse(question);
    }

    public QuestionResponse getQuestionById(Integer id) {
        return repository
                .findById(id)
                .map(mapper::mapEntityToResponse)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Question with id %d doesn't exist", id)));
    }

    public List<QuestionResponse> getQuestionsToTest() {
        return repository.getRandomQuestions(40)
                .stream()
                .map(mapper::mapEntityToResponse)
                .collect(Collectors.toList());
    }
}
