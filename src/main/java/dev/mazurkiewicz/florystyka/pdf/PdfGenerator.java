package dev.mazurkiewicz.florystyka.pdf;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import dev.mazurkiewicz.florystyka.exception.PdfRenderException;
import dev.mazurkiewicz.florystyka.question.QuestionResponse;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Component
public class PdfGenerator {

    private String parseThymeleafTemplate(List<QuestionResponse> questions) {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        Context context = new Context();
        context.setVariable("questions", questions);
        return templateEngine.process("templates/pdf_template", context);
    }

    public byte[] generatePdfFromHtml(String html) {

        Resource fontResource = new ClassPathResource("fonts/arial.ttf");
        ITextRenderer renderer = new ITextRenderer();
        ITextFontResolver resolver = renderer.getFontResolver();
        renderer.getSharedContext().setReplacedElementFactory(
                new ImageReplacedElementFactory(renderer.getSharedContext().getReplacedElementFactory()));
        try {
            resolver.addFont(fontResource.getURL().getPath(), BaseFont.IDENTITY_H, true);
        } catch (DocumentException | IOException e) {
            System.err.println("Failed to set font: " + e.getMessage());
        }
        renderer.setDocumentFromString(html);
        renderer.layout();

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            renderer.createPDF(out);
            return out.toByteArray();
        } catch (DocumentException | IOException e) {
            throw new PdfRenderException(String.format("PDF file rendering failed: %s", e.getMessage()));
        }
    }

    public byte[] generateTest(List<QuestionResponse> questions) {
        String html = parseThymeleafTemplate(questions);
        return generatePdfFromHtml(html);
    }
}
