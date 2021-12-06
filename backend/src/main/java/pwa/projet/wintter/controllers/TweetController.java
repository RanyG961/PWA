package pwa.projet.wintter.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pwa.projet.wintter.models.Tweet;
import pwa.projet.wintter.requests.MessageRequest;
import pwa.projet.wintter.requests.TweetRequest;
import pwa.projet.wintter.services.TweetService;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import javax.persistence.Entity;
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


    @PostMapping("/addTweet")
    public ResponseEntity<String> addTweet(@RequestHeader(AUTHORIZATION) String token, @RequestBody TweetRequest tweetRequest) throws IOException
    {
        String username = tweetService.usernameFromToken(token);
        System.out.println(tweetRequest.getContent() + " media : " + tweetRequest.getMedia());
        tweetService.addTweet(tweetRequest, username);
        return new ResponseEntity<>("Test added !", HttpStatus.OK);
    }

    @PostMapping(value = "/allTweets", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> allTweets(@RequestHeader(AUTHORIZATION) String token) throws IOException, JSONException
    {
        String username = tweetService.usernameFromToken(token);
        List<Tweet> listTweets = tweetService.findAllTweets();

        HashMap<Integer, Object> hashTweets = tweetService.hashFindAllTweet(listTweets);
//        List<JSONObject> allT = tweetService.jsonFindAllTweet();
//        Hashtable hashTweets = tweetService.hashFindAllTweet(listTweets);

        return new ResponseEntity<>(hashTweets, HttpStatus.OK);
    }
}
