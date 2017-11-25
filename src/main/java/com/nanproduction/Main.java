package com.nanproduction;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static com.nanproduction.MainWindowController.disconnect;
import static com.nanproduction.MainWindowController.webSocket;

public class Main extends Application {

    private static final int SCREEN_SIZE_X = 1280;
    private static final int SCREEN_SIZE_Y = 750;


    public static void main(String[] args) {
        System.out.println("main()");
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        System.out.println("start()");
        Parent root = FXMLLoader.load(getClass().getResource("/mainWindow.fxml"));
        primaryStage.setTitle("TronAIClient");
        primaryStage.setScene(new Scene(root, SCREEN_SIZE_X, SCREEN_SIZE_Y));
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