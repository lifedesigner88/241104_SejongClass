package sejong.park.user.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sejong.park.security.JwtUtil;
import sejong.park.user.dao.UserDAO;
import sejong.park.user.dao.impl.UserDAOImpl;
import sejong.park.user.dto.req.AuthReqDto;
import sejong.park.user.dto.req.CreateUserReqDto;
import sejong.park.user.dto.res.AuthResDto;
import sejong.park.user.dto.res.UserResDto;
import sejong.park.user.entity.User;
import sejong.park.user.service.UserCRUD;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserCRUDImpl implements UserCRUD {

    private final UserDAO userDAO;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;

    public UserCRUDImpl(UserDAOImpl dao, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userDAO = dao;
        this.jwtUtil = jwtUtil;
        this.encoder = passwordEncoder;
    }

    @Override
    public AuthResDto loginUser(AuthReqDto dto) {

        String email = dto.getEmail();
        if (!userDAO.isEmailExist(email))
            return new AuthResDto(email, "없는 이메일입니다", "토큰 발급 불가");

        User user = userDAO.GetUserByEmail(email);
        if (!encoder.matches(dto.getPassword(), user.getPassword()))
            return new AuthResDto(email, "비밀번호가 일치하지 않습니다.", "토큰 발급 불가");

        String token = jwtUtil.generateToken(user.getEmail());
        System.out.println(token);
        return new AuthResDto(user.getName(), user.getName(), token);

    }

    @Override
    public UserResDto createUser(CreateUserReqDto dto) {

        // 유저 객체 생성 후 비밀번호 삽입
        User user = dto.makeReqDtoToUser();
        user.setPassword(encoder.encode(dto.getPassword()));

        // 중복체크
        if (userDAO.isEmailExist(dto.getEmail())) {
            user.setName("이메일이 중복됩니다.");
            return new UserResDto(user);
        } else {
            User savedUser = userDAO.CreateUser(user);
            return new UserResDto(savedUser);
        }
    }

    @Override
    public List<UserResDto> getUsers() {
        return userDAO.getAllUsers().stream()
                .map(UserResDto::new)
                .collect(Collectors.toList());
    }

}
