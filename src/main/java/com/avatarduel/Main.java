package com.avatarduel;

import java.io.IOException;
import java.net.URISyntaxException;

import com.avatarduel.controller.CardDao;
import com.avatarduel.controller.GameController;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) throws URISyntaxException, IOException {
        GameController game = new GameController(new CardDao());
        primaryStage.setTitle("Avatar Duel by K03-G01");
        primaryStage.setScene(game.getRenderController().getScene());
        // primaryStage.setResizable(false);
        primaryStage.show();
    }
}
