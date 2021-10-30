package dev.mazurkiewicz.florystyka.question.admin.question;

import dev.mazurkiewicz.florystyka.exception.ErrorResponse;
import dev.mazurkiewicz.florystyka.exception.FileTypeException;
import dev.mazurkiewicz.florystyka.exception.validation.ErrorInfo;
import dev.mazurkiewicz.florystyka.exception.validation.ErrorType;
import io.jsonwebtoken.lang.Maps;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("api/v3/admin/questions")
@Secured({"ROLE_ADMIN"})
@RequiredArgsConstructor
public class AdminQuestionController {

    private final AdminQuestionService questionService;

    @GetMapping
    public Page<AdminQuestionResponse> getAllQuestions(@RequestParam @Min(0) Integer page) {
        return questionService.getPagedQuestions(page);
    }

    @GetMapping("/{id}")
    public AdminQuestionResponse getQuestionById(@PathVariable("id") Integer id) {
        return questionService.getQuestionById(id);
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<?> saveNewQuestion(@Valid @ModelAttribute NewQuestionRequest questionRequest) {
        Long questionId;
        try {
            questionId = questionService.saveQuestion(questionRequest);
        } catch (IOException e) {
            String path = ServletUriComponentsBuilder.fromCurrentRequest().build().getPath();
            ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), path, ErrorType.FILE_PROCESSING_ERROR,
                    Maps.of("image", new ErrorInfo(ErrorType.FILE_PROCESSING_ERROR, e.getMessage())).build());
            return ResponseEntity.badRequest().body(error);
        } catch (FileTypeException e) {
            String path = ServletUriComponentsBuilder.fromCurrentRequest().build().getPath();
            ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage(), path, ErrorType.VALIDATION_ERROR,
                    Maps.of("image", new ErrorInfo(ErrorType.TYPE_MISMATCH, e.getMessage())).build());
            return ResponseEntity.badRequest().body(error);
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/v3/admin/questions/{id}")
                .buildAndExpand(questionId)
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
