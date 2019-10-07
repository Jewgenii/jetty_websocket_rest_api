package com.example.websocket.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;

/**
 * The executable main class.
 * @author amrishodiq
 */

public class MessagingServer {

    private Server server;

    public void setup() {
        server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(8099);
        server.addConnector(connector);

        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        handler.setContextPath("/");
        server.setHandler(handler);

        handler.addServlet(MessagingServlet.class, "/messaging");
    }

    public void start() throws Exception {
        server.start();
       // server.dump(System.err);
        server.join();
    }

    public static void main(String args[]) throws Exception {

        MessagingServer theServer = new MessagingServer();
        theServer.setup();
        theServer.start();
    }
}