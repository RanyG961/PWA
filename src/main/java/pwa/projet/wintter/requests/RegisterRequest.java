package pwa.projet.wintter.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest
{
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private LocalDate birthDate;
    
}
