package invest.robot.mexc.ws;

import invest.robot.mexc.wslistenkey.CreateListenKey;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okio.ByteString;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
public abstract class MexcWebStock extends WebSocketListener {

    private static WebSocket privateClient;
    private static WebSocket publicClient;

    protected WebSocket getPrivateClient() {
        if(null == privateClient){
            OkHttpClient client = new OkHttpClient.Builder()
                    .readTimeout(0, TimeUnit.MILLISECONDS)
                    .build();
            Map<String, String> params = new HashMap<>();
            params.put("recWindow", "60000");
            String listenKey = CreateListenKey.postUserDataStream(params).get("listenKey");

            Request request = new Request.Builder()
                    .url("wss://wbs.mexc.com/ws?listenKey=" + listenKey)
                    .build();
            WebSocket webSocket = client.newWebSocket(request, this);

            //Trigger shutdown of the dispatcher's executor so this process can exit cleanly.
            client.dispatcher().executorService().shutdown();
            this.privateClient = webSocket;
        }
        return privateClient;
    }

    protected WebSocket getPublicClient() {
        if(null == publicClient){
            OkHttpClient client = new OkHttpClient.Builder()
                    .readTimeout(0, TimeUnit.MILLISECONDS)
                    .build();

            Request request = new Request.Builder()
                    .url("wss://wbs.mexc.com/ws")
                    .build();
            WebSocket webSocket = client.newWebSocket(request, this);

            //Trigger shutdown of the dispatcher's executor so this process can exit cleanly.
            client.dispatcher().executorService().shutdown();
            this.publicClient = webSocket;
        }
        return publicClient;
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        log.info("MEXC-Exchange WebSocket CONNECTED ....");
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
    }

    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
        log.info("MESSAGE: {}", bytes.hex());
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        webSocket.close(1000, null);
        log.info("CLOSE: {} {}" , code , reason);
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        t.printStackTrace();
    }

}
