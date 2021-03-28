package firstaid.app.Chat.service;

import firstaid.app.Chat.model.ChatRoom;
import firstaid.app.Chat.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatRoomService {
    @Autowired
    private ChatRoomRepository chatRoomRepository;

    public Optional<Integer> getChatId(int senderId, int recipientId, boolean createIfNotExist) {
        return chatRoomRepository.findBySenderIdAndRecipientId(senderId, recipientId)
                .map(ChatRoom::getChatId).or(()-> {
                    if(!createIfNotExist) {
                        return Optional.empty();
                    }
                    int chatId = Integer.parseInt(String.format("%s_%s", senderId, recipientId));

                    ChatRoom senderRecipient = ChatRoom
                            .builder()
                            .chatId(chatId)
                            .senderId(senderId)
                            .recipientId(recipientId)
                            .build();
                    ChatRoom recipientSender = ChatRoom
                            .builder()
                            .chatId(chatId)
                            .senderId(recipientId)
                            .recipientId(senderId)
                            .build();
                    chatRoomRepository.save(senderRecipient);
                    chatRoomRepository.save(recipientSender);
                    return Optional.of(chatId);
                });
    }
}
