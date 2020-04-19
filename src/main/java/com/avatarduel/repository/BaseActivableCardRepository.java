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

import com.avatarduel.model.card.ActivableCard;
import com.avatarduel.util.CSVReader;
import com.avatarduel.util.ResourceFinder;

public abstract class BaseActivableCardRepository implements Repository<ActivableCard, UUID> {

    protected List<ActivableCard> cardList;

    /** 
     * @param id
     * @return Nullable of Activable Card
     */
    @Override
    public Optional<ActivableCard> get(UUID id) {
        return cardList.stream().filter(card -> card.getId().equals(id)).findAny();
    }

    /** 
     * @return List of ActivableCards
     */
    @Override
    public List<ActivableCard> getAll() {
        return cardList;
    }

    /** 
     * @param card
     */
    @Override
    public void save(ActivableCard card) {
        cardList.add(card);
    }

    /** 
     * @param card
     */
    @Override
    public void delete(ActivableCard card) {
        cardList.remove(card);
    }

    /** 
     * @param id
     * @param card
     */
    @Override
    public void update(UUID id, ActivableCard card) {
        Iterator<ActivableCard> iter = cardList.iterator();
        while (iter.hasNext()) {
            ActivableCard next = iter.next();
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
    public List<ActivableCard> getRandomCards(int count) {
        List<ActivableCard> res = new ArrayList<>();
        int listLength = cardList.size();
        while (count > 0) {
            int take = Math.min(count, listLength);
            if (take != listLength) {
                Collections.shuffle(cardList);
            }
            res.addAll(
                    cardList.subList(0, take).stream().map(c -> (ActivableCard) c.copy()).collect(Collectors.toList()));
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