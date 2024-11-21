package sejong.park.user.service;

import sejong.park.user.dto.req.AuthReqDto;
import sejong.park.user.dto.req.CreateUserReqDto;
import sejong.park.user.dto.res.AuthResDto;
import sejong.park.user.dto.res.UserResDto;

import java.util.List;

public interface UserCRUD {

    AuthResDto loginUser(AuthReqDto dto);
    UserResDto createUser(CreateUserReqDto dto);
    List<UserResDto> getUsers();

}
