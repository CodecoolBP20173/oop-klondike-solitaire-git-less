package com.codecool.klondike;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Klondike extends Application {

    private static final double WINDOW_WIDTH = 1400;
    private static final double WINDOW_HEIGHT = 800;

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

    void restart2(Stage stage) {
        cleanup();
        start2(stage);
    }

    void restartPopup(String infoMessage, String titleBar, Stage stage) {
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null, "Would You Like to restart the game?", "Restart!", dialogButton);
        if (dialogResult == JOptionPane.YES_OPTION) {
            restart(stage);
        }

    }


    @Override
    public void start(Stage primaryStage) {
        Card.loadCardImages();
        Game game = new Game();
        game.setTableBackground(new Image("/table/green.png"));


        // add button to game Pane
        Button button = new Button("Restart");
        MenuButton menuButton = new MenuButton("Theme");
        MenuItem menuItem1 = new MenuItem("Original");
        MenuItem menuItem2 = new MenuItem("Codecool");
        menuButton.getItems().addAll(menuItem1, menuItem2);
        button.setLayoutX(10);
        button.setLayoutY(40);
        menuButton.setLayoutX(10);
        menuButton.setLayoutY(200);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                restartPopup("GG", "GG", primaryStage);
            }
        });
        menuItem2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                restart2(primaryStage);
            }
        });
        game.getChildren().add(menuButton);
        game.getChildren().add(button);


        primaryStage.setTitle("Klondike Solitaire");
        primaryStage.setScene(new Scene(game, WINDOW_WIDTH, WINDOW_HEIGHT));
        primaryStage.show();

    }

    public void start2(Stage primaryStage) {
        Card.loadCardImagesCc();
        Game game = new Game();
        game.setTableBackground(new Image("/table/codecool_logo.jpg"));


        // add button to game Pane
        Button button = new Button("Restart");
        MenuButton menuButton = new MenuButton("Theme");
        MenuItem menuItem1 = new MenuItem("Original");
        MenuItem menuItem2 = new MenuItem("Codecool");
        menuButton.getItems().addAll(menuItem1, menuItem2);
        button.setLayoutX(10);
        button.setLayoutY(40);
        menuButton.setLayoutX(10);
        menuButton.setLayoutY(200);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                restartPopup("GG", "GG", primaryStage);
            }
        });
        menuItem1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                restart(primaryStage);
            }
        });

        game.getChildren().add(menuButton);
        game.getChildren().add(button);


        primaryStage.setTitle("Klondike Solitaire");
        primaryStage.setScene(new Scene(game, WINDOW_WIDTH, WINDOW_HEIGHT));
        primaryStage.show();

    }

}
