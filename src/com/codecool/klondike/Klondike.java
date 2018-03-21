package com.codecool.klondike;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Klondike extends Application {

    private static final double WINDOW_WIDTH = 1400;
    private static final double WINDOW_HEIGHT = 900;

    public static void main(String[] args) {
            launch(args);
    }

    void cleanup() {
        // stop animations reset model ect.
    }

    void restart(Stage stage) {
        cleanup();
        start(stage);
    }

    @Override
    public void start(Stage primaryStage) {
            Card.loadCardImages();
            Game game = new Game();
            game.setTableBackground(new Image("/table/green.png"));

        // add button to game Pane
        Button button = new Button("Restart");
        button.setLayoutX(10);
        button.setLayoutY(40);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               restart(primaryStage);
            }
        });
        game.getChildren().add(button);



        primaryStage.setTitle("Klondike Solitaire");
        primaryStage.setScene(new Scene(game, WINDOW_WIDTH, WINDOW_HEIGHT));
        primaryStage.show();

    }

}
