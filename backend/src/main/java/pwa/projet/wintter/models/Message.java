package pwa.projet.wintter.models;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Table(name = "message")
@Entity(name = "message")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id", nullable = false)
    private Long messageId;

    @Column(
            name = "content",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String content;

    @Column(
            name = "media",
            columnDefinition = "TEXT"
    )
    private String media;

    @Column(
            name = "created_time",
            nullable = false
    )
    private Instant createdTime;

    public Message(String content, String media, Instant createdTime)
    {
        this.content = content;
        this.media = media;
        this.createdTime = createdTime;
    }
}
