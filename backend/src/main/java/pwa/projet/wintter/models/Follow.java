package pwa.projet.wintter.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "follow")
@Entity(name = "follow")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Follow
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follow_id", updatable = false, nullable = false)
    private Long followId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "user_follower_id",
            referencedColumnName = "user_id",
            nullable = false,
            insertable = false,
            updatable = false,
            foreignKey = @ForeignKey(name = "FK_follower_user_id")
    )
    private User follower;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_following_id",
            foreignKey = @ForeignKey(name = "FK_following_user_id")
    )
    private User following;

}
