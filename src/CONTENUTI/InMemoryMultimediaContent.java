package CONTENUTI;

import POI.PointOfInterest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryMultimediaContent implements MultimediaContentRepository{
    private Map<String, MultimediaContent> storage = new HashMap<>();

    @Override
    public void save(MultimediaContent multimediaContent) {
        if (multimediaContent != null) {
            storage.put(multimediaContent.getId(), multimediaContent);
        }
    }

    @Override
    public List<MultimediaContent> findAll() {
        List<MultimediaContent> multimediaContentList = new ArrayList<>();
        for (Map.Entry<String, MultimediaContent> entry : storage.entrySet()) {
            MultimediaContent value = entry.getValue();
            multimediaContentList.add(value);
        }
        return multimediaContentList;
    }

    @Override
    public MultimediaContent findById(String id) {
        return storage.get(id);
    }
}
