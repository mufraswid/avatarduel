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
import com.avatarduel.model.card.ActiveCharacterCard;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CharacterCard;
import com.avatarduel.model.card.LandCard;
import com.avatarduel.model.card.skill.AuraSkillCard;
import com.avatarduel.model.card.skill.DestroySkillCard;
import com.avatarduel.model.card.skill.PowerUpSkillCard;
import com.avatarduel.model.card.skill.SkillCard;
import com.avatarduel.util.CSVReader;
import com.avatarduel.util.PathConverter;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;
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
    private Player player1, player2, turn;
    private Phase phase;

    private RenderController renderController;

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
        playPhase();
    }

    public Phase getPhase() {
        return phase;
    }

    public List<Card> getCards() {
        return cards;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getCurrentPlayerTurn() {
        return turn;
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
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");

        Collections.shuffle(cards);
        addToPlayers(cards.stream().filter(c -> c instanceof SkillCard).collect(Collectors.toList()),
                Constants.CARD_RATIO);
        addToPlayers(cards.stream().filter(c -> c instanceof CharacterCard).collect(Collectors.toList()),
                Constants.CARD_RATIO * 2);
        addToPlayers(cards.stream().filter(c -> c instanceof LandCard).collect(Collectors.toList()),
                Constants.CARD_RATIO * 2);

        player1.drawCard(Constants.FIRST_CARD_DRAWN);
        player2.drawCard(Constants.FIRST_CARD_DRAWN);

        phase = Phase.values()[0];
        turn = player1;
        renderController = createRenderController();

        // TODO delete later
        player1.putCard(0, 5, player1.getHandCards().get(0));
        ActiveCharacterCard ac = ((CharacterCard) cards.stream().filter(c -> c instanceof CharacterCard).findAny()
                .get()).createActiveCard();
        ac.switchPosition();
        player1.putCard(0, 4, ac);
        renderController.updateFieldCard(player1);

        playPhase();
    }

    public RenderController createRenderController() {
        return new RenderController(player1, player2, new CardHandEventListener(this, renderController),
                new CardFieldEventListener(this, renderController), new PhaseEventListener(this, renderController));
    }

    public Player getEnemyCurrentTurn() {
        return turn == player1 ? player2 : player1;
    }

    public void playPhase() {
        renderController.updatePhase(phase);
        switch (phase) {
            case DRAW: {
                if (turn.getCurrentDeckCount() <= 0) {
                    endGame(getEnemyCurrentTurn());
                    return;
                }
                turn.drawCard();
                renderController.updateHandCard(turn);
                break;
            }
            case MAIN1: {

                break;
            }
            case BATTLE: {

                break;
            }
            case MAIN2: {

                break;
            }
            case END: {

                break;
            }
        }
    }

    public void endGame(Player winner) {
        Alert alert = new Alert(AlertType.INFORMATION, winner.getName() + " win!", ButtonType.OK);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
        Platform.exit();
    }

    private void addToPlayers(List<Card> cards, int ratio) {
        player1.addToDeck(cards.subList(0, ratio));
        player2.addToDeck(cards.subList(ratio, ratio * 2));
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Avatar Duel by K03-G01");
        primaryStage.setScene(renderController.getScene());
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}