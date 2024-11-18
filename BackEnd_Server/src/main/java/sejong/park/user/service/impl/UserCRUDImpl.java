package sejong.park.user.service.impl;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;
import sejong.park.user.dao.UserDAO;
import sejong.park.user.dao.impl.UserDAOImpl;
import sejong.park.user.dto.req.CreateUserReqDto;
import sejong.park.user.dto.res.UserResDto;
import sejong.park.user.entity.User;
import sejong.park.user.service.UserCRUD;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserCRUDImpl implements UserCRUD {


    private final UserDAO userDAO;

    public UserCRUDImpl(UserDAOImpl dao) {
        this.userDAO = dao;
    }

    @Override
    public UserResDto createUser(CreateUserReqDto dto) {
        User user = dto.makeReqDtoToUser();
        User savedUser = userDAO.CreateUser(user);
        return new UserResDto(savedUser);
    }

    @Override
    public List<UserResDto> getUsers() {
        return userDAO.getAllUsers().stream()
                .map(UserResDto::new)
                .collect(Collectors.toList());
    }

}
