package it.sevenbits.courses.quizzes.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * class data bases
 */
@Configuration
public class QuizzesDatabaseConfig {
    /**
     * sources
     * @return Data Source
     */

    @Bean
    @Qualifier("quizzesDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.seven-quizzes")
    public DataSource quizzesDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * Operations
     * @param quizzesDataSource - source
     * @return operations
     */
    @Bean
    @Qualifier("quizzesJdbcOperations")
    public JdbcOperations quizzesJdbcOperations(
            @Qualifier("quizzesDataSource")
            final DataSource quizzesDataSource
    ) {
        return new JdbcTemplate(quizzesDataSource);
    }
}