package pwa.projet.wintter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pwa.projet.wintter.models.Follow;

public interface FollowRepository extends JpaRepository<Follow, Long>
{

}
