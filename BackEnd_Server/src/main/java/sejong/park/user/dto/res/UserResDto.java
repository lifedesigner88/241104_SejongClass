package sejong.park.user.dto.res;


import lombok.Getter;
import sejong.park.user.entity.User;

@Getter
public class UserResDto {

    private final Long userId;
    private final String name;
    private final String email;
    private final String password;

    public UserResDto(User user) {
        this.userId = user.getUserId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

}
