package dev.mazurkiewicz.quizer.solution;

import dev.mazurkiewicz.quizer.config.QuizerConfiguration;
import dev.mazurkiewicz.quizer.exception.ResourceNotFoundException;
import dev.mazurkiewicz.quizer.questions.AnswerType;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SolutionService {

    private final AnswerRepository repository;
    private final QuizerConfiguration quizerConfiguration;

    public AnswerResponse checkSingle(AnswerRequest answerRequest) {
        Map<Integer, AnswerType> correctAnswer = repository
                .findById(answerRequest.questionId())
                .map(ans -> Map.of(ans.getQuestionId(), ans.getCorrect()))
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Question with id %d doesn't exist", answerRequest.questionId())));

        return prepareResponse(answerRequest, correctAnswer);
    }

    public AnswerResponse checkTest(List<AnswerRequest> answerRequests) {
        Set<Integer> questionIds = getQuestionsIdSet(answerRequests);
        if (questionIds.size() != quizerConfiguration.examQuestionsNumber()) {
            throw new IncorrectResultSizeDataAccessException("Some answers are missing", questionIds.size(), quizerConfiguration.examTimeInSeconds());
        }

        Map<Integer, AnswerType> correctAnswers = getCorrectAnswersForQuestions(questionIds);
        if (!correctAnswers.keySet().containsAll(questionIds)) {
            String missingIds = getMissingIds(answerRequests, correctAnswers);
            throw new ResourceNotFoundException(String.format("Question(s) with id: %s not found", missingIds));
        }
        return prepareResponse(answerRequests, correctAnswers);
    }

    private String getMissingIds(List<AnswerRequest> answerRequests, Map<Integer, AnswerType> correctAnswers) {
        return answerRequests
                .stream()
                .map(AnswerRequest::questionId)
                .filter(id -> !correctAnswers.containsKey(id))
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
    }

    private int countPoints(List<AnswerRequest> answerRequests, Map<Integer, AnswerType> correctAnswers) {
        return answerRequests
                .stream()
                .map(ans -> getScore(ans.selectedAnswer(), correctAnswers.get(ans.questionId())))
                .reduce(0, Integer::sum);
    }

    private Integer getScore(AnswerType selectedAnswer, AnswerType correctAnswer) {
        return correctAnswer == selectedAnswer ? 1 : 0;
    }

    private Map<Integer, AnswerType> getCorrectAnswersForQuestions(Set<Integer> questionIds) {
        return repository.findAllById(questionIds)
                .stream()
                .collect(Collectors.toMap(CorrectAnswerEntity::getQuestionId, CorrectAnswerEntity::getCorrect));
    }

    private Set<Integer> getQuestionsIdSet(Collection<? extends AnswerRequest> solutions) {
        return solutions.stream()
                .map(AnswerRequest::questionId).collect(Collectors.toSet());
    }

    private AnswerResponse prepareResponse(List<AnswerRequest> answerRequests, Map<Integer, AnswerType> correctAnswers) {
        int points = countPoints(answerRequests, correctAnswers);
        return new AnswerResponse(points, correctAnswers.size(), correctAnswers);
    }

    private AnswerResponse prepareResponse(AnswerRequest answerRequest, Map<Integer, AnswerType> correctAnswer) {
        int score = getScore(answerRequest.selectedAnswer(), correctAnswer.get(answerRequest.questionId()));
        return new AnswerResponse(score, 1, correctAnswer);
    }
}
