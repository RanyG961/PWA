package pwa.projet.wintter.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pwa.projet.wintter.models.Email;
import pwa.projet.wintter.models.Role;
import pwa.projet.wintter.models.User;
import pwa.projet.wintter.models.VerificationToken;
import pwa.projet.wintter.repositories.RoleRepository;
import pwa.projet.wintter.repositories.UserRepository;
import pwa.projet.wintter.repositories.VerificationTokenRepository;
import pwa.projet.wintter.requests.RegisterRequest;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.*;

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

//    @Transactional
//    public void addUserTest(User user)
//    {
//        user.setFirstName(user.getFirstName());
//        user.setLastName(user.getLastName());
//        user.setUsername(user.getUsername());
//        user.setEmail(user.getEmail());
//        System.out.println("Get password " + user.getPassword());
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setBirthDate(user.getBirthDate());
//        user.setCreatedTime(Instant.now());
//        user.setProfileEnable(false);
//
//        userRepo.save(user);
//
//        sendMail(user);
//
//    }

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
}