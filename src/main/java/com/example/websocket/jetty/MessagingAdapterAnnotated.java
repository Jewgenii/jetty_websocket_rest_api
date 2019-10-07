package com.example.websocket.jetty;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;

import java.io.IOException;
import java.io.InputStream;

@WebSocket
public class MessagingAdapterAnnotated {

    @OnWebSocketMessage
    public void onText(Session session,String text){
        try {
            session.getRemote().sendString("text");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @OnWebSocketMessage
    public void onBinaryUploadFile(Session session, InputStream stream){


    }

    @OnWebSocketConnect
    public void onConnect(Session session){
        System.out.println("connected");
    }

    @OnWebSocketClose
    public void onClose(Session session, int statusCode, String reason){

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
