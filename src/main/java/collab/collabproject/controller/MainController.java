package collab.collabproject.controller;

import collab.collabproject.models.User;
import collab.collabproject.request.UserRequest;
import collab.collabproject.service.interfaces.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/profiles")
public class MainController {

    private final UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{personId:\\d+}")
    public User getUserById(@PathVariable int personId) {
        return userService.getUser(personId);
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getUsers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@Valid @RequestBody UserRequest request) {
        userService.addUser(
                request.username(),
                request.email(),
                request.password()
        );
    }

    @PutMapping("/{id:\\d+}")
    public Optional<User> updateProduct(@RequestBody UserRequest request, @PathVariable int id) {
        return userService.updateUser(
                id,
                request.username(),
                request.email()
        );
    }

    @DeleteMapping("/{id:\\d+}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }
}
