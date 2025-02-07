package OSM;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import POI.POIType;
import POI.PointOfInterest;
import POI.PointOfInterestFactory;
import USER.User;

public class OSMSearch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Inserisci il nome della città: ");
        String city = scanner.nextLine();

        System.out.print("Inserisci il tipo di POI:\n"
        		+ "-TURISMO: monumenti, musei, quartieri_storici, teatri, luoghi_culto, zone_pedonali, planetari\n"
        		+ "-ALLOGGI: hotels, motels, ostelli, guest_house\n"
        		+ "-SERVIZI: scuole, università, ospedali, farmacie, cinema, mercati, ristoranti\n"
        		+ "-NATURA: parchi, foreste, vette, vigneti, spiagge");
        String poi = scanner.nextLine();

        System.out.println("Ricerca per: " + poi + " a " + city);
        String poiFilter = POIFilterStrategy.getFilter(poi);

        try {
            String overpassQuery = OverpassQueryFactory.createQuery(city, poiFilter);
            System.out.println("Query Overpass:\n" + overpassQuery);

            String jsonResponse = OverpassClient.fetchOverpassData(overpassQuery);
            System.out.println("Risultato JSON:\n" + jsonResponse);

            OverpassResponse overpassResponse = JsonParserSingleton.getInstance()
                    .readValue(jsonResponse, OverpassResponse.class);

            System.out.println("\nRisultati filtrati:");
            List<OverpassElement> filteredElements = Arrays.stream(overpassResponse.getElements())
                    .filter(element -> element.tags != null && element.tags.name != null && !element.tags.name.trim().isEmpty())
                    .collect(Collectors.toList());

            if (filteredElements.isEmpty()) {
                System.out.println("Nessun elemento con nome trovato.");
            } else {
                // Visualizzazione degli elementi trovati con indice
                System.out.println("\nElementi trovati:");
                for (int i = 0; i < filteredElements.size(); i++) {
                    System.out.println((i + 1) + ". " + filteredElements.get(i));
                }

                // Richiesta all'utente di selezionare un elemento
                System.out.print("\nSeleziona l'elemento da utilizzare (inserisci il numero): ");
                int selection = scanner.nextInt();
                scanner.nextLine(); // Consuma il newline

                if (selection < 1 || selection > filteredElements.size()) {
                    System.out.println("Selezione non valida.");
                } else {
                    OverpassElement selectedElement = filteredElements.get(selection - 1);

                    // Ottieni l'utente attuale (dummy) e determina il tipo di POI
                    User currentUser = getCurrentUser();
                    POIType poiType = POIType.fromOSMTag(poi);

                    // Crea il PointOfInterest tramite la factory
                    PointOfInterest poiObj = PointOfInterestFactory.createFromOverpassElement(
                            selectedElement, currentUser, poiType);

                    System.out.println("\nPointOfInterest creato:");
                    System.out.println(poiObj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
 // Metodo dummy per ottenere l'utente attuale
    private static User getCurrentUser() {
        User user = new User();
        user.setUsername("utente_demo");
        return user;
    }
    }


