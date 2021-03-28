package firstaid.app.Chat.service;

import firstaid.app.Chat.model.ChatMessage;
import firstaid.app.Chat.model.MessageStatus;
import firstaid.app.Chat.repository.ChatMessageRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChatMessageService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;
    @Autowired
    private ChatRoomService chatRoomService;

    public ChatMessage save(ChatMessage chatMessage) {
        chatMessage.setStatus(MessageStatus.RECEIVED);
        chatMessageRepository.save(chatMessage);
        return chatMessage;
    }

    public List<ChatMessage> findByChatMessage(int senderId, int recipientId) {
        var chatId = chatRoomService.getChatId(senderId, recipientId, false);
        var messages =  chatId.map(cId -> chatMessageRepository.findByChatId(cId)).orElse(new ArrayList<>());

        if(messages.size() >0) {
            chatMessageRepository.updateStatus(senderId, recipientId);
        }
        return messages;
    }

    public ChatMessage findById(Long id) throws NotFoundException {
        return chatMessageRepository
                .findById(id)
                .map(chatMessage -> {
                    chatMessage.setStatus(MessageStatus.DELIVERED);
                    return chatMessageRepository.save(chatMessage);
                })
                .orElseThrow(() ->
                        new NotFoundException("can't find message (" + id + ")"));
    }
}
