package team.hackerping.nanuri.chat.application.dto;

import java.time.LocalDateTime;
import team.hackerping.nanuri.chat.domain.ChatMessage;

public class ChatMessageInfo {
    public record Detail(
            Long senderId,
            Long receiverId,
            String message,
            LocalDateTime createdAt
    ) {
        public static Detail from(ChatMessage chatMessage) {
            return new Detail(chatMessage.getSenderId(),
                    chatMessage.getReceiverId(),
                    chatMessage.getMessage(),
                    chatMessage.getCreatedAt()
            );
        }
    }
}
