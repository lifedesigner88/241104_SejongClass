package sejong.park.user.dto.req;

import lombok.Getter;
import sejong.park.user.entity.User;

@Getter
public class CreateUserReqDto {

    private String name;
    private String password;
    private String email;

    public User makeReqDtoToUser() {
        return User.builder()
                .email(this.email)
                .name(this.name)
                .build();
    }

}
