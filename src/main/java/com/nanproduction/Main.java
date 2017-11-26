package com.nanproduction;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import static com.nanproduction.MainWindowController.disconnect;
import static com.nanproduction.MainWindowController.webSocket;

public class Main extends Application {

    private static final int SCREEN_SIZE_X = 500;
    private static final int SCREEN_SIZE_Y = 250;

    private Scene scene;




    public static void main(String[] args) {
        System.out.println("main()");
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        System.out.println("start()");
        Parent root = FXMLLoader.load(getClass().getResource("/mainWindow.fxml"));
        primaryStage.setTitle("TronAIClient");
        scene=new Scene(root, SCREEN_SIZE_X, SCREEN_SIZE_Y);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                MainWindowController.webSocket.sendText(String.valueOf(event.getCode()));
            }
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        //webSocket.disconnect();
        disconnect();
        System.out.println("stop()");
        super.stop();
    }


}