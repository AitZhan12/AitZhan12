package firstaid.app.Person.Doctor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import firstaid.app.Location.Location;
import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "doctor")
@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String position;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn
    @JsonIgnore
    private Location location;

}
