package it.sevenbits.courses.quizzes.web.service;

import it.sevenbits.courses.quizzes.core.model.player.Player;
import it.sevenbits.courses.quizzes.core.repository.room.RoomRepository;
import it.sevenbits.courses.quizzes.web.controller.security.UserCredentials;
import it.sevenbits.courses.quizzes.web.model.room.*;
import it.sevenbits.courses.quizzes.web.service.room.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoomServiceTest {

    private RoomService roomService;
    private RoomRepository mockRoomRepository;

    @BeforeEach
    public void setup() {
        mockRoomRepository = mock(RoomRepository.class);
        roomService = new RoomService(mockRoomRepository);
    }

    @Test
    void getRoomsTest() {
        List<RoomsResponse> getRoomResponse = mock(List.class);
        GetRoomsResponse mockGetRoomResponse = mock(GetRoomsResponse.class);

        when(mockGetRoomResponse.getRoomsResponse()).thenReturn(getRoomResponse);

        when(mockRoomRepository.getRooms()).thenReturn(getRoomResponse);


        GetRoomsResponse answer = roomService.getRooms();

        verify(mockRoomRepository, times(1)).getRooms();
        assertSame(new GetRoomsResponse(getRoomResponse).getRoomsResponse(), answer.getRoomsResponse());
    }

    @Test
    void createRoomTest() {
        CreateRoomResponse mockCreateRoomResponse = mock(CreateRoomResponse.class);
        CreateRoomRequest mockCreateRoomRequest = mock(CreateRoomRequest.class);
        UserCredentials mockUserCredentials = mock(UserCredentials.class);
        when(mockRoomRepository.createRoom(mockCreateRoomRequest, mockUserCredentials)).thenReturn(mockCreateRoomResponse);
        CreateRoomResponse answer = roomService.createRoom(mockCreateRoomRequest, mockUserCredentials);
        verify(mockRoomRepository,times(1)).createRoom(mockCreateRoomRequest, mockUserCredentials);
        assertSame(mockCreateRoomResponse, answer);
    }

    @Test
    void getRoomIdTest() {
        GetRoomResponse mockGetRoomResponse = mock(GetRoomResponse.class);
        List<Player> mockList = mock(List.class);
        when(mockRoomRepository.getRoomId("1")).thenReturn(mockGetRoomResponse);
        when(mockGetRoomResponse.getRoomId()).thenReturn("1");
        when(mockGetRoomResponse.getRoomName()).thenReturn("1");
        when(mockGetRoomResponse.getPlayers()).thenReturn(mockList);
        when(mockGetRoomResponse.getOwnerId()).thenReturn("1");

        GetRoomResponse answer = roomService.getRoomId("1");
        verify(mockRoomRepository,times(4)).getRoomId("1");
        assertEquals(new GetRoomResponse("1","1","1",mockList), answer);
    }
}