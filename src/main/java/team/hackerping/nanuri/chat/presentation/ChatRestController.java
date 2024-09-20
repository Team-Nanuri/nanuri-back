package team.hackerping.nanuri.chat.presentation;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.hackerping.nanuri.chat.presentation.dto.ChatRequest;
import team.hackerping.nanuri.chat.presentation.dto.ChatResponse.Paging;
import team.hackerping.nanuri.chat.presentation.dto.ChatResponse.RoomDetail;

@RestController
@RequestMapping("/api/chat")
public class ChatRestController implements ChatController {

    @Override
    @PostMapping()
    public void sendMessage(ChatRequest.SendMessage request) {
        //Todo
    }

    @Override
    @GetMapping()
    public ResponseEntity<Paging> getRooms(
            @PageableDefault Pageable pageable
    ) {
        return null;
    }

    @Override
    @GetMapping("/{roomId}")
    public ResponseEntity<RoomDetail> getMessage(@PathVariable Long roomId) {
        return null;
    }
}
