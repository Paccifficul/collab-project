package collab.collabproject.controller;

import collab.collabproject.models.User;
import collab.collabproject.request.UserRequest;
import collab.collabproject.service.interfaces.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
