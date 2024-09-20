package team.hackerping.nanuri.chat.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import team.hackerping.nanuri.user.domain.User;

import java.time.LocalDateTime;

@Entity
public class ChatMessage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CHAT_MESSAGE_ID")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "ROOM_ID")
    private ChatRoom room;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "SENDER_ID")
    private User sender;

    @NotNull
    private String content;
    @NotNull
    private LocalDateTime createdAt;
}
