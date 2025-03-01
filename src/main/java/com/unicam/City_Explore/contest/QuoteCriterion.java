package com.unicam.City_Explore.contest;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Component
public class QuoteCriterion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    private int vote;
    private String description;
    private boolean isQuote = false;

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getQuote() {
        return isQuote;
    }

    public void setQuote(boolean isQuote) {
        this.isQuote = isQuote;
    }
}
