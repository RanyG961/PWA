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
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follow_id", nullable = false)
    private Long followId;

    @ManyToOne
    @JoinColumn(
            name="user_id",
            referencedColumnName = "user_id",
            nullable = false,
            insertable = false,
            updatable = false,
            foreignKey = @ForeignKey(name = "FK_follower_user_id")
    )
    private User follower;

    @ManyToOne
    @JoinColumn(
            name="user_id",
            referencedColumnName = "user_id",
            nullable = false,
            insertable = false,
            updatable = false,
            foreignKey = @ForeignKey(name = "FK_following_user_id")
    )
    private User following;
}
