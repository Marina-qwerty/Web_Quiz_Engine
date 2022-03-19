package engine.service;

import engine.businesslayer.User;
import engine.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import javax.annotation.Resource;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private PasswordEncoder encoder;

    public Optional<User> findUserById(String id) {
        return userRepository.findById(id);
    }

    public User save(User userToSave) {
        Optional<User> user = userRepository.findById(userToSave.getEmail());
        if (user.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        userToSave.setPassword(encoder.encode(userToSave.getPassword()));
        return userRepository.save(userToSave);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findById(username).get();
    }
}
