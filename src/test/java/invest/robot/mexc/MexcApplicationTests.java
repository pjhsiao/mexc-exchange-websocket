package invest.robot.mexc;

import invest.robot.mexc.entity.ChannelDataEntity;
import invest.robot.mexc.ws.MexcSpotWebStock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MexcApplicationTests {

    @Autowired
    private MexcSpotWebStock mexcSpotWebStock;

    @Test
    void contentLoad(){

    }

    @Test
    void webStockTest() throws InterruptedException {

        ChannelDataEntity channelDataEntity =  ChannelDataEntity.builder()
                                                .channelName("spot@public.kline.v3.api@BTCUSDT@Min5")
                                                .reqStreamMethod("SUBSCRIPTION")
                                                .reqStreamParams("spot@public.kline.v3.api@BTCUSDT@Min5")
                                                .build();
                                                mexcSpotWebStock.send(channelDataEntity);


        channelDataEntity =  ChannelDataEntity.builder()
                                            .channelName("spot@public.kline.v3.api@BTCUSDT@Min15")
                                            .reqStreamMethod("SUBSCRIPTION")
                                            .reqStreamParams("spot@public.kline.v3.api@BTCUSDT@Min15")
                                            .build();
                                                mexcSpotWebStock.send(channelDataEntity);

        channelDataEntity =  ChannelDataEntity.builder()
                                            .channelName("spot@public.kline.v3.api@BTCUSDT@Min30")
                                            .reqStreamMethod("SUBSCRIPTION")
                                            .reqStreamParams("spot@public.kline.v3.api@BTCUSDT@Min30")
                                            .build();
                                                mexcSpotWebStock.send(channelDataEntity);

        channelDataEntity =  ChannelDataEntity.builder()
                                            .channelName("spot@public.kline.v3.api@BTCUSDT@Min60")
                                            .reqStreamMethod("SUBSCRIPTION")
                                            .reqStreamParams("spot@public.kline.v3.api@BTCUSDT@Min60")
                                            .build();
                                                mexcSpotWebStock.send(channelDataEntity);
            // make main thread wait
            Thread.currentThread().join();

    }


}
