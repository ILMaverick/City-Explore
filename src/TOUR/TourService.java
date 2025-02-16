package TOUR;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import POI.POIRepository;
import POI.PointOfInterest;
import USER.User;

public class TourService {
	 private TourRepository tourRepository;

	    public TourService(TourRepository tourRepository) {
	        this.tourRepository = tourRepository;
	    }
    
    /**
     * Metodo interattivo per costruire un Tour a partire da una lista di PointOfInterest.
     */
    public Tour buildTourFromPOIs(List<PointOfInterest> poiList, User author) {
        // 1. Crea le tappe a partire dai POI
        List<Tappa> tappe = new ArrayList<>();
        int count = 1;
        for (PointOfInterest poi : poiList) {
            Tappa tappa = new Tappa(poi.getName(), poi.getDescription(), poi.getLatitude(), poi.getLongitude(), poi.getAuthor(), poi.getType(), count++);
            tappe.add(tappa);
        }
        
        // 2. Raggruppa le tappe in gruppi per formare i percorsi
        List<List<Tappa>> gruppiTappe = groupTappe(tappe);
        
        Scanner scanner = new Scanner(System.in);
        List<Percorso> percorsi = new ArrayList<>();
        
        // 3. Per ogni gruppo, chiedi i dettagli e crea il Percorso
        for (List<Tappa> gruppo : gruppiTappe) {
            System.out.println("\nCreazione di un Percorso per le seguenti tappe:");
            for (Tappa t : gruppo) {
                System.out.println("Tappa " + t.getNumeroTappa() + ": " + t.getName());
            }
            System.out.print("Inserisci la lunghezza del percorso (double): ");
            double lunghezza = Double.parseDouble(scanner.nextLine());
            System.out.print("Inserisci la durata del percorso (double): ");
            double durata = Double.parseDouble(scanner.nextLine());
            System.out.print("Inserisci la difficoltà del percorso (facile, medio, difficile): ");
            String diff = scanner.nextLine().trim();
            PercorsoType percorsoType = PercorsoType.fromString(diff);
            
            Percorso percorso = new Percorso(lunghezza, durata, percorsoType, gruppo);
            percorsi.add(percorso);
        }
        
        // 4. Raccogli nome e descrizione del Tour
        System.out.print("\nInserisci il nome del Tour: ");
        String tourName = scanner.nextLine();
        System.out.print("Inserisci la descrizione del Tour: ");
        String tourDescription = scanner.nextLine();
        
        TourBuilder builder = new TourBuilder();
        
        builder.withName(tourName);
        builder.withDescription(tourDescription);
        builder.withAuthor(author);
        builder.addPercorso(percorsi);
        
        Tour tour = builder.build();
        
        saveTour(tour);
        // scanner.close(); // Attenzione a non chiudere System.in se usato altrove
        return tour;
    }
    
    /**
     * Raggruppa le tappe in gruppi per la creazione dei percorsi.
     */
    private List<List<Tappa>> groupTappe(List<Tappa> tappe) {
        List<List<Tappa>> gruppi = new ArrayList<>();
        int total = tappe.size();
        
        if (total >= 3 && total % 2 == 1) {
            for (int i = 0; i < total - 3; i += 2) {
                gruppi.add(new ArrayList<>(tappe.subList(i, i + 2)));
            }
            gruppi.add(new ArrayList<>(tappe.subList(total - 3, total)));
        } else {
            for (int i = 0; i < total; i += 2) {
                if (i + 1 < total) {
                    gruppi.add(new ArrayList<>(tappe.subList(i, i + 2)));
                } else {
                    gruppi.add(new ArrayList<>(tappe.subList(i, total)));
                }
            }
        }
        return gruppi;
    }

    public void saveTour(Tour tour) {
        tourRepository.save(tour);
    }

    public List<Tour> getAllTours() {
        return tourRepository.findAll();
    }

    public Tour getTourById(String id) {
        return tourRepository.findById(id);
    }
}
