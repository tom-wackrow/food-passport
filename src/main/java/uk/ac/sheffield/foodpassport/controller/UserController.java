package uk.ac.sheffield.foodpassport.controller;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import uk.ac.sheffield.foodpassport.model.User;
import uk.ac.sheffield.foodpassport.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    // CRUD

    @GetMapping("")
    public String showOwnUser(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null) {
            return "login";
        } else {
            String username = auth.getName();
            User user = userService.getUserByUsername(username).get();

            model.addAttribute("user", user);
        }

        return "user";
    }

    @GetMapping("/{username}")
    public String showUser(@PathVariable String username, Model model) {
        Optional<User> userOptional = userService.getUserByUsername(username);
        if (userOptional.isPresent()) {
            model.addAttribute("user", userOptional.get());
        } else {
            model.addAttribute("error", "User with username " + username + " not found");
        }
        return "user";
    }

    @PostMapping("/search")
    public String searchUser(String username) {
        return "redirect:/users/" + username;
    }
}
