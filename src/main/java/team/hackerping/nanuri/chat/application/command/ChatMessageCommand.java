package team.hackerping.nanuri.chat.application.command;

public class ChatMessageCommand {

    public record Create(
            Long roomId,
            Long senderId,
            String message
    ) {
    }
}
