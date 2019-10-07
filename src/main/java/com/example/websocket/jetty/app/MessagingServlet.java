package com.example.websocket.jetty.app;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

public class MessagingServlet extends WebSocketServlet {

    @Override
    public void configure(WebSocketServletFactory factory) {

        /*factory.register(MessagingAdapter.class);*/
        factory.register(MessagingAdapterAnnotated.class);
    }

}