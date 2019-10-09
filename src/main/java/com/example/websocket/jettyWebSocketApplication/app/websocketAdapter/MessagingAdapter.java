package com.example.websocket.jettyWebSocketApplication.app.websocketAdapter;
;
import com.example.websocket.jettyWebSocketApplication.app.User;
import com.example.websocket.jettyWebSocketApplication.app.UserSession;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class MessagingAdapter extends WebSocketAdapter implements UserSession {

    private Session session;
    private User currentUser;

    static List<Session> list = new CopyOnWriteArrayList<>();

    @Override
    public void onWebSocketConnect(Session session) {
        super.onWebSocketConnect(session);
        list.add(session);

        this.session = session;
    }
    @Override
    public void onWebSocketClose(int statusCode, String reason) {
      //  MessagingLogic.getInstance().setOffline(currentUser.username);

        this.session = null;

        System.err.println("Close connection "+statusCode+", "+reason);

        super.onWebSocketClose(statusCode, reason);
    }
    @Override
    public void onWebSocketText(String message) {
       // super.onWebSocketText(message);

        try {

            for (Session _session: this.list.stream().filter(currentSession -> !currentSession.equals(session)).collect(Collectors.toList())) {
                _session.getRemote().sendString("echo: " + message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // MessagingLogic.getInstance().receiveText(this, message);
    }
    @Override
    public void receiveText(String text) throws Exception {
        if (session != null && session.isOpen()) {
            session.getRemote().sendString(text);

        }
    }
    @Override
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }
    @Override
    public void disconnect(int status, String reason) {

        session.close(status, reason);
        disconnect(status, reason);
        list.remove(session);
    }

}