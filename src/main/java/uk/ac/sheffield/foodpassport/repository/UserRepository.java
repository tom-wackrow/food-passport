package uk.ac.sheffield.foodpassport.repository;import java.util.Optional;import org.springframework.data.jpa.repository.JpaRepository;import uk.ac.sheffield.foodpassport.model.User;public interface UserRepository extends JpaRepository<User,String>{Optional<User> findByUsername(String username);}