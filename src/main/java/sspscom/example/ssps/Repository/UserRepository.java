package sspscom.example.ssps.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sspscom.example.ssps.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
