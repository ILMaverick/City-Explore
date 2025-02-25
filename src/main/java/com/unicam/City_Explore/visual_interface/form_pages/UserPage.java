package com.unicam.City_Explore.visual_interface.form_pages;

import com.unicam.City_Explore.user.User;
import com.unicam.City_Explore.user.UserService;
import com.unicam.City_Explore.visual_interface.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class UserPage extends FormPage {

    @Autowired
    private UserService userService;

    public UserPage() {
        super("Visualizza Utenti Registrati");

    }

    @Override
    public void startForm(Scanner scanner) {
        System.out.println("=== Elenco Utenti Registrati ===");
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("Nessun Utente salvato!");
        } else {
            for (User user : users) {
                System.out.println(user + "\n");
            }
        }

    }

    @Override
    public Page getNext() {
        return this.getPrevious();
    }
}