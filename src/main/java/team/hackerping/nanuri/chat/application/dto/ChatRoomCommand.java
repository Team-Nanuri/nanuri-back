package team.hackerping.nanuri.chat.application.dto;

public class ChatRoomCommand {
    public record Create(
            Long articleId,
            Long senderId
    ) {
    }
}
