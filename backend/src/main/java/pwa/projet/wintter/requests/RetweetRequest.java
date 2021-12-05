package pwa.projet.wintter.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pwa.projet.wintter.models.Tweet;
import pwa.projet.wintter.models.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RetweetRequest
{
    private User user;
    private Tweet tweet;
}
