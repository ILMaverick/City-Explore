package CONTEST;

import java.util.ArrayList;
import java.util.List;

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
}
