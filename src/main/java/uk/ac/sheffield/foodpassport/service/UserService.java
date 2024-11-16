package uk.ac.sheffield.foodpassport.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uk.ac.sheffield.foodpassport.model.Meal;
import uk.ac.sheffield.foodpassport.repository.MealRepository;
import uk.ac.sheffield.foodpassport.repository.UserRepository;
import uk.ac.sheffield.foodpassport.model.User;

import java.util.List;
import java.util.Optional;

import uk.ac.sheffield.foodpassport.model.User;
import uk.ac.sheffield.foodpassport.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MealRepository mealRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, MealRepository mealRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mealRepository = mealRepository;
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findById(username);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User addMealToUser(String username, Long mealId) {
        Optional<User> userOptional = userRepository.findById(username);
        Optional<Meal> mealOptional = mealRepository.findById(mealId);

        if (userOptional.isPresent() && mealOptional.isPresent()) {
            User user = userOptional.get();
            Meal meal = mealOptional.get();

            user.getMeals().add(meal);
            return userRepository.save(user);
        }
        return null;
    }

    public User register(User user) throws Exception {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new Exception("Username already exists: " + user.getUsername());
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
