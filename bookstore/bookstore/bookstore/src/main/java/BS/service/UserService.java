package BS.service;

import BS.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    Optional<User> findByUsername(String username);
    void saveUser(User user);
    boolean isUserPresent(String username);
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    List<User> findAllUsers();
    void deleteUser(String username);
}
