package it.sevenbits.courses.quizzes.web.service.game;

import it.sevenbits.courses.quizzes.core.model.game.GameStatus;
import it.sevenbits.courses.quizzes.core.model.question.Question;
import it.sevenbits.courses.quizzes.core.model.question.QuestionWithOptions;
import it.sevenbits.courses.quizzes.core.repository.question.QuestionException;
import it.sevenbits.courses.quizzes.web.model.answer.AnswerQuestionRequest;
import it.sevenbits.courses.quizzes.web.model.answer.AnswerQuestionResponse;

/**
 * interface game service
 */
public interface IGameService {
    /**
     * get rules
     * @return rules
     */
    String getRules();

    /**
     * create room
     *
     * @param roomId - room id
     * @param playerId - player id
     */
    void createGame(String roomId, String playerId);

    /**
     * gameStart start game
     *
     * @param roomId   - string id
     * @param playerId - player id
     * @return string question id
     * @throws QuestionException - Question
     */
    Question gameStart(String roomId, String playerId) throws QuestionException;

    /**
     * getQuestionById
     *
     * @param questId - question Id
     * @param roomId  - room id
     * @param playerId  - player id
     * @return QuestionById - return question
     * @throws QuestionException - exception
     */
    QuestionWithOptions getQuestionById(String questId, String roomId, String playerId) throws QuestionException;

    /**
     * getCorrectAnswer
     *
     * @param roomId                - roomId
     * @param questId               - question Id
     * @param answerQuestionRequest - request answer
     * @param playerId - player id
     * @return AnswerQuestionResponse - return answer
     * @throws QuestionException - exception
     */
    AnswerQuestionResponse getCorrectAnswer(String roomId, String questId,
                                            AnswerQuestionRequest answerQuestionRequest, String playerId) throws QuestionException;

    /**
     * get status
     *
     * @param roomId - room id
     * @param playerId -player id
     * @return game status
     */

    GameStatus getStatus(String roomId, String playerId);

}
