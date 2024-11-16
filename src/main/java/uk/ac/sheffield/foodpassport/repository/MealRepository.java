package uk.ac.sheffield.foodpassport.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uk.ac.sheffield.foodpassport.model.Meal;

public interface MealRepository extends JpaRepository<Meal, Long> {
}
