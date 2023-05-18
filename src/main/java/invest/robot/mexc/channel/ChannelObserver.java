package invest.robot.mexc.channel;

import invest.robot.mexc.worker.RawDataWorker;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Slf4j
@Builder
@Getter
public class ChannelObserver implements IChannelObserver<String>{

    private String channelName;

    private ThreadPoolTaskExecutor threadPool;

    @Override
    public void update(String raw) {
        if(raw.contains(channelName)){
            log.debug("Channel : {} Received : {}", channelName, raw);
            threadPool.execute(RawDataWorker.builder().raw(raw).channelName(channelName).build());
        }
    }
}
