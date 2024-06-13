package it.sevenbits.courses.quizzes.web.controller;

import it.sevenbits.courses.quizzes.core.model.game.GameStatus;
import it.sevenbits.courses.quizzes.core.model.question.Question;
import it.sevenbits.courses.quizzes.core.model.question.QuestionWithOptions;
import it.sevenbits.courses.quizzes.core.repository.question.QuestionException;
import it.sevenbits.courses.quizzes.web.controller.security.UserCredentials;
import it.sevenbits.courses.quizzes.web.model.game.StartGameRequest;
import it.sevenbits.courses.quizzes.web.model.rules.RulesResponse;
import it.sevenbits.courses.quizzes.web.service.game.GameService;
import it.sevenbits.courses.quizzes.web.model.answer.AnswerQuestionRequest;
import it.sevenbits.courses.quizzes.web.model.answer.AnswerQuestionResponse;
import it.sevenbits.courses.quizzes.web.service.room.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameControllerTest {
    private GameService mockGameService;
    private GameController gameController;

    @BeforeEach
    public void setup() {
        mockGameService = mock(GameService.class);
        gameController = new GameController(mockGameService);
    }

    @Test
    public void testGameStart() throws QuestionException {
       Question mockQuestion = mock(Question.class);
        UserCredentials mockUserCredentials = mock(UserCredentials.class);
       when(mockGameService.gameStart("1", "1")).thenReturn(mockQuestion);
       when(mockUserCredentials.getPlayerId()).thenReturn("1");
        ResponseEntity<Question> answer = gameController.gameStart("1",mockUserCredentials);
        verify(mockUserCredentials, times(1)).getPlayerId();
        verify(mockGameService, times(1)).gameStart("1", "1");
        assertEquals(HttpStatus.OK, answer.getStatusCode());
        assertSame(mockQuestion, answer.getBody());
    }

    @Test
    public void testGetQuestionId() throws QuestionException {
        QuestionWithOptions mockQuestionWithOptions = mock(QuestionWithOptions.class);
        UserCredentials mockUserCredentials = mock(UserCredentials.class);
        when(mockGameService.getQuestionById("1", "1", "1")).thenReturn(mockQuestionWithOptions);
        when(mockUserCredentials.getPlayerId()).thenReturn("1");

        ResponseEntity<QuestionWithOptions> answer = gameController.getQuestionId("1", "1", mockUserCredentials);
        verify(mockGameService, times(1)).getQuestionById("1", "1","1" );
        assertEquals(HttpStatus.OK, answer.getStatusCode());
        assertSame(mockQuestionWithOptions, answer.getBody());
    }

    @Test
    public void testAnswerQuestion() throws QuestionException {
        AnswerQuestionResponse mockAnswerQuestionResponse = mock(AnswerQuestionResponse.class);
        AnswerQuestionRequest mockAnswerQuestionRequest = mock(AnswerQuestionRequest.class);
        UserCredentials mockUserCredentials = mock(UserCredentials.class);
        when(mockUserCredentials.getPlayerId()).thenReturn("1");
        when(mockGameService.getCorrectAnswer("1", "1", mockAnswerQuestionRequest, "1")).thenReturn(mockAnswerQuestionResponse);

        ResponseEntity<AnswerQuestionResponse> answer = gameController.answerQuestion("1", "1", mockAnswerQuestionRequest, mockUserCredentials);
        verify(mockGameService, times(1)).getCorrectAnswer("1","1", mockAnswerQuestionRequest,"1");
        assertEquals(HttpStatus.OK, answer.getStatusCode());
        assertSame(mockAnswerQuestionResponse, answer.getBody());
    }

    @Test
    public void testGetRules() {
        RulesResponse rules = new RulesResponse("1");
        when(mockGameService.getRules()).thenReturn("1");

        ResponseEntity<RulesResponse> answer = gameController.getRules();
        assertEquals(HttpStatus.OK, answer.getStatusCode());
        assertEquals(rules, answer.getBody());
    }

    @Test
    public void testGetStatus() {
        GameStatus mockGameTest = mock(GameStatus.class);
        UserCredentials mockUserCredentials = mock(UserCredentials.class);
        when(mockUserCredentials.getPlayerId()).thenReturn("1");
        when(mockGameService.getStatus("1", "1")).thenReturn(mockGameTest);

        ResponseEntity<GameStatus> answer = gameController.getStatus("1", mockUserCredentials);
        verify(mockGameService, times(1)).getStatus("1", "1");
        assertEquals(HttpStatus.OK, answer.getStatusCode());
        assertSame(mockGameTest, answer.getBody());
    }
}