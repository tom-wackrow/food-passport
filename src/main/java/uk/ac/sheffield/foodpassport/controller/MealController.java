package uk.ac.sheffield.foodpassport.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uk.ac.sheffield.foodpassport.model.Meal;
import uk.ac.sheffield.foodpassport.service.MealService;

import java.util.List;

@Controller
@RequestMapping("/meals")
public class MealController {

    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @PostMapping("/")
    public String createMeal(Meal meal, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        //Check validation errors
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error creating meal: " + bindingResult.getAllErrors().get(0).getDefaultMessage());
            return "redirect:/users/" + meal.getOwner();
        }

        try {
            Meal savedMeal = mealService.saveMeal(meal);
            redirectAttributes.addFlashAttribute("successMessage", "Meal created successfully");
            return "redirect:/users/" + savedMeal.getOwner();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Error creating meal: " + e.getMessage());
            return "redirect:/users/" + meal.getOwner();
        }
    }

    @GetMapping("/{username}")
    public List<Meal> getMealsByUsername(@RequestParam String username) {
        return mealService.getMealsByUsername(username);
    }
}
