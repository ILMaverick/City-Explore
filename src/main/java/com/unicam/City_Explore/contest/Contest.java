package com.unicam.City_Explore.contest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.unicam.City_Explore.elementi.AbstractElement;
import com.unicam.City_Explore.elementi.Status;
import com.unicam.City_Explore.evento.Event;
import com.unicam.City_Explore.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;


@Entity
public class Contest extends AbstractElement {

    private String rules;
    private String goal;
    private String prize;

    private LocalDate deadline;
    private boolean active;
    @OneToMany(fetch = FetchType.EAGER)
    private final List<ContestParticipation> participationContestList = new ArrayList<>();
    @ManyToMany(fetch = FetchType.EAGER)
    public final List<Event> eventList = new ArrayList<>();;
    
    public Contest() {
    	
    }

    public Contest(String name, String description, User author) {
        super(name, description, author);
        super.setStatus(Status.APPROVED);
        this.active = true;
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

    public List<ContestParticipation>  getParticipationContestList() {
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
    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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
