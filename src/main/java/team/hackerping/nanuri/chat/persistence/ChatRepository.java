package team.hackerping.nanuri.chat.persistence;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import team.hackerping.nanuri.chat.domain.ChatRoom;

public interface ChatRepository extends JpaRepository<ChatRoom, Long> {
    Optional<ChatRoom> findChatRoomByArticleIdAndRecipientId(Long articleId, Long recipientId);

    @Query(value = """
            SELECT cr.*
            FROM chat_room cr
            JOIN (
                SELECT chat_room_chat_room_id, MAX(created_at) AS last_message_time
                FROM chat_room_messages
                WHERE sender_id = :userId OR receiver_id = :userId
                GROUP BY chat_room_chat_room_id
            ) cm ON cr.chat_room_id = cm.chat_room_chat_room_id
            ORDER BY cm.last_message_time DESC
            """, nativeQuery = true)
    Page<ChatRoom> findAllChatRoomById(@Param("userId") Long userId, Pageable pageable);
}
