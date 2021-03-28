package firstaid.app.Chat.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatNotification {

    private Long id;
    private int senderId;
    private String senderName;
}
