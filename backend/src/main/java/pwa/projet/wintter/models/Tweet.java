package pwa.projet.wintter.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

//    @OneToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(
//            name = "message_id",
//            referencedColumnName = "message_id",
//            nullable = false,
//            foreignKey = @ForeignKey(name = "FK_tweet_message_id")
//    )
//    private Message message;

    @Column(
            name = "content",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String content;

    @Column(
            name = "media",
            columnDefinition = "TEXT"
    )
    private String media;

    @Column(
            name = "created_time",
            nullable = false
    )
    private Instant createdTime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_tweet_user_id")
    )
    @JsonBackReference
    private User user;
}
