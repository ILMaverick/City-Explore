package com.unicam.City_Explore.visual_interface;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
public class PageController {

	private Scanner scanner = new Scanner(System.in);
	
	@Autowired
	private PageDisplay viewer = new PageDisplay();
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
	
	private Page pointerPage = new MainPage();
	
	public PageController() {
		
	}

	public void start() {
		while (this.pointerPage != null) {
			if (this.pointerPage instanceof MenuPage) {
				MenuPage toShow = (MenuPage) this.pointerPage;
				this.showMenu(toShow);
				int idChapter = this.scanner.nextInt();
				if (idChapter == 0) {
					this.next(toShow.getPrevious());
				} else {
					this.next(toShow.getNext(idChapter - 1));
				}
			} else {
				
			}
		}
		this.close();
	}
	
	private void showMenu(MenuPage toShow) {
		this.viewer.showMenu(toShow);
	}
	
	private void next(Page toShow) {
		this.pointerPage = toShow;
	}
	
	public void initializer() {
		poiController.initializer();
		multimediaContentController.initializer();
		contestController.initializer();
		eventController.initializer();
		System.out.println("Inizializzazione avvenuta con successo.");
	}
	
	private void close() {
		System.out.println("Arrivederci!");
		scanner.close();
	}
	
}
