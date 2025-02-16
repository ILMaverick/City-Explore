package CONTENUTI;

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
        return new ArrayList<>(storage.values());
    }

    @Override
    public MultimediaContent findById(String id) {
        return storage.get(id);
    }
}
