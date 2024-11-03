package collab.collabproject.repository.interfaces;

import collab.collabproject.models.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> getUserById(int id);
    List<User> getUsers();
    Optional<User> addUser(String username, String email, String password);
    Optional<User> updateUser(int id, String username, String email);
    ResponseEntity<?> deleteUserById(int id);
}
