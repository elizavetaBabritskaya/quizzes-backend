package it.sevenbits.courses.quizzes.core.repository;

import it.sevenbits.courses.quizzes.core.model.player.Player;
import it.sevenbits.courses.quizzes.core.repository.room.RoomRepository;
import it.sevenbits.courses.quizzes.web.controller.security.UserCredentials;
import it.sevenbits.courses.quizzes.web.model.room.CreateRoomRequest;
import it.sevenbits.courses.quizzes.web.model.room.CreateRoomResponse;
import it.sevenbits.courses.quizzes.web.model.room.GetRoomResponse;
import it.sevenbits.courses.quizzes.web.model.room.RoomsResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.AdditionalMatchers.aryEq;
import static org.mockito.Mockito.*;


class RoomRepositoryTest {
    private JdbcOperations mockJdbcOperations;
    private RoomRepository databaseRoomRepository;

    @BeforeEach
    public void setup() {
        mockJdbcOperations = mock(JdbcOperations.class);
        databaseRoomRepository = new RoomRepository(mockJdbcOperations);
    }


    @Test
    void getRoomsTest() {
        List<RoomsResponse> mockListRooms = mock(List.class);

        when(mockJdbcOperations.query(anyString(), any(RowMapper.class))).thenReturn(mockListRooms);

        List<RoomsResponse> actualList = databaseRoomRepository.getRooms();
        verify(mockJdbcOperations, times(1)).query(
                eq("SELECT id_room, room_name FROM rooms"),
                any(RowMapper.class)
        );

        assertSame(mockListRooms, actualList);

    }

    @Test
    void createRoomTest() {
        CreateRoomResponse mockResponse = mock(CreateRoomResponse.class);
        CreateRoomRequest mockRequest = mock(CreateRoomRequest.class);
        UserCredentials mockUserCredentials = mock(UserCredentials.class);
        String roomName =  "name";
        String roomId = "8d4fdd17-41ab-46a5-8e77-aec1b151b19d";
        String playerId = "8d4fdd17-41ab-46a5-8e77-aec1b151b19d";

        when(mockRequest.getRoomName()).thenReturn(roomName).thenReturn(roomName);
        when(mockUserCredentials.getPlayerId()).thenReturn(playerId).thenReturn(playerId);
        CreateRoomResponse answer = databaseRoomRepository.createRoom(mockRequest, mockUserCredentials);

        verify(mockJdbcOperations, times(1)).update(eq("INSERT INTO rooms (id_room, room_name, owner) VALUES (?, ?, ?)") ,any(), eq(roomName), eq(playerId));
        verify(mockJdbcOperations, times(1)).update(eq("INSERT INTO rooms_in_player (id_room, id_player) VALUES (?, ?)") ,any(), eq(playerId));
        assertEquals(new CreateRoomResponse(answer.getRoomId(),playerId,roomName , new Player(playerId)) ,answer);
    }

    @Test
    void getRoomIdTest() {
        String roomId = "8d4fdd17-41ab-46a5-8e77-aec1b151b19d";
        GetRoomResponse mockResponse = mock(GetRoomResponse.class);

        when(mockJdbcOperations.queryForObject(anyString(), any(RowMapper.class), anyString())).thenReturn(mockResponse);

        GetRoomResponse actualResponse = databaseRoomRepository.getRoomId(roomId);

        verify(mockJdbcOperations, times(1)).queryForObject(
                eq("SELECT DISTINCT rooms_in_player.id_room," +
                        "rooms_in_player.id_player, rooms.room_name, rooms.owner FROM rooms_in_player INNER JOIN rooms ON " +
                        "rooms_in_player.id_room = rooms.id_room WHERE rooms.id_room = ?"),
                any(RowMapper.class),
                eq(roomId)
        );

        assertEquals(mockResponse, actualResponse);

    }
}