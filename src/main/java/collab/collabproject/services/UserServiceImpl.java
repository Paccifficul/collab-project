package collab.collabproject.services;

import collab.collabproject.models.User;
import collab.collabproject.repositories.interfaces.UserRepository;
import collab.collabproject.services.interfaces.UserService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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

}
