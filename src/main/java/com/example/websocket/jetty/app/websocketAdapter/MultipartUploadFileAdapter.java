package com.example.websocket.jetty.app.websocketAdapter;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.io.InputStream;

@WebSocket
public class MultipartUploadFileAdapter {

    @OnWebSocketConnect
    public void onConnect(Session session){
        System.out.println("connected");
    }

    // upload files in parts
    @OnWebSocketMessage
    public void onMessage(Session session, InputStream stream){

    }


}
