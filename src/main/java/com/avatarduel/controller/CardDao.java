package com.avatarduel.controller;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.avatarduel.Constants;
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

public class CardDao {
    private List<Card> landCardList, characterCardList, auraSkillList, destroySkillList, powerUpSkillList;

    public CardDao() throws IOException, URISyntaxException {
        this.landCardList = new ArrayList<>();
        this.characterCardList = new ArrayList<>();
        this.auraSkillList = new ArrayList<>();
        this.destroySkillList = new ArrayList<>();
        this.powerUpSkillList = new ArrayList<>();

        // Land
        for (String[] row : getListFromCsv(Constants.LAND_CSV_FILE_PATH)) {
            landCardList.add(new LandCard(row[4], Integer.parseInt(row[0]), row[1], row[3], Element.valueOf(row[2])));
        }

        // Character
        for (String[] row : getListFromCsv(Constants.CHARACTER_CSV_FILE_PATH)) {
            characterCardList
                    .add(new CharacterCard(row[4], Integer.parseInt(row[0]), row[1], row[3], Element.valueOf(row[2]),
                            Integer.parseInt(row[7]), Integer.parseInt(row[5]), Integer.parseInt(row[6])));
        }

        // Aura skill
        for (String[] row : getListFromCsv(Constants.AURA_SKILL_CSV_FILE_PATH)) {
            auraSkillList
                    .add(new AuraSkillCard(row[4], Integer.parseInt(row[0]), row[1], row[3], Element.valueOf(row[2]),
                            Integer.parseInt(row[5]), Integer.parseInt(row[6]), Integer.parseInt(row[7])));
        }

        // Destroy skill
        for (String[] row : getListFromCsv(Constants.DESTROY_SKILL_CSV_FILE_PATH)) {
            destroySkillList.add(new DestroySkillCard(row[4], Integer.parseInt(row[0]), row[1], row[3],
                    Element.valueOf(row[2]), Integer.parseInt(row[5])));
        }

        // Powerup skill
        for (String[] row : getListFromCsv(Constants.POWERUP_SKILL_CSV_FILE_PATH)) {
            powerUpSkillList.add(new PowerUpSkillCard(row[4], Integer.parseInt(row[0]), row[1], row[3],
                    Element.valueOf(row[2]), Integer.parseInt(row[5])));
        }
    }

    private List<String[]> getListFromCsv(String path) throws IOException, URISyntaxException {
        File csvFile = new File(ResourceFinder.getURI(path));
        CSVReader reader = new CSVReader(csvFile, ";");
        reader.setSkipHeader(true);
        return reader.read();
    }

    //#Card List getter
    public List<Card> getLandCardList() {
        return this.landCardList;
    }

    public List<Card> getCharacterCardList() {
        return this.characterCardList;
    }

    public List<Card> getAuraSkillCardList() {
        return this.auraSkillList;
    }

    public List<Card> getDestroySkillCardList() {
        return this.destroySkillList;
    }

    public List<Card> getPowerUpSkillCardList() {
        return this.powerUpSkillList;
    }

    //#region Random getter
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
            if (take == count) {
                Collections.shuffle(collect);
            }
            res.addAll(collect.subList(0, take));
            count -= take;
        }
        return res;
    }
    //#endregion

    //#Randomly return a card
    // public LandCard getLandCard() {
    //     Random rand = new Random();
    //     int rIdx = rand.nextInt(this.landCardList.size());
    //     Card landCardList[rIdx] = new Array<>();
    //     return new LandCard(landCardList[rIdx][4], Integer.parseInt(landCardList[rIdx][0]), landCardList[rIdx][1], landCardList[rIdx][3], Element.valueOf(landCardList[rIdx][2]))
    // }

    // public CharacterCard getCharacterCard() {
    //     Random rand = new Random();
    //     int rIdx = rand.nextInt(this.characterCardList.size());
    //     return new CharacterCard(characterCardList[rIdx][4], Integer.parseInt(characterCardList[rIdx][0]), characterCardList[rIdx][1], characterCardList[rIdx][3], Element.valueOf(characterCardList[rIdx][2]),
    //                 Integer.parseInt(characterCardList[rIdx][7]), Integer.parseInt(characterCardList[rIdx][5]), Integer.parseInt(characterCardList[rIdx][6]))
    // }

    // public AuraSkillCard getAuraSkillCard() {
    //     Random rand = new Random();
    //     int rIdx = rand.nextInt(this.auraSkillList.size());
    //     return new AuraSkillCard(auraSkillList[rIdx][4], Integer.parseInt(auraSkillList[rIdx][0]), auraSkillList[rIdx][1], auraSkillList[rIdx][3], Element.valueOf(auraSkillList[rIdx][2]),
    //                 Integer.parseInt(auraSkillList[rIdx][5]), Integer.parseInt(auraSkillList[rIdx][6]), Integer.parseInt(auraSkillList[rIdx][7]))
    // }

    // public DestroySkillCard getDestroySkillCard() {
    //     Random rand = new Random();
    //     int rIdx = rand.nextInt(this.destroySkillList.size());
    //     return new DestroySkillCard(destroySkillList[rIdx][4], Integer.parseInt(destroySkillList[rIdx][0]), destroySkillList[rIdx][1], destroySkillList[rIdx][3], Element.valueOf(destroySkillList[rIdx][2]),
    //                 Integer.parseInt(destroySkillList[rIdx][5]))
    // }

    // public PowerUpSkillCard getPowerUpSkillCard() {
    //     Random rand = new Random();
    //     int rIdx = rand.nextInt(this.powerUpSkillList.size());
    //     return new PowerUpSkillCard(powerUpSkillList[rIdx][4], Integer.parseInt(powerUpSkillList[rIdx][0]), powerUpSkillList[rIdx][1], powerUpSkillList[rIdx][3], Element.valueOf(powerUpSkillList[rIdx][2]))
    // }

}