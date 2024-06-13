package it.sevenbits.courses.quizzes.web.model.game;

import java.io.Serializable;

/**
 * start game request
 */
public class StartGameRequest implements Serializable {
    private String playerId;

    /**
     * simple request
     */
    public StartGameRequest() {
    }


    /**
     * constructor
     * @param playerId - player id
     */
    public StartGameRequest(final String playerId) {
        this.playerId = playerId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(final String playerId) {
        this.playerId = playerId;
    }
}
