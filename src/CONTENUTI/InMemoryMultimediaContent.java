package CONTENUTI;

import java.util.ArrayList;
import java.util.List;

public class InMemoryMultimediaContent implements MultimediaContentRepository{
    private final List<MultimediaContent> storage = new ArrayList<>();

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
