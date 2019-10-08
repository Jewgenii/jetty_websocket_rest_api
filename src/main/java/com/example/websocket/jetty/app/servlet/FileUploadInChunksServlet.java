package com.example.websocket.jetty.app.servlet;

import com.example.websocket.jetty.app.websocketAdapter.MultipartUploadFileAdapter;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

public class FileUploadInChunksServlet  extends WebSocketServlet {

    @Override
    public void configure(WebSocketServletFactory factory) {

        factory.register(MultipartUploadFileAdapter.class);

    }

}