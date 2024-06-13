package it.sevenbits.courses.quizzes.core.repository.question;

import it.sevenbits.courses.quizzes.core.model.question.QuestionAnswer;
import it.sevenbits.courses.quizzes.core.model.question.QuestionWithOptions;

import java.util.List;

/**
 * interfase question repository
 */
public interface IQuestionRepository {
    /**
     * get list question
     * @return list
     */
    List<String> getListQuestion();

    /**
     * Get question by id
     *
     * @param questionId - question id
     * @param roomId     - room id
     * @param playerId - player id
     * @return - questionWithOptions
     * @throws QuestionException - QuestionException
     */
    QuestionWithOptions getQuestionById(String questionId, String roomId, String playerId) throws QuestionException;

    /**
     * Get correct answer by question id
     *
     * @param questionId - question id
     * @param roomId     - room id
     * @param playerId - player id
     * @return correct answer
     * @throws QuestionException - question exception
     */
    QuestionAnswer getCorrectAnswer(String roomId, String questionId, String playerId) throws QuestionException;

    /**
     * select all
     * @return list
     */
    List<QuestionAnswer> selectAll();
}
