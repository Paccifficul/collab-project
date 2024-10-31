package collab.collabproject.models;

public record User(
        int id, String username, String email, String password
) {}
