package com.unicam.City_Explore.contest;

import com.unicam.City_Explore.contenuti.MultimediaContent;
import com.unicam.City_Explore.user.Role;
import com.unicam.City_Explore.user.UserRepository;
import com.unicam.City_Explore.user.UserService;
import com.unicam.City_Explore.user.User;
import com.unicam.City_Explore.notifica.NotificationListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class ContestService {
    private Scanner scanner;
    @Autowired
    private UserService userService;
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

        createContest("nome", "descrizione", "non fare il birbante", "foto piu' bella", "gita in barca", deadline);
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

    public Contest createContest(String name, String description, String rules, String goal, String prize, LocalDate deadline) {
        User author = userService.getCurrentUser();
    	Contest contest = new Contest(name, description, author);
            contest.setRules(rules);
            contest.setGoal(goal);
            contest.setPrize(prize);
            contest.setDeadline(deadline);
            if(checkContestData(contest)) {
                contestRepository.save(contest);
                notificationListener.handleNewContest(contest);
                return contest;
            } else {
                notificationListener.handleRefuseContest(contest);
            }
        return contest;
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
                contestSelected.setActive(contest.getActive());
                if(checkContestData(contestSelected)) {
                    contestRepository.save(contestSelected);
                    notificationListener.handleUpdateContest(contest);
                    return contestSelected;
                } else {
                    notificationListener.handleRefuseContest(contestSelected);
                }
            }
        } else {
            notificationListener.handleDenialPermission(contest.getAuthor());
        }
        return null;
    }

    public List<Contest> getAllContest() {
        return contestRepository.findAll();
    }
    
    public Contest getContestById(int id) {
    	return contestRepository.findById(id).get();
    }

    public List<Contest> searchContestByName(String name) {
        if(name == null) return List.of();
        return contestRepository.searchByName(name);
    }

    public List<Contest> searchContestByDescription(String description) {
        if(description == null) return List.of();
        return contestRepository.searchByDescription(description);
    }

    public void participateContest(int idContest, int idUser, List<MultimediaContent> multimediaContentList) {
        Optional<Contest> optionalContest = contestRepository.findById(idContest);
        Optional<User> optionalUser = userRepository.findById(idUser);

        if(optionalContest.isPresent() && optionalUser.isPresent()) {
            Contest contest = optionalContest.get();
            User user = optionalUser.get();
            if(contest.getActive() && (user.getRole() == Role.TOURIST || user.getRole() == Role.AUTHENTICATED_TOURIST)) {
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
            contest.setActive(false);
            return winners;
        } else {
            notificationListener.handleDenialPermission(animatorAuthor);
        }
        return null;
    }

    private boolean checkContestData(Contest contest) {
        if(contest == null) return false;
        if(contest.getName()==null) return false;
        if(contest.getDescription()==null) return false;
        if(contest.getGoal() == null) return false;
        if(contest.getRules() == null) return false;
        return contest.getDeadline() != null;
    }


    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }

	public Contest updateContest(int id, String name, String description, String roles, String goal, String price) {
		Contest selectedContest = contestRepository.findById(id).orElse(null);
        if (!description.isEmpty()) {
            selectedContest.setDescription(description);
        }if (!name.isEmpty()) {
            selectedContest.setName(name);
        }
        if (!roles.isEmpty()) {
            selectedContest.setRules(roles);
        }
        if (!goal.isEmpty()) {
            selectedContest.setGoal(goal);
        }
        if (!price.isEmpty()) {
            selectedContest.setPrize(price);
        }
        if(checkContestData(selectedContest)) {
            contestRepository.save(selectedContest);
            notificationListener.handleUpdateContest(selectedContest);
        } else {
            notificationListener.handleRefuseContest(selectedContest);
        }
        return getContestById(selectedContest.getId());
	}
	
}
