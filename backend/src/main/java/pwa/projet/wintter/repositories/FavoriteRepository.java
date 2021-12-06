package pwa.projet.wintter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pwa.projet.wintter.models.Favorite;
import pwa.projet.wintter.models.Tweet;
import pwa.projet.wintter.models.User;

import java.util.Optional;


public interface FavoriteRepository extends JpaRepository<Favorite, Long>
{
    Optional<Favorite> findFavoriteByTweetAndUser(Tweet tweet, User user);
    void deleteByTweetAndUser(Tweet tweet, User user);
    int countFavoriteByTweet(Tweet tweet);
    void deleteFavoriteByTweet(Tweet tweet);
}
