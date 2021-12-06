package pwa.projet.wintter.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pwa.projet.wintter.models.Tweet;
import pwa.projet.wintter.requests.RetweetFavoriteRequest;
import pwa.projet.wintter.requests.TweetRequest;
import pwa.projet.wintter.services.TweetService;

import java.io.IOException;
import java.util.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping("/api/tweet")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:8080/")
public class TweetController
{
    private final TweetService tweetService;
//    private String retweetRepo;


    @PostMapping("/addTweet")
    public ResponseEntity<String> addTweet(@RequestHeader(AUTHORIZATION) String token, @RequestBody TweetRequest tweetRequest) throws IOException
    {
        tweetService.addTweet(tweetRequest, token);
        return new ResponseEntity<>("Tweet added !", HttpStatus.OK);
    }

    @PostMapping("/deleteTweet")
    public ResponseEntity<String> deleteTweet(@RequestHeader(AUTHORIZATION) String token, @RequestBody RetweetFavoriteRequest tweetRequest) throws IOException
    {
        boolean tweetFromThisUser = tweetService.deleteTweet(token, tweetRequest);
        if(tweetFromThisUser)
        {
            return new ResponseEntity<>("Tweet deleted !", HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>("Tweet not deleted !", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping(value = "/allTweets", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> allTweets(@RequestHeader(AUTHORIZATION) String token) throws IOException, JSONException
    {
        List<Tweet> listTweets = tweetService.findAllTweets();

        HashMap<Integer, Object> hashTweets = tweetService.hashFindAllTweet(listTweets, token);

        return new ResponseEntity<>(hashTweets, HttpStatus.OK);
    }

    @PostMapping("/rt")
    public ResponseEntity<String> retweetTweet(@RequestHeader(AUTHORIZATION) String token, @RequestBody RetweetFavoriteRequest retweetFavRequest) throws IOException
    {
        tweetService.retweetTweet(token, retweetFavRequest);
        System.out.println("Rt ok : " + tweetService.isRt(token, retweetFavRequest));

        return new ResponseEntity<>("Tweet retweeted", HttpStatus.OK);
    }

    @PostMapping("/unRt")
    public ResponseEntity<String> unRtTweet(@RequestHeader(AUTHORIZATION) String token, @RequestBody RetweetFavoriteRequest retweetRequest) throws IOException
    {
        tweetService.unRetweetTweet(token, retweetRequest);

        return new ResponseEntity<>("Unretweeted successfully.", HttpStatus.OK);
    }

    @PostMapping("/fav")
    public ResponseEntity<String> favoriteTweet(@RequestHeader(AUTHORIZATION) String token, @RequestBody RetweetFavoriteRequest retweetFavRequest) throws IOException
    {
        tweetService.favoriteTweet(token, retweetFavRequest);
        System.out.println("Fav ok : " + tweetService.isFav(token, retweetFavRequest));

        return new ResponseEntity<>("Tweet fav", HttpStatus.OK);
    }

    @PostMapping("/unFav")
    public ResponseEntity<String> unFavoriteTweet(@RequestHeader(AUTHORIZATION) String token, @RequestBody RetweetFavoriteRequest favoriteRequest) throws IOException
    {
        tweetService.unFavoriteTweet(token, favoriteRequest);

        return new ResponseEntity<>("Unfavorited successfully.", HttpStatus.OK);
    }

}
