package pwa.projet.wintter.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Table(name="Tweets")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tweet
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long tweetId;

    private String content;
    private Instant createdTime;
}
