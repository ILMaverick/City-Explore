package com.unicam.City_Explore.visual_interface;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.autorizzazione.AuthorizationService;
import com.unicam.City_Explore.contenuti.MultimediaContentController;
import com.unicam.City_Explore.contest.ContestController;
import com.unicam.City_Explore.eliminazione.DeletionController;
import com.unicam.City_Explore.evento.EventController;
import com.unicam.City_Explore.poi.POIController;
import com.unicam.City_Explore.tour.TourController;
import com.unicam.City_Explore.validazione.ValidationController;
import com.unicam.City_Explore.visual_interface.form_pages.FormPage;
import com.unicam.City_Explore.visual_interface.form_pages.LoginPage;
import com.unicam.City_Explore.visual_interface.menu_pages.HomePage;
import com.unicam.City_Explore.visual_interface.menu_pages.MenuPage;
import com.unicam.City_Explore.visual_interface.menu_pages.WelcomePage;

@Component
public class PageController implements CommandLineRunner{
	
	@Autowired
	private WelcomePage welcomePage;
	@Autowired
	private AuthorizationService autService;
	@Autowired
	private PageExecutioner executioner;
	@Autowired
	private POIController poiController;
	@Autowired
	private TourController tourController;
	@Autowired
	private ContestController contestController;
	@Autowired
	private EventController eventController;
	@Autowired
	private MultimediaContentController multimediaContentController;
	@Autowired
	private ValidationController validationController;
	@Autowired
	private DeletionController deletionController;
	
	private Page pointerPage = welcomePage;
	
	public PageController() {
		
	}
	
	@Override
	public void run(String... args) {
//		this.poiController.initializer();
//		this.multimediaContentController.initializer();
//		this.contestController.initializer();
//		this.eventController.initializer();
//		System.out.println("Inizializzazione avvenuta con successo.");
		this.pointerPage = this.welcomePage;
		while (this.pointerPage != null) {
			this.execute(this.pointerPage);
		}
		this.close();
	}
	
	private void execute(Page toExecute) {
		if (toExecute instanceof MenuPage) {
			MenuPage menuToExecute = (MenuPage) toExecute;
			menuToExecute.setAuthorization();
			menuToExecute.populateLinksTable();
			ArrayList<String> authorizedPages = this.getAuthorizedPages(menuToExecute);
			int idChapter = this.executioner.executeMenu(menuToExecute, authorizedPages);
			if (idChapter == 0) {
				if (toExecute instanceof WelcomePage) {
					this.next(null);;
				} else {
					this.next(menuToExecute.getPrevious());
				}
			} else {
				this.next(menuToExecute.getLinksTable().get(authorizedPages.get(idChapter - 1)));
				if (this.pointerPage.getPrevious() == null) {
					this.pointerPage.setPrevious(menuToExecute);
				}
			}
		} else {
			FormPage formToExecute = (FormPage) toExecute;
			next(this.executioner.executeForm(formToExecute));
			this.pointerPage.setPrevious(formToExecute.getPrevious());
		}
	}
	
	private void next(Page nextPage) {
		this.pointerPage = nextPage;
	}
	
	private ArrayList<String> getAuthorizedPages(MenuPage menu){
		ArrayList<String> authorizedPages = new ArrayList<String>();
		if (menu instanceof WelcomePage) {
			authorizedPages = menu.getChapters();
		} else {
			for (String chapter : menu.getChapters()) {
				if (autService.checkAutorization(chapter)) {
					authorizedPages.add(chapter);
				}
			}
		}
		return authorizedPages;
	}
	
	private void close() {
		this.executioner.close();
		System.out.println("Programma terminato. Arrivederci!");
	}	
}
