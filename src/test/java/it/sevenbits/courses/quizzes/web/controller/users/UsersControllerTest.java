package it.sevenbits.courses.quizzes.web.controller.users;

import it.sevenbits.courses.quizzes.core.model.user.User;
import it.sevenbits.courses.quizzes.core.repository.users.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsersControllerTest {
    UsersController usersController;
    UsersRepository mockUserRepository;

    @BeforeEach
    void setUp() {
        mockUserRepository = mock(UsersRepository.class);
        usersController = new UsersController(mockUserRepository);
    }

    @Test
    void getAllUsers() {
        List<User> mockList = mock(List.class);
        when(mockUserRepository.findAll()).thenReturn(mockList);
        ResponseEntity<List<User>> answer = usersController.getAllUsers();

        verify(mockUserRepository, times(1)).findAll();
        assertEquals(HttpStatus.OK, answer.getStatusCode());
        assertSame(mockList, answer.getBody());
    }

    @Test
    void getUserInfo() {
        User mockUser = mock(User.class);
        String user = "1";
        when(mockUserRepository.findByUserName(user)).thenReturn(mockUser);
        ResponseEntity<User> answer = usersController.getUserInfo(user);

        verify(mockUserRepository, times(1)).findByUserName(user);
        assertEquals(HttpStatus.OK, answer.getStatusCode());
        assertSame(mockUser, answer.getBody());
    }
}