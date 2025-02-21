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
	
	private Page pointerPage;
	
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
			this.execute();
		}
		this.close();
	}
	
	private void execute() {
		if (this.pointerPage instanceof MenuPage) {
			MenuPage toExecute = (MenuPage) this.pointerPage;
			this.next(this.executioner.executeMenu(toExecute));
		} else {
			
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
