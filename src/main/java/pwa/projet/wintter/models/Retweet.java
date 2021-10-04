package pwa.projet.wintter.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "retweet")
@Entity(name = "Retweet")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Retweet
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "retweet_id", nullable = false)
    private Long retweetId;

    @ManyToOne
    @JoinColumn(
            name="user_id",
            referencedColumnName = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_rt_user_id")
    )
    private User user;

    @ManyToOne
    @JoinColumn(
            name="tweet_id",
            referencedColumnName = "tweet_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_rt_tweet_id")
    )
    private Tweet tweet;
}
