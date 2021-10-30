package dev.mazurkiewicz.florystyka.question.admin.question;

import dev.mazurkiewicz.florystyka.answer.AnswerType;
import dev.mazurkiewicz.florystyka.utils.validation.FileFormat;
import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Value
public class NewQuestionRequest {

    @NotEmpty
    String content;
    @NotEmpty
    String answerA;
    @NotEmpty
    String answerB;
    @NotEmpty
    String answerC;
    @NotEmpty
    String answerD;
    @NotNull
    AnswerType correct;
    @FileFormat(contentTypes = {"image/png", "image/jpeg"})
    MultipartFile image;
    @Min(1)
    @Max(12)
    int month;
    @Min(2000)
    int year;

}
