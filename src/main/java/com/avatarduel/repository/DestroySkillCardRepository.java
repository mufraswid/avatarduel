package com.avatarduel.repository;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import com.avatarduel.model.Element;
import com.avatarduel.model.card.skill.DestroySkillCard;

/**
 * CardDao serve as an object where all data about cards are stored
 */
public class DestroySkillCardRepository extends BaseCardRepository {

    private static final String DESTROY_SKILL_CSV_FILE_PATH = "card/data/skill/destroy.csv";

    /**
     * Constructor
     *
     * @throws IOException        if an input or output exception occured
     * @throws URISyntaxException if a string could not be parsed as a URI reference
     */
    public DestroySkillCardRepository() throws IOException, URISyntaxException {
        this.cardList = new ArrayList<>();
        for (String[] row : getListFromCsv(DESTROY_SKILL_CSV_FILE_PATH)) {
            cardList.add(
                    new DestroySkillCard(row[4], row[1], row[3], Element.valueOf(row[2]), Integer.parseInt(row[5])));
        }
    }

}
