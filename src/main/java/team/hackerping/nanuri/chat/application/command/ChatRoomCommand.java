package team.hackerping.nanuri.chat.application.command;

public class ChatRoomCommand {
    public record Create(
            Long articleId,
            Long senderId
    ) {
    }
}
