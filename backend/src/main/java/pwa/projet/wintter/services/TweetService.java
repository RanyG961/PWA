package pwa.projet.wintter.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestHeader;
import pwa.projet.wintter.models.Message;
import pwa.projet.wintter.models.Tweet;
import pwa.projet.wintter.models.User;
import pwa.projet.wintter.repositories.MessageRepository;
import pwa.projet.wintter.repositories.TweetRepository;
import pwa.projet.wintter.repositories.UserRepository;
import pwa.projet.wintter.requests.MessageRequest;
import pwa.projet.wintter.requests.RegisterRequest;
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

    @Transactional
    public Tweet addTweet(TweetRequest tweetRequest, String username)
    {
//        if(messageRequest.getMedia().equals("null"))
//        {
//            messageRequest.setMedia("");
//        }
        Tweet tweet = new Tweet();
//        Message message = new Message(messageRequest.getContent(), messageRequest.getMedia(), Instant.now());
        User user;

        user = userRepo.findUserByUsername(username).orElseThrow();

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

    public HashMap<Integer, Object> hashFindAllTweet(List<Tweet> allTweets)
    {
        HashMap<Integer, Object> hashL = new HashMap<>();
        Integer hashId = 0;

        for(Tweet t : allTweets)
        {
            HashMap<String, Object> hashT = new HashMap<>();

            hashT.put("id", t.getTweetId());
            hashT.put("content", t.getContent());
            hashT.put("media", t.getMedia());
            hashT.put("createdTime", t.getCreatedTime());
            hashT.put("username", t.getUser().getUsername());
            hashL.put(hashId, hashT);
            hashId++;
        }

        return hashL;
    }

    public void deleteTweet(Long id)
    {
        tweetRepo.deleteById(id);
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
}
