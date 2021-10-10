package pwa.projet.wintter.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Table(name = "role")
@Entity(name = "role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "role_id",
            updatable = false
    )
    private Long roleId;

    @Column(
            name = "role_type",
            columnDefinition = "TEXT"
    )
    private String type;
}
