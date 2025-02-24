package com.unicam.City_Explore.visual_interface.menu_pages.contest;

import com.unicam.City_Explore.user.Role;
import com.unicam.City_Explore.visual_interface.form_pages.contest.CreationContestPage;
import com.unicam.City_Explore.visual_interface.form_pages.contest.PartecipateContestPage;
import com.unicam.City_Explore.visual_interface.form_pages.contest.UpdateContestPage;
import com.unicam.City_Explore.visual_interface.menu_pages.MenuPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManageContestPage extends MenuPage {
	
	@Autowired
	private CreationContestPage creationContestPage;
	@Autowired
	private UpdateContestPage updateContestPage;
	@Autowired
	private PartecipateContestPage partecipateContestPage;

	public ManageContestPage() {
		super ("Gestione Contest");
		this.getChapters().add("Crea Contest");
		this.getChapters().add("Aggiorna un Contest");
		this.getChapters().add("Partecipa ad un Contest");
	}

	@Override
	public void setAuthorization() {
		this.authService.addAuthorization("Crea Contest", Role.ANIMATOR, Role.ADMINISTRATOR);
		this.authService.addAuthorization("Aggiorna un Contest", Role.ANIMATOR, Role.ADMINISTRATOR);
		this.authService.addAuthorization("Partecipa ad un Contest", Role.ANIMATOR, Role.ADMINISTRATOR);
	}

	@Override
	public void populateLinksTable() {
		this.getLinksTable().put("Crea Contest", this.creationContestPage);
		this.getLinksTable().put("Aggiorna un Contest", this.updateContestPage);
		this.getLinksTable().put("Partecipa ad un Contest", this.partecipateContestPage);
	}
}
