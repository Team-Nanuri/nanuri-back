package team.hackerping.nanuri.chat.presentation.dto;

import java.time.LocalDateTime;
import java.util.List;
import team.hackerping.nanuri.article.presentation.dto.ArticleResponse;
import team.hackerping.nanuri.chat.application.dto.ChatRoomInfo;
import team.hackerping.nanuri.user.presentation.dto.UserResponse;

public class ChatResponse {

    public record Paging(
            List<RoomInfo> contents,
            Integer totalPages
    ) {
        public static Paging from(ChatRoomInfo.Paging paging) {
            return new Paging(paging.contents().stream()
                    .map(RoomInfo::from)
                    .toList(), paging.totalPages());
        }
    }

    public record RoomInfo(
            Long roomId,
            ArticleResponse.Simple article,
            ChatMessage lastMessage,
            UserResponse.MaskedUserDto otherUser
    ) {
        public static RoomInfo from(ChatRoomInfo.Simple simple) {
            return new RoomInfo(
                    simple.roomId(),
                    ArticleResponse.Simple.from(simple.article()),
                    ChatMessage.from(simple.lastMessage()),
                    UserResponse.MaskedUserDto.from(simple.otherUser())
            );
        }
    }

    public record RoomDetail(
            Long roomId,
            ArticleResponse.Simple article,
            List<ChatMessage> messages
    ) {
        public static RoomDetail from(ChatRoomInfo.Detail detail) {
            return new RoomDetail(
                    detail.id(),
                    ArticleResponse.Simple.from(detail.article()),
                    detail.messages().stream()
                            .map(ChatMessage::from)
                            .toList()
            );
        }
    }

    public record ChatMessage(
            Long senderId,
            Long receiverId,
            String message,
            LocalDateTime createdAt
    ) {
        public static ChatMessage from(team.hackerping.nanuri.chat.application.dto.ChatMessageInfo.Detail detail) {
            return new ChatMessage(detail.senderId(),
                    detail.receiverId(),
                    detail.message(),
                    detail.createdAt()
            );
        }
    }
}
