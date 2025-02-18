package CONTENUTI;

import ELEMENT.ElementStatus;
import POI.PointOfInterest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public MultimediaContent findById(int id) {
        return storage.get(id);
    }

    @Override
    public List<MultimediaContent> findAll() {
        return this.storage;
    }

    @Override
    public List<MultimediaContent> findByStatus(ElementStatus status) {
        return this.storage.stream().filter(multimediaContent -> multimediaContent.getStatus() == status).collect(Collectors.toList());
    }

    @Override
    public List<MultimediaContent> searchByName(String name) {
        return this.storage.stream().filter(multimediaContent -> multimediaContent.getName().equals(name)).collect(Collectors.toList());
    }

    @Override
    public List<MultimediaContent> searchByDescription(String description) {
        return this.storage.stream().filter(multimediaContent -> multimediaContent.getDescription().equals(description)).collect(Collectors.toList());
    }
}
