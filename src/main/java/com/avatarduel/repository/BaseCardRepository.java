package com.avatarduel.repository;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.avatarduel.model.card.Card;
import com.avatarduel.util.CSVReader;
import com.avatarduel.util.ResourceFinder;

public abstract class BaseCardRepository implements Repository<Card, UUID> {

    protected List<Card> cardList;

    @Override
    public Optional<Card> get(UUID id) {
        return cardList.stream().filter(card -> card.getId().equals(id)).findAny();
    }

    @Override
    public List<Card> getAll() {
        return cardList;
    }

    @Override
    public void save(Card card) {
        cardList.add(card);
    }

    @Override
    public void delete(Card card) {
        cardList.remove(card);
    }

    @Override
    public void update(UUID id, Card card) {
        Iterator<Card> iter = cardList.iterator();
        while (iter.hasNext()) {
            Card next = iter.next();
            if (next.getId().equals(id)) {
                iter.remove();
                break;
            }
        }
        cardList.add(card);
    }

    /**
     * Get random collection of cards with specified type and number
     *
     * @param count number of cards that want to be randomly picked
     * @return list of randomly selected card
     */
    public List<Card> getRandomCards(int count) {
        List<Card> res = new ArrayList<>();
        int listLength = cardList.size();
        while (count > 0) {
            int take = Math.min(count, listLength);
            if (take != listLength) {
                Collections.shuffle(cardList);
            }
            res.addAll(cardList.subList(0, take).stream().map(c -> c.copy()).collect(Collectors.toList()));
            count -= take;
        }
        return res;
    }

    /**
     * Get list of lines separated with ";" in csv file from the specified path
     *
     * @param path path to csv file that want to be parsed
     * @return list of string arrays each represent 1 row
     * @throws IOException        if an input or output exception occured
     * @throws URISyntaxException if a string could not be parsed as a URI reference
     */
    protected List<String[]> getListFromCsv(String path) throws IOException, URISyntaxException {
        File csvFile = new File(ResourceFinder.getURI(path));
        CSVReader reader = new CSVReader(csvFile, ";");
        reader.setSkipHeader(true);
        return reader.read();
    }

}