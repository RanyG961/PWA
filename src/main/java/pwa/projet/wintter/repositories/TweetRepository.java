package pwa.projet.wintter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pwa.projet.wintter.models.Tweet;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long>
{
}
