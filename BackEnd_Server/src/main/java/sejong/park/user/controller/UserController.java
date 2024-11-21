package sejong.park.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import sejong.park.user.dto.req.AuthReqDto;
import sejong.park.user.dto.req.CreateUserReqDto;
import sejong.park.user.dto.res.AuthResDto;
import sejong.park.user.dto.res.UserResDto;
import sejong.park.user.service.UserCRUD;
import sejong.park.user.service.impl.UserCRUDImpl;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    private final UserCRUD userCRUD;

    public UserController(UserCRUDImpl userCRUDImpl) {
        this.userCRUD = userCRUDImpl;
    }

    @PostMapping("create")
    public ResponseEntity<UserResDto> createUser(@RequestBody CreateUserReqDto dto) {
        UserResDto userResDto = userCRUD.createUser(dto);
        return ResponseEntity.ok(userResDto);
    }

    @PostMapping("login")
    public ResponseEntity<AuthResDto> loginUser(@RequestBody AuthReqDto dto) {
        return ResponseEntity.ok(userCRUD.loginUser(dto));
    }

    @GetMapping("list")
    public ResponseEntity<List<UserResDto>> getUsers() {
        return ResponseEntity.ok(userCRUD.getUsers());
    }

}
