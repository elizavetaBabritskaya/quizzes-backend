package it.sevenbits.courses.quizzes.core.model.game;

/**
 * score player
 */
public class PlayerScore {
    private String playerId;
    private String name;
    private int score;

    /**
     * constructor
     *
     * @param playerId - id player
     * @param score    - total score
     * @param name - name player
     */
    public PlayerScore(final String playerId, final String name, final int score) {
        this.playerId = playerId;
        this.name = name;
        this.score = score;
    }

    /**
     * simple constructor
     */

    public PlayerScore() {
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setPlayerId(final String playerId) {
        this.playerId = playerId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(final int score) {
        this.score = score;
    }


}
