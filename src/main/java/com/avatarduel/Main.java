package com.avatarduel;

import java.io.IOException;
import java.net.URISyntaxException;

import com.avatarduel.controller.CardDao;
import com.avatarduel.controller.GameController;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class
 */
public class Main extends Application {

    /**
     * Launch the Application
     *
     * @param args
     */
    public static void main(String[] args) {
        Application.launch();
    }

    /**
     * Start the game
     *
     * @param primaryStage the primary stage
     * @throws IOException        if an input or output exception occured
     * @throws URISyntaxException if a string could not be parsed as a URI reference
     */
    @Override
    public void start(Stage primaryStage) throws URISyntaxException, IOException {
        GameController game = new GameController(new CardDao());
        primaryStage.setTitle("Avatar Duel by K03-G01");
        primaryStage.setScene(game.getRenderController().getScene());
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
