package it.sevenbits.courses.quizzes.web.controller.users;

import it.sevenbits.courses.quizzes.web.controller.model.Login;
import it.sevenbits.courses.quizzes.web.service.login.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegisterControllerTest {
    RegisterController registerController;
    LoginService mockLoginService;

    @BeforeEach
    void setUp() {
        mockLoginService = mock(LoginService.class);
        registerController = new RegisterController(mockLoginService);
    }

    @Test
    void registerUserTest() {
        Login mockLogin = mock(Login.class);
        when(mockLoginService.addUser(mockLogin)).thenReturn(true);
        ResponseEntity<?> answer = registerController.registerUser(mockLogin);

        verify(mockLoginService, times(1)).addUser(mockLogin);
        assertEquals(HttpStatus.OK, answer.getStatusCode());
    }
}