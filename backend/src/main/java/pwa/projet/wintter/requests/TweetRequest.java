package pwa.projet.wintter.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pwa.projet.wintter.models.Message;
import pwa.projet.wintter.models.User;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TweetRequest
{
    private String content;
    private String media;
    private Long userId;
}
