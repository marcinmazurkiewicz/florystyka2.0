package dev.mazurkiewicz.florystyka.solution.util;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SolutionUtil {

    public Set<Long> getQuestionsId(Collection<? extends Solution> solutions) {
        return solutions.stream().map(Solution::getQuestionId).collect(Collectors.toSet());
    }
}
