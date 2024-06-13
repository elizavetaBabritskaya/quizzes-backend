package it.sevenbits.courses.quizzes.web.controller.users;

import it.sevenbits.courses.quizzes.core.model.user.User;
import it.sevenbits.courses.quizzes.web.controller.model.Login;
import it.sevenbits.courses.quizzes.web.controller.model.Token;
import it.sevenbits.courses.quizzes.web.controller.security.JwtTokenService;
import it.sevenbits.courses.quizzes.web.service.login.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BodyLoginControllerTest {
    BodyLoginController bodyLoginController;
    LoginService mockLoginService;
    JwtTokenService mockJwtLoginService;
    @BeforeEach
    void setUp() {
        mockLoginService = mock(LoginService.class);
        mockJwtLoginService = mock(JwtTokenService.class);
        bodyLoginController = new BodyLoginController(mockLoginService, mockJwtLoginService);
    }

    @Test
    void createTest() {
        Login mockLogin = mock(Login.class);
        User mockUser = mock(User.class);
        when(mockLoginService.login(mockLogin)).thenReturn(mockUser);
        when(mockJwtLoginService.createToken(mockUser)).thenReturn("1");
        ResponseEntity<Token> answer = bodyLoginController.create(mockLogin);
        verify(mockLoginService, times(1)).login(mockLogin);
        verify(mockJwtLoginService, times(1)).createToken(mockUser);

        assertEquals(HttpStatus.OK, answer.getStatusCode());
        assertEquals(new Token("1"), answer.getBody());
    }
}