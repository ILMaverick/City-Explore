package TOUR;

public enum PercorsoType {
	Facile,
	Intermedio,
	Difficile,
	Altro;

	public static PercorsoType fromString(String difficulty) {
        if (difficulty == null) {
            return Altro;
        }
        switch (difficulty.trim().toLowerCase()) {
            case "facile":
                return Facile;
            case "medio":
                return Intermedio;
            case "difficile":
                return Difficile;
            default:
                return Altro;
        }
    }

}
