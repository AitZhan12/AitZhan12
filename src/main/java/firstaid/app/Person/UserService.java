package firstaid.app.Person;

import firstaid.app.Person.token.ConfirmationToken;
import firstaid.app.Person.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ConfirmationTokenService tokenService;
    private final String USER_NOT_FOUND = "user with %s email not found";


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));
    }

    public String signUp(User user) {

        boolean isUserExist = userRepository.findByEmail(user.getEmail()).isPresent();
        if(isUserExist) {
            try {
                throw new IllegalAccessException("user with %s email is exists");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user

        );
        tokenService.saveConfirmation(confirmationToken);
        return  token;
    }
    public int enableAppUser(String email) {
        return userRepository.enableAppUser(email);
    }
}
