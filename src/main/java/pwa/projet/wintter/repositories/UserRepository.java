package pwa.projet.wintter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pwa.projet.wintter.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>
{

//    public Optional<User> findUserByNickNameOrEmail(String nickname);
}
