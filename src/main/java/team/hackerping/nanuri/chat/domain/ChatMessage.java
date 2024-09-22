package team.hackerping.nanuri.chat.domain;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessage {
    @NotNull
    private Long senderId;
    @NotNull
    private Long receiverId;
    @NotNull
    private String message;

    @NotNull
    private LocalDateTime createdAt;

    private ChatMessage(Long senderId, Long receiverId, String message) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
        this.createdAt = LocalDateTime.now();
    }

    public static ChatMessage of(Long senderId, Long receiverId, String message) {
        return new ChatMessage(senderId, receiverId, message);
    }
}
