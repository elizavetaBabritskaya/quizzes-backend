package it.sevenbits.courses.quizzes.core.model.question;

import it.sevenbits.courses.quizzes.core.model.question.QuestionAnswer;

import java.util.List;

/**
 * getQuestionById
 *
 */
public class QuestionWithOptions {
    private String questionId;
    private String questionText;
    private List<QuestionAnswer> answersList;

    /**
     * QuestionWithOptions
     *
     * @param questionId - question id
     * @param questionText - question text
     * @param questionAnswers - answers List
     */

    public QuestionWithOptions(final String questionId, final String questionText, final List<QuestionAnswer> questionAnswers) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.answersList = questionAnswers;
    }

    public String getQuestionId() {
        return questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<QuestionAnswer> getAnswersList() {
        return answersList;
    }

    public void setQuestionId(final String questionId) {
        this.questionId = questionId;
    }

    public void setQuestionText(final String questionText) {
        this.questionText = questionText;
    }

    public void setAnswersList(final List<QuestionAnswer> answersList) {
        this.answersList = answersList;
    }
}
