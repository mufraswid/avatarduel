package com.avatarduel.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.avatarduel.Constants;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CharacterCard;
import com.avatarduel.model.card.LandCard;
import com.avatarduel.model.card.skill.AuraSkillCard;
import com.avatarduel.model.card.skill.DestroySkillCard;
import com.avatarduel.model.card.skill.PowerUpSkillCard;
import com.avatarduel.model.card.skill.SkillCard;
import com.avatarduel.util.CSVReader;
import com.avatarduel.util.PathConverter;

public class CardDao {
    List<String[]> landCardList, characterCardList, auraSkillList, destroySkillList, powerUpSkillList;

    public CardDao(){
        this.landCardList = new ArrayList<>(getListFromCsv(Constants.LAND_CSV_FILE_PATH));
        this.characterCardList = new ArrayList<>(getListFromCsv(Constants.CHARACTER_CSV_FILE_PATH));
        this.auraSkillList = new ArrayList<>(getListFromCsv(Constants.AURA_SKILL_CSV_FILE_PATH));
        this.destroySkillList = new ArrayList<>(getListFromCsv(Constants.DESTROY_SKILL_CSV_FILE_PATH));
        this.powerUpSkillList = new ArrayList<>(getListFromCsv(Constants.POWERUP_SKILL_CSV_FILE_PATH));
    }

    private List<String[]> getListFromCsv(String path) throws IOException, URISyntaxException {
        File csvFile = new File(PathConverter.convertPathToURI(path));
        CSVReader reader = new CSVReader(csvFile, "\t");
        reader.setSkipHeader(true);
        return reader.read();
    }

    //#Card List getter
    public List<String[]> getLandCardList(){
        return this.landCardList;
    }

    public List<String[]> getCharacterCardList(){
        return this.characterCardList;
    }

    public List<String[]> getAuraSkillCardList(){
        return this.auraSkillList;
    }

    public List<String[]> getDestroySkillCardList(){
        return this.destroySkillList;
    }

    public List<String[]> getPowerUpSkillCardList(){
        return this.powerUpSkillList;
    }

    //#Randomly return a card
    public LandCard getLandCard() {
        Random rand = new Random();
        int rIdx = rand.nextInt(this.landCardList.size());
        String[] landCardList[rIdx] = new Array<>();
        return new LandCard(landCardList[rIdx][4], Integer.parseInt(landCardList[rIdx][0]), landCardList[rIdx][1], landCardList[rIdx][3], Element.valueOf(landCardList[rIdx][2]))
    }

    public CharacterCard getCharacterCard() {
        Random rand = new Random();
        int rIdx = rand.nextInt(this.characterCardList.size());
        return new CharacterCard(characterCardList[rIdx][4], Integer.parseInt(characterCardList[rIdx][0]), characterCardList[rIdx][1], characterCardList[rIdx][3], Element.valueOf(characterCardList[rIdx][2]),
                    Integer.parseInt(characterCardList[rIdx][7]), Integer.parseInt(characterCardList[rIdx][5]), Integer.parseInt(characterCardList[rIdx][6]))
    }

    public AuraSkillCard getAuraSkillCard() {
        Random rand = new Random();
        int rIdx = rand.nextInt(this.auraSkillList.size());
        return new AuraSkillCard(auraSkillList[rIdx][4], Integer.parseInt(auraSkillList[rIdx][0]), auraSkillList[rIdx][1], auraSkillList[rIdx][3], Element.valueOf(auraSkillList[rIdx][2]),
                    Integer.parseInt(auraSkillList[rIdx][5]), Integer.parseInt(auraSkillList[rIdx][6]), Integer.parseInt(auraSkillList[rIdx][7]))
    }

    public DestroySkillCard getDestroySkillCard() {
        Random rand = new Random();
        int rIdx = rand.nextInt(this.destroySkillList.size());
        return new DestroySkillCard(destroySkillList[rIdx][4], Integer.parseInt(destroySkillList[rIdx][0]), destroySkillList[rIdx][1], destroySkillList[rIdx][3], Element.valueOf(destroySkillList[rIdx][2]),
                    Integer.parseInt(destroySkillList[rIdx][5]))
    }

    public PowerUpSkillCard getPowerUpSkillCard() {
        Random rand = new Random();
        int rIdx = rand.nextInt(this.powerUpSkillList.size());
        return new PowerUpSkillCard(powerUpSkillList[rIdx][4], Integer.parseInt(powerUpSkillList[rIdx][0]), powerUpSkillList[rIdx][1], powerUpSkillList[rIdx][3], Element.valueOf(powerUpSkillList[rIdx][2]))
    }

}