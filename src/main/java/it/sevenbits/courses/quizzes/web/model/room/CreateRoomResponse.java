package it.sevenbits.courses.quizzes.web.model.room;

import it.sevenbits.courses.quizzes.core.model.player.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Create room response
 */

public class CreateRoomResponse implements Serializable {
    private String roomId;
    private String ownerId;
    private String roomName;
    private List<Player> players;

    /**
     * simple response
     */
    public CreateRoomResponse() {
    }

    /**
     * constructor
     *
     * @param roomId   - room id
     * @param roomName - room name
     * @param players  - list
     * @param ownerId  - owner
     */

    public CreateRoomResponse(final String roomId, final String ownerId, final String roomName, final List<Player> players) {
        this.roomId = roomId;
        this.ownerId = ownerId;
        this.roomName = roomName;
        this.players = players;
    }

    /**
     * constructor
     *
     * @param roomId   - room id
     * @param roomName - room name
     * @param players  - player
     * @param ownerId  - ownerId
     */

    public CreateRoomResponse(final String roomId, final String ownerId, final String roomName, final Player players) {
        this.roomId = roomId;
        this.ownerId = ownerId;
        this.roomName = roomName;
        this.players = new ArrayList<>();
        this.players.add(players);
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setRoomId(final String roomId) {
        this.roomId = roomId;
    }

    public void setRoomName(final String roomName) {
        this.roomName = roomName;
    }

    public void setPlayers(final List<Player> players) {
        this.players = players;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreateRoomResponse that = (CreateRoomResponse) o;
        return Objects.equals(roomId, that.roomId) && Objects.equals(roomName, that.roomName) && Objects.equals(players, that.players);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, roomName, players);
    }
}
