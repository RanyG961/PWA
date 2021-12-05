package pwa.projet.wintter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pwa.projet.wintter.models.Retweet;

public interface RetweetRepository extends JpaRepository<Retweet, Long>
{
}
