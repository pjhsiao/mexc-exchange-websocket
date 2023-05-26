package invest.robot.mexc.ws;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okio.ByteString;

import java.util.concurrent.TimeUnit;

@Slf4j
public abstract class MexcWebStock extends WebSocketListener implements IMexcWebStock{

    private static WebSocket publicClient;

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
        heartbeat(webSocket);
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
        log.info("Failure: {} {}" , t.getMessage() , t);
    }

    @Override
    public void toLog(String toLogStr) {
        log.info(toLogStr);
    }
}
