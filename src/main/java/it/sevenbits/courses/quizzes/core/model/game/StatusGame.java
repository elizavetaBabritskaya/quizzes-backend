package it.sevenbits.courses.quizzes.core.model.game;

/**
 * enum status game
 */
public enum StatusGame {
    /**
     * game ready start, in progress, finish game
     */
    READY_TO_START("READY_TO_START"), IN_PROGRESS("IN_PROGRESS"), FINISHED("FINISHED");
    private final String status;

    /**
     * constructor
     * @param string - status game
     */

    StatusGame(final String string) {
        status = string;
    }

    public String getStatus() {
        return status;
    }

    /**
     * set status
     * @param status - status game
     * @return status game
     */

    public static StatusGame fromString(final String status) {
        for (StatusGame b : StatusGame.values()) {
            if (b.status.equalsIgnoreCase(status)) {
                return b;
            }
        }
        return null;
    }
}
