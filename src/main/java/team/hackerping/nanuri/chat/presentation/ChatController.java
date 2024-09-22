package team.hackerping.nanuri.chat.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import team.hackerping.nanuri.chat.presentation.dto.ChatRequest;
import team.hackerping.nanuri.chat.presentation.dto.ChatResponse;

@Tag(name = "Chat", description = "채팅 API")
public interface ChatController {

    @Operation(summary = "채팅룸 생성")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "채팅룸 생성 성공, roomId 반환")
    })
    ResponseEntity<Long> createRoom(ChatRequest.Create request,
                                    Authentication authentication
    );

    @Operation(summary = "메시지 전송")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "메시지 전송 성공")
    })
    void sendMessage(ChatRequest.SendMessage request,
                     Authentication authentication
    );

    @Operation(summary = "채팅방 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "메시지 조회 성공")
    })
    ResponseEntity<ChatResponse.Paging> getRooms(@ParameterObject Pageable pageable,
                                                 Authentication authentication
    );

    @Operation(summary = "채팅방 상세 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "채팅방 상세 조회 성공")
    })
    ResponseEntity<ChatResponse.RoomDetail> getMessage(@PathVariable Long roomId,
                                                       Authentication authentication
    );

}
