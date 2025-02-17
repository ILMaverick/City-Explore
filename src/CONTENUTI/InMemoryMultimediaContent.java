package CONTENUTI;

import POI.PointOfInterest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryMultimediaContent implements MultimediaContentRepository{
    private List<MultimediaContent> storage = new ArrayList<MultimediaContent>();

    @Override
    public void save(MultimediaContent multimediaContent) {
        if (multimediaContent != null) {
        	multimediaContent.setId(storage.size());
            storage.add(multimediaContent);
        }
    }

    @Override
    public List<MultimediaContent> findAll() {
        return this.storage;
    }

    @Override
    public MultimediaContent findById(int id) {
        return storage.get(id);
    }
}
