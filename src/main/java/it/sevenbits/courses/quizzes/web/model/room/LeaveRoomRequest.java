package it.sevenbits.courses.quizzes.web.model.room;

/**
 * Leave room Request
 */
public class LeaveRoomRequest {
    private String playerId;

    /**
     * simple constructor
     */
    public LeaveRoomRequest() {
    }

    public void setPlayerId(final String playerId) {
        this.playerId = playerId;
    }

    public String getPlayerId() {
        return playerId;
    }
}
