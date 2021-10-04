package pwa.projet.wintter.models;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Table(name="tweet")
@Entity(name="Tweet")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tweet
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name="tweet_id",
            updatable = false
    )
    private long tweetId;

//    @Column(
//            name="content",
//            nullable = false,
//            columnDefinition = "TEXT"
//    )
//    private String content;
//
//    @Column(
//            name="media",
//            columnDefinition = "TEXT"
//    )
//    private String media;
//
//    @Column(
//            name="created_time"
//    )
//    private Instant createdTime;

//    @OneToOne(optional = false)
//    @JoinColumn(name = "message_id",
//                nullable = false,
//                columnDefinition = "TEXT")
//    private Message message;

    @ManyToOne
    @JoinColumn(
            name = "message_id",
            referencedColumnName = "message_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_tweet_message_id")
    )
    private Message message;

    @ManyToOne
    @JoinColumn(
                name="user_id",
                referencedColumnName = "user_id",
                nullable = false,
                foreignKey = @ForeignKey(name = "FK_tweet_user_id")
    )
    private User user;
}
