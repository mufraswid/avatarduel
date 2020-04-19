package com.avatarduel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;

import com.avatarduel.controller.CardFieldDimension;
import com.avatarduel.controller.PlayerController;
import com.avatarduel.model.CardPosition;
import com.avatarduel.model.Element;
import com.avatarduel.model.Player;
import com.avatarduel.model.card.ArenaCharacterCard;
import com.avatarduel.model.card.CharacterCard;
import com.avatarduel.model.card.skill.AuraSkillCard;
import com.avatarduel.model.card.skill.PowerUpSkillCard;
import com.avatarduel.repository.AuraSkillCardRepository;
import com.avatarduel.repository.CharacterCardRepository;
import com.avatarduel.repository.DestroySkillCardRepository;
import com.avatarduel.repository.LandCardRepository;
import com.avatarduel.repository.PowerUpSkillCardRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

/**
 * This class is used for testing the game mechanism
 */
public class ArenaCharacterCardTest {

    private PlayerController playerController;

    /**
     * Initialize before testing
     *
     * @throws IOException        if an input or output exception occured
     * @throws URISyntaxException if a string could not be parsed as a URI reference
     */
    @BeforeEach
    public void init() throws IOException, URISyntaxException {
        CardFieldDimension cardFieldDimension = new CardFieldDimension(6, 6);
        playerController = new PlayerController(0, new Player("Player 1", cardFieldDimension),
                new Player("Player 2", cardFieldDimension), new LandCardRepository(), new CharacterCardRepository(),
                new AuraSkillCardRepository(), new DestroySkillCardRepository(), new PowerUpSkillCardRepository());
    }

    /**
     * Make arena card with specified attack and defense in attack position
     *
     * @param attack  attack point
     * @param defense defense point
     * @return new arena character card
     */
    private ArenaCharacterCard createCharacterCard(int attack, int defense) {
        return createCharacterCard(attack, defense, CardPosition.ATTACK);
    }

    /**
     * Make arena card with specified attack and defense and position
     *
     * @param attack   attack point
     * @param defense  defense point
     * @param position position
     * @return new arena character card
     */
    private ArenaCharacterCard createCharacterCard(int attack, int defense, CardPosition position) {
        ArenaCharacterCard res = new ArenaCharacterCard(new CharacterCard("", "", "", Element.AIR, 0, attack, defense));
        res.setPosition(position);
        return res;
    }

    /**
     * @return new power up card
     */
    private PowerUpSkillCard createPowerUpCard() {
        return new PowerUpSkillCard("", "", "", Element.AIR, 0);
    }

    /**
     * Create new aura card with specified extra attack and defense
     *
     * @param datk
     * @param ddef
     * @return new aura card
     */
    private AuraSkillCard createAuraCard(int datk, int ddef) {
        return new AuraSkillCard("", "", "", Element.AIR, 0, datk, ddef);
    }

    /**
     * @return enemy HP
     */
    private int getEnemyHP() {
        return playerController.getEnemyCurrentTurn().getHP();
    }

    /**
     * Test attack mechanism
     *
     * @param atk      attack point
     * @param powerUp  extra power up
     * @param auras    extra aura
     * @param atkDef   attack point for defender card
     * @param defDef   defend point for defender card
     * @param aurasDef extra aura for defender card
     * @param position position of defender card
     * @param damage   target damage dealt
     * @param succeed  result flag
     */
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

    @Test
    public void testAddPowerUp() {
        ArenaCharacterCard card = createCharacterCard(0, 0);
        PowerUpSkillCard skillCard = createPowerUpCard();
        card.addSkill(skillCard);
        assertEquals(true, card.isPoweredUp());
    }

    @Test
    public void testTotalAttack() {
        ArenaCharacterCard card = createCharacterCard(0, 0);
        AuraSkillCard aura1 = createAuraCard(20, -50);
        AuraSkillCard aura2 = createAuraCard(80, -40);
        card.addSkill(aura1);
        card.addSkill(aura2);
        assertEquals(100, card.getTotalAttack());
        card.removeSkill(aura1);
        assertEquals(80, card.getTotalAttack());
        card.removeSkill(aura1);
        assertEquals(80, card.getTotalAttack());
        card.removeSkill(aura2);
        assertEquals(0, card.getTotalAttack());
    }

    @Test
    public void testRemovePowerUp() {
        ArenaCharacterCard card = createCharacterCard(0, 0);
        PowerUpSkillCard skillCard = createPowerUpCard();
        card.addSkill(skillCard);
        card.removeSkill(skillCard);
        assertEquals(false, card.isPoweredUp());
    }

    @Test
    public void testTotalDefense() {
        ArenaCharacterCard card = createCharacterCard(0, 0);
        AuraSkillCard aura1 = createAuraCard(20, -50);
        AuraSkillCard aura2 = createAuraCard(80, -40);
        card.addSkill(aura1);
        card.addSkill(aura2);
        assertEquals(-90, card.getTotalDefense());
        card.removeSkill(aura1);
        assertEquals(-40, card.getTotalDefense());
        card.removeSkill(aura1);
        assertEquals(-40, card.getTotalDefense());
        card.removeSkill(aura2);
        assertEquals(0, card.getTotalDefense());
    }

    @Test
    public void testPosition() {
        ArenaCharacterCard card = createCharacterCard(0, 0);
        assertEquals(CardPosition.ATTACK, card.getPosition());
    }

    @Test
    public void testSwitchPosition() {
        ArenaCharacterCard card = createCharacterCard(0, 0);
        card.switchPosition();
        assertEquals(CardPosition.DEFENSE, card.getPosition());
    }

    @Test
    public void testHasAttacked() {
        ArenaCharacterCard attacker = createCharacterCard(20, 0);
        assertEquals(false, attacker.hasAttacked());
    }

    @Test
    public void testHasAttackedAfterAttack() {
        ArenaCharacterCard attacker = createCharacterCard(20, 0);
        ArenaCharacterCard defender = createCharacterCard(19, 0);
        playerController.doAttack(attacker, defender);
        assertEquals(true, attacker.hasAttacked());
    }

    @Test
    public void testEnableToAttack() {
        ArenaCharacterCard attacker = createCharacterCard(20, 0);
        assertEquals(false, attacker.isEnableToAttack());
    }

    @Test
    public void testEnableToAttackAfterDrawPhase() {
        ArenaCharacterCard attacker = createCharacterCard(20, 0);
        playerController.getPlayer1().putCharacterCard(attacker);
        playerController.doDrawPhase();
        assertEquals(true, attacker.isEnableToAttack());
    }

    @Test
    public void testDescription() {
        String desc = "description value";
        ArenaCharacterCard card = new ArenaCharacterCard(new CharacterCard("", "", desc, Element.AIR, 0, 0, 0));
        assertEquals("Character Card\n" + desc, card.getFullDescription());
    }

}
