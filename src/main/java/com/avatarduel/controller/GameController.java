package com.avatarduel.controller;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.avatarduel.Constants;
import com.avatarduel.controller.listener.CardEventListener;
import com.avatarduel.controller.listener.MouseEventListener;
import com.avatarduel.model.Element;
import com.avatarduel.model.Phase;
import com.avatarduel.model.Player;
import com.avatarduel.model.card.ActiveCharacterCard;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CharacterCard;
import com.avatarduel.model.card.ClosedCard;
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
import javafx.scene.Cursor;
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
    private Player player1, player2, turn;
    private Card lastTouchedCard, closedCard;
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
        playPhase();
        refreshView();
    }

    public Card getLastTouchedCard() {
        return lastTouchedCard;
    }

    public void setTouchedCard(Card cc) {
        if (lastTouchedCard != cc) {
            lastTouchedCard = cc;
            refreshView();
        }
    }

    public Scene getScene() {
        return scene;
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
        player1 = new Player("Player 1", ViewPosition.BOTTOM);
        player2 = new Player("Player 2", ViewPosition.TOP);

        Collections.shuffle(cards);
        addToPlayers(cards.stream().filter(c -> c instanceof SkillCard).collect(Collectors.toList()),
                Constants.CARD_RATIO);
        addToPlayers(cards.stream().filter(c -> c instanceof CharacterCard).collect(Collectors.toList()),
                Constants.CARD_RATIO * 2);
        addToPlayers(cards.stream().filter(c -> c instanceof LandCard).collect(Collectors.toList()),
                Constants.CARD_RATIO * 2);

        lastTouchedCard = closedCard = new ClosedCard();

        player1.drawCard(Constants.FIRST_CARD_DRAWN);
        player2.drawCard(Constants.FIRST_CARD_DRAWN);

        phase = Phase.values()[0];
        turn = player1;
        scene = new Scene(createMainView(), Constants.WIDTH, Constants.HEIGHT);

        // TODO delete later
        player1.putCard(0, 5, player1.getHandCards().get(0));
        ActiveCharacterCard ac = ((CharacterCard) cards.stream().filter(c -> c instanceof CharacterCard).findAny()
                .get()).createActiveCard();
        ac.switchPosition();
        player1.putCard(0, 4, ac);

        playPhase();
    }

    public Card getClosedCard() {
        return closedCard;
    }

    public void playPhase() {
        switch (phase) {
            case DRAW: {
                turn.drawCard();
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
        refreshView();
    }

    public MainView createMainView() {
        CardEventListener defaultCardEventListener = new CardEventListener() {

            @Override
            public void onMouseEntered(Card card) {
                setLastTouchedCard(card);
                getScene().setCursor(Cursor.HAND);
            }

            @Override
            public void onMouseExited(Card card) {
                getScene().setCursor(Cursor.DEFAULT);
            }

            @Override
            public void onMouseRightClicked(Card card) {

            }

            @Override
            public void onMouseLeftClicked(Card card) {

            }

        };
        return new MainView(phase, player1, player2, lastTouchedCard, defaultCardEventListener,
                defaultCardEventListener, new MouseEventListener() {

                    @Override
                    public void onMouseEntered() {
                        getScene().setCursor(Cursor.HAND);
                    }

                    @Override
                    public void onMouseExited() {
                        getScene().setCursor(Cursor.DEFAULT);
                    }

                    @Override
                    public void onMouseRightClicked() {

                    }

                    @Override
                    public void onMouseLeftClicked() {
                        nextPhase();
                    }

                });
    }

    protected void setLastTouchedCard(Card card) {
        lastTouchedCard = card;
        refreshView();
    }

    public void refreshView() {
        this.scene.setRoot(createMainView());
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