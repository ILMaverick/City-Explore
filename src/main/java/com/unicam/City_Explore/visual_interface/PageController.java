package com.unicam.City_Explore.visual_interface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.contenuti.MultimediaContentController;
import com.unicam.City_Explore.contest.ContestController;
import com.unicam.City_Explore.eliminazione.DeletionController;
import com.unicam.City_Explore.evento.EventController;
import com.unicam.City_Explore.poi.POIController;
import com.unicam.City_Explore.tour.TourController;
import com.unicam.City_Explore.validazione.ValidationController;
import com.unicam.City_Explore.visual_interface.form_pages.FormPage;
import com.unicam.City_Explore.visual_interface.menu_pages.MainPage;
import com.unicam.City_Explore.visual_interface.menu_pages.MenuPage;

@Component
public class PageController implements CommandLineRunner{
	
	@Autowired
	private MainPage mainPage;
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
	
	private Page pointerPage = mainPage;
	private MenuPage previousPage;
	
	public PageController() {
		
	}
	
	@Override
	public void run(String... args) {
//		this.pointerPage = new MainPage();
//		this.poiController.initializer();
//		this.multimediaContentController.initializer();
//		this.contestController.initializer();
//		this.eventController.initializer();
//		System.out.println("Inizializzazione avvenuta con successo.");
		this.pointerPage = this.mainPage;
		while (this.pointerPage != null) {
			this.execute(this.pointerPage);
		}
		this.close();
	}
	
	private void execute(Page toExecute) {
		Page nextPage;
		if (toExecute instanceof MenuPage) {
			MenuPage menuToExecute = (MenuPage) toExecute;
			nextPage = this.executioner.executeMenu(menuToExecute);
		} else {
			FormPage formToExecute = (FormPage) this.pointerPage;
			nextPage = this.executioner.executeForm(formToExecute);
		}
		this.next(nextPage);
		if(nextPage != null && nextPage.getPrevious() != null) {
			this.previousPage = nextPage.getPrevious();
		}
	}
	
	private void next(Page nextPage) {
		this.pointerPage = nextPage;
	}
	
	private void close() {
		this.executioner.close();
		System.out.println("Programma terminato. Arrivederci!");
	}	
}
