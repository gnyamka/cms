package com.notetaking.note_taking.Controller;

import com.notetaking.note_taking.model.core.CreateUserRequest;
import com.notetaking.note_taking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import com.notetaking.note_taking.model.core.User;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(value = "/create")
    public String createUser(Model model) {
        CreateUserRequest form = new CreateUserRequest();
        model.addAttribute("moduleForm",form);
        return "create-user";
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request) {

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // Hash this in real apps!
        user.setCreatedAt(new Date());

        userService.save(user);

        return ResponseEntity.ok("User created successfully.");
    }

}
