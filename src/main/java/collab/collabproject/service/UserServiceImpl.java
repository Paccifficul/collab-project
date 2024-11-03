package collab.collabproject.service;

import collab.collabproject.models.User;
import collab.collabproject.repository.interfaces.UserRepository;
import collab.collabproject.service.interfaces.UserService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Primary
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(int personId) {
        return userRepository.getUserById(personId)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + personId));
    }

    @Override
    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    @Override
    public Optional<User> addUser(String username, String email, String password) {
        return userRepository.addUser(username, email, password);
    }
}
