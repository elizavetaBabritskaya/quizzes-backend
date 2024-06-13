package it.sevenbits.courses.quizzes.web.controller;

import it.sevenbits.courses.quizzes.core.model.game.GameStatus;
import it.sevenbits.courses.quizzes.core.repository.question.QuestionException;
import it.sevenbits.courses.quizzes.web.controller.security.AuthRoleRequired;
import it.sevenbits.courses.quizzes.web.controller.security.UserCredentials;
import it.sevenbits.courses.quizzes.web.model.answer.AnswerQuestionRequest;
import it.sevenbits.courses.quizzes.web.model.answer.AnswerQuestionResponse;
import it.sevenbits.courses.quizzes.core.model.question.Question;
import it.sevenbits.courses.quizzes.core.model.question.QuestionWithOptions;
import it.sevenbits.courses.quizzes.web.model.rules.RulesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import it.sevenbits.courses.quizzes.web.service.game.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * GameController - controller
 */

@RestController
public class GameController {

    private final GameService gameService;
    private static final int HTTP_NOT_FOUND = 404;
    /**
     * constructer
     * @param gameService - game service
     */
    @Autowired
    public GameController(final GameService gameService) {
        this.gameService = gameService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("rules")
    public ResponseEntity<RulesResponse> getRules() {
        return ResponseEntity.ok().body(new RulesResponse(gameService.getRules()));
    }

    /**
     * get status
     * @param roomId - room id
     * @param userCredentials - user
     * @return response entity
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("rooms/{roomId}/game")
    @AuthRoleRequired("USER")
    public ResponseEntity<GameStatus> getStatus(@PathVariable(value = "roomId") final String roomId,
                                                final UserCredentials userCredentials) {
        return ResponseEntity.ok().body(gameService.getStatus(roomId, userCredentials.getPlayerId()));
    }

    /**
     * getQuestionById
     *
     * @param roomId - room id
     * @param userCredentials - user start game
     * @return QuestionById - id first question
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("rooms/{roomId}/game/start")
    @AuthRoleRequired("USER")
    public ResponseEntity<Question> gameStart(@PathVariable(value = "roomId") final String roomId,
                                              final UserCredentials userCredentials) {
        try {
            return ResponseEntity.ok().body(gameService.gameStart(roomId, userCredentials.getPlayerId()));
        } catch (QuestionException e) {
            return  ResponseEntity.status(HTTP_NOT_FOUND).build();
        }
    }
    /**
     *
     * getQuestionId
     *
     * @param roomId     - room id
     * @param questionId - question id
     * @param userCredentials  - user
     * @return QuestionById - id first question
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("rooms/{roomId}/game/question/{questionId}")
    @AuthRoleRequired("USER")
    public ResponseEntity<QuestionWithOptions> getQuestionId(@PathVariable(value = "roomId") final String roomId,
                  @PathVariable(value = "questionId") final String questionId, final UserCredentials userCredentials) {
        try {
            return ResponseEntity.ok().body(gameService.getQuestionById(questionId, roomId, userCredentials.getPlayerId()));
        } catch (QuestionException e) {
            return ResponseEntity.status(HTTP_NOT_FOUND).build();
        }
    }

    /**
     * answerQuestion
     *
     * @param roomId                - room id
     * @param questionId            - questionId
     * @param answerQuestionRequest - request answer
     * @param userCredentials - user info
     * @return HashMap - answer response
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "rooms/{roomId}/game/question/{questionId}/answer")
    @AuthRoleRequired("USER")
    public ResponseEntity<AnswerQuestionResponse> answerQuestion(@PathVariable(value = "roomId") final String roomId,
                                                                 @PathVariable(value = "questionId") final String questionId,
               @RequestBody final AnswerQuestionRequest answerQuestionRequest, final UserCredentials userCredentials) {
        try {
            return ResponseEntity.ok().body(gameService.getCorrectAnswer(roomId, questionId,
                    answerQuestionRequest, userCredentials.getPlayerId()));
        } catch (QuestionException e) {
            return ResponseEntity.status(HTTP_NOT_FOUND).build();
        }
    }

    /**
     * join Game
     * @param roomId - room id
     * @param userCredentials - user info
     * @return status
     * @throws QuestionException exception join
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "rooms/{roomId}/join")
    @AuthRoleRequired("USER")
    public ResponseEntity<?> joinGame(@PathVariable(value = "roomId") final String roomId,
                                      final UserCredentials userCredentials) throws QuestionException {
        gameService.joinGame(roomId, userCredentials.getPlayerId());
        return ResponseEntity.ok().build();
    }

}
