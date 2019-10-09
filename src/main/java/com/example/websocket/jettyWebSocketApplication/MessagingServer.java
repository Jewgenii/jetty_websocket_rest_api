package com.example.websocket.jettyWebSocketApplication;

import com.example.websocket.jettyWebSocketApplication.app.servlet.FileUploadInChunksServlet;
import com.example.websocket.jettyWebSocketApplication.app.servlet.FileUploadServlet;
import com.example.websocket.jettyWebSocketApplication.app.servlet.MessagingServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;

/**
 * The executable main class.
 * @author amrishodiq
 * @
 */
public class MessagingServer {

    private Server server;

    public void setup() {

        server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(8099);
        server.addConnector(connector);

        /*ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);*/
        ServletContextHandler handler = new ServletContextHandler();
        handler.setContextPath("/");

        handler.addServlet(FileUploadServlet.class,"/file");
        handler.addServlet(MessagingServlet.class,"/messaging");
        handler.addServlet(FileUploadInChunksServlet.class,"/multipartuploadfile");

        server.setHandler(handler);
    }

    public void start() throws Exception {
        server.start();
       // server.dump(System.err);
        server.join();
    }

    // signature of the methods
    //https://www.eclipse.org/jetty/documentation/current/jetty-websocket-api-annotations.html
    public static void main(String args[]) throws Exception {

        MessagingServer theServer = new MessagingServer();
        theServer.setup();
        theServer.start();
    }
}