package uk.ac.sheffield.foodpassport.service;

import java.util.List;

import org.springframework.stereotype.Service;

import uk.ac.sheffield.foodpassport.model.Meal;
import uk.ac.sheffield.foodpassport.repository.MealRepository;

@Service
public class MealService {
    private final MealRepository mealRepository;

    public MealService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    public List<Meal> getMealsByUsername(String username) {
        return mealRepository.findMealsByUsername(username);
    }

    public Meal saveMeal(Meal meal) {
        return mealRepository.save(meal);
    }

    public void deleteMeal(Long id) {
        mealRepository.deleteById(id);
    }

    public List<Meal> findAll() {
        return mealRepository.findAll();
    }
}
