package com.speriamochemelacavo.City_Explore.TOUR;

public enum WayDifficultyType {
	Facile,
	Intermedio,
	Difficile,
	Altro;

	public static WayDifficultyType fromString(String difficulty) {
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
