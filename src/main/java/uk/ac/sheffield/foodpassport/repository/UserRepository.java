package uk.ac.sheffield.foodpassport.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uk.ac.sheffield.foodpassport.model.User;

public interface UserRepository extends JpaRepository<User, String> {
}
