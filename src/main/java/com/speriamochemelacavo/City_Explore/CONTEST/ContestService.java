package CONTEST;

import USER.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

@Service
public class ContestService {
    private Scanner scanner;
    @Autowired
    private ContestRepository contestRepository;

    public ContestService() {
        scanner = new Scanner(System.in);
    }

    public void initializer() {
        User user = new User();
        user.setUsername("SilverSimon");

        LocalDate deadline = LocalDate.of(2025,02,17);

        createContest("nome", "descrizione", user, "non fare il birbante", "foto piu' bella", "gita in barca", deadline);
    }

    public Contest createContest(String name, String description, User author, String rules, String goal, String prize, LocalDate deadline) {
        Contest contest = new Contest(name, description, author);
        contest.setRules(rules);
        contest.setGoal(goal);
        contest.setPrize(prize);
        contest.setDeadline(deadline);
        contestRepository.save(contest);
        return contest;
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

        Contest newContest = createContest(name, description, currentUser, rules, goal, prize, deadline);

        System.out.println("Contest creato da zero: ");
        System.out.println(newContest);
    }

    public void searchContestByName() {

        System.out.println("=== Ricerca Contest tramite nome ===");
        System.out.print("Inserisci il nome: ");

        String name = scanner.nextLine();
        List<Contest> contestList = searchContestByName(name);
        if(contestList.isEmpty()) {
            System.out.println("Non e' presente un Contest con questo nome.");
        } else {
            System.out.println("Elenco Contest con il nome cercato:");
            for(Contest contest: contestList) {
                System.out.println(contest);
            }
        }
    }

    public void searchContestByDescription() {
        System.out.println("=== Ricerca Contest tramite descrizione ===");
        System.out.print("Inserisci la descrizione: ");

        String description = scanner.nextLine();
        List<Contest> contestList = searchContestByDescription(description);
        if(contestList.isEmpty()) {
            System.out.println("Non e' presente un Contest con questa descrizione.");
        } else {
            System.out.println("Elenco Contest con la descrizione cercata:");
            for(Contest contest: contestList) {
                System.out.println(contest);
            }
        }
    }

    public void saveContest(Contest contest) {
        contestRepository.save(contest);
    }

    public List<Contest> getAllContest() {
        return contestRepository.findAll();
    }

    public Contest getContestById(int id) {
        return contestRepository.findById(id).get();
    }

    public List<Contest> searchContestByName(String name) {
        return contestRepository.searchByName(name);
    }

    public List<Contest> searchContestByDescription(String description) {
        return contestRepository.searchByDescription(description);
    }

    private User getCurrentUser() {
        User user = new User();
        user.setName("utente");
        user.setSurname("demo");
        user.setUsername("utente_demo");
        user.setEmail("utente_demo.mail@gmail.com");
        user.setPassword("1234567890");
        return user;
    }

    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }

}
