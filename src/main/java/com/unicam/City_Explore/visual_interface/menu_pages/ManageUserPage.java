package com.unicam.City_Explore.visual_interface.menu_pages;

import com.unicam.City_Explore.user.Role;
import com.unicam.City_Explore.visual_interface.form_pages.UserPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManageUserPage extends MenuPage {
    @Autowired
    private UserPage userPage;


    public ManageUserPage() {
        super ("Visualizza Utenti Registrati");
        this.getChapters().add("Crea Evento");

    }

    @Override
    public void setAuthorization() {
        this.authService.addAuthorization("Visualizza Utenti Registrati", Role.ADMINISTRATOR);

    }

    @Override
    public void populateLinksTable() {
        this.getLinksTable().put("Visualizza Utenti Registrati", this.userPage);

    }
}
