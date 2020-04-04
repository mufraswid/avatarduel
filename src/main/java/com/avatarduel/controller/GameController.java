package com.avatarduel.controller;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.avatarduel.Constants;
import com.avatarduel.model.Element;
import com.avatarduel.model.Phase;
import com.avatarduel.model.Player;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CharacterCard;
import com.avatarduel.model.card.LandCard;
import com.avatarduel.model.card.skill.AuraSkillCard;
import com.avatarduel.model.card.skill.DestroySkillCard;
import com.avatarduel.model.card.skill.PowerUpSkillCard;
import com.avatarduel.model.card.skill.SkillCard;
import com.avatarduel.util.CSVReader;
import com.avatarduel.util.PathConverter;
import com.avatarduel.view.ViewPosition;
import com.avatarduel.view.main.MainView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameController extends Application {

    private static GameController instance;

    public static void start() {
        Application.launch();
    }

    public static GameController getInstance() {
        return GameController.instance;
    }

    private List<Card> cards;
    private PlayerController player1, player2, turn;
    private MainView mainView;
    private Scene scene;
    private Phase phase;

    public GameController() {
        GameController.instance = this;
    }

    public void nextPhase() {
        if (phase.ordinal() == Phase.values().length - 1) {
            phase = Phase.values()[0];
            turn = turn == player1 ? player2 : player1;
        } else {
            phase = Phase.values()[phase.ordinal() + 1];
        }
    }

    public Scene getScene() {
        return scene;
    }

    public MainView getMainView() {
        return mainView;
    }

    public List<Card> getCards() {
        return cards;
    }

    public PlayerController getPlayer1() {
        return player1;
    }

    public PlayerController getPlayer2() {
        return player2;
    }

    private List<String[]> getListFromCsv(String path) throws IOException, URISyntaxException {
        File csvFile = new File(PathConverter.convertPathToURI(path));
        CSVReader reader = new CSVReader(csvFile, "\t");
        reader.setSkipHeader(true);
        return reader.read();
    }

    private void loadCards() throws IOException, URISyntaxException {
        cards = new ArrayList<>();

        // Land
        for (String[] row : getListFromCsv(Constants.LAND_CSV_FILE_PATH)) {
            cards.add(new LandCard(row[4], Integer.parseInt(row[0]), row[1], row[3], Element.valueOf(row[2])));
        }

        // Character
        for (String[] row : getListFromCsv(Constants.CHARACTER_CSV_FILE_PATH)) {
            cards.add(new CharacterCard(row[4], Integer.parseInt(row[0]), row[1], row[3], Element.valueOf(row[2]),
                    Integer.parseInt(row[7]), Integer.parseInt(row[5]), Integer.parseInt(row[6])));
        }

        // Aura skill
        for (String[] row : getListFromCsv(Constants.AURA_SKILL_CSV_FILE_PATH)) {
            cards.add(new AuraSkillCard(row[4], Integer.parseInt(row[0]), row[1], row[3], Element.valueOf(row[2]),
                    Integer.parseInt(row[5]), Integer.parseInt(row[6]), Integer.parseInt(row[7])));
        }

        // Destroy skill
        for (String[] row : getListFromCsv(Constants.DESTROY_SKILL_CSV_FILE_PATH)) {
            cards.add(new DestroySkillCard(row[4], Integer.parseInt(row[0]), row[1], row[3], Element.valueOf(row[2]),
                    Integer.parseInt(row[5])));
        }

        // Powerup skill
        for (String[] row : getListFromCsv(Constants.POWERUP_SKILL_CSV_FILE_PATH)) {
            cards.add(new PowerUpSkillCard(row[4], Integer.parseInt(row[0]), row[1], row[3], Element.valueOf(row[2]),
                    Integer.parseInt(row[5])));
        }
    }

    @Override
    public void init() throws Exception {
        this.loadCards();
        player1 = new PlayerController(new Player("Player 1"), ViewPosition.BOTTOM);
        player2 = new PlayerController(new Player("Player 2"), ViewPosition.TOP);

        int ratio = Constants.CARD_RATIO;
        Collections.shuffle(cards);
        addToPlayers(cards.stream().filter(c -> c instanceof SkillCard).collect(Collectors.toList()), ratio);
        addToPlayers(cards.stream().filter(c -> c instanceof CharacterCard).collect(Collectors.toList()), ratio * 2);
        addToPlayers(cards.stream().filter(c -> c instanceof LandCard).collect(Collectors.toList()), ratio * 2);

        scene = new Scene(this.mainView = new MainView(), Constants.WIDTH, Constants.HEIGHT);

        turn = player1;
        mainView.rightMainView.phaseView.setPhase(phase = Phase.DRAW);
    }

    private void addToPlayers(List<Card> cards, int ratio) {
        player1.addToDeck(cards.subList(0, ratio));
        player2.addToDeck(cards.subList(ratio, ratio * 2));
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Avatar Duel by K03-G01");
        primaryStage.setScene(this.scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}