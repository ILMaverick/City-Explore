package CONTEST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryContestRepository implements ContestRepository {

    private List<Contest> storage = new ArrayList<Contest>();

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
