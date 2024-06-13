package it.sevenbits.courses.quizzes.web.model.answer;

import java.util.Objects;

/**
 * AnswerQuestionResponse
 *
 */
public class AnswerQuestionResponse {
    private String correctAnswerId;
    private String questionId;
    private int totalScore;
    private int questionScore;

    /**
     * AnswerQuestionResponse
     *
     * @param correctAnswerId - id correct answer id
     * @param nextQuestionId - id question id
     * @param totalScore - score
     * @param questionScore - count score
     */
    public AnswerQuestionResponse(final String correctAnswerId, final String nextQuestionId,
                                  final int totalScore, final int questionScore) {
        this.correctAnswerId = correctAnswerId;
        this.questionId = nextQuestionId;
        this.totalScore = totalScore;
        this.questionScore = questionScore;
    }

    public String getCorrectAnswerId() {
        return correctAnswerId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public int getQuestionScore() {
        return questionScore;
    }

    public void setCorrectAnswerId(final String correctAnswerId) {
        this.correctAnswerId = correctAnswerId;
    }

    public void setQuestionId(final String questionId) {
        this.questionId = questionId;
    }

    public void setTotalScore(final int totalScore) {
        this.totalScore = totalScore;
    }

    public void setQuestionScore(final int questionScore) {
        this.questionScore = questionScore;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AnswerQuestionResponse that = (AnswerQuestionResponse) o;
        return totalScore == that.totalScore && questionScore == that.questionScore && Objects.equals(correctAnswerId,
                that.correctAnswerId) && Objects.equals(questionId, that.questionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(correctAnswerId, questionId, totalScore, questionScore);
    }
}
