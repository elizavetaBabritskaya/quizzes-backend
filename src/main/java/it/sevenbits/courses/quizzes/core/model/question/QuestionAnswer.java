package it.sevenbits.courses.quizzes.core.model.question;

import java.util.Objects;

/**
 * QuestionAnswer
 *
 */
public class QuestionAnswer {
    private final String answerId;
    private final String answerText;

    /**
     * QuestionAnswer - constructor
     *
     * @param answerId - answer id
     * @param answerText - answer text
     */
    public QuestionAnswer(final String answerId, final String answerText) {
        this.answerId = answerId;
        this.answerText = answerText;
    }

    public String getAnswerId() {
        return answerId;
    }

    public String getAnswerText() {
        return answerText;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        QuestionAnswer that = (QuestionAnswer) o;
        return Objects.equals(answerId, that.answerId) && Objects.equals(answerText, that.answerText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answerId, answerText);
    }
}
