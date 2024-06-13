package it.sevenbits.courses.quizzes.core.repository.question;

import it.sevenbits.courses.quizzes.core.model.question.Question;
import it.sevenbits.courses.quizzes.core.model.question.QuestionAnswer;
import it.sevenbits.courses.quizzes.core.model.question.QuestionWithOptions;
import it.sevenbits.courses.quizzes.core.repository.question.QuestionException;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.ArrayList;
import java.util.List;

/**
 * Question repository
 */
public class QuestionRepository implements IQuestionRepository {

    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private final JdbcOperations jdbcOperations;

    /**
     * Constructor
     *
     * @param jdbcOperations - jdbc operation
     */
    public QuestionRepository(final JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public List<String> getListQuestion() {
        return jdbcOperations.query("SELECT id_question FROM questions", (result, i) -> result.getString(ONE));
    }

    /**
     * Get question by id
     *
     * @param questionId - question id
     * @param roomId     - room id
     * @return - questionWithOptions
     * @throws QuestionException - QuestionException
     */
    @Override
    public QuestionWithOptions getQuestionById(final String questionId, final String roomId, final String playerId)
            throws QuestionException {
        String gameId = jdbcOperations.queryForObject("SELECT id_game FROM games WHERE id_room = ?",
                (result, i) -> result.getString(ONE), roomId);
        if ("".equals(gameId)) {
            throw new QuestionException();
        }
        Question res = jdbcOperations.queryForObject("SELECT id_question FROM player_in_game WHERE id_player = ? AND id_game = ?",
                (result, i) -> new Question(result.getString(ONE)), playerId, gameId);
        if (res == null) {
            throw new QuestionException();
        }

        List<QuestionAnswer> answers = jdbcOperations.query(
                "SELECT DISTINCT answers.id_answer, answers.text FROM answers inner join questions on answers.id_question = ?",
                (resultSet, i) -> {
                    String id = resultSet.getString(ONE);
                    String text = resultSet.getString(TWO);
                    return new QuestionAnswer(id, text);
                }, questionId);
        QuestionWithOptions question = jdbcOperations.queryForObject(
                "SELECT id_question, text FROM questions WHERE id_question = ?",
                (resultSet, i) -> {
                    String idQuestion = resultSet.getString(ONE);
                    String text = resultSet.getString(TWO);
                    return new QuestionWithOptions(idQuestion, text, new ArrayList<>());
                },
                questionId);
        question.setAnswersList(answers);
        return question;
    }

    /**
     * Get correct answer by question id
     *
     * @param questionId - question id
     * @param roomId     - room id
     * @return correct answer
     * @throws QuestionException - question exception
     */
    @Override
    public QuestionAnswer getCorrectAnswer(final String roomId, final String questionId, final String playerId)
            throws QuestionException {
        String str = jdbcOperations.queryForObject("SELECT id_room FROM rooms WHERE id_room = ?",
                (result, i) -> result.getString(ONE), roomId);
        if ("".equals(str)) {
            throw new QuestionException();
        }

        String s = jdbcOperations.queryForObject("SELECT id_player FROM rooms_in_player WHERE id_room = ?",
                (result, i) -> result.getString(ONE), roomId);
        if ("".equals(s)) {
            throw new QuestionException();
        }

        Question res = jdbcOperations.queryForObject("SELECT id_question FROM player_in_game WHERE id_player = ?",
                (result, i) -> new Question(result.getString(ONE)), playerId);
        if (res == null) {
            throw new QuestionException();
        }

        return jdbcOperations.queryForObject(
                "SELECT DISTINCT questions.id_correct_answer, answers.text FROM questions INNER JOIN answers ON " +
                        "questions.id_question = answers.id_question WHERE questions.id_question = ? " +
                        "and questions.id_correct_answer = answers.id_answer",
                (resultSet, i) -> {
                    String idCorrectAnswer = resultSet.getString(ONE);
                    String textAnswer = resultSet.getString(TWO);
                    return new QuestionAnswer(idCorrectAnswer, textAnswer);
                },
                questionId);
    }

    /**
     * select all
     *
     * @return list
     */
    @Override
    public List<QuestionAnswer> selectAll() {
        return jdbcOperations.query(
                "SELECT id_question, text, id_correct_answer FROM questions",
                (resultSet, i) -> {
                    String idCorrectAnswer = resultSet.getString(THREE);
                    String textAnswer = resultSet.getString(TWO);
                    return new QuestionAnswer(idCorrectAnswer, textAnswer);
                });
    }
}