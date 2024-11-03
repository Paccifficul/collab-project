package collab.collabproject.service.interfaces;

import collab.collabproject.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User getUser(int userId);
    List<User> getUsers();
    Optional<User> addUser(String username, String email, String password);
}
