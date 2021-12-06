package pwa.projet.wintter.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import pwa.projet.wintter.models.Favorite;
import pwa.projet.wintter.models.Retweet;
import pwa.projet.wintter.models.Tweet;
import pwa.projet.wintter.models.User;
import pwa.projet.wintter.repositories.*;
import pwa.projet.wintter.requests.RetweetFavoriteRequest;
import pwa.projet.wintter.requests.TweetRequest;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.Instant;
import java.util.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TweetService
{
    private final TweetRepository tweetRepo;
//    private final UserService userService;
    private final UserRepository userRepo;
    private final MessageRepository messageRepo;
    private final RetweetRepository retweetRepo;
    private final FavoriteRepository favoriteRepo;

    @Transactional
    public Tweet addTweet(TweetRequest tweetRequest, @RequestHeader(AUTHORIZATION) String token) throws IOException
    {
//        if(messageRequest.getMedia().equals("null"))
//        {
//            messageRequest.setMedia("");
//        }
        Tweet tweet = new Tweet();
//        Message message = new Message(messageRequest.getContent(), messageRequest.getMedia(), Instant.now());

        User user = getUserByToken(token);


        tweet.setContent(tweetRequest.getContent());
        tweet.setMedia(tweetRequest.getMedia());
        tweet.setCreatedTime(Instant.now());
        tweet.setUser(user);
        return tweetRepo.save(tweet);
    }

    public List<Tweet> findAllTweets()
    {
        return tweetRepo.findAll();
    }

    public HashMap<Integer, Object> hashFindAllTweet(List<Tweet> allTweets, @RequestHeader(AUTHORIZATION) String token) throws IOException
    {
        User user = getUserByToken(token);
        HashMap<Integer, Object> hashL = new HashMap<>();
        Integer hashId = 0;

        for(Tweet t : allTweets)
        {
            HashMap<String, Object> hashT = new HashMap<>();
            hashT.put("isRetweeted", rtExist(t, user));
            hashT.put("isFavorited", favExist(t, user));
            hashTweets(t, hashT);
            hashL.put(hashId, hashTweets(t, hashT));
            hashId++;
        }
       return hashL;
    }

    private Boolean rtExist(Tweet tweet, User user)
    {
        return retweetRepo.findRetweetByTweetAndUser(tweet, user).isPresent();
    }

    private Boolean favExist(Tweet tweet, User user)
    {
        return favoriteRepo.findFavoriteByTweetAndUser(tweet, user).isPresent();
    }

    private HashMap<String, Object> hashTweets(Tweet t, HashMap<String, Object> hashT)
    {
        hashT.put("id", t.getTweetId());
        hashT.put("content", t.getContent());
        hashT.put("media", t.getMedia());
        hashT.put("createdTime", t.getCreatedTime());
        hashT.put("username", t.getUser().getUsername());
        hashT.put("nbRt",nbRetweet(t));
        hashT.put("nbFav", nbFav(t));
//        hashT.put("isRetweeted", rtExist(t, t.getUser()));
//        hashT.put("isFavorited", favExist(t, t.getUser()));

        return hashT;
    }

    public ArrayList<HashMap<String, Object>> tweetsOfUser(User u)
    {
        ArrayList<HashMap<String, Object>> tweetsOfUser = new ArrayList<>();

        for (Tweet t : u.getTweets())
        {
            HashMap<String, Object> hashT = new HashMap<>();

            hashT.put("isRetweeted", rtExist(t, t.getUser()));
            hashT.put("isFavorited", favExist(t, t.getUser()));

            tweetsOfUser.add(hashTweets(t, hashT));
        }

        return tweetsOfUser;
    }

    public ArrayList<HashMap<String, Object>> tweetsOfUserFromFollower(User u, User follower)
    {
        ArrayList<HashMap<String, Object>> tweetsOfUser = new ArrayList<>();

        for (Tweet t : u.getTweets())
        {
            HashMap<String, Object> hashT = new HashMap<>();

            hashT.put("isRetweeted", rtExist(t, follower));
            hashT.put("isFavorited", favExist(t, follower));
            System.out.println("RT is ok ? " + rtExist(t, follower));
            System.out.println("Fav is ok ? " + favExist(t, follower));
            tweetsOfUser.add(hashTweets(t, hashT));
        }

        return tweetsOfUser;
    }


    public boolean deleteTweet(@RequestHeader(AUTHORIZATION) String token, @RequestBody RetweetFavoriteRequest tweetRequest) throws IOException
    {
        User user = getUserByToken(token);
        Tweet tweet = tweetRepo.findTweetByTweetId(tweetRequest.getTweetId()).orElseThrow();

        retweetRepo.deleteRetweetByTweet(tweet);
        favoriteRepo.deleteFavoriteByTweet(tweet);

        if(tweetRepo.existsTweetByTweetIdAndUser(tweetRequest.getTweetId(), user))
        {
            tweetRepo.deleteById(tweetRequest.getTweetId());
            return true;
        }
        else
        {
            return false;
        }
//        tweetRepo.deleteById();
    }


   public Optional<List<Tweet>> findTweetByUser(Long userId)
   {
       return tweetRepo.findTweetByUser_UserId(userId);
   }

    public String usernameFromToken(@RequestHeader(AUTHORIZATION) String token) throws IOException
    {
        String username = null;
        if (token != null)
        {
            try
            {
                Algorithm algo = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algo).build();
                DecodedJWT decodedJWT = verifier.verify(token);
                username = decodedJWT.getSubject();

            } catch (IllegalArgumentException e)
            {
                e.printStackTrace();
            } catch (JWTVerificationException e)
            {
                e.printStackTrace();
            }
        } else
        {
            throw new RuntimeException("Refresh token is missing !");
        }
        System.out.println(username);
        return username;
    }

    @Transactional
    public Retweet retweetTweet(@RequestHeader(AUTHORIZATION) String token, RetweetFavoriteRequest retweetRequest) throws IOException
    {
        User user = getUserByToken(token);
        Tweet tweet = tweetRepo.findTweetByTweetId(retweetRequest.getTweetId()).orElseThrow();
        Retweet rt = new Retweet();

        rt.setTweet(tweet);
        rt.setUser(user);

        return retweetRepo.saveAndFlush(rt);
    }

    @Transactional
    public void unRetweetTweet(@RequestHeader(AUTHORIZATION) String token, RetweetFavoriteRequest retweetRequest) throws IOException
    {
        User user = getUserByToken(token);
        Tweet tweet = tweetRepo.findTweetByTweetId(retweetRequest.getTweetId()).orElseThrow();

        retweetRepo.deleteRetweetByTweetAndUser(tweet, user);
    }

    @Transactional
    public int nbRetweet(Tweet tweet)
    {
        return retweetRepo.countRetweetByTweet(tweet);
    }

    public boolean isRt(@RequestHeader String token, RetweetFavoriteRequest rq) throws IOException
    {
        User user = getUserByToken(token);
        Tweet tweet = tweetRepo.findTweetByTweetId(rq.getTweetId()).orElseThrow();

        return rtExist(tweet, user);
    }

    public boolean isFav(@RequestHeader String token, RetweetFavoriteRequest rq) throws IOException
    {
        User user = getUserByToken(token);
        Tweet tweet = tweetRepo.findTweetByTweetId(rq.getTweetId()).orElseThrow();

        return favExist(tweet, user);
    }

    @Transactional
    public Favorite favoriteTweet(@RequestHeader(AUTHORIZATION) String token, RetweetFavoriteRequest favoriteRequest) throws IOException
    {
        User user = getUserByToken(token);
        Tweet tweet = tweetRepo.findTweetByTweetId(favoriteRequest.getTweetId()).orElseThrow();

        Favorite fav = new Favorite();

        fav.setTweet(tweet);
        fav.setUser(user);

        return favoriteRepo.saveAndFlush(fav);
    }

    @Transactional
    public void unFavoriteTweet(@RequestHeader(AUTHORIZATION) String token, RetweetFavoriteRequest favoriteRequest) throws IOException
    {
        User user = getUserByToken(token);
        Tweet tweet = tweetRepo.findTweetByTweetId(favoriteRequest.getTweetId()).orElseThrow();

        favoriteRepo.deleteByTweetAndUser(tweet, user);
    }

    @Transactional
    public int nbFav(Tweet tweet)
    {
        return favoriteRepo.countFavoriteByTweet(tweet);
    }

    public User getUserByToken(String token) throws IOException
    {
        String username = usernameFromToken(token);
        User user = userRepo.findUserByUsername(username).orElseThrow();

        return user;
    }
}
