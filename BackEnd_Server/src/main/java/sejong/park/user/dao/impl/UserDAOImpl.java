package sejong.park.user.dao.impl;

import org.springframework.stereotype.Component;
import sejong.park.user.dao.UserDAO;
import sejong.park.user.entity.User;
import sejong.park.user.repository.UserRepository;

import java.util.List;

@Component
public class UserDAOImpl implements UserDAO {

    private final UserRepository userRepository;

    public UserDAOImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User CreateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
