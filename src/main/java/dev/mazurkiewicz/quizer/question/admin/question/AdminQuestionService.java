package dev.mazurkiewicz.quizer.question.admin.question;

import dev.mazurkiewicz.quizer.exception.FileTypeException;
import dev.mazurkiewicz.quizer.exception.ResourceNotFoundException;
import dev.mazurkiewicz.quizer.question.open.db.Question;
import dev.mazurkiewicz.quizer.question.open.domain.QuestionMapper;
import dev.mazurkiewicz.quizer.resource.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AdminQuestionService {

    private final AdminQuestionRepository repository;
    private final QuestionMapper mapper;
    private final ResourceService resourceService;

    public Page<AdminQuestionResponse> getPagedQuestions(int page) {
        return repository.findAll(PageRequest.of(page, 20, Sort.by("id").ascending()))
                .map(AdminQuestionResponse::new);
    }

    public AdminQuestionResponse getQuestionById(Integer questionId) {
        return repository.findById(questionId)
                .map(AdminQuestionResponse::new)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Question with id %d doesn't exist", questionId)));
    }

    public Long saveQuestion(NewQuestionRequest newQuestionRequest) throws IOException, FileTypeException {
        Question question = mapper.mapRequestToEntity(newQuestionRequest);
        if (newQuestionRequest.getImage() != null) {
            question.setImg(resourceService.saveQuestionImage(newQuestionRequest.getImage()));
        }
        return repository.save(question).getId();
    }
}
