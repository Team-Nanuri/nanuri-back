package team.hackerping.nanuri.chat.application.dto;

import java.util.List;
import org.springframework.data.domain.Page;
import team.hackerping.nanuri.article.application.info.ArticleInfo;
import team.hackerping.nanuri.chat.domain.ChatRoom;
import team.hackerping.nanuri.user.application.UserInfo;

public class ChatRoomInfo {
    public record Paging(
            List<Simple> contents,
            Integer totalPages
    ) {
        public static Paging from(Page<ChatRoom> page) {
            var chatRooms = page.getContent();
            return new Paging(chatRooms.stream()
                    .map(Simple::from)
                    .toList(), page.getTotalPages());
        }
    }

    public record Simple(
            ArticleInfo.Simple article,
            ChatMessageInfo.Detail lastMessage,
            UserInfo otherUser
    ) {
        public static Simple from(ChatRoom chatRoom) {
            return new Simple(
                    ArticleInfo.Simple.from(chatRoom.getArticle()),
                    ChatMessageInfo.Detail.from(chatRoom.getLastMessage()),
                    UserInfo.from(chatRoom.getRecipient())
            );
        }
    }
}