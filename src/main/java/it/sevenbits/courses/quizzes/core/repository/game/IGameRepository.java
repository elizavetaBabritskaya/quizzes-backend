package it.sevenbits.courses.quizzes.core.repository.game;

import it.sevenbits.courses.quizzes.core.model.game.GameStatus;
import it.sevenbits.courses.quizzes.core.model.question.Question;
import it.sevenbits.courses.quizzes.core.repository.question.QuestionException;

import java.util.List;

/**
 * interface repository
 */
public interface IGameRepository {
    /**
     * get rules
     * @return rules
     */
    String getRules();

    /**
     * create game
     * @param playerId - player id
     * @param roomId - id room
     */
    void createGame(String roomId, String playerId);

    /**
     * gameStart
     *
     * @param roomId      - game id
     * @param playerId    - player id
     * @param questionsId - list question id
     * @return first question
     * @throws QuestionException - Question exception
     */
    Question gameStart(String roomId, String playerId, List<String> questionsId) throws QuestionException;

    /**
     * get status
     *
     * @param roomId - id room
     * @param playerId - player id
     * @return Game Status
     */
    GameStatus getStatus(String roomId, String playerId);

    /**
     * update score
     *
     * @param roomId - room id
     */
    void updateStatus(String roomId);

    /**
     * get total score
     *
     * @param playerId - player id
     * @return total score
     */
    int getTotalScore(String playerId);

    /**
     * Get question score
     *
     * @param playerId - player id
     * @return - question score
     */
    int getQuestionScore(String playerId);

    /**
     * get next question by id
     *
     * @param roomId - room id
     * @param playerId - player id
     * @return next question or null
     */
    String getNextQuestionById(String roomId, String playerId);

    /**
     * finish
     *
     * @param roomId - room id
     */

    void finish(String roomId);

    /**
     * count questions
     * @return count question
     */
    int getCountQuestions();
}
