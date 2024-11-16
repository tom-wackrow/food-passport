package uk.ac.sheffield.foodpassport.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uk.ac.sheffield.foodpassport.model.Meal;

import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {

    @Query("SELECT m FROM Meal m WHERE m.owner = :userName")
    List<Meal> findMealsByUsername(@Param("userName") String userName);
}
