package sejong.park.user.service;

import sejong.park.user.dto.req.CreateUserReqDto;
import sejong.park.user.dto.res.UserResDto;

import java.util.List;

public interface UserCRUD {

    UserResDto createUser(CreateUserReqDto dto);
    List<UserResDto> getUsers();
}
