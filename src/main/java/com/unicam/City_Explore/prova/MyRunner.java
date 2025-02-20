package com.unicam.City_Explore.prova;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.visual_interface.PageController;

@Component
public class MyRunner implements CommandLineRunner {
	@Autowired
	PageController controller;
    @Override
    public void run(String... args) {
        System.out.println("Metodo eseguito dopo il main!");
        myMethod();
    }

    private void myMethod() {
        System.out.println("Eseguendo il metodo specifico...");
        
    }
}
