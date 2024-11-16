package uk.ac.sheffield.foodpassport.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uk.ac.sheffield.foodpassport.model.User;
import uk.ac.sheffield.foodpassport.service.UserService;

import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    //CRUD

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
}
