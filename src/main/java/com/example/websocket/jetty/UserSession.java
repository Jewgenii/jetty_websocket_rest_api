package com.example.websocket.jetty;

public interface UserSession {
    void receiveText(String text) throws Exception;
    void setCurrentUser(User user);
    void disconnect(int status, String reason);
}