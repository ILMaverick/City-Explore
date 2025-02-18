package com.speriamochemelacavo.City_Explore.CONTEST;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryContestRepository implements ContestRepository {

    private final List<Contest> storage = new ArrayList<>();

    @Override
    public void save(Contest contest) {
        if(contest.getId() == 0) {
            contest.setId(storage.size()+1);
            storage.add(contest);
        } else if (contest.getId() <= storage.size()){
            storage.set(contest.getId()-1, contest);
        }
    }

    @Override
    public List<Contest> findAll() {
        return this.storage;
    }

    @Override
    public Contest findById(int id) {
        return storage.stream()
                .filter(contest -> contest.getId() == id)
                .toList()
                .get(0);
    }


    @Override
    public List<Contest> searchByName(String name) {
        return this.storage.stream().filter(contest -> contest.getName().equals(name)).collect(Collectors.toList());
    }

    @Override
    public List<Contest> searchByDescription(String description) {
        return this.storage.stream().filter(contest -> contest.getDescription().equals(description)).collect(Collectors.toList());
    }

    @Override
    public void deleteByID(int id) {
        this.storage.removeIf(contest -> contest.getId() == id);
    }
}