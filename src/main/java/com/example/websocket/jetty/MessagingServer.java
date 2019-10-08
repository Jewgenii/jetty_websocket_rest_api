package com.example.websocket.jetty;

import com.example.websocket.jetty.app.servlet.FileUploadInChunksServlet;
import com.example.websocket.jetty.app.servlet.FileUploadServlet;
import com.example.websocket.jetty.app.servlet.MessagingServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

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

        /*ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);*/
        ServletContextHandler handler = new ServletContextHandler();
        handler.setContextPath("/");

        handler.addServlet(FileUploadServlet.class,"/file");
        handler.addServlet(MessagingServlet.class,"/messaging");
        handler.addServlet(FileUploadInChunksServlet.class,"/multipartuploadfile");



/*
        ServletContextHandler fileUploadHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        fileUploadHandler.setContextPath("/");
        fileUploadHandler.addServlet(FileUploadServlet.class, "/receive/fileserver");*/

       /* HandlerCollection handlerCollection = new HandlerCollection();
        handlerCollection.addHandler(handler);
        handlerCollection.addHandler(fileUploadHandler);*/


        server.setHandler(handler);

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