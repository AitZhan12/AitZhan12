package firstaid.app.Location;

import firstaid.app.Person.Doctor.Doctor;
import firstaid.app.Person.User;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "location")
@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String address;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<User> userList;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Doctor> doctorList;
}
