package com.nanproduction;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Collection;


public class MainWindowController {

    public static WebSocket webSocket;
    private static final String SERVER = "ws://tarcloud2.ddns.net:8090/websocket";
    private static final int TIMEOUT = 300;
    private boolean connected = false;

    public static final int CELL_SIZE = 20;
    public static final double BODY_SCALE = 0.5;

    private Game game;


    @FXML
    public Canvas canvas;

    @FXML
    public Text stateText;

    @FXML
    public Text scoreText;

    @FXML
    public ColorPicker colorPicker;

    @FXML
    public TextField tF_playerName;

    private static GraphicsContext gc;

    public MainWindowController() {
        System.out.println("MainWindowController() called");
    }

    @FXML
    void initialize() {
        System.out.println("Initialize() called");
        gc=canvas.getGraphicsContext2D();
        stateText.setText("PLAYING");

        game=Game.getInstance();
        game.init(this);

        initWindow();
        drawBase();
    }


    private WebSocket connectToServer() throws IOException, WebSocketException {
        return new WebSocketFactory()
                .setConnectionTimeout(TIMEOUT)
                .createSocket(SERVER)
                .addListener(new WebSocketAdapter() {
                    // A text message arrived from the server.
                    public void onTextMessage(WebSocket websocket, String message) {

                        game.refreshGameState(message);
                        drawPlayers();

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
                //webSocket.sendText("PLAYER");
                webSocket.sendText("{\"name\":\""+tF_playerName.getText()+"\",\"color\":\"#"+colorPicker.getValue().toString().substring(2)+"\"}");
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
        if(webSocket==null) return;
        System.err.println("Disconnecting...");
        webSocket.disconnect();
        System.err.println("Disconnected");
    }

    public void onReady() {
        webSocket.sendText("READY");
    }


    public void setScore(Collection<Player> playerList){
        StringBuilder stringBuilder=new StringBuilder();
        for(Player player: playerList){
            stringBuilder.append(player.getId()+"\t"+player.getScore()+"\n");
        }
        scoreText.setText(stringBuilder.toString());
    }

    public void endingGame(){
        stateText.setText("GAME OVER!");
    }

    private void initWindow() {
        canvas.setHeight(CELL_SIZE *Game.MAP_SIZE_Y);
        canvas.setWidth(CELL_SIZE*Game.MAP_SIZE_X);
    }

    private void drawBase(){
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(4);
        gc.strokeRect(0,0,canvas.getWidth(),canvas.getHeight());

        gc.setLineWidth(0.5);

        for(int i=0;i<Game.MAP_SIZE_X;i++){
            gc.strokeLine(i*CELL_SIZE,0,i*CELL_SIZE,canvas.getHeight());
        }
        for(int j=0; j<Game.MAP_SIZE_Y; j++){
            gc.strokeLine(0,j* CELL_SIZE,canvas.getWidth(),j*CELL_SIZE);
        }
    }

    public void drawPlayers()
    {
        Collection<Player> playerList= game.getPlayers();
        gc.clearRect(0, 0, Game.MAP_SIZE_X*CELL_SIZE, Game.MAP_SIZE_Y*CELL_SIZE);
        drawBase();

        gc.setFill(Color.RED); //achivemenet
        gc.fillRect(CELL_SIZE * game.getAchievement().getX(), CELL_SIZE * game.getAchievement().getY(), CELL_SIZE, CELL_SIZE);

        for(Player player: playerList) {
            gc.setFill(player.getColor()); //playerHead
            gc.fillRect(CELL_SIZE * player.getHead().getX(), CELL_SIZE * player.getHead().getY(), CELL_SIZE, CELL_SIZE); //Fill the Head



            gc.setStroke(player.getColor()); //playerBody
            gc.setLineWidth(CELL_SIZE * BODY_SCALE);
            if (player.getTail().size() == 0) {
                continue;
            }
            gc.strokeLine(CELL_SIZE * (player.getHead().getX() + 0.5), CELL_SIZE * (player.getHead().getY() + 0.5), CELL_SIZE * (player.getTail().get(0).getX() + 0.5), CELL_SIZE * (player.getTail().get(0).getY() + 0.5));
            for (int i = 1; i < player.getTail().size(); i++) {
                gc.strokeLine(CELL_SIZE * (player.getTail().get(i - 1).getX() + 0.5), CELL_SIZE * (player.getTail().get(i - 1).getY() + 0.5), CELL_SIZE * (player.getTail().get(i).getX() + 0.5), CELL_SIZE * (player.getTail().get(i).getY() + 0.5)); //draw body
            }
        }



    }
}
