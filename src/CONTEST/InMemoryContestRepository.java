package CONTEST;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryContestRepository implements ContestRepository {

    private final List<Contest> storage = new ArrayList<>();

    @Override
    public void save(Contest contest) {
        if (contest != null) {
        	contest.setId(storage.size());
            storage.add(contest);
        }
    }

    @Override
    public List<Contest> findAll() {
        return this.storage;
    }

    @Override
    public Contest findById(int id) {
        return storage.get(id);
    }


    @Override
    public List<Contest> searchByName(String name) {
        return this.storage.stream().filter(contest -> contest.getName() == name).collect(Collectors.toList());
    }

    @Override
    public List<Contest> searchByDescription(String description) {
        return this.storage.stream().filter(contest -> contest.getDescription() == description).collect(Collectors.toList());
    }
}
