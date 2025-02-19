package TOUR;

import java.util.List;

public class Way {
	private double length;
	private double duration;
	private WayDifficultyType type;
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
	public List<Tappa> getTappe() {
		return tappe;
	}
	public void setTappe(List<Tappa> tappe) {
		this.tappe = tappe;
	}
}
