package collab.collabproject.repositories.interfaces;

import collab.collabproject.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> getUserById(int id);

    List<User> getUsers();
}
