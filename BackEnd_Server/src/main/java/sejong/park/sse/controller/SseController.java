package sejong.park.sse.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Date;

@RestController
public class SseController {

    @GetMapping(value = "/time", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamSee() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> "Spring " + new Date());
    }

}
