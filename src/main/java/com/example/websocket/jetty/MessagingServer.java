package com.example.websocket.jetty;

import com.example.websocket.jetty.app.MessagingServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * The executable main class.
 * @author amrishodiq
 */


public class MessagingServer {

/*    @Autowired
    org.springframework.core.env.Environment environment;*/

    private Server server;

    public void setup() {
/*        try{
            String port = environment.getProperty("server.port");
        }
       catch(Exception ex){
           System.out.println("ex = " + ex);
       }*/
      //  int port  =  Integer.parseInt(environment.getProperty("server.port"));

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

        /*SpringApplication.run(MessagingServer.class, args);*/
/*        AnnotationConfigApplicationContext con = new AnnotationConfigApplicationContext(MessagingServer.class);
        con.register(MessagingServer.class);
        MessagingServer messagingServer = con.getBean(MessagingServer.class);
        messagingServer.setup();
        messagingServer.start();*/


        MessagingServer theServer = new MessagingServer();
        theServer.setup();
        theServer.start();
    }
}