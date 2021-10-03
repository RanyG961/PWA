package pwa.projet.wintter.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name="chat")
@Entity(name="Chat")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Chat
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "chat_id",
            nullable = false
    )
    private Long chat_id;

    @Column(
            name="message",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String message;

}
