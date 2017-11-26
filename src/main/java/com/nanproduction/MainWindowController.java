package com.nanproduction;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;
import javafx.fxml.FXML;

import java.io.IOException;


public class MainWindowController {

    public static WebSocket webSocket;
    private static final String SERVER = "ws://192.168.137.1:8090/websocket";
    private static final int TIMEOUT = 150;
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
                webSocket.sendText("PLAYER");
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
            disconnect();
            connected = false;

        }
    }

    public static void disconnect(){
        System.err.println("Disconnecting...");
        webSocket.disconnect();
        System.err.println("Disconnected");
    }

    public void onReady() {
        webSocket.sendText("READY");
    }
}
