package com.unicam.City_Explore.tour;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Way {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private double length;
	private double duration;
	private WayDifficultyType type;
	@ManyToOne
	private Tour tour;
	@OneToMany
	private List<Tappa> tappe;
	
	public Way(double length, double duration, WayDifficultyType type, List<Tappa> tappe) {
		this.length = length;
		this.duration = duration;
		this.type = type;
		this.tappe = tappe;
	}
	
	@Override
	public String toString() {
		return "Percorso [lunghezza=" + length +
				", durata=" + duration +
				", type=" + type + 
				", tappe=" + tappe + "]";
	}

	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}
	public double getDuration() {
		return duration;
	}
	public void setDuration(double duration) {
		this.duration = duration;
	}
	public WayDifficultyType getType() {
		return type;
	}
	public void setType(WayDifficultyType type) {
		this.type = type;
	}
	public Tour getTour() { return tour; }
	public List<Tappa> getTappe() {
		return tappe;
	}
	public void setTappe(List<Tappa> tappe) {
		this.tappe = tappe;
	}
}
