package team.hackerping.nanuri.chat.presentation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.hackerping.nanuri.chat.application.ChatService;
import team.hackerping.nanuri.chat.presentation.dto.ChatRequest;
import team.hackerping.nanuri.chat.presentation.dto.ChatRequest.Create;
import team.hackerping.nanuri.chat.presentation.dto.ChatResponse;
import team.hackerping.nanuri.chat.presentation.dto.ChatResponse.Paging;
import team.hackerping.nanuri.chat.presentation.dto.ChatResponse.RoomDetail;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class ChatRestController implements ChatController {
    private final ChatService chatService;

    @Override
    @PostMapping()
    public ResponseEntity<Long> createRoom(@RequestBody Create request,
                                           Authentication authentication
    ) {
        var userId = Long.valueOf(authentication.getPrincipal().toString());
        var command = request.toCommand(userId);
        var roomId = chatService.createRoom(command);

        return ResponseEntity.ok()
                .body(roomId);
    }

    @Override
    @PostMapping("/message")
    public void sendMessage(@RequestBody ChatRequest.SendMessage request,
                            Authentication authentication
    ) {
        var userId = Long.valueOf(authentication.getPrincipal().toString());
        var command = request.toCommand(userId);
        chatService.sendMessage(command);
    }

    @Override
    @GetMapping()
    public ResponseEntity<Paging> getRooms(@PageableDefault Pageable pageable,
                                           Authentication authentication
    ) {
        var userId = Long.valueOf(authentication.getPrincipal().toString());
        var rooms = chatService.getRooms(userId, pageable);

        var response = Paging.from(rooms);
        return ResponseEntity.ok()
                .body(response);
    }

    @Override
    @GetMapping("/{roomId}")
    public ResponseEntity<ChatResponse.RoomDetail> getMessage(@PathVariable Long roomId,
                                                              Authentication authentication

    ) {
        var userId = Long.valueOf(authentication.getPrincipal().toString());
        var room = chatService.getRoom(roomId, userId);

        var response = RoomDetail.from(room);
        return ResponseEntity.ok()
                .body(response);
    }
}
