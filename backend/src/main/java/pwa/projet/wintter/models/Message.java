package pwa.projet.wintter.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
            name = "created_time"
    )
    private Instant createdTime;

}
