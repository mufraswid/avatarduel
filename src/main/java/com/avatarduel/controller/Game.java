package com.avatarduel.controller;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.avatarduel.Constants;
import com.avatarduel.Main;
import com.avatarduel.model.Element;
import com.avatarduel.model.Player;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CharacterCard;
import com.avatarduel.model.card.LandCard;
import com.avatarduel.model.card.skill.AuraSkillCard;
import com.avatarduel.model.card.skill.DestroySkillCard;
import com.avatarduel.model.card.skill.PowerUpSkillCard;
import com.avatarduel.util.CSVReader;
import com.avatarduel.view.main.MainView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Game extends Application {

    private static Game instance;

    public static void start() {
        Application.launch();
    }

    public static Game getInstance() {
        return Game.instance;
    }

    private List<Card> cards;
    private Player player1, player2;

    public Game() {
        Game.instance = this;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    private List<String[]> getListFromCsv(String path) throws IOException, URISyntaxException {
        File csvFile = new File(Main.class.getResource(path).toURI());
        CSVReader reader = new CSVReader(csvFile, "\t");
        reader.setSkipHeader(true);
        return reader.read();
    }

    private void loadCards() throws IOException, URISyntaxException {
        cards = new ArrayList<>();

        // Land
        for (String[] row : getListFromCsv(Constants.LAND_CSV_FILE_PATH)) {
            cards.add(new LandCard(row[4], row[1], row[3], Element.valueOf(row[2])));
        }

        // Character
        for (String[] row : getListFromCsv(Constants.CHARACTER_CSV_FILE_PATH)) {
            cards.add(new CharacterCard(row[4], row[1], row[3], Element.valueOf(row[2]), Integer.parseInt(row[7]),
                    Integer.parseInt(row[5]), Integer.parseInt(row[6])));
        }

        // Aura skill
        for (String[] row : getListFromCsv(Constants.AURA_SKILL_CSV_FILE_PATH)) {
            cards.add(new AuraSkillCard(row[4], row[1], row[3], Element.valueOf(row[2]), Integer.parseInt(row[5]),
                    Integer.parseInt(row[6]), Integer.parseInt(row[7])));
        }

        // Destroy skill
        for (String[] row : getListFromCsv(Constants.DESTROY_SKILL_CSV_FILE_PATH)) {
            cards.add(new DestroySkillCard(row[4], row[1], row[3], Element.valueOf(row[2]), Integer.parseInt(row[5])));
        }

        // Powerup skill
        for (String[] row : getListFromCsv(Constants.POWERUP_SKILL_CSV_FILE_PATH)) {
            cards.add(new PowerUpSkillCard(row[4], row[1], row[3], Element.valueOf(row[2]), Integer.parseInt(row[5])));
        }
    }

    @Override
    public void init() throws Exception {
        this.loadCards();
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(new MainView(), Constants.WIDTH, Constants.HEIGHT);
        scene.setFill(Color.GREY);

        primaryStage.setTitle("Avatar Duel by K03-G01");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}