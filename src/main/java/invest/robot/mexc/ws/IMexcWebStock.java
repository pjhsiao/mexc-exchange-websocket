package invest.robot.mexc.ws;


import invest.robot.mexc.entity.ChannelDataEntity;
import okhttp3.WebSocket;
import reactor.core.publisher.Flux;

import java.time.Duration;


public interface IMexcWebStock {

    default void heartbeat(WebSocket webSocket){
        Flux.interval(
                Duration.ofSeconds(5l)
        ).subscribe(value->{
            toLog(String.format("webSocket ping: %s, counter: %d", webSocket.send(""), value));
        });
    }

    void send(ChannelDataEntity channelDataEntity);

    void toLog(String toLogStr);
}
