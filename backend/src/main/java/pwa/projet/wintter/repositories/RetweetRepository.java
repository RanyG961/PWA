package pwa.projet.wintter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pwa.projet.wintter.models.Retweet;
import pwa.projet.wintter.models.Tweet;
import pwa.projet.wintter.models.User;

import java.util.Optional;

public interface RetweetRepository extends JpaRepository<Retweet, Long>
{
    Optional<Retweet> findRetweetByTweetAndUser(Tweet tweet, User user);
    void deleteRetweetByTweetAndUser(Tweet tweet, User user);
    int countRetweetByTweet(Tweet tweet);
    void deleteRetweetByTweet(Tweet tweet);
}
