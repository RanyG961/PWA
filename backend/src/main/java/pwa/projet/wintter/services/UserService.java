package pwa.projet.wintter.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import pwa.projet.wintter.models.*;
import pwa.projet.wintter.repositories.FollowRepository;
import pwa.projet.wintter.repositories.RoleRepository;
import pwa.projet.wintter.repositories.UserRepository;
import pwa.projet.wintter.repositories.VerificationTokenRepository;
import pwa.projet.wintter.requests.FollowRequest;
import pwa.projet.wintter.requests.RegisterRequest;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.Instant;
import java.util.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService implements UserDetailsService
{
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final VerificationTokenRepository verificationTokenRepo;
    private final MailService mailService;
    private final FollowRepository followRepo;
    private final TweetService tweetService;

    @Transactional
    public void addUserJson(RegisterRequest registerRequest)
    {

//        Verification que le mot de passe contient au moins :
//              - Une lettre minuscule
//              - Une lettre majuscule
//              - Un chiffre
//              - Un symbole
//        A uncomment quand les tests seront finis.
//        if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#&()–{}:;',?/*~$^+=<>]).{6,20}$")
//        {
//
//        }

        User user = new User();

        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());

        log.info("Get password {}", registerRequest.getPassword());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        user.setBirthDate(registerRequest.getBirthDate());
        user.setCreatedTime(Instant.now());
        user.setProfileEnable(false);

        userRepo.save(user);

        sendMail(user);

    }

    public void showUserJson(RegisterRequest registerRequest)
    {
        System.out.println("firstName : " + registerRequest.getFirstName());
        System.out.println("lastName : " + registerRequest.getLastName());
        System.out.println("userName : " + registerRequest.getUsername());
        System.out.println("email : " + registerRequest.getEmail());
        System.out.println("password : " + registerRequest.getPassword());
        System.out.println("birthDate : " + registerRequest.getBirthDate());
    }

    @Transactional
    public void addUser(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedTime(Instant.now());
        user.setProfileEnable(false);

        userRepo.save(user);

        sendMail(user);
    }

    private void sendMail(User user)
    {
        log.info("Saving new user {} to the database", user.getEmail());
        String token = generateVerifcationToken(user);
        mailService.sendMail(new Email("A",
                user.getEmail(),
                "Activate your account on Wintter Social Network " +
                        "http://localhost:8181/api/authentification/accountVerification/"
                        + token));
    }

    private String generateVerifcationToken(User user)
    {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();

        verificationToken.setToken(token);
        verificationToken.setUser(user);

        verificationTokenRepo.save(verificationToken);

        return token;
    }

    public void verifyAccount(String token) throws Exception
    {
        Optional<VerificationToken> verificationToken = verificationTokenRepo.findByToken(token);

        verificationToken.orElseThrow(() -> new Exception("Token doesn't exist"));
        enableUserWithToken(verificationToken.get());
    }

    @Transactional
    public void enableUserWithToken(VerificationToken verificationToken) throws Exception
    {
        String username = verificationToken.getUser().getUsername();
        User user = userRepo.findUserByUsername(username).orElseThrow(() -> new Exception("User doesn't exist"));
        user.setProfileEnable(true);
        userRepo.save(user);
    }

    public List<User> findAllUsers()
    {
        log.info("Fetching all users");
        return userRepo.findAll();
    }

    public List<Role> findAllRoles()
    {
        log.info("Fetching all roles");
        return roleRepo.findAll();
    }

    public User updateUser(User user)
    {
        return userRepo.save(user);
    }

    public Optional<User> findUserById(Long id)
    {
        return userRepo.findById(id);
    }

    public Optional<User> getUserByUsername(String nickNameOrEmail)
    {
        log.info("Fetching user {}", nickNameOrEmail);
        return userRepo.findUserByUsername(nickNameOrEmail);
    }

    public User getUser(String nickname)
    {
        log.info("Fetching user {}", nickname);
        return userRepo.findByUsername(nickname);
    }

    public void addRoleToUser(String username, String type)
    {
        log.info("Adding role {} to user {}", type, username);
        User user = userRepo.findByUsername(username);
        Role role = roleRepo.findByType(type);

        user.getRoles().add(role);
    }

    public Role findRoles(String type)
    {
        return roleRepo.findByType(type);
    }

    @Transactional
    public Role saveRole(Role role)
    {
        return roleRepo.save(role);
    }

    public void deleteUser(Long id)
    {
        userRepo.deleteById(id);
    }

    @Transactional
    public Follow followSomeone(@RequestHeader(AUTHORIZATION) String token, @RequestBody FollowRequest followingRequest) throws IOException
    {
        String followerUsername = tweetService.usernameFromToken(token);
        System.out.println("followerUsername : " + followerUsername + " followingUsername : " + followingRequest.getUsername());
        User follower = findByUsername(followerUsername);
        User following = findByUsername(followingRequest.getUsername());

        Follow follow = new Follow();

        follow.setFollower(follower);
        follow.setFollowing(following);

        return followRepo.saveAndFlush(follow);
    }

    @Transactional
    public void unFollowSomeone(@RequestHeader(AUTHORIZATION) String token, @RequestBody FollowRequest followingRequest) throws IOException
    {
        String followerUsername = tweetService.usernameFromToken(token);
        System.out.println("followerUsername : " + followerUsername + " followingUsername : " + followingRequest.getUsername());
        User follower = findByUsername(followerUsername);
        User following = findByUsername(followingRequest.getUsername());

        followRepo.deleteByFollowerAndFollowing(follower, following);
    }

    @Transactional
    public int countFollowers(@RequestBody FollowRequest followRequest)
    {
        User user = findByUsername(followRequest.getUsername());

        return followRepo.countFollowByFollowing(user);

    }

    @Transactional
    public int countFollowing(@RequestBody FollowRequest followRequest)
    {
        User user = findByUsername(followRequest.getUsername());
        return followRepo.countFollowByFollower(user);
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException
    {
        User user = userRepo.findUserByUsernameOrEmail(usernameOrEmail, usernameOrEmail);

        if (user == null)
        {
            log.error("User {} not found in the database", usernameOrEmail);
        } else
        {
            log.info("User {} found in the database", usernameOrEmail);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        assert user != null;
        user.getRoles().forEach(role ->
                authorities.add(new SimpleGrantedAuthority(role.getType())));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), authorities);
    }

    public HashMap<Integer, Object> allUsers(@RequestHeader(AUTHORIZATION) String token) throws IOException
    {
        User user = tweetService.getUserByToken(token);
        List<User> allUsers = userRepo.findUsersByUsernameIsNotLike(user.getUsername());
        HashMap<Integer, Object> hashUsers = new HashMap<>();
        Integer hashId = 0;

        for (User u : allUsers){
            hashingUsers(u);
            hashUsers.put(hashId, hashingUsers(u));
            hashId++;
        }

        return hashUsers;
    }

    public HashMap<String, Object> findAnotherUser(String token, String username) throws IOException
    {
        User following = getUser(username);
        User follower = getUser(tweetService.usernameFromToken(token));
        Boolean exist = followRepo.findFollowByFollowerIsAndFollowingIs(follower, following).isPresent();
        System.out.println("Do they follow each other : " + exist);
        HashMap<String, Object> otherUser = hashingUser(following, follower);
        otherUser.put("follows", exist);


        return otherUser;
    }

    private HashMap<String, Object> hashingUsers(User u)
    {
        HashMap<String, Object> hashUser = new HashMap<>();


        hashUser.put("userId", u.getUserId());
        hashUser.put("username", u.getUsername());
        hashUser.put("profilePicture", u.getProfilePicture());

        return hashUser;
    }

    private HashMap<String, Object> hashingUser(User u, User follower)
    {
        HashMap<String, Object> hashUser = new HashMap<>();


        hashUser.put("userId", u.getUserId());
        hashUser.put("username", u.getUsername());
        hashUser.put("profilePicture", u.getProfilePicture());
        hashUser.put("tweets", tweetService.tweetsOfUserFromFollower(u,  follower));

        return hashUser;
    }

    public User findByUsername(String username)
    {
        User user = userRepo.findUserByUsernameOrEmail(username, username);

        if (user == null)
        {
            log.error("User {} not found in the database", username);
        } else
        {
            log.info("User {} found in the database", username);
        }

        return user;
    }

}