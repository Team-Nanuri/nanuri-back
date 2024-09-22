package team.hackerping.nanuri.chat.persistence;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import team.hackerping.nanuri.chat.domain.ChatRoom;

public interface ChatRepository extends JpaRepository<ChatRoom, Long> {
    Optional<ChatRoom> findChatRoomByArticleIdAndRecipientId(Long articleId, Long recipientId);
}
