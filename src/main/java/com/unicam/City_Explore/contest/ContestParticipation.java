package com.unicam.City_Explore.contest;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.unicam.City_Explore.contenuti.MultimediaContent;
import com.unicam.City_Explore.user.User;

@Entity
@Component
public class ContestParticipation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Contest contest;
    @ManyToOne
    private User user;
    @OneToMany
    private final List<MultimediaContent> multimediaContentList;
    @OneToOne
    private QuoteCriterion quoteCriterion;

    public ContestParticipation() {
        this.multimediaContentList = new ArrayList<>();
    }

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
