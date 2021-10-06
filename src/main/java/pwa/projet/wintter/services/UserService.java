package pwa.projet.wintter.services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pwa.projet.wintter.models.User;
import pwa.projet.wintter.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepo;

    @Autowired
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User addUser(User user)
    {
        String password = user.getPassword();
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
        String salt = BCrypt.gensalt(12);
        user.setPassword(BCrypt.hashpw(password, salt));
        return userRepo.save(user);
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

//    public Optional<User> findUserByNicknameOrEmail(String auth)
//    {
//
//    }

    public void deleteUser(Long id)
    {
        userRepo.deleteById(id);
    }
}
