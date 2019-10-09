package com.example.websocket.jettyWebSocketApplication.app.websocketAdapter;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.io.*;

@WebSocket(maxIdleTime = 300_000 /*5min*/,maxBinaryMessageSize = 1024*1024 * 500 /*500 Mb if 1Gb&> not working*/)
public class MultipartUploadFileAdapter {

    static File uploadedFile = null;
    static String fileName = null;
    static FileOutputStream fos = null;
    final static String filePath = "download"+File.pathSeparator;

    @OnWebSocketConnect
    public void onConnect(Session session){
        System.out.println("connected");
    }

    @OnWebSocketMessage
    public void onMessage(Session session, String msg){

        fileName = msg.substring(msg.indexOf(':')+1);
        uploadedFile = new File(filePath+fileName);

        try {
            fos = new FileOutputStream(uploadedFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    // upload files in parts
    @OnWebSocketMessage
    public void onMessage(Session session, InputStream stream){


        FileOutputStream outputStream = null;

        try {
            outputStream = new FileOutputStream("file.gz");
            outputStream.write(stream.readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
