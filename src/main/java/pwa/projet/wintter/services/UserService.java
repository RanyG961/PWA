package pwa.projet.wintter.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pwa.projet.wintter.models.Email;
import pwa.projet.wintter.models.User;
import pwa.projet.wintter.models.VerificationToken;
import pwa.projet.wintter.repositories.UserRepository;
import pwa.projet.wintter.repositories.VerificationTokenRepository;
import pwa.projet.wintter.requests.RegisterRequest;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService
{
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepo;
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
        user.setNickName(registerRequest.getNickName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setBirthDate(registerRequest.getBirthDate());
        user.setCreatedTime(Instant.now());
        user.setProfileEnable(false);

        userRepo.save(user);

        sendMail(user);

    }

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
                "Thank you for signing up to Spring Reddit, " +
                        "please click on the below url to activate your account : " +
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

    public List<User> findAllUsers()
    {
        return userRepo.findAll();
    }

    public User updateUser(User user)
    {
        return userRepo.save(user);
    }

    public Optional<User> findUserById(Long id)
    {
        return userRepo.findById(id);
    }

    public User getUser(String nickNameOrEmail)
    {
        log.info("Fetching user {}", nickNameOrEmail);
        return userRepo.findUserByNickName(nickNameOrEmail);
    }

    public void deleteUser(Long id)
    {
        userRepo.deleteById(id);
    }

}
