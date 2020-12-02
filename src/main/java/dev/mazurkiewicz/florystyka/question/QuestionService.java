package dev.mazurkiewicz.florystyka.question;

import dev.mazurkiewicz.florystyka.exception.ResourceNotFoundException;
import dev.mazurkiewicz.florystyka.repository.QuestionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    private final QuestionRepository repository;
    private final QuestionMapper mapper;

    public QuestionService(QuestionRepository repository, QuestionMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public QuestionResponse getRandomQuestion() {
        int limit = 5;
        long totalQuestions = repository.count();
        long totalPages = (totalQuestions % limit == 0) ? (totalQuestions / limit) : ((totalQuestions / limit) + 1);
        int pageNo = (int) (Math.random() * totalPages);
        PageRequest pageRequest = PageRequest.of(pageNo, limit);
        Page<Question> questions = repository.findAll(pageRequest);
        Question question = questions.getContent().get(0);
        return mapper.mapEntityToResponse(question);
    }

    public QuestionResponse getQuestionById(Integer id) {
        return repository
                .findById(id)
                .map(mapper::mapEntityToResponse)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Question with id %d doesn't exist", id)));
    }
}
