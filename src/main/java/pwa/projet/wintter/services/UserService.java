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
        String salt = BCrypt.gensalt(12);
        user.setPassword(BCrypt.hashpw(user.getPassword(), salt));
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

    public void deleteUser(Long id)
    {
        userRepo.deleteById(id);
    }
}
