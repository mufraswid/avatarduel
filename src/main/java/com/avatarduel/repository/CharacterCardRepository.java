package com.avatarduel.repository;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import com.avatarduel.model.Element;
import com.avatarduel.model.card.CharacterCard;

/**
 * CardDao serve as an object where all data about cards are stored
 */
public class CharacterCardRepository extends BaseActivableCardRepository {

    private static final String CHARACTER_CSV_FILE_PATH = "card/data/character.csv";

    /**
     * Constructor
     *
     * @throws IOException        if an input or output exception occured
     * @throws URISyntaxException if a string could not be parsed as a URI reference
     */
    public CharacterCardRepository() throws IOException, URISyntaxException {
        this.cardList = new ArrayList<>();
        for (String[] row : getListFromCsv(CHARACTER_CSV_FILE_PATH)) {
            cardList.add(new CharacterCard(row[4], row[1], row[3], Element.valueOf(row[2]), Integer.parseInt(row[7]),
                    Integer.parseInt(row[5]), Integer.parseInt(row[6])));
        }
    }

}
