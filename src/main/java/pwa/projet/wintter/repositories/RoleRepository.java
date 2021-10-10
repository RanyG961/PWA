package pwa.projet.wintter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pwa.projet.wintter.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long>
{
    Role findByType(String type);
}
