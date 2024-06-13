package it.sevenbits.courses.quizzes.web.service.login;

import it.sevenbits.courses.quizzes.core.model.user.User;
import it.sevenbits.courses.quizzes.core.repository.room.RoomRepository;
import it.sevenbits.courses.quizzes.core.repository.security.PasswordEncoder;
import it.sevenbits.courses.quizzes.core.repository.users.UsersRepository;
import it.sevenbits.courses.quizzes.web.controller.model.Login;
import it.sevenbits.courses.quizzes.web.service.room.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginServiceTest {
    private LoginService loginService;
    private UsersRepository mockUsers;
    private PasswordEncoder mockPasswordEncoder;

    @BeforeEach
    public void setup() {
        mockUsers = mock(UsersRepository.class);
        mockPasswordEncoder = mock(PasswordEncoder.class);
        loginService = new LoginService(mockUsers, mockPasswordEncoder);
    }

    @Test
    public void loginTest() {
        User mockUser = mock(User.class);
        Login mockLogin = mock(Login.class);
        List<String> mockList = mock(List.class);
        when(mockLogin.getEmail()).thenReturn("1");
        when(mockLogin.getPassword()).thenReturn("1");
        when(mockUser.getPassword()).thenReturn("1").thenReturn("1");
        when(mockUser.getPlayerId()).thenReturn("1");
        when(mockUser.getName()).thenReturn("1");
        when(mockUser.getRoles()).thenReturn(mockList);
        when(mockUser.getEmail()).thenReturn("1");

        when(mockPasswordEncoder.matches(anyString(), anyString())).thenReturn(true);
        when(mockUsers.findByUserName(mockLogin.getEmail())).thenReturn(mockUser);
        User answer = loginService.login(mockLogin);

        verify(mockUsers, times(1)).findByUserName(mockLogin.getEmail());
        assertEquals(new User("1", "1", "1", "1", mockList), answer);
    }

    @Test
    public void addUserTest() {
        Login mockLogin = mock(Login.class);
        User mockUser = mock(User.class);
        when(mockLogin.getEmail()).thenReturn("1");
        when(mockLogin.getPassword()).thenReturn("1");
        when(mockLogin.getName()).thenReturn("1");

        when(mockPasswordEncoder.hash(mockLogin.getPassword())).thenReturn("1");
        when(mockUsers.addUser(any())).thenReturn(true);

        boolean answer = loginService.addUser(mockLogin);
        verify(mockUsers, times(1)).addUser(any());
        assertTrue(answer);
    }

}