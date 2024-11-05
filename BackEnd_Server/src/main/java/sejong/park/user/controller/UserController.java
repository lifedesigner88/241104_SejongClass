package sejong.park.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import sejong.park.user.dto.req.CreateUserReqDto;
import sejong.park.user.dto.res.UserResDto;
import sejong.park.user.service.UserCRUD;
import sejong.park.user.service.impl.UserCRUDImpl;

@Controller
@RequestMapping("user")
public class UserController {

    private final UserCRUD userCRUD;

    public UserController(UserCRUDImpl userCRUDImpl) {
        this.userCRUD = userCRUDImpl;
    }

    @PostMapping
    public ResponseEntity<UserResDto> createUser(@RequestBody CreateUserReqDto dtd) {

        UserResDto userResDto = userCRUD.createUser(dtd);

        return ResponseEntity.ok(userResDto);
    }

}
