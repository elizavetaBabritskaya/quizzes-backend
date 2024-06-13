package it.sevenbits.courses.quizzes.core.repository.question;

import org.springframework.http.HttpStatus;

/**
 * exception
 */
public class QuestionException extends Exception {
    private int status;

    /**
     * constructor
     * @param status - status error
     */
    public QuestionException(final int status) {
        this.status = status;
    }

    /**
     * simple constructor
     */
    public QuestionException() {
        this.status = HttpStatus.NOT_FOUND.value();
    }

    public int getStatus() {
        return status;
    }
}
