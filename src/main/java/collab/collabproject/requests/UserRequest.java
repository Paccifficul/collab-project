package collab.collabproject.requests;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

public record UserRequest (
    @NotNull
    String username,
    @NotNull
    String email,
    @NotNull
    @Size(min = 7)
    String password
) {}
