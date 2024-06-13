package it.sevenbits.courses.quizzes.core.repository;

import it.sevenbits.courses.quizzes.core.model.question.Question;
import it.sevenbits.courses.quizzes.core.repository.game.GameRepository;
import it.sevenbits.courses.quizzes.core.repository.question.QuestionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class GameRepositoryTest {
    private JdbcOperations mockJdbcOperations;
    private GameRepository databaseRepository;

    @BeforeEach
    public void setup() {
        mockJdbcOperations = mock(JdbcOperations.class);
        databaseRepository = new GameRepository(mockJdbcOperations);
    }

    @Test
    void createGameTest() {

//        databaseRepository.createGame("1", "1");
//        when(mockJdbcOperations.update(anyString(),any(RowMapper.class), anyString(), anyString(), anyString())).thenReturn(anyInt());
//        when(mockJdbcOperations.update(anyString(),any(RowMapper.class), anyString(), anyString())).thenReturn(anyInt());
//        verify(mockJdbcOperations, times(1)).update(eq("INSERT INTO games(id_game, id_room, status, question_count, start_date_time, time_limit)" +
//                " VALUES (?, ?, 'READY_TO_START', 4, ?, ?)"), any(RowMapper.class), any(), eq("1"), any(), 12000);
//        verify(mockJdbcOperations, times(1)).update(eq("INSERT INTO player_in_game(id_game, id_player, id_question, question_number, " +
//                "total_score) VALUES(?, ? ,'', 0, 0), gameId, playerId) "), any(RowMapper.class), any(), eq("1"));
    }



}