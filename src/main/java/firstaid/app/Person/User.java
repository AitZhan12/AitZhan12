package firstaid.app.Person;
import com.fasterxml.jackson.annotation.JsonIgnore;
import firstaid.app.Diagnose.Diagnose;
import firstaid.app.Location.Location;
import firstaid.app.Person.token.ConfirmationToken;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Data
@Table(name = "users")
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String secondName;
    private String jobType;
    private String email;
    private String userName;
    private String password;
    private int age;
    private int phone;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn
    @JsonIgnore
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn
    @JsonIgnore
    private Diagnose diagnose;
    private Boolean locked;
    private Boolean enabled;

    public User(String firstName, String secondName, String jobType, String email, String userName, String password,
                int age, int phone,
                UserRole userRole) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.jobType = jobType;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.age = age;
        this.phone = phone;
        this.userRole = userRole;
        this.locked = false;

    }

    public User() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(userRole.name());
        return Collections.singletonList(simpleGrantedAuthority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
