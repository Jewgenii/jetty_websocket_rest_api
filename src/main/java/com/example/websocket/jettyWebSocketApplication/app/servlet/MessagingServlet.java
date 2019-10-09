package com.example.websocket.jettyWebSocketApplication.app.servlet;

import com.example.websocket.jettyWebSocketApplication.app.websocketAdapter.MessagingAdapterAnnotated;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

public class MessagingServlet extends WebSocketServlet {

    @Override
    public void configure(WebSocketServletFactory factory) {

       factory.register(MessagingAdapterAnnotated.class);

    }

}