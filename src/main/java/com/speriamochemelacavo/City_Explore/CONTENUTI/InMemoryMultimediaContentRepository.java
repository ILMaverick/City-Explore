package com.speriamochemelacavo.City_Explore.CONTENUTI;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.speriamochemelacavo.City_Explore.ELEMENT.ElementStatus;

public class InMemoryMultimediaContentRepository implements MultimediaContentRepository{
    private final List<MultimediaContent> storage = new ArrayList<>();

    @Override
    public void save(MultimediaContent multimediaContent) {
        if(multimediaContent.getId() == 0) {
            multimediaContent.setId(storage.size()+1);
            storage.add(multimediaContent);
        } else if (multimediaContent.getId() <= storage.size()){
            storage.set(multimediaContent.getId()-1, multimediaContent);
        }
    }
    @Override
    public MultimediaContent findById(int id) {
        return storage.stream()
                .filter(multimediaContent -> multimediaContent.getId() == id)
                .toList()
                .get(0);
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

    @Override
    public void deleteByID(int id) {
        this.storage.removeIf(multimediaContent -> multimediaContent.getId() == id);
    }
}
