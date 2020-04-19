package com.avatarduel.repository;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import com.avatarduel.model.Element;
import com.avatarduel.model.card.LandCard;

/**
 * CardDao serve as an object where all data about cards are stored
 */
public class LandCardRepository extends BaseActivableCardRepository {

    private static final String LAND_CSV_FILE_PATH = "card/data/land.csv";

    /**
     * Constructor
     *
     * @throws IOException        if an input or output exception occured
     * @throws URISyntaxException if a string could not be parsed as a URI reference
     */
    public LandCardRepository() throws IOException, URISyntaxException {
        this.cardList = new ArrayList<>();
        for (String[] row : getListFromCsv(LAND_CSV_FILE_PATH)) {
            cardList.add(new LandCard(row[4], row[1], row[3], Element.valueOf(row[2])));
        }
    }

}
