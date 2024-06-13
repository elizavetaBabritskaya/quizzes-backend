package it.sevenbits.courses.quizzes.web.controller;

import it.sevenbits.courses.quizzes.web.controller.security.AuthRoleRequired;
import it.sevenbits.courses.quizzes.web.controller.security.UserCredentials;
import it.sevenbits.courses.quizzes.web.model.room.RoomsResponse;
import it.sevenbits.courses.quizzes.web.model.room.CreateRoomResponse;
import it.sevenbits.courses.quizzes.web.model.room.CreateRoomRequest;
import it.sevenbits.courses.quizzes.web.model.room.GetRoomResponse;
import it.sevenbits.courses.quizzes.web.service.game.GameService;
import it.sevenbits.courses.quizzes.web.service.room.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * room controller
 */
@RestController
public class RoomController {
    private final RoomService roomService;
    private final GameService gameService;

    /**
     * constructor
     * @param roomService - room service
     * @param gameService - game service
     */
    public RoomController(final RoomService roomService, final GameService gameService) {
        this.roomService = roomService;
        this.gameService = gameService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("rooms")
    @AuthRoleRequired("USER")
    public ResponseEntity<List<RoomsResponse>> getRooms() {
        return ResponseEntity.ok().body(roomService.getRooms().getRoomsResponse());
    }

    /**
     * create room
     * @param createRoomRequest - request
     * @param userCredentials - user credentials
     * @return responce entity
     */

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("rooms")
    @AuthRoleRequired("USER")
    public ResponseEntity<CreateRoomResponse> createRoom(@RequestBody final CreateRoomRequest createRoomRequest,
                                                         final UserCredentials userCredentials) {
        CreateRoomResponse createRoomResponse = roomService.createRoom(createRoomRequest, userCredentials);
        gameService.createGame(createRoomResponse.getRoomId(), userCredentials.getPlayerId());
        return ResponseEntity.ok().body(createRoomResponse);
    }

    /**
     * get room id
     * @param roomId - room id
     * @return response entitu
     */

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("rooms/{roomId}")
    @AuthRoleRequired("USER")
    public ResponseEntity<GetRoomResponse> getRoomId(@PathVariable(value = "roomId") final String roomId) {
        return ResponseEntity.ok().body(roomService.getRoomId(roomId));
    }

    /**
     * leave room
     * @param userCredentials - request
     * @return response entity
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("rooms/leave")
    @AuthRoleRequired("USER")
    public ResponseEntity<?> leaveRoom(final UserCredentials userCredentials) {
        return ResponseEntity.status(roomService.leaveRoom(userCredentials)).build();
    }
}
