package sejong.park.user.dao;

import sejong.park.user.entity.User;

import java.util.List;

public interface UserDAO {

    boolean isEmailExist(String email);
    User CreateUser(User user);
    List<User> getAllUsers();
    User GetUserByEmail(String email);

}
