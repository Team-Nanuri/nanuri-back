package team.hackerping.nanuri.chat.application.dto;

public class ChatMessageCommand {

    public record Create(
            Long roomId,
            Long senderId,
            String message
    ) {
    }
}
