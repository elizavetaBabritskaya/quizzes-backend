package it.sevenbits.courses.quizzes.core.repository.room;

import it.sevenbits.courses.quizzes.core.model.player.Player;
import it.sevenbits.courses.quizzes.web.controller.security.UserCredentials;
import it.sevenbits.courses.quizzes.web.model.room.RoomsResponse;
import it.sevenbits.courses.quizzes.web.model.room.GetRoomResponse;
import it.sevenbits.courses.quizzes.web.model.room.CreateRoomResponse;
import it.sevenbits.courses.quizzes.web.model.room.CreateRoomRequest;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.List;
import java.util.UUID;

/**
 * room repository
 */

public class RoomRepository implements IRoomRepository {
    private final JdbcOperations jdbcOperations;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int FOUR = 4;

    /**
     * constructor
     *
     * @param jdbcOperations - jdbc operation
     */
    public RoomRepository(final JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    /**
     * get all rooms
     *
     * @return List rooms response
     */
    @Override

    public List<RoomsResponse> getRooms() {
        return jdbcOperations.query("SELECT id_room, room_name FROM rooms", (result, i) -> {
            RoomsResponse curr = new RoomsResponse(result.getString(ONE), result.getString(TWO));
            return curr;
        });
    }

    /**
     * create new room
     *
     * @param createRoomRequest - request room
     * @return response
     */
    @Override
    public CreateRoomResponse createRoom(final CreateRoomRequest createRoomRequest, final UserCredentials userCredentials) {
        String roomId = UUID.randomUUID().toString();
        int rowInRooms = jdbcOperations.update("INSERT INTO rooms (id_room, room_name, owner) VALUES (?, ?, ?)",
                roomId, createRoomRequest.getRoomName(), userCredentials.getPlayerId());
        int rowAddPlayer = jdbcOperations.update("INSERT INTO rooms_in_player (id_room, id_player) VALUES (?, ?)",
                roomId, userCredentials.getPlayerId());

        return new CreateRoomResponse(roomId, userCredentials.getPlayerId(),
                createRoomRequest.getRoomName(), new Player(userCredentials.getPlayerId()));
    }

    /**
     * get room id
     *
     * @param roomId - room id
     * @return get room response
     */

    @Override
    public GetRoomResponse getRoomId(final String roomId) {
        return jdbcOperations.queryForObject("SELECT DISTINCT rooms_in_player.id_room," +
                "rooms_in_player.id_player, rooms.room_name, rooms.owner FROM rooms_in_player INNER JOIN rooms ON " +
                "rooms_in_player.id_room = rooms.id_room WHERE rooms.id_room = ?", (result, i) -> {
            GetRoomResponse curr = new GetRoomResponse(result.getString(ONE), result.getString(FOUR),
                    result.getString(THREE), result.getString(TWO));
            return curr;
        }, roomId);
    }

    /**
     * leave room
     *
     * @param userCredentials - request
     * @return true - leave room, false else
     */
    @Override
    public boolean leaveRoom(final UserCredentials userCredentials) {
        List<String> result = jdbcOperations.query("SELECT id_player FROM rooms_in_player WHERE id_player = ?",
                (res, i) -> res.getString(ONE), userCredentials.getPlayerId());
        jdbcOperations.update("DELETE FROM rooms_in_player WHERE id_player = ?", userCredentials.getPlayerId());
        return result.size() == 1;
    }

}
