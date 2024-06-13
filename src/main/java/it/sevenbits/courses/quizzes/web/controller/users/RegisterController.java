package it.sevenbits.courses.quizzes.web.controller.users;

import it.sevenbits.courses.quizzes.web.controller.model.Login;
import it.sevenbits.courses.quizzes.web.service.login.LoginFailedException;
import it.sevenbits.courses.quizzes.web.service.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * controller register
 */
@RestController
@RequestMapping("/signup")
public class RegisterController {
    @Autowired
    private final LoginService loginService;
    private static final  int STATUS_ERROR = 404;

    /**
     * constructor
     * @param loginService - login service
     */
    public RegisterController(final LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * register user
     * @param login - login
     * @return status
     */
    @PostMapping
    @ResponseBody
    public ResponseEntity<?> registerUser(@RequestBody final Login login) {
        try {
            loginService.addUser(login);
        } catch (LoginFailedException e) {
            return ResponseEntity.status(STATUS_ERROR).build();
        }

        return ResponseEntity.ok().build();
    }
}
