package pwa.projet.wintter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pwa.projet.wintter.models.Tweet;
import pwa.projet.wintter.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long>
{
    Optional<List<Tweet>> findTweetByUser_UserId(Long userId);
    Optional<Tweet> findTweetByTweetId(Long tweetId);
    boolean existsTweetByTweetIdAndUser(Long tweetId, User user);
}
