package sejong.park.sse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import sejong.park.user.dto.res.UserResDto;
import sejong.park.user.repository.UserRepository;

import java.util.List;

@Service
public class AlarmService {

    private final UserRepository userRepository;

    private final Sinks.Many<List<UserResDto>> sink =
            Sinks.many()
                    .multicast()
                    .onBackpressureBuffer();

    public AlarmService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    public Flux<List<UserResDto>> getAlarms() {
        return sink.asFlux().onBackpressureBuffer();
    }


}
