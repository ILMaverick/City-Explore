package com.unicam.City_Explore.contest;

import com.unicam.City_Explore.contenuti.MultimediaContent;
import com.unicam.City_Explore.user.Role;
import com.unicam.City_Explore.user.UserRepository;
import com.unicam.City_Explore.user.User;
import com.unicam.City_Explore.notifica.NotificationListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class ContestService {
    private Scanner scanner;
    @Autowired
    private ContestRepository contestRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ContestParticipationRepository contestParticipationRepository;
    @Autowired
    private NotificationListener notificationListener;

    public ContestService() {
        scanner = new Scanner(System.in);
    }

    public void initializer() {
        User user = new User();
        user.setName("Simone");
        user.setSurname("Stacchiotti");
        user.setUsername("SilverSimon");
        user.setEmail("simone.stacchiotti.email@gmail.com");
        user.setRole(Role.ADMINISTRATOR);

        LocalDate deadline = LocalDate.of(2025, 2,17);

        createContest("nome", "descrizione", user, "non fare il birbante", "foto piu' bella", "gita in barca", deadline);
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

    public Contest createContest(String name, String description, User author, String rules, String goal, String prize, LocalDate deadline) {
        Contest contest = new Contest(name, description, author);
        if(author.getRole() == Role.ANIMATOR) {
            contest.setRules(rules);
            contest.setGoal(goal);
            contest.setPrize(prize);
            contest.setDeadline(deadline);
            contestRepository.save(contest);
            notificationListener.handleNewContest(contest);
            return contest;
        } else {
            notificationListener.handleDenialPermission(author);
        }
        return null;
    }

    public Contest updateContest(int idContest, Contest contest) {
        Contest contestSelected = contestRepository.findById(idContest).orElse(null);
        if(contestSelected != null) {
            if (contest.getAuthor().getRole() == Role.ANIMATOR) {
                contestSelected.setName(contest.getName());
                contestSelected.setDescription(contest.getDescription());
                contestSelected.setRules(contest.getRules());
                contestSelected.setGoal(contest.getGoal());
                contestSelected.setPrize(contest.getPrize());
                contestSelected.setDeadline(contest.getDeadline());
                contestRepository.save(contestSelected);
                notificationListener.handleUpdateContest(contest);
            }
            return contestSelected;
        } else {
            notificationListener.handleDenialPermission(contest.getAuthor());
        }
        return null;
    }

    public List<Contest> getAllContest() {
        return contestRepository.findAll();
    }

    public List<Contest> searchContestByName(String name) {
        return contestRepository.searchByName(name);
    }

    public List<Contest> searchContestByDescription(String description) {
        return contestRepository.searchByDescription(description);
    }

    public void participateContest(int idContest, int idUser, List<MultimediaContent> multimediaContentList) {
        Optional<Contest> optionalContest = contestRepository.findById(idContest);
        Optional<User> optionalUser = userRepository.findById(idUser);

        if(optionalContest.isPresent() && optionalUser.isPresent()) {
            Contest contest = optionalContest.get();
            User user = optionalUser.get();
            if(user.getRole() == Role.TOURIST || user.getRole() == Role.AUTHENTICATED_TOURIST) {
                ContestParticipation participation = new ContestParticipation();
                participation.setContest(contest);
                participation.setUser(user);
                participation.getMultimediaContentList().addAll(multimediaContentList);
                contestParticipationRepository.save(participation);
                notificationListener.handleParticipationContest(participation);
            } else {
                notificationListener.handleDenialPermission(user);
            }
        } else {
            throw new RuntimeException("Contest o Utente non trovato.");
        }
    }

    public void deleteParticipationContest(ContestParticipation participation, String reason) {
        User user = participation.getUser();
        if(user.getRole() == Role.TOURIST || user.getRole() == Role.AUTHENTICATED_TOURIST) {
            contestParticipationRepository.delete(participation);
            notificationListener.handleDeleteParticipationContest(participation, reason);
        }  else {
            notificationListener.handleDenialPermission(user);
        }
    }

    public void evaluateParticipant(int idParticipant, int vote, String description) {
        Optional<ContestParticipation> optionalContestParticipation = contestParticipationRepository.findById(idParticipant);
        if(optionalContestParticipation.isPresent()) {
            ContestParticipation participation = optionalContestParticipation.get();
            User animatorAuthor = participation.getContest().getAuthor();
            if(animatorAuthor.getRole() == Role.ANIMATOR) {
                if (!participation.getQuoteCriterion().getQuote()) {
                    QuoteCriterion quoteCriterion = new QuoteCriterion();
                    quoteCriterion.setVote(vote);
                    quoteCriterion.setDescription(description);
                    quoteCriterion.setQuote(true);
                    participation.setQuoteCriterion(quoteCriterion);
                    contestParticipationRepository.save(participation);
                    notificationListener.handleEvaluateParticipantContest(participation, quoteCriterion);
                } else {
                    notificationListener.handleAlreadyQuote(participation);
                }
            } else {
                notificationListener.handleDenialPermission(animatorAuthor);
            }
        } else {
            throw new RuntimeException("Partecipante non trovato.");
        }
    }

    public List<User> declareWinners(int idContest) {
        Contest contest = contestRepository.findById(idContest).orElseThrow(() -> new RuntimeException("Contest non trovato."));
        User animatorAuthor = contest.getAuthor();
        if(animatorAuthor.getRole() == Role.ANIMATOR) {
            int maxScore = contest.getParticipationContestList().stream()
                    .mapToInt(p -> p.getQuoteCriterion().getVote())
                    .max()
                    .orElse(0);
            List<User> winners = contest.getParticipationContestList().stream()
                    .filter(p -> p.getQuoteCriterion().getVote() == maxScore)
                    .map(ContestParticipation::getUser)
                    .toList();
            notificationListener.handleWinnersParticipantContest(winners, contest);
            return winners;
        } else {
            notificationListener.handleDenialPermission(animatorAuthor);
        }
        return null;
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
