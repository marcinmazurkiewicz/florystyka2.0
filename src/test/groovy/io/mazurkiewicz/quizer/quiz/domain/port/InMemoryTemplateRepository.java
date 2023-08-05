package io.mazurkiewicz.quizer.quiz.domain.port;

import io.mazurkiewicz.quizer.quiz.domain.model.QuizTemplate;
import io.mazurkiewicz.quizer.quiz.domain.model.TemplateId;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InMemoryTemplateRepository implements QuizTemplateRepository {

    private final Map<UUID, QuizTemplate> entities = new HashMap<>();
    @Override
    public void saveTemplate(@NotNull QuizTemplate template) {
        entities.put(template.getTemplateId().getId(), template);
    }

    @NotNull
    @Override
    public QuizTemplate findTemplateById(@NotNull TemplateId templateId) {
        return entities.get(templateId.getId());
    }
}
