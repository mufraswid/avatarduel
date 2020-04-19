package com.avatarduel.controller;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.avatarduel.model.CardType;
import com.avatarduel.model.Element;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CharacterCard;
import com.avatarduel.model.card.LandCard;
import com.avatarduel.model.card.skill.AuraSkillCard;
import com.avatarduel.model.card.skill.DestroySkillCard;
import com.avatarduel.model.card.skill.PowerUpSkillCard;
import com.avatarduel.util.CSVReader;
import com.avatarduel.util.ResourceFinder;

/**
 * CardDao serve as an object where all data about cards are stored
 */
public class CardDao {

    private static final String LAND_CSV_FILE_PATH = "card/data/land.csv";
    private static final String CHARACTER_CSV_FILE_PATH = "card/data/character.csv";
    private static final String AURA_SKILL_CSV_FILE_PATH = "card/data/skill/aura.csv";
    private static final String DESTROY_SKILL_CSV_FILE_PATH = "card/data/skill/destroy.csv";
    private static final String POWERUP_SKILL_CSV_FILE_PATH = "card/data/skill/powerup.csv";

    private List<Card> landCardList, characterCardList, auraSkillList, destroySkillList, powerUpSkillList;

    /**
     * Constructor
     *
     * @throws IOException        if an input or output exception occured
     * @throws URISyntaxException if a string could not be parsed as a URI reference
     */
    public CardDao() throws IOException, URISyntaxException {
        this.landCardList = new ArrayList<>();
        this.characterCardList = new ArrayList<>();
        this.auraSkillList = new ArrayList<>();
        this.destroySkillList = new ArrayList<>();
        this.powerUpSkillList = new ArrayList<>();

        // Land
        for (String[] row : getListFromCsv(LAND_CSV_FILE_PATH)) {
            landCardList.add(new LandCard(row[4], row[1], row[3], Element.valueOf(row[2])));
        }

        // Character
        for (String[] row : getListFromCsv(CHARACTER_CSV_FILE_PATH)) {
            characterCardList.add(new CharacterCard(row[4], row[1], row[3], Element.valueOf(row[2]),
                    Integer.parseInt(row[7]), Integer.parseInt(row[5]), Integer.parseInt(row[6])));
        }

        // Aura skill
        for (String[] row : getListFromCsv(AURA_SKILL_CSV_FILE_PATH)) {
            auraSkillList.add(new AuraSkillCard(row[4], row[1], row[3], Element.valueOf(row[2]),
                    Integer.parseInt(row[5]), Integer.parseInt(row[6]), Integer.parseInt(row[7])));
        }

        // Destroy skill
        for (String[] row : getListFromCsv(DESTROY_SKILL_CSV_FILE_PATH)) {
            destroySkillList.add(
                    new DestroySkillCard(row[4], row[1], row[3], Element.valueOf(row[2]), Integer.parseInt(row[5])));
        }

        // Powerup skill
        for (String[] row : getListFromCsv(POWERUP_SKILL_CSV_FILE_PATH)) {
            powerUpSkillList.add(
                    new PowerUpSkillCard(row[4], row[1], row[3], Element.valueOf(row[2]), Integer.parseInt(row[5])));
        }
    }

    /**
     * Get list of lines separated with ";" in csv file from the specified path
     *
     * @param path path to csv file that want to be parsed
     * @return list of string arrays each represent 1 row
     * @throws IOException        if an input or output exception occured
     * @throws URISyntaxException if a string could not be parsed as a URI reference
     */
    private List<String[]> getListFromCsv(String path) throws IOException, URISyntaxException {
        File csvFile = new File(ResourceFinder.getURI(path));
        CSVReader reader = new CSVReader(csvFile, ";");
        reader.setSkipHeader(true);
        return reader.read();
    }

    // #Card List getter
    /**
     * @return this list of land cards
     */
    public List<Card> getLandCardList() {
        return this.landCardList;
    }

    /**
     * @return this list of character cards
     */
    public List<Card> getCharacterCardList() {
        return this.characterCardList;
    }

    /**
     * @return this list of aura skill cards
     */
    public List<Card> getAuraSkillCardList() {
        return this.auraSkillList;
    }

    /**
     * @return this list of destroy skill cards
     */
    public List<Card> getDestroySkillCardList() {
        return this.destroySkillList;
    }

    /**
     * @return this list of power up skill cards
     */
    public List<Card> getPowerUpSkillCardList() {
        return this.powerUpSkillList;
    }

    // #region Random getter
    /**
     * Get a randomized deck with a specified number of each type
     *
     * @param landCount      number of land cards for the deck
     * @param characterCount number of character cards for the deck
     * @param auraCount      number of aura cards for the deck
     * @param destroyCount   number of destroy cards for the deck
     * @param powerupCount   number of power up cards for the deck
     * @return randomized list of cards with each specified number of type
     */
    public List<Card> getRandomDeck(int landCount, int characterCount, int auraCount, int destroyCount,
            int powerupCount) {
        List<Card> res = new ArrayList<>();

        res.addAll(getRandomCards(CardType.LAND, landCount));
        res.addAll(getRandomCards(CardType.CHARACTER, characterCount));
        res.addAll(getRandomCards(CardType.AURA_SKILL, auraCount));
        res.addAll(getRandomCards(CardType.DESTROY_SKILL, destroyCount));
        res.addAll(getRandomCards(CardType.POWERUP_SKILL, powerupCount));

        Collections.shuffle(res);

        return res;
    }

    /**
     * Get random collection of cards with specified type and number
     *
     * @param type  type of card that want to be randomly picked
     * @param count number of cards that want to be randomly picked
     * @return list of randomly selected card
     */
    private List<Card> getRandomCards(CardType type, int count) {
        List<Card> res = new ArrayList<>();
        List<Card> collect;
        switch (type) {
            case AURA_SKILL:
                collect = auraSkillList;
                break;
            case CHARACTER:
                collect = characterCardList;
                break;
            case DESTROY_SKILL:
                collect = destroySkillList;
                break;
            case LAND:
                collect = landCardList;
                break;
            case POWERUP_SKILL:
                collect = powerUpSkillList;
                break;
            default:
                throw new RuntimeException("Card Data Access Object doesn't have the type " + type);
        }
        int listLength = collect.size();
        while (count > 0) {
            int take = Math.min(count, listLength);
            if (take != listLength) {
                Collections.shuffle(collect);
            }
            res.addAll(collect.subList(0, take).stream().map(c -> c.copy()).collect(Collectors.toList()));
            count -= take;
        }
        return res;
    }
    // #endregion

}
