package TOUR;

import java.util.List;

public class Percorso {
	private double lunghezza;
	private double durata;
	private PercorsoType type;
	private List<Tappa> tappe;
	
	public Percorso(double lunghezza, double durata, PercorsoType type, List<Tappa> tappe) {
		this.lunghezza = lunghezza;
		this.durata = durata;
		this.type = type;
		this.tappe = tappe;
	}
	
	@Override
	public String toString() {
		return "Percorso [lunghezza=" + lunghezza + 
				", durata=" + durata + 
				", type=" + type + 
				", tappe=" + tappe + "]";
	}

	public double getLunghezza() {
		return lunghezza;
	}
	public void setLunghezza(double lunghezza) {
		this.lunghezza = lunghezza;
	}
	public double getDurata() {
		return durata;
	}
	public void setDurata(double durata) {
		this.durata = durata;
	}
	public PercorsoType getType() {
		return type;
	}
	public void setType(PercorsoType type) {
		this.type = type;
	}
	public List<Tappa> getTappe() {
		return tappe;
	}
	public void setTappe(List<Tappa> tappe) {
		this.tappe = tappe;
	}
}
