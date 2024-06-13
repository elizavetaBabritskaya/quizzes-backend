package it.sevenbits.courses.quizzes.config;

import it.sevenbits.courses.quizzes.core.repository.game.GameRepository;
import it.sevenbits.courses.quizzes.core.repository.question.QuestionRepository;
import it.sevenbits.courses.quizzes.core.repository.room.RoomRepository;
import it.sevenbits.courses.quizzes.core.repository.security.BCryptPasswordEncoder;
import it.sevenbits.courses.quizzes.core.repository.security.PasswordEncoder;
import it.sevenbits.courses.quizzes.core.repository.users.UsersRepository;
import it.sevenbits.courses.quizzes.web.controller.security.JsonWebTokenService;
import it.sevenbits.courses.quizzes.web.controller.security.JwtSettings;
import it.sevenbits.courses.quizzes.web.controller.security.JwtTokenService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;

/**
 * Repository config
 */
@Configuration
public class RepositoryConfig {

    /**
     * Question repository
     * @param jdbcOperations - operations
     * @return Question Repository
     */
    @Bean
    public QuestionRepository quizzesRepository(
            @Qualifier("quizzesJdbcOperations")
            final JdbcOperations jdbcOperations
    ) {
        return new QuestionRepository(jdbcOperations);
    }

    /**
     * game Repository
     * @param jdbcOperations - operations
     * @return Game Repository
     */
    @Bean
    public GameRepository gameQuizzesRepository(
            @Qualifier("quizzesJdbcOperations")
            final JdbcOperations jdbcOperations
    ) {
        return new GameRepository(jdbcOperations);
    }

    /**
     * Room repository
     * @param jdbcOperations - operations
     * @return Room Repository
     */

    @Bean
    public RoomRepository roomQuizzesRepository(
            @Qualifier("quizzesJdbcOperations")
            final JdbcOperations jdbcOperations
    ) {
        return new RoomRepository(jdbcOperations);
    }

    /**
     * repository
     * @param jdbcOperations - operations
     * @return user repository
     */
    @Bean
    public UsersRepository userQuizzesRepository(
            @Qualifier("quizzesJdbcOperations")
            final JdbcOperations jdbcOperations
    ) {
        return new UsersRepository(jdbcOperations);
    }

    /**
     * password encoder
     * @return password
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * jwt token service
     * @param settings - settings
     * @return token
     */
    @Bean
    public JwtTokenService jwtTokenService(final JwtSettings settings) {
        return new JsonWebTokenService(settings);
    }




}
