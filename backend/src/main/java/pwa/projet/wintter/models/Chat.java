package pwa.projet.wintter.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Table(name = "chat")
@Entity(name = "Chat")
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
    private Long chatId;

//    @ManyToOne
//    @JoinColumn(
//            name = "message_id",
//            referencedColumnName = "message_id",
//            nullable = false,
//            foreignKey = @ForeignKey(name = "FK_chat_message_id")
//    )
//    private Message message;

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

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_chat_user_id")
    )
    private User user;

    @ManyToOne
    @JoinColumn(name = "user_2_id",
            referencedColumnName = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_chat_user2_id")
    )
    private User user2;

}
