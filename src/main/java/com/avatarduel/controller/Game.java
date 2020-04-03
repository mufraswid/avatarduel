package com.avatarduel.controller;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.avatarduel.Main;
import com.avatarduel.model.Element;
import com.avatarduel.model.card.CharacterCard;
import com.avatarduel.model.card.LandCard;
import com.avatarduel.model.card.skill.AuraSkillCard;
import com.avatarduel.util.CSVReader;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Game extends Application {

    private static final double WIDTH = 1080, HEIGHT = 720;
    private static final String LAND_CSV_FILE_PATH = "card/data/land.csv",
            CHARACTER_CSV_FILE_PATH = "card/data/character.csv", AURA_SKILL_CSV_FILE_PATH = "card/data/skill_aura.csv";
    private static Game instance;

    public static void start() {
        Application.launch();
    }

    public static Game getInstance() {
        return Game.instance;
    }

    private List<LandCard> landCards;
    private List<CharacterCard> characterCards;
    private List<AuraSkillCard> auraSkillCards;

    public Game() {
        Game.instance = this;
    }

    private List<String[]> getListFromCsv(String path) throws IOException, URISyntaxException {
        File csvFile = new File(Main.class.getResource(path).toURI());
        CSVReader reader = new CSVReader(csvFile, "\t");
        reader.setSkipHeader(true);
        return reader.read();
    }

    public void loadCards() throws IOException, URISyntaxException {
        landCards = new ArrayList<>();
        for (String[] row : getListFromCsv(LAND_CSV_FILE_PATH)) {
            landCards.add(new LandCard(row[4], row[1], row[3], Element.valueOf(row[2])));
        }

        characterCards = new ArrayList<>();
        for (String[] row : getListFromCsv(CHARACTER_CSV_FILE_PATH)) {
            characterCards.add(new CharacterCard(row[4], row[1], row[3], Element.valueOf(row[2]),
                    Integer.parseInt(row[7]), Integer.parseInt(row[5]), Integer.parseInt(row[6])));
        }

        auraSkillCards = new ArrayList<>();
        for (String[] row : getListFromCsv(AURA_SKILL_CSV_FILE_PATH)) {
            auraSkillCards.add(new AuraSkillCard(row[4], row[1], row[3], Element.valueOf(row[2]),
                    Integer.parseInt(row[5]), Integer.parseInt(row[6]), Integer.parseInt(row[7])));
        }
    }

    @Override
    public void init() throws Exception {
        this.loadCards();
    }

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.setFill(Color.GREY);

        primaryStage.setTitle("Avatar Duel by K03-01");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}