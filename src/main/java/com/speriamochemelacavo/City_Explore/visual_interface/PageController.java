package visual_interface;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import contenuti.MultimediaContentController;
import contest.ContestController;
import eliminazione.DeletionController;
import evento.EventController;
import poi.POIController;
import tour.TourController;
import validazione.ValidationController;
import visual_interface.menu_pages.GestionePOIPage;
import visual_interface.menu_pages.MainPage;
import visual_interface.menu_pages.MenuPage;

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
