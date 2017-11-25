package com.nanproduction;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;
import javafx.fxml.FXML;

import java.io.IOException;


public class MainWindowController {

    private static WebSocket webSocket;
    private static final String SERVER = "ws://localhost:8081/websocket";
    private static final int TIMEOUT = 150;
    private boolean firstQuery = true;
    private boolean connected = false;


    public MainWindowController() {
        System.out.println("MainWindowController() called");
    }

    @FXML
    void initialize() {
        System.out.println("Initialize() called");
    }


    private WebSocket connectToServer() throws IOException, WebSocketException {
        return new WebSocketFactory()
                .setConnectionTimeout(TIMEOUT)
                .createSocket(SERVER)
                .addListener(new WebSocketAdapter() {
                    // A text message arrived from the server.
                    public void onTextMessage(WebSocket websocket, String message) {
                        System.out.println(message);
                    }
                })
                .connect();
    }

    @FXML
    public void onConnectToServer() {
        if (!connected) {
            System.err.println("Connecting...");
            try {
                webSocket = connectToServer();
                System.err.println("Connected");
                connected = true;
            } catch (IOException | WebSocketException e){
                System.out.println(e);
            }
        }

    }

    @FXML
    public void onDisconnect() {
        if (connected) {
            System.err.println("Disconnecting...");
            webSocket.disconnect();
            System.err.println("Disconnected");
            connected = false;
            firstQuery = true;
        }
    }
}
