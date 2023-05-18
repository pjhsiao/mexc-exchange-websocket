package invest.robot.mexc.channel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class ChannelObservable implements IChannelObservable<String> {

   private List<IChannelObserver> channels = new ArrayList<>();

    @Override
    public void add(IChannelObserver<String> observer) {
        channels.add(observer);
    }

    @Override
    public void remove(IChannelObserver<String> observer) {
        channels.remove(observer);
    }

    @Override
    public void notifyObservers(String raw) {
        log.debug("ChannelObservable Received: {}", raw);
        channels.parallelStream().forEach(channel -> channel.update(raw));
    }
}
