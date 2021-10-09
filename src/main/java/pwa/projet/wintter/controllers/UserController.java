package pwa.projet.wintter.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pwa.projet.wintter.requests.RegisterRequest;
import pwa.projet.wintter.services.UserService;

@RestController
@RequestMapping("/api/authentification")
@RequiredArgsConstructor
public class UserController
{
    private final UserService userService;

    @PostMapping("/registerjson")
    public ResponseEntity<String> registerJson(@ModelAttribute RegisterRequest registerRequest)
    {
//        model.addAttribute("registerRequest", registerRequest);
        userService.addUserJson(registerRequest);
        return new ResponseEntity<>("User added !", HttpStatus.OK);
    }

//    @GetMapping("/users")
//    public ResponseEntity<List<User>>getUsers()
//    {
//        return ResponseEntity.ok().body(userService.findAllUsers());
//    }
//
//    @PostMapping("/user/save")
//    public ResponseEntity<User>addUser(@RequestBody User user)
//    {
//        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
//        return ResponseEntity.created(uri).body(userService.addUser(user));
//    }
//
//    @PostMapping("role/save")
//    public ResponseEntity<Role>addRole(@RequestBody Role role)
//    {
//        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
//        return ResponseEntity.created(uri).body(userService.addRole(role));
//    }
//
//    @PostMapping("/role/addroletouser")
//    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form)
//    {
//        userService.addRoleToUser(form.getNickName(), form.getRoleName());
//        return ResponseEntity.ok().build();
//    }
}
