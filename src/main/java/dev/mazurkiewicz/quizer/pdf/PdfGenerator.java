package dev.mazurkiewicz.quizer.pdf;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import dev.mazurkiewicz.quizer.config.QuizerConfiguration;
import dev.mazurkiewicz.quizer.exception.PdfRenderException;
import dev.mazurkiewicz.quizer.question.infrastructure.db.QuestionDBEntity;
import dev.mazurkiewicz.quizer.resource.ResourceService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class PdfGenerator {

    ResourceService resourceService;
    QuizerConfiguration quizerConfiguration;

    protected String parseThymeleafTemplate(List<QuestionDBEntity> questions) {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        Context context = new Context();
        context.setVariable("questions", questions);
        boolean isQuestionNumberEven = questions.size() % 2 == 0;
        int answerTableOffset = questions.size() / 2;
        if (!isQuestionNumberEven) {
            answerTableOffset++;
        }
        int answerTableRows = answerTableOffset;
        context.setVariable("answerOffset", answerTableOffset);
        context.setVariable("answersTableRows", answerTableRows);
        return templateEngine.process("templates/pdf_template", context);
    }

    public byte[] generatePdfFromHtml(String html)
            throws PdfRenderException {
        StringBuilder fontPath = new StringBuilder();
        fontPath.append(quizerConfiguration.resourcesFolder());
        if (!quizerConfiguration.resourcesFolder().endsWith(File.separator))
            fontPath.append(File.separator);
        fontPath.append("fonts/arial.ttf");

        Resource fontResource = new FileSystemResource(fontPath.toString());
        ITextRenderer renderer = new ITextRenderer();
        ITextFontResolver resolver = renderer.getFontResolver();
        renderer.getSharedContext().setReplacedElementFactory(
                new ImageReplacedElementFactory(renderer.getSharedContext().getReplacedElementFactory(), resourceService));
        try {
            resolver.addFont(fontResource.getURL().getPath(), BaseFont.IDENTITY_H, true);
        } catch (DocumentException | IOException e) {
            log.error("Failed to set font: {}", e.getMessage());
        }
        renderer.setDocumentFromString(html);
        renderer.layout();

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            renderer.createPDF(out);
            return out.toByteArray();
        } catch (DocumentException | IOException e) {
            log.error(e.getMessage(), e);
            throw new PdfRenderException(String.format("PDF file rendering failed: %s", e.getMessage()));
        }
    }

    public byte[] generateTest(Set<QuestionDBEntity> questions) throws PdfRenderException {
        ArrayList<QuestionDBEntity> questionArrayList = new ArrayList<>(questions);
        String html = parseThymeleafTemplate(questionArrayList);
        return generatePdfFromHtml(html);
    }
}
