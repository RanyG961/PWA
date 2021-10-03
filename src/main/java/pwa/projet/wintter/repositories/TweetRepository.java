package pwa.projet.wintter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pwa.projet.wintter.models.Tweet;

public interface TweetRepository extends JpaRepository<Tweet, Long> {
}
