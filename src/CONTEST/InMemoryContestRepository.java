package CONTEST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryContestRepository implements ContestRepository {

    private Map<String, Contest> storage = new HashMap<>();

    @Override
    public void save(Contest contest) {
        if (contest != null) {
            storage.put(contest.getId(), contest);
        }
    }

    @Override
    public List<Contest> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Contest findById(String id) {
        return storage.get(id);
    }
}
