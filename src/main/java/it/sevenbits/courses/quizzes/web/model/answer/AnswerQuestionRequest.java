package it.sevenbits.courses.quizzes.web.model.answer;

import java.io.Serializable;
import java.util.Objects;

/**
 * AnswerQuestionRequest
 *
 */
public class AnswerQuestionRequest implements Serializable {
    private String answerId;

    /**
     * AnswerQuestionRequest
     *
     * @param answerId - answer id
     * @param playerId - player id
     */
    public AnswerQuestionRequest(final String answerId, final String playerId) {
        this.answerId = answerId;
    }

    /**
     * AnswerQuestionRequest - constructor
     *
     */
    public AnswerQuestionRequest() {
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(final String answerId) {
        this.answerId = answerId;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AnswerQuestionRequest that = (AnswerQuestionRequest) o;
        return Objects.equals(answerId, that.answerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answerId);
    }
}
