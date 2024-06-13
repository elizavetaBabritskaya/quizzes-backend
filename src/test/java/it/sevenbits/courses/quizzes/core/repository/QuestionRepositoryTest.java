package it.sevenbits.courses.quizzes.core.repository;

import it.sevenbits.courses.quizzes.core.model.question.Question;
import it.sevenbits.courses.quizzes.core.model.question.QuestionAnswer;
import it.sevenbits.courses.quizzes.core.model.question.QuestionWithOptions;
import it.sevenbits.courses.quizzes.core.repository.question.QuestionException;
import it.sevenbits.courses.quizzes.core.repository.question.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class QuestionRepositoryTest {
    private JdbcOperations mockJdbcOperations;
    private QuestionRepository databaseRepository;

    @BeforeEach
    public void setup() {
        mockJdbcOperations = mock(JdbcOperations.class);
        databaseRepository = new QuestionRepository(mockJdbcOperations);
    }


    @Test
    void getCorrectAnswer() throws QuestionException {
        String roomId = "2";
        String questionId = "1";
        String playerId = "1";
        QuestionAnswer mockQuestionAnswer = mock(QuestionAnswer.class);
        when(mockJdbcOperations.queryForObject(anyString(), any(RowMapper.class), anyString())).thenReturn("1").thenReturn("1").thenReturn(new Question(questionId)).thenReturn(mockQuestionAnswer);

        QuestionAnswer questionAnswer = databaseRepository.getCorrectAnswer(roomId, questionId, playerId);
        verify(mockJdbcOperations, times(1)).queryForObject(eq("SELECT id_room FROM rooms WHERE id_room = ?"), any(RowMapper.class), eq(roomId));
        verify(mockJdbcOperations, times(1)).queryForObject(eq("SELECT id_player FROM rooms_in_player WHERE id_room = ?"), any(RowMapper.class), eq(roomId));
        verify(mockJdbcOperations, times(1)).queryForObject(eq("SELECT id_question FROM player_in_game WHERE id_player = ?"), any(RowMapper.class), eq(playerId));
        verify(mockJdbcOperations, times(1)).queryForObject(eq("SELECT DISTINCT questions.id_correct_answer, answers.text FROM questions INNER JOIN answers ON " +
                "questions.id_question = answers.id_question WHERE questions.id_question = ? " +
                "and questions.id_correct_answer = answers.id_answer"), any(RowMapper.class), eq(questionId));


        assertSame(mockQuestionAnswer, questionAnswer);
    }

}