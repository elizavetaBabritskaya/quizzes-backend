package it.sevenbits.courses.quizzes.core.repository.game;

import it.sevenbits.courses.quizzes.core.model.game.GameStatus;
import it.sevenbits.courses.quizzes.core.model.game.PlayerScore;
import it.sevenbits.courses.quizzes.core.model.game.StatusGame;
import it.sevenbits.courses.quizzes.core.model.question.Question;
import it.sevenbits.courses.quizzes.core.repository.question.QuestionException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;


import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;


/**
 * Game Repository
 */
public class GameRepository implements IGameRepository {
    private final JdbcOperations jdbcOperations;
    private Date startDateTime;
    private int timeLimit;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int FIVE = 5;
    private static final int SIX = 6;
    private static final int QUANTITY_OF_QUESTIONS_IN_GAME = 4;
    private static final int SECONDS = 30000;

    /**
     * Constructor
     *
     * @param quizzesJdbcOperations - operations
     */
    public GameRepository(final JdbcOperations quizzesJdbcOperations) {
        this.jdbcOperations = quizzesJdbcOperations;
        startDateTime = new Date();
        timeLimit = SECONDS * QUANTITY_OF_QUESTIONS_IN_GAME;
    }

    @Override
    public String getRules() {
        return jdbcOperations.queryForObject("SELECT rules FROM rules", (result, i) -> result.getString(ONE));
    }


    /**
     * create game
     *
     * @param roomId - id room
     */
    @Override
    public void createGame(final String roomId, final String playerId) {
        String gameId = UUID.randomUUID().toString();
        jdbcOperations.update(
                "INSERT INTO games(id_game, id_room, status, question_count, start_date_time, time_limit)" +
                        " VALUES (?, ?, 'READY_TO_START', 4, ?, ?)",
                gameId, roomId, startDateTime, timeLimit);
        jdbcOperations.update("INSERT INTO player_in_game(id_game, id_player, id_question, question_number, " +
                "total_score) VALUES(?, ? ,'', 0, 0)", gameId, playerId);
    }

    /**
     * gameStart
     *
     * @param roomId      - game id
     * @param playerId    - player id
     * @param questionsId - list question id
     * @return first question
     * @throws QuestionException - Question exception
     */
    @Override
    public Question gameStart(final String roomId, final String playerId, final List<String> questionsId)
            throws QuestionException {
        startDateTime = new Date();
        if (!"".equals(jdbcOperations.queryForObject("SELECT id_room, id_player FROM rooms_in_player WHERE id_room = ?" +
                " and id_player = ?", (result, i) -> result.getString(ONE), roomId, playerId))) {
            jdbcOperations.update("UPDATE games SET status = ?::game_status, start_date_time = ? WHERE id_room = ?",
                    StatusGame.IN_PROGRESS.getStatus(), startDateTime, roomId);
        } else {
            throw new QuestionException();
        }
        String gameId = jdbcOperations.queryForObject("SELECT id_game FROM games WHERE id_room = ?", (result, i) ->
                result.getString(ONE), roomId);
        Random random = new Random();

        int r = random.nextInt(questionsId.size() - 1);
        Question question = new Question(questionsId.get(r));
        jdbcOperations.update("UPDATE player_in_game SET id_question = ? WHERE id_game = ? AND id_player = ?",
                questionsId.get(r), gameId, playerId);
        for (int i = 0; i < QUANTITY_OF_QUESTIONS_IN_GAME; i++) {
            jdbcOperations.update("INSERT INTO questions_in_games(id_game, id_question) VALUES(?, ?)",
                    gameId, questionsId.get(r));
            questionsId.remove(r);
            r = random.nextInt(questionsId.size() - 1);
        }
        return question;
    }

    /**
     * get status
     *
     * @param roomId - id room
     * @return Game Status
     */
    @Override
    public GameStatus getStatus(final String roomId, final String playerId) {
        List<PlayerScore> playersScore = jdbcOperations.query("SELECT DISTINCT player_in_game.id_player, users.name, "
                        + "player_in_game.total_score " +
                        "FROM player_in_game INNER JOIN games ON games.id_room = ? AND " +
                        "games.id_game = player_in_game.id_game INNER JOIN users ON users.player_id = player_in_game.id_player",
                (result, i) -> new PlayerScore(result.getString(ONE), result.getString(TWO),
                        Integer.parseInt(result.getString(THREE))), roomId);

        return jdbcOperations.queryForObject("SELECT DISTINCT " +
                "games.status, games.question_count, games.start_date_time, games.time_limit, player_in_game.id_question, " +
                "player_in_game.question_number, player_in_game.id_player, games.id_room FROM rooms_in_player "
                + "INNER JOIN games ON rooms_in_player.id_room = games.id_room and games.id_room = ? " +
                "INNER JOIN player_in_game ON player_in_game.id_player = ? and rooms_in_player.id_player = player_in_game.id_player " +
                "AND games.id_game = player_in_game.id_game", (result, i) ->
                new GameStatus(StatusGame.valueOf(result.getString(ONE)), result.getString(FIVE), Integer.parseInt(result.getString(SIX)),
                        Integer.parseInt(result.getString(TWO)),
                        result.getString(THREE), Integer.parseInt(result.getString(FOUR)),
                        playersScore), roomId, playerId);
    }


