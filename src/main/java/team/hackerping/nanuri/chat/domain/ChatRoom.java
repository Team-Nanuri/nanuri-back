package team.hackerping.nanuri.chat.domain;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.hackerping.nanuri.article.domain.Article;
import team.hackerping.nanuri.user.domain.User;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CHAT_ROOM_ID")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "ARTICLE_ID")
    private Article article;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "RECIPIENT_ID")
    private User recipient;

    private LocalDateTime createdAt;

    @ElementCollection
    private List<ChatMessage> messages;

    private ChatRoom(Article article, User recipient) {
        this.article = article;
        this.recipient = recipient;
        this.createdAt = LocalDateTime.now();
        this.messages = new ArrayList<>();
    }

    public static ChatRoom of(Article article, User recipient) {
        return new ChatRoom(article, recipient);
    }

    public void sendMessage(Long senderId, String message) {
        var receiverId = getReceiverId(senderId);
        var chatMessage = ChatMessage.of(senderId, receiverId, message);
        messages.add(chatMessage);
    }

    private long getReceiverId(Long senderId) {
        return senderId.equals(recipient.getId()) ? article.getWriter().getId() : recipient.getId();
    }

    public ChatMessage getLastMessage() {
        return messages.get(messages.size() - 1);
    }

    public boolean isParticipant(Long userId) {
        return recipient.getId().equals(userId) || article.getWriter().getId().equals(userId);
    }

    public boolean isNotParticipant(Long userId) {
        return !isParticipant(userId);
    }
}
