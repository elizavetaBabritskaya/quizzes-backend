package it.sevenbits.courses.quizzes.web.model.room;

import it.sevenbits.courses.quizzes.core.model.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Get Room Response
 */
public class GetRoomResponse {
    private final String roomId;
    private final String ownerId;
    private final String roomName;
    private final List<Player> players;

    /**
     * constructor
     *
     * @param roomId   - room id
     * @param roomName - room name
     * @param playerId - player id
     * @param ownerId - owner id
     */
    public GetRoomResponse(final String roomId, final String ownerId, final String roomName, final String playerId) {
        this.roomId = roomId;
        this.ownerId = ownerId;
        this.roomName = roomName;
        this.players = new ArrayList<>();
        players.add(new Player(playerId));
    }

    /**
     * constructor
     *
     * @param roomId   - room id
     * @param roomName - room name
     * @param players  - list
     * @param ownerId  - owner
     */
    public GetRoomResponse(final String roomId, final String ownerId, final String roomName, final List<Player> players) {
        this.roomId = roomId;
        this.ownerId = ownerId;
        this.roomName = roomName;
        this.players = players;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getRoomName() {
        return roomName;
    }

    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GetRoomResponse that = (GetRoomResponse) o;
        return Objects.equals(roomId, that.roomId) && Objects.equals(roomName, that.roomName) && Objects.equals(players, that.players);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, roomName, players);
    }
}
