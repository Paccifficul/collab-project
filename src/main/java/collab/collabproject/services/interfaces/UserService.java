package collab.collabproject.services.interfaces;

import collab.collabproject.models.User;

import java.util.List;

public interface UserService {
    User getUser(int userId);
    List<User> getUsers();
}
