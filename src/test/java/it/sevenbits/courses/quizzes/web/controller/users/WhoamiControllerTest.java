package it.sevenbits.courses.quizzes.web.controller.users;

import it.sevenbits.courses.quizzes.web.controller.security.UserCredentials;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class WhoamiControllerTest {

    WhoamiController whoamiController;
    @BeforeEach
    void setUp() {
        whoamiController = new WhoamiController();
    }

    @Test
    void getTest() {
        UserCredentials mockUserCredentials = mock(UserCredentials.class);
        ResponseEntity<UserCredentials> answer = whoamiController.get(mockUserCredentials);

        assertEquals(HttpStatus.OK, answer.getStatusCode());
        assertSame(mockUserCredentials, answer.getBody());
    }
}