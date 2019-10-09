package com.example.websocket.jettyWebSocketApplication.app.servlet;

import com.example.websocket.jettyWebSocketApplication.app.websocketAdapter.MultipartUploadFileAdapter;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

public class FileUploadInChunksServlet  extends WebSocketServlet {

    @Override
    public void configure(WebSocketServletFactory factory) {

        factory.register(MultipartUploadFileAdapter.class);
       // factory.getPolicy().setIdleTimeout(Integer.MAX_VALUE);
    }

}