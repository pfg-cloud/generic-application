package controllers;

import base.BaseController;
import lombok.extern.slf4j.Slf4j;
import model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController extends BaseController {

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return success(userService.getUsers());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") final Long userId) {
        return success(userService.getUserById(userId));
    }
}
