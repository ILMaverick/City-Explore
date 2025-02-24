package com.unicam.City_Explore.visual_interface.menu_pages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.user.Role;
import com.unicam.City_Explore.visual_interface.form_pages.contenuti.DeleteContentPage;
import com.unicam.City_Explore.visual_interface.form_pages.contest.DeleteContestPage;
import com.unicam.City_Explore.visual_interface.form_pages.evento.DeleteEventPage;
import com.unicam.City_Explore.visual_interface.form_pages.poi.DeletePOIPage;
import com.unicam.City_Explore.visual_interface.form_pages.tour.DeleteTourPage;

@Component
public class ManageEliminationPage extends MenuPage{
	@Autowired
	private DeletePOIPage deletePOIPage;
	@Autowired
	private DeleteTourPage deleteTourPage;
	@Autowired
	private DeleteEventPage deleteEventPage;
	@Autowired
	private DeleteContestPage deleteContestPage;
	@Autowired
	private DeleteContentPage deleteContentPage;

	public ManageEliminationPage() {
		super ("Gestione Eliminazione");
		this.getChapters().add("Elimina POI");
		this.getChapters().add("Elimina Itinerario");
		this.getChapters().add("Elimina Evento");
		this.getChapters().add("Elimina Contest");
		this.getChapters().add("Elimina Contenuto");
	}

	@Override
	public void setAuthorization() {
		this.authService.addAuthorization("Elimina POI", Role.CURATOR, Role.ADMINISTRATOR);
		this.authService.addAuthorization("Elimina Itinerario", Role.CURATOR, Role.ADMINISTRATOR);
		this.authService.addAuthorization("Elimina Evento", Role.ANIMATOR, Role.ADMINISTRATOR);
		this.authService.addAuthorization("Elimina Contest", Role.ANIMATOR, Role.ADMINISTRATOR);
		this.authService.addAuthorization("Elimina Contenuto", Role.ANIMATOR, Role.ADMINISTRATOR, Role.CURATOR);
	}

	@Override
	public void populateLinksTable() {
		this.getLinksTable().put("Elimina POI", this.deletePOIPage);
		this.getLinksTable().put("Elimina Itinerario", this.deleteTourPage);
		this.getLinksTable().put("Elimina Evento", this.deleteEventPage);
		this.getLinksTable().put("Elimina Contest", this.deleteContestPage);
		this.getLinksTable().put("Elimina Contenuto", this.deleteContentPage);
	}
}
