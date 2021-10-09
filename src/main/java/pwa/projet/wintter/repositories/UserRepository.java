package pwa.projet.wintter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pwa.projet.wintter.models.User;

import java.util.Optional;

// e227fbf1-bdb9-43f1-8311-195b255265c9

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    User findUserByNickName(String username);

    Optional<User> findByEmail(String email);
}