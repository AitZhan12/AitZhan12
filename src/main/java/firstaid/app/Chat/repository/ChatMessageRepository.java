package firstaid.app.Chat.repository;

import firstaid.app.Chat.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    @Query(value = "SELECT * from chat_message where chat_id=1?", nativeQuery = true)
    List<ChatMessage> findByChatId(int chatId);

    @Query(value = "UPDATE chat_message set status='DELIVERED' where sender_id=1? and recipient_id=2?", nativeQuery = true)
    void updateStatus(int senderId, int recipientId);
}
