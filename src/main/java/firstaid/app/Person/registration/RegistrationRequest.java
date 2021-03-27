package firstaid.app.Person.registration;

import firstaid.app.Person.UserRole;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {

    private final String firstName;
    private final String secondName;
    private final String jobType;
    private final String email;
    private final String userName;
    private final  String password;
    private final int age;
    private final int phone;
    private final UserRole userRole;
}
