package firstaid.app.Person.token;

import firstaid.app.Person.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ConfirmationToken {

    @SequenceGenerator(
            name = "confirmation_token_sequence",
            sequenceName = "confirmation_token_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "confirmation_token_sequence"
    )
    private Long id;
    private String token;
    private LocalDateTime createdDate;
    private LocalDateTime expiresAt;
    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(nullable = false,
                name = "user_id")
    private User user;

    public ConfirmationToken(String token, LocalDateTime createdDate,
                             LocalDateTime expiresAt,User user) {
        this.token = token;
        this.createdDate = createdDate;
        this.expiresAt = expiresAt;
        this.user = user;
    }
}
