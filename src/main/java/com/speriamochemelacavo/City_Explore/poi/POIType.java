package poi;

public enum POIType {
	Turismo,
	Alloggio,
	Servizio,
	Natura,
	Altro;
	 public static POIType fromOSMTag(String osmTag) {
	        switch (osmTag.toLowerCase()) {
	            // Categoria TURISMO: monumenti, musei, quartieri_storici, teatri, luoghi_culto, zone_pedonali, planetari
	            case "monumenti":
	            case "musei":
	            case "quartieri_storici":
	            case "teatri":
	            case "luoghi_culto":
	            case "zone_pedonali":
	            case "planetari":
	                return Turismo;
	            // Categoria ALLOGGI: hotels, motels, ostelli, guest_house
	            case "hotels":
	            case "motels":
	            case "ostelli":
	            case "guest_house":
	                return Alloggio;
	            // Categoria SERVIZI: scuole, università, ospedali, farmacie, cinema, mercati, ristoranti
	            case "scuole":
	            case "università":
	            case "ospedali":
	            case "farmacie":
	            case "cinema":
	            case "mercati":
	            case "ristoranti":
	                return Servizio;
	            // Categoria NATURA: parchi, foreste, vette, vigneti, spiagge
	            case "parchi":
	            case "foreste":
	            case "vette":
	            case "vigneti":
	            case "spiagge":
	                return Natura;
	            default:
	                // Se non viene riconosciuto, si può scegliere un default
	                return Altro;
	        }
	    }
}
