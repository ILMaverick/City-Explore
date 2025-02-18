package com.speriamochemelacavo.City_Explore.CONTEST;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.speriamochemelacavo.City_Explore.ELEMENT.AbstractElement;
import com.speriamochemelacavo.City_Explore.EVENTO.Event;
import com.speriamochemelacavo.City_Explore.USER.User;

public class Contest extends AbstractElement {

    private String rules;
    private String goal;
    private String prize;
    private List<User> participationContestList = new ArrayList<>();
    private LocalDate deadline;

    public final List<Event> eventList;

    public Contest(String name, String description, User author) {
        super(name, description, author);
        this.eventList = new ArrayList<>();
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public List<User>  getParticipationContestList() {
        return participationContestList;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public List<Event> getEventList() {
        return eventList;
    }

    @Override
    public String toString() {
        return "Contest{" +
                "id='" + super.getId() + "\'" +
                ",\n  name='" + super.getName() + "\'" +
                ",\n  description='" + super.getDescription() + "\'" +
                ",\n  author='" + super.getAuthor() + "\'" +
                ",\n  rules='" + rules + '\'' +
                ",\n  goal='" + goal + '\'' +
                ",\n  prize='" + prize + '\'' +
                ",\n  participationContestList='" + participationContestList + '\'' +
                ",\n  deadline='" + deadline +
                "\n}";
    }
}
