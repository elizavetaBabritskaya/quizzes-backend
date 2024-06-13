package it.sevenbits.courses.quizzes.core.model.game;

import java.util.ArrayList;
import java.util.List;

/**
 * Game status class
 */
public class GameStatus {
    private StatusGame status;
    private String questionId;
    private int questionNumber;
    private int questionsCount;
    private List<PlayerScore> playersScore;
    private String startDateTime;
    private int timeLimit;

    /**
     * simple constructor
     */
    public GameStatus() {

    }

    /**
     * constructor
     *
     * @param status         - game status
     * @param startDataTime  - start data time
     * @param timeLimit      - limit
     * @param questionsCount - count question now
     * @param questionId - question id
     * @param questionNumber - question number
     * @param playerScore    - total score
     */

    public GameStatus(final StatusGame status, final String questionId, final int questionNumber,
                      final int questionsCount, final String startDataTime, final int timeLimit,
                      final List<PlayerScore> playerScore) {
        this.status = status;
        this.questionId = questionId;
        this.questionNumber = questionNumber;
        this.questionsCount = questionsCount;
        this.playersScore = playerScore;
        this.startDateTime = startDataTime;
        this.timeLimit = timeLimit;
    }

    /**
     * constructor
     *
     * @param status         - status game
     * @param startDateTime  - time start game
     * @param timeLimit      - limit game
     * @param questionsCount - question count
     * @param questionNumber - number question
     * @param questionId - question id
     * @param playerScore    - player score
     */
    public GameStatus(final StatusGame status, final String questionId, final int questionNumber,
                      final String startDateTime, final int timeLimit, final int questionsCount,
                      final PlayerScore playerScore) {
        this.status = status;
        this.questionId = questionId;
        this.questionNumber = questionNumber;
        this.questionsCount = questionsCount;
        this.playersScore = new ArrayList<>();
        playersScore.add(playerScore);
        this.startDateTime = startDateTime;
        this.timeLimit = timeLimit;
    }

    public StatusGame getStatus() {
        return status;
    }

    public void setStatus(final StatusGame status) {
        this.status = status;
    }


    public int getQuestionsCount() {
        return questionsCount;
    }

    public void setQuestionsCount(final int questionsCount) {
        this.questionsCount = questionsCount;
    }

    public List<PlayerScore> getPlayersScore() {
        return playersScore;
    }

    public void setPlayersScore(final List<PlayerScore> playersScore) {
        this.playersScore = playersScore;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public String getQuestionId() {
        return questionId;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }
}
