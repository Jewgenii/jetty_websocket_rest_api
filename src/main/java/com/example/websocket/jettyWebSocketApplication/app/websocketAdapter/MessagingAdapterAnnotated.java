package com.example.websocket.jettyWebSocketApplication.app.websocketAdapter;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@WebSocket
public class MessagingAdapterAnnotated {

    static List<Session> sessions = new CopyOnWriteArrayList<>();
    @OnWebSocketMessage
    public void onText(Session session,String text){
        try {
            for (Session _session: sessions.stream().filter(session1 -> !session1.equals(session)).collect(Collectors.toList()) ) {
                    _session.getRemote().sendString("server:" + text);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnWebSocketMessage
    public void onBinaryUploadFile(Session session, InputStream stream){

        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream("file.gz");
            outputStream.write(stream.readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("stream. = ");
    }

    @OnWebSocketConnect
    public void onConnect(Session session){
        System.out.println("connected");
        sessions.add(session);
    }

    @OnWebSocketClose
    public void onClose(Session session, int statusCode, String reason){
        sessions.remove(session);
    }

    @OnWebSocketError
    public  void onError(Session session, Throwable error){

        try {
            session.getRemote().sendString(error.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
