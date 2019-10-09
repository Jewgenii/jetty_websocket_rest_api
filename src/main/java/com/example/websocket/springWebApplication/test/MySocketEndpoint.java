/*
package test;

import javax.websocket.*;

import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint(
        value="/app",
        decoders = MessageDecoder.class,
        encoders = MessageEncoder.class )
public class MySocketEndpoint {

    @OnOpen
    public void onOpen(Session session) throws IOException {
        System.out.println("session.toString() = " + session.toString());
    }

    @OnMessage
    public void onMessage(Session session, String message) throws IOException {
        System.out.println("message = " + message);
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        // WebSocket connection closes
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("on error session.toString() = " + session.toString());
    }

}
*/
