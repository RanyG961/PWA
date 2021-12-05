package pwa.projet.wintter.models;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Table(name = "tweet")
@Entity(name = "Tweet")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tweet
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "tweet_id",
            updatable = false
    )
    private long tweetId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "message_id",
            referencedColumnName = "message_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_tweet_message_id")
    )
    private Message message;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_tweet_user_id")
    )
    private User user;
}
