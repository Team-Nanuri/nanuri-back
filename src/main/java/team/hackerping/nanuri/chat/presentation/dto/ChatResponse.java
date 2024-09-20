package team.hackerping.nanuri.chat.presentation.dto;

import java.time.LocalDateTime;
import java.util.List;

import team.hackerping.nanuri.article.presentation.dto.ArticleResponse;
import team.hackerping.nanuri.user.presentation.dto.UserResponse;

public class ChatResponse {

    public record Paging(
            List<RoomInfo> contents,
            Integer totalPages
    ) { }

    public record RoomInfo(
            Long roomId,
            ArticleResponse.Simple article,
            ChatMessage lastMessage,
            UserResponse.MaskedUserDto otherUser
    ) { }

    public record RoomDetail(
            Long roomId,
            ArticleResponse.Simple article,
            List<ChatMessage> messages
    ) { }

    public record ChatMessage(
            Long senderId,
            Long receiverId,
            String message,
            LocalDateTime createdAt
    ) { }
}
