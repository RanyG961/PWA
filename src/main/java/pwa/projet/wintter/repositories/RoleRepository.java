package pwa.projet.wintter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pwa.projet.wintter.models.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long>
{
    Role findByType(String type);

}
