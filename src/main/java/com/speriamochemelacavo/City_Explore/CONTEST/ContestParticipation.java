package CONTEST;

import CONTENUTI.MultimediaContent;
import USER.User;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
public class ContestParticipation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Contest contest;
    private User user;
    @ManyToOne
    private final List<MultimediaContent> multimediaContentList = new ArrayList<>();
    private QuoteCriterion quoteCriterion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<MultimediaContent> getMultimediaContentList() {
        return multimediaContentList;
    }

    public QuoteCriterion getQuoteCriterion() {
        return quoteCriterion;
    }

    public void setQuoteCriterion(QuoteCriterion quoteCriterion) {
        this.quoteCriterion = quoteCriterion;
    }
}
