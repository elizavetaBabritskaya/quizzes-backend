package it.sevenbits.courses.quizzes.web.service.game;

import it.sevenbits.courses.quizzes.core.model.game.GameStatus;
import it.sevenbits.courses.quizzes.core.model.game.StatusGame;
import it.sevenbits.courses.quizzes.core.model.question.Question;
import it.sevenbits.courses.quizzes.core.model.question.QuestionAnswer;
import it.sevenbits.courses.quizzes.core.model.question.QuestionWithOptions;
import it.sevenbits.courses.quizzes.core.repository.game.GameRepository;
import it.sevenbits.courses.quizzes.core.repository.question.QuestionException;
import it.sevenbits.courses.quizzes.core.repository.question.QuestionRepository;
import it.sevenbits.courses.quizzes.timer.Timer;
import it.sevenbits.courses.quizzes.web.model.answer.AnswerQuestionRequest;
import it.sevenbits.courses.quizzes.web.model.answer.AnswerQuestionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


/**
 * GameService class service
 */
@Service
public class GameService implements IGameService {
    private GameRepository gameRepository;
    private QuestionRepository questionRepository;
    private Timer timer;

    /**
     * GameService constructor
     *
     * @param gameRepository     - repository
     * @param questionRepository - repository
     */

    public GameService(final GameRepository gameRepository, final QuestionRepository questionRepository) {
        this.gameRepository = gameRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public String getRules() {
        return gameRepository.getRules();
    }

    /**
     * create room
     *
     * @param roomId - room id
     */
    @Override
    public void createGame(final String roomId, final String playerId) {
        gameRepository.createGame(roomId, playerId);
    }

    /**
     * gameStart start game
     *
     * @param roomId   - string id
     * @param playerId - player id
     * @return string question id
     * @throws QuestionException - Question
     */
    @Override
    public Question gameStart(final String roomId, final String playerId) throws QuestionException {
        if (playerId.equals(gameRepository.getOwnerId(roomId))) {
            timer = new Timer(gameRepository, roomId);
            timer.start();
            return gameRepository.gameStart(roomId, playerId, questionRepository.getListQuestion());
        } else {
            if (gameRepository.getStatus(roomId, playerId).getStatus() != StatusGame.READY_TO_START) {
                throw new QuestionException(HttpStatus.CONFLICT.value());
            } else if (gameRepository.hasRoom(roomId)) {
                throw new QuestionException(HttpStatus.NOT_FOUND.value());
            }
        }
        throw new QuestionException(HttpStatus.FAILED_DEPENDENCY.value());
    }

    /**
     * getQuestionById
     *
     * @param questId - question Id
     * @param roomId  - room id
     * @return QuestionById - return question
     * @throws QuestionException - exception
     */
    @Override
    public QuestionWithOptions getQuestionById(final String questId, final String roomId, final String playerId) throws QuestionException {
        if (gameRepository.getStatus(roomId, playerId).getStatus() != StatusGame.FINISHED) {
            return questionRepository.getQuestionById(questId, roomId, playerId);
        } else {
            throw new QuestionException(HttpStatus.CONFLICT.value());
        }
    }

    /**
     * getCorrectAnswer
     *
     * @param roomId                - roomId
     * @param questId               - question Id
     * @param answerQuestionRequest - request answer
     * @return AnswerQuestionResponse - return answer
     * @throws QuestionException - exception
     */
    @Override
    public AnswerQuestionResponse getCorrectAnswer(final String roomId, final String questId,
                  final AnswerQuestionRequest answerQuestionRequest, final String playerId) throws QuestionException {
        QuestionAnswer correctAnswer = questionRepository.getCorrectAnswer(roomId, questId, playerId);

        if (correctAnswer.getAnswerId().equals(answerQuestionRequest.getAnswerId())) {
            gameRepository.updateStatus(playerId);
        }
        String nextQuestionId = gameRepository.getNextQuestionById(roomId, playerId);
        if (nextQuestionId == null) {
            gameRepository.finish(roomId);
        }
        return new AnswerQuestionResponse(correctAnswer.getAnswerId(), nextQuestionId,
                gameRepository.getTotalScore(playerId), gameRepository.getQuestionScore(playerId));
    }

    /**
     * get status
     *
     * @param roomId - room id
     * @return game status
     */
    @Override
    public GameStatus getStatus(final String roomId, final String playerId) {
        return gameRepository.getStatus(roomId, playerId);
    }

    /**
     * join game
     * @param roomId - string room id
     * @param playerId - player id
     * @return true if join
     * @throws QuestionException exception join
     */
    public boolean joinGame(final String roomId, final String playerId) throws QuestionException {
        return gameRepository.joinGame(roomId, playerId);
    }


}
