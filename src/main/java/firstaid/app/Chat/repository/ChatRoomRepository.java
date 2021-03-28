package firstaid.app.Chat.repository;

import firstaid.app.Chat.model.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    @Query(value = "SELECT * from chat_room where sender_id=1? and recipient_id=2?", nativeQuery = true)
    Optional<ChatRoom> findBySenderIdAndRecipientId(int senderId, int recipientId);
}
