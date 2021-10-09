package pwa.projet.wintter.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity(name = "Token")
@Table(name = "token")
//@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VerificationToken
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "token_id",
            updatable = false
    )
    private Long tokenID;

    @Column(
            name = "token",
            columnDefinition = "TEXT"
    )
    private String token;

    @OneToOne(orphanRemoval = true,
    fetch = FetchType.LAZY)
    @JoinColumn(name = "user_user_id")
    private User user;

    @Column(
            name = "expiry_date"
    )
    private Instant expiryDate;

}