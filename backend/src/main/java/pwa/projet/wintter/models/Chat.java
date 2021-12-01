package pwa.projet.wintter.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(
            name = "message_id",
            referencedColumnName = "message_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_chat_message_id")
    )
    private Message message;

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
