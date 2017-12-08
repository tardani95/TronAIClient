package com.nanproduction;

import com.nanproduction.communication.MainWindowController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import static com.nanproduction.communication.MainWindowController.disconnect;

public class Main extends Application {

    private static final int SCREEN_SIZE_X = 1300;
    private static final int SCREEN_SIZE_Y = 800;

    private Scene scene;


    public static void main(String[] args) {
        System.out.println("main()");
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("start()");
        Parent root = FXMLLoader.load(getClass().getResource("/mainWindow.fxml"));
        primaryStage.setTitle("TronAIClient");
        scene = new Scene(root, SCREEN_SIZE_X, SCREEN_SIZE_Y);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyCode keyCode = event.getCode();
                switch (event.getCode()) {
                    case W:
                        MainWindowController.webSocket.sendText("8");
                        break;
                    case A:
                        MainWindowController.webSocket.sendText("4");
                        break;
                    case S:
                        MainWindowController.webSocket.sendText("2");
                        break;
                    case D:
                        MainWindowController.webSocket.sendText("6");
                    default:
                        break;
                }

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