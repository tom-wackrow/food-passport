package uk.ac.sheffield.foodpassport.controller;

import java.time.Instant;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import uk.ac.sheffield.foodpassport.model.Meal;
import uk.ac.sheffield.foodpassport.model.User;
import uk.ac.sheffield.foodpassport.service.MealService;
import uk.ac.sheffield.foodpassport.service.UserService;

@Controller
@RequestMapping("/meals")
public class MealController {

    private final MealService mealService;
    private final UserService userService;

    public MealController(MealService mealService, UserService userService) {
        this.mealService = mealService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String showAllMeals(Model model) {
        List<Meal> meals = mealService.findAll();
        model.addAttribute("meals", meals);
        return "all_meals";
    }

    @PostMapping("/")
    public String createMeal(@RequestBody Meal meal, BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        // Check validation errors
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUsername(username).get();
        meal.setOwner(user);
        meal.setTime_created(Instant.now());

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Error creating meal: " + bindingResult.getAllErrors().get(0).getDefaultMessage());
            return "redirect:/users/" + meal.getOwner().getUsername();
        }

        try {
            Meal savedMeal = mealService.saveMeal(meal);
            redirectAttributes.addFlashAttribute("successMessage", "Meal created successfully");
            return "redirect:/users/" + savedMeal.getOwner().getUsername();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Error creating meal: " + e.getMessage());
            return "redirect:/users/" + meal.getOwner().getUsername();
        }
    }

    @GetMapping("/{username}")
    public List<Meal> getMealsByUsername(@RequestParam String username) {
        return mealService.getMealsByUsername(username);
    }
}
