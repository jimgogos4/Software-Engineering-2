package BS.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import BS.entity.User;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {
	Optional<User> findByUsername(String username);
	void deleteByUsername(String username);  // Added method to delete user by username
}
