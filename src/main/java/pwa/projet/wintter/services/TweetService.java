package pwa.projet.wintter.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pwa.projet.wintter.models.Tweet;
import pwa.projet.wintter.repositories.TweetRepository;

import java.util.List;

@Service
public class TweetService {
    private final TweetRepository tweetRepo;

    @Autowired
    public TweetService(TweetRepository tweetRepo) {
        this.tweetRepo = tweetRepo;
    }

    public Tweet addTweet(Tweet tweet)
    {
        return tweetRepo.save(tweet);
    }

    public List<Tweet> findAllTweets()
    {
        return tweetRepo.findAll();
    }

    public void deleteTweet(Long id)
    {
        tweetRepo.deleteById(id);
    }
}
