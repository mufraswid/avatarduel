package com.avatarduel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;

import com.avatarduel.controller.CardDao;
import com.avatarduel.controller.PlayerController;
import com.avatarduel.model.CardPosition;
import com.avatarduel.model.Element;
import com.avatarduel.model.card.ActiveCharacterCard;
import com.avatarduel.model.card.CharacterCard;
import com.avatarduel.model.card.skill.PowerUpSkillCard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LogicTest {

    private PlayerController playerController;

    @DisplayName("Example Test Method with No Business Logic")
    @Test
    public void testTheTest() {
        assertTrue(true);
    }

    @BeforeEach
    public void init() throws IOException, URISyntaxException {
        playerController = new PlayerController(new CardDao());
    }

    private ActiveCharacterCard createCharacterCard(int attack, int defense) {
        return createCharacterCard(attack, defense, CardPosition.ATTACK);
    }

    private ActiveCharacterCard createCharacterCard(int attack, int defense, boolean poweredUp) {
        ActiveCharacterCard res = createCharacterCard(attack, defense, CardPosition.ATTACK);
        res.addSkill(createPowerUpCard());
        return res;
    }

    private PowerUpSkillCard createPowerUpCard() {
        return new PowerUpSkillCard("", "", "", Element.AIR, 0);
    }

    private ActiveCharacterCard createCharacterCard(int attack, int defense, CardPosition position) {
        ActiveCharacterCard res = new ActiveCharacterCard(
                new CharacterCard("", "", "", Element.AIR, 0, attack, defense));
        res.setPosition(position);
        return res;
    }

    private int getEnemyHP() {
        return playerController.getEnemyCurrentTurn().getHP();
    }

    @Test
    public void testAttack() {
        int hp = getEnemyHP();
        playerController.doAttack(createCharacterCard(20, 20), createCharacterCard(20, 20));
        assertEquals(0, getEnemyHP() - hp, "(20, 20, attack) nyerang (20, 20, attack), HP lawan tetap");

        hp = getEnemyHP();
        playerController.doAttack(createCharacterCard(30, 30), createCharacterCard(10, 10, CardPosition.DEFENSE));
        assertEquals(0, getEnemyHP() - hp, "(30, 30, attack) nyerang (10, 10, defend), HP lawan tetap");

        hp = getEnemyHP();
        playerController.doAttack(createCharacterCard(30, 30), createCharacterCard(10, 10));
        assertEquals(-20, getEnemyHP() - hp, "(30, 30, attack) nyerang (10, 10, attack), HP lawan berkurang 20");

        hp = getEnemyHP();
        playerController.doAttack(createCharacterCard(30, 30), null);
        assertEquals(-30, getEnemyHP() - hp, "(30, 30, attack) nyerang null, HP lawan berkurang 30");

        hp = getEnemyHP();
        playerController.doAttack(createCharacterCard(30, 30, true), createCharacterCard(20, 10, CardPosition.DEFENSE));
        assertEquals(-20, getEnemyHP() - hp,
                "(30, 30, attack, powerup) nyerang (20, 10, defend), HP lawan berkurang 20");
    }

}