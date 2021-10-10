package pwa.projet.wintter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pwa.projet.wintter.models.VerificationToken;

import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long>
{

    Optional<VerificationToken> findByToken(String token);
}
