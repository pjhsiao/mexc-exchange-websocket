package invest.robot.mexc.ws;

import invest.robot.mexc.channel.ChannelObservable;
import invest.robot.mexc.channel.ChannelObserver;
import invest.robot.mexc.channel.IChannelObservable;
import invest.robot.mexc.entity.ChannelDataEntity;
import lombok.extern.slf4j.Slf4j;
import okhttp3.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MexcSpotWebStock extends MexcWebStock{
    @Autowired
    private IChannelObservable channelObservable;
    @Autowired
    private ThreadPoolTaskExecutor threadPool;

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        channelObservable.notifyObservers(text);
    }

    public void send(ChannelDataEntity channelDataEntity) {
        //sub public channel
        WebSocket publicClient = getPublicClient();
        publicClient.send(channelDataEntity.getRequestJson());
        channelObservable.add(ChannelObserver.builder().channelName(channelDataEntity.getChannelName()).threadPool(threadPool).build());
    }
}
