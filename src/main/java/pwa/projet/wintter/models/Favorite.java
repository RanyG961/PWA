package pwa.projet.wintter.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "favorite")
@Entity(name = "Favorite")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Favorite
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorite_id", nullable = false)
    private Long favoriteId;

    @ManyToOne
    @JoinColumn(
            name="user_id",
            referencedColumnName = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_fav_user_id")
    )
    private User user;

    @ManyToOne
    @JoinColumn(
            name="tweet_id",
            referencedColumnName = "tweet_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_fav_tweet_id")
    )
    private Tweet tweet;
}
