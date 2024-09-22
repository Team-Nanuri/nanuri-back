package team.hackerping.nanuri.chat.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.hackerping.nanuri.article.persistence.ArticleRepository;
import team.hackerping.nanuri.chat.application.command.ChatMessageCommand;
import team.hackerping.nanuri.chat.application.command.ChatRoomCommand;
import team.hackerping.nanuri.chat.application.dto.ChatRoomInfo;
import team.hackerping.nanuri.chat.domain.ChatRoom;
import team.hackerping.nanuri.chat.persistence.ChatRepository;
import team.hackerping.nanuri.global.exception.NanuriException;
import team.hackerping.nanuri.global.exception.code.GeneralError;
import team.hackerping.nanuri.user.persistence.UserRepository;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final ChatRepository chatRepository;

    @Transactional
    public Long createRoom(ChatRoomCommand.Create command) {
        return chatRepository.findChatRoomByArticleIdAndRecipientId(command.articleId(), command.senderId())
                .map(ChatRoom::getId)
                .orElseGet(() -> {
                    // 기존 채팅방이 없을 경우 새로운 채팅방 생성
                    var user = userRepository.findById(command.senderId())
                            .orElseThrow(() -> new NanuriException(GeneralError.NOT_FOUND, "사용자"));
                    var article = articleRepository.findById(command.articleId())
                            .orElseThrow(() -> new NanuriException(GeneralError.NOT_FOUND, "게시글"));

                    var room = ChatRoom.of(article, user);
                    chatRepository.save(room);
                    return room.getId();
                });
    }

    @Transactional
    public void sendMessage(ChatMessageCommand.Create command) {
        var room = chatRepository.findById(command.roomId())
                .orElseThrow(() -> new NanuriException(GeneralError.NOT_FOUND, "채팅방"));

        room.sendMessage(command.senderId(), command.message());
    }

    public ChatRoomInfo.Paging getRooms(Long userId, Pageable pageable) {
        var chatRooms = chatRepository.findAllChatRoomById(userId, pageable);

        return ChatRoomInfo.Paging.from(chatRooms);
    }
}
