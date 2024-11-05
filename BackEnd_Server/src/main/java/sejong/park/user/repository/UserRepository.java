package sejong.park.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sejong.park.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
