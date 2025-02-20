package com.unicam.City_Explore.visual_interface;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unicam.City_Explore.contenuti.MultimediaContentController;
import com.unicam.City_Explore.contest.ContestController;
import com.unicam.City_Explore.eliminazione.DeletionController;
import com.unicam.City_Explore.evento.EventController;
import com.unicam.City_Explore.poi.POIController;
import com.unicam.City_Explore.tour.TourController;
import com.unicam.City_Explore.validazione.ValidationController;
import com.unicam.City_Explore.visual_interface.menu_pages.GestionePOIPage;
import com.unicam.City_Explore.visual_interface.menu_pages.MainPage;
import com.unicam.City_Explore.visual_interface.menu_pages.MenuPage;

@Component
public class PageController implements CommandLineRunner{
	
	@Autowired
	private PageExecutioner executioner = new PageExecutioner();
	@Autowired
	private POIController poiController = new POIController();
	@Autowired
	private TourController tourController;
	@Autowired
	private ContestController contestController = new ContestController();
	@Autowired
	private EventController eventController = new EventController();
	@Autowired
	private MultimediaContentController multimediaContentController = new MultimediaContentController();
	@Autowired
	private ValidationController validationController;
	@Autowired
	private DeletionController deletionController;
	
	private Page pointerPage;
	
	public PageController() {
//		poiController.initializer();
//		multimediaContentController.initializer();
//		contestController.initializer();
//		eventController.initializer();
//		System.out.println("Inizializzazione avvenuta con successo.");
	}
	@Override
	public void run(String... args) throws Exception {
		this.pointerPage = new MainPage();
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
