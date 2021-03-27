package firstaid.app.Diagnose;

import firstaid.app.Person.User;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "diagnose")
public class Diagnose {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;

    @OneToMany(mappedBy = "diagnose", cascade = CascadeType.ALL)
    private List<User> userList;
}
