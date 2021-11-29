package pwa.projet.wintter.controllers;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pwa.projet.wintter.models.Role;
import pwa.projet.wintter.models.RoleToUserForm;
import pwa.projet.wintter.models.User;
import pwa.projet.wintter.requests.RegisterRequest;
import pwa.projet.wintter.services.UserService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/authentification")
@RequiredArgsConstructor
public class UserController
{
    private final UserService userService;

    @PostMapping("/registerjson")
    public ResponseEntity<String> registerJson(@RequestBody RegisterRequest registerRequest)
    {
        userService.addUserJson(registerRequest);
        userService.showUserJson(registerRequest);
        return new ResponseEntity<>("User added !", HttpStatus.OK);
    }

    @GetMapping("accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token) throws Exception
    {
        userService.verifyAccount(token);
        return new ResponseEntity<>("User enabled", HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>>getUsers()
    {
        return ResponseEntity.ok().body(userService.findAllUsers());
    }

//    @PostMapping("/user/save")
//    public ResponseEntity<User>addUser(@RequestBody User user)
//    {
//        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
//        return ResponseEntity.created(uri).body(userService.addUserTest(user));
//    }

    @PostMapping("role/save")
    public ResponseEntity<Role>addRole(@RequestBody Role role)
    {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/addroletouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form)
    {
        userService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }
}
