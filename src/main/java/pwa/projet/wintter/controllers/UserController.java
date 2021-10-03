package pwa.projet.wintter.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pwa.projet.wintter.models.User;
import pwa.projet.wintter.services.UserService;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController
{
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping
//    public ResponseEntity<List<User>> getAllUsers()
//    {
//        List<User> users = userService.findAllUsers();
//        return ResponseEntity<>(users, HttpStatus.OK);
//    }
}
