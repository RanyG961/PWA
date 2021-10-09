package pwa.projet.wintter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pwa.projet.wintter.models.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long>
{

}
