package pwa.projet.wintter.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Table(
        name = "user",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "user_email_unique",
                        columnNames = "email"
                ),
                @UniqueConstraint(
                        name = "user_nickname_unique",
                        columnNames = "nick_name"
                )
        }
)
@Entity(name = "user")
//@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name="user_id",
            updatable = false
    )
    private Long userId;

    @Column(
            name="first_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String firstName;

    @Column(
            name="last_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String lastName;

    @Column(
            name="nick_name",
            nullable = false,
            columnDefinition = "TEXT",
            unique = true
    )
    private String nickName;

    @Column(
            name="email",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String email;

    @Column(
            name="password",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String password;

    @Column(
            name="biography",
            columnDefinition = "TEXT"
    )
    private String biography;

    @Column(
            name="birth_date",
            nullable = false
    )
    private LocalDate birthDate;

    @Column(
            name="location",
            columnDefinition = "TEXT"
    )
    private String location;

    @Column(
            name="website",
            columnDefinition = "TEXT"
    )
    private String website;

    @Column(
            name="profile_picture",
            columnDefinition = "TEXT"
    )
    private String profilePicture;

    @Column(
            name="profile_banner",
            columnDefinition = "TEXT"
    )
    private String profileBanner;
//
//    @OneToMany(targetEntity = Tweet.class, mappedBy = "user")
//    private List<Tweet> tweets = new ArrayList<>();
//
//    @OneToMany(targetEntity = Chat.class, mappedBy = "user")
//    private List<Chat> chats = new ArrayList<>();

    public User(String firstName, String lastName, String nickName, String email, String password, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
    }
}
