package com.avatarduel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;

import com.avatarduel.controller.CardDao;
import com.avatarduel.controller.PlayerController;
import com.avatarduel.model.CardPosition;
import com.avatarduel.model.Element;
import com.avatarduel.model.card.ArenaCharacterCard;
import com.avatarduel.model.card.CharacterCard;
import com.avatarduel.model.card.skill.AuraSkillCard;
import com.avatarduel.model.card.skill.PowerUpSkillCard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class LogicTest {

    private PlayerController playerController;

    @BeforeEach
    public void init() throws IOException, URISyntaxException {
        playerController = new PlayerController(new CardDao());
    }

    private ArenaCharacterCard createCharacterCard(int attack, int defense) {
        return createCharacterCard(attack, defense, CardPosition.ATTACK);
    }

    private PowerUpSkillCard createPowerUpCard() {
        return new PowerUpSkillCard("", "", "", Element.AIR, 0);
    }

    private AuraSkillCard createAuraCard(int datk, int ddef) {
        return new AuraSkillCard("", "", "", Element.AIR, 0, datk, ddef);
    }

    private ArenaCharacterCard createCharacterCard(int attack, int defense, CardPosition position) {
        ArenaCharacterCard res = new ArenaCharacterCard(new CharacterCard("", "", "", Element.AIR, 0, attack, defense));
        res.setPosition(position);
        return res;
    }

    private int getEnemyHP() {
        return playerController.getEnemyCurrentTurn().getHP();
    }

    @DisplayName("Testing attack and defend calculation")
    @ParameterizedTest(name = "AttackValue {0}, PowerUp {1}, Auras {2} attacking AttackValue {3}, DefenseValue {4}, Auras {5}, with Position {6} should be resulting damage {7} to the enemy player!")
    @CsvFileSource(resources = "testAttack.csv", delimiter = '\t', emptyValue = "-")
    public void testAttack(int atk, String powerUp, String auras, String atkDef, String defDef, String aurasDef,
            String position, int damage, boolean succeed) {
        ArenaCharacterCard attacker = createCharacterCard(atk, 0);
        if (!powerUp.equals("-")) {
            attacker.addSkill(createPowerUpCard());
        }
        if (!auras.equals("-")) {
            for (String aura : auras.split(";")) {
                String[] split = aura.split(" ");
                attacker.addSkill(createAuraCard(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
            }
        }

        ArenaCharacterCard defender = null;
        if (!atkDef.equals("-") && !defDef.equals("-")) {
            defender = createCharacterCard(Integer.parseInt(atkDef), Integer.parseInt(defDef),
                    position.equals("-") ? CardPosition.ATTACK : CardPosition.valueOf(position.toUpperCase()));
            if (!aurasDef.equals("-")) {
                for (String aura : aurasDef.split(";")) {
                    String[] split = aura.split(" ");
                    defender.addSkill(createAuraCard(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
                }
            }
        }

        int hp = getEnemyHP();
        assertEquals(succeed, playerController.doAttack(attacker, defender));
        assertEquals(damage, getEnemyHP() - hp);
    }

}