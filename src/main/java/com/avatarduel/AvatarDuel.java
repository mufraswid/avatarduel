package com.avatarduel;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.avatarduel.model.Element;
import com.avatarduel.model.card.CharacterCard;
import com.avatarduel.model.card.LandCard;
import com.avatarduel.model.card.skill.AuraSkillCard;
import com.avatarduel.util.CSVReader;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AvatarDuel extends Application {
    private static final String LAND_CSV_FILE_PATH = "card/data/land.csv",
            CHARACTER_CSV_FILE_PATH = "card/data/character.csv", AURA_SKILL_CSV_FILE_PATH = "card/data/skill_aura.csv";

    private List<LandCard> landCards;
    private List<CharacterCard> characterCards;
    private List<AuraSkillCard> auraSkillCards;

    private List<String[]> getListFromCsv(String path) throws IOException, URISyntaxException {
        File csvFile = new File(getClass().getResource(path).toURI());
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
    public void start(Stage stage) {
        Text text = new Text();
        text.setText("Loading...");
        text.setX(50);
        text.setY(50);

        Group root = new Group();
        root.getChildren().add(text);

        Scene scene = new Scene(root, 1280, 720);

        stage.setTitle("Avatar Duel");
        stage.setScene(scene);
        stage.show();

        try {
            this.loadCards();
            text.setText("Avatar Duel!");
        } catch (Exception e) {
            text.setText("Failed to load cards: " + e);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}