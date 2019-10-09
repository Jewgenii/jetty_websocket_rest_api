package com.example.websocket.jettyWebSocketApplication.app.websocketAdapter;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
*  if signature of any annotated method doesnt match error will raise.
* */
@WebSocket(maxBinaryMessageSize = 1024*1024*1024, maxIdleTime = 120_000)
public class FileUploadAdapter {

    static File uploadedFile = null;
    static String fileName = null;
    static FileOutputStream fos = null;
    final static String filePath = "download"+File.pathSeparator;

    @OnWebSocketConnect
    public void open(Session session) {

        System.out.println("session = " + session);
    }

    @OnWebSocketClose
    public void onClose(Session session,int statusCode, String reason){
        System.out.println("leave a session = " + session);
    }

    @OnWebSocketMessage
    public void onTextMessage(Session session, String msg){
        System.out.println("got msg: " + msg);

        if(!msg.equals("end")) {
            fileName=msg.substring(msg.indexOf(':')+1);
            uploadedFile = new File(filePath+fileName);
            try {
                fos = new FileOutputStream(uploadedFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }else {
            try {
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    // less than 1 gb
    @OnWebSocketMessage
    public void onBinaryMessage(Session session, InputStream stream){
        System.out.println("Binary Data");

        try {

            byte[] buffer = new byte[1024];
            int read = 0;
            int counter = 0;

        try{

            while((read = stream.read(buffer,0,buffer.length))>0){

                fos.write(buffer,0, read);
                session.getRemote().sendString("in progresss..." + counter++);
            }

        }catch (Exception ex){
            Logger.getAnonymousLogger().log(Level.SEVERE,ex.toString());
        }

            session.getRemote().sendString("finished!");
           // fos.write(stream.readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

