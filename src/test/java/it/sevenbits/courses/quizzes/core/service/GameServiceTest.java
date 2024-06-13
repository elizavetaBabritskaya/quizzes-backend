package it.sevenbits.courses.quizzes.core.service;

import it.sevenbits.courses.quizzes.core.model.game.GameStatus;
import it.sevenbits.courses.quizzes.core.model.game.StatusGame;
import it.sevenbits.courses.quizzes.core.model.question.Question;
import it.sevenbits.courses.quizzes.core.model.question.QuestionAnswer;
import it.sevenbits.courses.quizzes.core.model.question.QuestionWithOptions;
import it.sevenbits.courses.quizzes.core.repository.game.GameRepository;
import it.sevenbits.courses.quizzes.core.repository.question.QuestionException;
import it.sevenbits.courses.quizzes.core.repository.question.QuestionRepository;
import it.sevenbits.courses.quizzes.web.model.answer.AnswerQuestionRequest;
import it.sevenbits.courses.quizzes.web.model.answer.AnswerQuestionResponse;
import it.sevenbits.courses.quizzes.web.service.game.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameServiceTest {
    private GameService gameService;
    private GameRepository mockGameRepository;
    private QuestionRepository mockQuestionRepository;

    @BeforeEach
    public void setup() {
        mockGameRepository = mock(GameRepository.class);
        mockQuestionRepository = mock(QuestionRepository.class);
        gameService = new GameService(mockGameRepository, mockQuestionRepository);
    }

    @Test
    public void testGameStart() throws QuestionException {
        Question mockQuestion = mock(Question.class);
        List<String> list = mock(ArrayList.class);
        when(mockQuestionRepository.getListQuestion()).thenReturn(list);
        when(mockGameRepository.gameStart("1", "1", list)).thenReturn(mockQuestion);
        when(mockGameRepository.getOwnerId("1")).thenReturn("1");

        Question answer = gameService.gameStart("1", "1");
        verify(mockQuestionRepository,times(1)).getListQuestion();
        verify(mockGameRepository, times(1)).gameStart("1", "1", list);
        assertSame(mockQuestion, answer);
    }

    @Test
    void testGetQuestionById() throws QuestionException {
        QuestionWithOptions mockQuestionWithOptions = mock(QuestionWithOptions.class);
        GameStatus mockGameStatus = mock(GameStatus.class);
        when(mockQuestionRepository.getQuestionById("1", "1", "1")).thenReturn(mockQuestionWithOptions);
        when(mockGameRepository.getStatus("1","1")).thenReturn(mockGameStatus);
        when(mockGameStatus.getStatus()).thenReturn(StatusGame.READY_TO_START);

        QuestionWithOptions answer = gameService.getQuestionById("1", "1", "1");
        verify(mockQuestionRepository, times(1)).getQuestionById("1", "1", "1");
        assertSame(mockQuestionWithOptions, answer);
    }

    @Test
    void TestGetCorrectAnswer() throws QuestionException {
        AnswerQuestionRequest mockQuestionRequest = mock(AnswerQuestionRequest.class);
        QuestionAnswer mockQuestionAnswer = mock(QuestionAnswer.class);

        when(mockQuestionAnswer.getAnswerId()).thenReturn("2");
        when(mockGameRepository.getTotalScore("1")).thenReturn(1);
        when(mockGameRepository.getQuestionScore("1")).thenReturn(2);
        when(mockQuestionRequest.getAnswerId()).thenReturn( "2");

        when(mockGameRepository.getNextQuestionById("1", "1")).thenReturn("3");
        when(mockQuestionRepository.getCorrectAnswer("1", "1", "1")).thenReturn(mockQuestionAnswer);

        AnswerQuestionResponse answer = gameService.getCorrectAnswer("1", "1", mockQuestionRequest, "1");

        verify(mockQuestionRepository, times(1)).getCorrectAnswer("1", "1", "1");

        assertEquals(new AnswerQuestionResponse("2", "3", 1, 2), answer);
    }

    @Test
    void testGetStatus() {
        GameStatus mockGameStatus = mock(GameStatus.class);
        when(mockGameRepository.getStatus("1", "1")).thenReturn(mockGameStatus);

        GameStatus answer = gameService.getStatus("1","1");
        verify(mockGameRepository, times(1)).getStatus("1", "1");
        assertSame(mockGameStatus, answer);
    }
}