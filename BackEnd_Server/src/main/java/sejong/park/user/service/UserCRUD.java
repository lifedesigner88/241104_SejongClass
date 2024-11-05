package sejong.park.user.service;

import sejong.park.user.dto.req.CreateUserReqDto;
import sejong.park.user.dto.res.UserResDto;

public interface UserCRUD {

    UserResDto createUser(CreateUserReqDto dto);

}
