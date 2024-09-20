package team.hackerping.nanuri.chat.presentation.dto;

public class ChatRequest {
    public record SendMessage(
            Long receiverId,
            Long articleId,
            String message
    ) { }
}