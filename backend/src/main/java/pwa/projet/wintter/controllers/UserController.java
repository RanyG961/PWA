package pwa.projet.wintter.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pwa.projet.wintter.models.Role;
import pwa.projet.wintter.models.RoleToUserForm;
import pwa.projet.wintter.models.User;
import pwa.projet.wintter.requests.RegisterRequest;
import pwa.projet.wintter.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:8080/")
public class UserController
{
    private final UserService userService;

    @PostMapping("/register")
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

    @GetMapping("/getUsers")
    public ResponseEntity<List<User>>getUsers()
    {
        return ResponseEntity.ok().body(userService.findAllUsers());
    }

//    @GetMapping("/infos/{username}")
//    public ResponseEntity<User>getUserByUsernameGet(@PathVariable String username)
//    {
//        return ResponseEntity.ok().body(userService.findByUsername(username));
//    }

//    @PostMapping("/infos")
//    public ResponseEntity<User>getUserByUsernamePost(@RequestHeader (name="Authorization") String token)
//    {
//        token = token.substring("Bearer ".length());
//        Algorithm algo = Algorithm.HMAC256("secret".getBytes());
//        JWTVerifier verifier = JWT.require(algo).build();
//        DecodedJWT decodedJWT = verifier.verify(token);
//        String username = decodedJWT.getSubject();
//        return ResponseEntity.ok().body(userService.findByUsername(username));
//    }

    @PostMapping(value = "/infos", produces = APPLICATION_JSON_VALUE)
//    @RequestMapping( produces = MediaType.APPLICATION_JSON_VALUE))
    public ResponseEntity<Object>getUserByUsernamePost( HttpServletRequest request, HttpServletResponse response) throws IOException
    {

        String authorizationHeader = request.getHeader(AUTHORIZATION);
        String refreshToken = null;

        System.out.println("Post getUser " + authorizationHeader);

        if (authorizationHeader != null)
        {
            try
            {
                if( authorizationHeader.contains("Bearer"))
                {
                    refreshToken = authorizationHeader.substring("Bearer ".length());
                }
                else
                {
                    refreshToken = authorizationHeader;
                }

                Algorithm algo = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algo).build();
                DecodedJWT decodedJWT = verifier.verify(refreshToken);
                System.out.println("decoded : " + decodedJWT.getSubject());
                String username = decodedJWT.getSubject();
                User user = userService.getUser(username);

                JSONObject userJSON = new JSONObject();
//                HashMap<String, Object> userJSON = new HashMap<>();
                userJSON.put("id", user.getUserId());
                userJSON.put("lastName", user.getLastName());
                userJSON.put("firstName", user.getFirstName());
                userJSON.put("username", user.getUsername());
                userJSON.put("email", user.getEmail());
                userJSON.put("roles", user.getRoles());
                userJSON.put("tweets", user.getTweets());
                userJSON.put("profilePicture", user.getProfilePicture());
                userJSON.put("profileBanner", user.getProfileBanner());
                userJSON.put("chats", user.getChats());
                userJSON.put("createdTime", user.getCreatedTime());
                userJSON.put("birthDate", user.getBirthDate());
                userJSON.put("biography", user.getBiography());

//                List<JSONObject> user = new ArrayList<>(JSONObject)

//                log.info("Hello I found this user {}", user.getLastName());
                System.out.println("Hello I found this user " + username);
//                return ResponseEntity.ok().body(userJSON);
                return new ResponseEntity<>(user, HttpStatus.OK);
            } catch (Exception e)
            {
                log.error("Error logging in : {}", e.getMessage());

                response.setHeader("error", e.getMessage());
                response.setStatus(FORBIDDEN.value());

                Map<String, String> error = new HashMap<>();

                error.put("error_message", e.getMessage());

                response.setContentType(APPLICATION_JSON_VALUE);

                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else
        {
            throw new RuntimeException("Refresh token is missing !");
        }
        return ResponseEntity.notFound().build();
    }



    @GetMapping("/getRoles")
    public ResponseEntity<List<Role>>getRoles()
    {
        return ResponseEntity.ok().body(userService.findAllRoles());
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
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/addroletouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form)
    {
        userService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        log.info("Hello there");
        String authorizationHeader = request.getHeader(AUTHORIZATION);

        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer "))
        {
            try
            {
                String refreshToken = authorizationHeader.substring("Bearer ".length());
                Algorithm algo = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algo).build();
                DecodedJWT decodedJWT = verifier.verify(refreshToken);
                String username = decodedJWT.getSubject();
                User user = userService.getUser(username);

                String accessToken = JWT.create().
                        withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 1 * 60 * 1000)) // 1 minute
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getRoles().stream().map(Role::getType).collect(Collectors.toList()))
                        .sign(algo);


                Map<String, String> tokens = new HashMap<>();

                tokens.put("access_token", accessToken);
                tokens.put("refresh_token", refreshToken);

                response.setContentType(APPLICATION_JSON_VALUE);

                new ObjectMapper().writeValue(response.getOutputStream(), tokens);


            } catch (Exception e)
            {
                log.error("Error logging in : {}", e.getMessage());

                response.setHeader("error", e.getMessage());
                response.setStatus(FORBIDDEN.value());

                Map<String, String> error = new HashMap<>();

                error.put("error_message", e.getMessage());

                response.setContentType(APPLICATION_JSON_VALUE);

                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing !");
        }
    }

    @RequestMapping(value = "{_:^(?!index\\.html|api).$}")
    public String redirectApi() {
        log.info("URL entered directly into the Browser, so we need to redirect...");
        return "forward:/";
    }


}
