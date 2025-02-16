package CONTEST;

import USER.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class ContestController {
    private Scanner scanner;
    private ContestService contestService;

    public ContestController() {
        scanner = new Scanner(System.in);
        this.contestService = new ContestService();
    }

    public void createContest() {
        System.out.println("=== Creazione di un Contest ===");

        System.out.print("Inserisci il nome: ");
        String name = scanner.nextLine();

        System.out.print("Inserisci la descrizione: ");
        String description = scanner.nextLine();

        User currentUser = getCurrentUser();

        System.out.print("Inserisci le regole: ");
        String rules = scanner.nextLine();

        System.out.print("Inserisci l'obiettivo: ");
        String goal = scanner.nextLine();

        System.out.print("Inserisci il premio: ");
        String prize = scanner.nextLine();

        System.out.print("Inserisci la scadenza (il formato e' dd-MM-yyyy): ");
        String deadlineString = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate deadline = null;
        try {
            deadline = LocalDate.parse(deadlineString, formatter);
            System.out.println("Data convertita: " + deadline);
        } catch (DateTimeParseException e) {
            System.out.println("Formato della data non valido: " + e.getMessage());
        }

        Contest newContest = contestService.createContest(name, description, currentUser, rules, goal, prize, deadline);

        System.out.println("Contest creato da zero: ");
        System.out.println(newContest);
    }

    public void displayAllContest() {
        List<Contest> contestList = contestService.getAllContest();
        if(contestList.isEmpty()) {
            System.out.println("Nessun Contest salvato.");
        } else {
            System.out.println("Elenco Contest salvati:");
            for(Contest contest: contestList) {
                System.out.println(contest);
            }
        }
    }

    private User getCurrentUser() {
        User user = new User();
        user.setUsername("utente_demo");
        return user;
    }

    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
