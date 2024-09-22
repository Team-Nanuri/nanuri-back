package team.hackerping.nanuri.chat.presentation.dto;

import team.hackerping.nanuri.chat.application.command.ChatMessageCommand;
import team.hackerping.nanuri.chat.application.command.ChatRoomCommand;

public class ChatRequest {
    public record Create(
            Long articleId
    ) {
        public ChatRoomCommand.Create toCommand(Long userId) {
            return new ChatRoomCommand.Create(articleId, userId);
        }
    }

    public record SendMessage(
            Long roomId,
            String message
    ) {
        public ChatMessageCommand.Create toCommand(Long userId) {
            return new ChatMessageCommand.Create(roomId, userId, message);
        }
    }
}