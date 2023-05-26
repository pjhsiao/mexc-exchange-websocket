package invest.robot.mexc.worker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import invest.robot.mexc.entity.KlineEntity;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Predicate;


@Builder
@Slf4j
public class RawDataWorker implements Runnable{
    private final String raw;
    private final String channelName;

    //filter response msg of SUBSCRIPTION
    private final Predicate<String> filterSubscribe = raw -> raw.contains("msg");


    public RawDataWorker(String raw, String channelName) {
        this.raw = raw;
        this.channelName = channelName;
    }

    @Override
    public void run() {
        try {
            if(!filterSubscribe.test(raw)) {
                KlineEntity klineEntity = new ObjectMapper().readValue(raw, KlineEntity.class);
                log.info("klineEntity : {}", klineEntity);
            }
        } catch (JsonProcessingException e) {
            log.error("Occurred an Error : {}, {}", e.getMessage(), e);
        }
    }

}
