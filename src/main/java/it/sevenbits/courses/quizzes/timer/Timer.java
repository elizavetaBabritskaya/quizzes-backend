package it.sevenbits.courses.quizzes.timer;


import it.sevenbits.courses.quizzes.core.repository.game.GameRepository;

/**
 * Timer class
 */
public class Timer extends Thread {
    private GameRepository gameRepository;
    private static final int SECONDS = 30000;
    private String roomId;

    /**
     * Constructor
     *
     * @param gameRepository - gameRepository
     * @param roomId         - roomId
     */

    public Timer(final GameRepository gameRepository, final String roomId) {
        super();
        this.gameRepository = gameRepository;
        this.roomId = roomId;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(gameRepository.getTimeLimit());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        gameRepository.finish(roomId);
    }

    public GameRepository getGameRepository() {
        return gameRepository;
    }


    public String getRoomId() {
        return roomId;
    }
}
