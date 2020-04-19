package com.avatarduel;

import java.io.IOException;
import java.net.URISyntaxException;

import com.avatarduel.controller.CardFieldDimension;
import com.avatarduel.controller.GameController;
import com.avatarduel.controller.PlayerController;
import com.avatarduel.model.Player;
import com.avatarduel.repository.AuraSkillCardRepository;
import com.avatarduel.repository.CharacterCardRepository;
import com.avatarduel.repository.DestroySkillCardRepository;
import com.avatarduel.repository.LandCardRepository;
import com.avatarduel.repository.PowerUpSkillCardRepository;

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
        CardFieldDimension cardFieldDimension = new CardFieldDimension(6, 6);
        PlayerController playerController = new PlayerController(7, new Player("Player 1", cardFieldDimension),
                new Player("Player 2", cardFieldDimension), new LandCardRepository(), new CharacterCardRepository(),
                new AuraSkillCardRepository(), new DestroySkillCardRepository(), new PowerUpSkillCardRepository());
        GameController game = new GameController(playerController);
        primaryStage.setTitle("Avatar Duel by K03-G01");
        primaryStage.setScene(game.getRenderController().getScene());
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
