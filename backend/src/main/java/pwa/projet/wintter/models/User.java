package pwa.projet.wintter.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Table(
        name = "user",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "user_email_unique",
                        columnNames = "email"
                ),
                @UniqueConstraint(
                        name = "user_username_unique",
                        columnNames = "username"
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
            name = "user_id",
            updatable = false
    )
    private Long userId;

    @Column(
            name = "first_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String firstName;

    @Column(
            name = "last_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String lastName;

    @Column(
            name = "username",
            nullable = false,
            length = 255,
            unique = true
    )
    private String username;

    @Column(
            name = "email",
            nullable = false,
            length = 255,
            unique = true
    )
    private String email;

    @Column(
            name = "password",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String password;

    @Column(
            name = "biography",
            columnDefinition = "TEXT"
    )
    private String biography;

    @Column(
            name = "birth_date",
            nullable = false
    )
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "YYYY-MM-dd")
    private LocalDate birthDate;

    @Column(
            name = "created_time",
            nullable = false
    )
    private Instant createdTime;

    @Column(
            name = "location",
            columnDefinition = "TEXT"
    )
    private String location;

    @Column(
            name = "website",
            columnDefinition = "TEXT"
    )
    private String website;

    @Column(
            name = "profile_picture",
            columnDefinition = "TEXT"
    )
    private String profilePicture;

    @Column(
            name = "profile_banner",
            columnDefinition = "TEXT"
    )
    private String profileBanner;

    @Column(
            name = "profile_enable"
    )
    private Boolean profileEnable;

    @OneToMany(mappedBy = "user")
//    @JsonIgnore
    @JsonManagedReference
    private List<Tweet> tweets = new ArrayList<>();

    @OneToMany(targetEntity = Chat.class, mappedBy = "user")
    private List<Chat> chats = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_user_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_role_id"))
    private Collection<Role> roles;

    public User(String firstName, String lastName, String username, String email, String password, LocalDate birthDate)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
    }
}