    /**
     * update score
     *
     * @param playerId - room id
     */
    @Override
    public void updateStatus(final String playerId) {
        int totalScore = getTotalScore(playerId) + getQuestionScore(playerId);
        jdbcOperations.update("UPDATE player_in_game SET total_score = ? WHERE id_player = ?", totalScore, playerId);
    }

    /**
     * get total score
     *
     * @param playerId - room id
     * @return total score
     */
    @Override
    public int getTotalScore(final String playerId) {
        return jdbcOperations.queryForObject("SELECT total_score FROM player_in_game WHERE id_player = ?",
                (result, i) -> Integer.parseInt(result.getString(ONE)), playerId);
    }

    /**
     * Get question score
     *
     * @param playerId - roomId
     * @return - question score
     */
    @Override
    public int getQuestionScore(final String playerId) {
        String questionId = jdbcOperations.queryForObject("SELECT id_question FROM player_in_game WHERE id_player = ?",
                (result, i) -> result.getString(1), playerId);
        return jdbcOperations.queryForObject("SELECT question_score FROM questions WHERE id_question = ?",
                (result, i) -> Integer.parseInt(result.getString(ONE)), questionId);
    }

    /**
     * get next question by id
     *
     * @param roomId - room id
     * @return next question or null
     */
    @Override
    public String getNextQuestionById(final String roomId, final String playerId) {
        int questionCount = jdbcOperations.queryForObject("SELECT DISTINCT question_number FROM player_in_game WHERE id_player = ?",
                (result, i) -> Integer.parseInt(result.getString(ONE)), playerId) + 1;

        jdbcOperations.update("UPDATE player_in_game SET question_number = ? WHERE id_player = ?",
                questionCount, playerId);

        List<String> list = jdbcOperations.query("SELECT DISTINCT questions_in_games.id_question FROM player_in_game INNER JOIN " +
                        "questions_in_games ON player_in_game.id_game = questions_in_games.id_game AND player_in_game.id_player = ?",
                (result, id) -> result.getString(1), playerId);
        if (questionCount == QUANTITY_OF_QUESTIONS_IN_GAME) {
            return null;
        }
        return list.get(questionCount);
    }

    /**
     * finish
     *
     * @param roomId - room id
     */
    @Override

    public void finish(final String roomId) {
        jdbcOperations.update("UPDATE games SET status = 'FINISHED'::game_status WHERE id_room = ?", roomId);
    }

    @Override

    public int getCountQuestions() {
        return QUANTITY_OF_QUESTIONS_IN_GAME;
    }

    /**
     * get owner id
     *
     * @param roomId - room id
     * @return owner id
     */
    public String getOwnerId(final String roomId) {
        return jdbcOperations.queryForObject("SELECT owner FROM rooms WHERE id_room = ?",
                (result, i) -> result.getString(1), roomId);

    }

    /**
     * check has room db
     *
     * @param roomId - room id
     * @return true if has db
     */
    public boolean hasRoom(final String roomId) {
        try {
            jdbcOperations.queryForObject("SELECT id_room FROM rooms WHERE id_room = ?",
                    (result, i) -> result.getString(1), roomId);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;
    }

    /**
     * join game
     *
     * @param roomId   - id room where exit player
     * @param playerId - player id
     * @return true if success
     */
    public boolean joinGame(final String roomId, final String playerId) {
        String gameId = jdbcOperations.queryForObject("SELECT id_game FROM games WHERE id_room = ?",
                (result, i) -> result.getString(1), roomId);
        return jdbcOperations.update("INSERT INTO player_in_game(id_game, id_player, id_question, question_number, " +
                "total_score) VALUES(?, ? ,'', 0, 0)", gameId, playerId) > 0;

    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    /**
     * get status game in room
     *
     * @param roomId - room id
     * @return game status
     */
    public GameStatus getGameStatusByRoomId(final String roomId) {
        return jdbcOperations.queryForObject("SELECT status FROM games WHERE id_room = ?",
                (result, i) -> new GameStatus(StatusGame.valueOf(result.getString(ONE)),
                        null, 0, 0, null, 0, null), roomId);
    }
}