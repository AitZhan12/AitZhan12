package firstaid.app.Chat;

import firstaid.app.Chat.model.ChatMessage;
import firstaid.app.Chat.model.ChatNotification;
import firstaid.app.Chat.service.ChatMessageService;
import firstaid.app.Chat.service.ChatRoomService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("send")
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private ChatMessageService chatMessageService;
    @Autowired
    private ChatRoomService chatRoomService;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage) {
        var chatId = chatRoomService.getChatId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true);
        chatMessage.setChatId(chatId.get());

        ChatMessage saved = chatMessageService.save(chatMessage);
        messagingTemplate.convertAndSendToUser(
                String.valueOf(chatMessage.getRecipientId()), "queue/messages",
                new ChatNotification(
                        saved.getId(),
                        saved.getSenderId(),
                        saved.getSenderName()
                )
        );
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<?> findChatMessages ( @PathVariable("senderId") int senderId,
                                                @PathVariable("recipientId") int recipientId) {
        return ResponseEntity
                .ok(chatMessageService.findByChatMessage(senderId, recipientId));
    }

    @GetMapping("/messages/{id}")
    public ChatMessage findMessage (@PathVariable("id") Long id) {
        try {
            return chatMessageService.findById(id);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

