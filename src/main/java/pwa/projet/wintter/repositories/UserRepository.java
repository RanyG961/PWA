package pwa.projet.wintter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pwa.projet.wintter.models.User;

public interface UserRepository extends JpaRepository<User, Long>
{

}
