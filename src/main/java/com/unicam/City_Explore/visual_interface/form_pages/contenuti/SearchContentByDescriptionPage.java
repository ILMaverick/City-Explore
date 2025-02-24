package com.unicam.City_Explore.visual_interface.form_pages.contenuti;

import java.util.List;
import java.util.Scanner;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.contenuti.MultimediaContent;
import com.unicam.City_Explore.contenuti.MultimediaContentService;
import com.unicam.City_Explore.visual_interface.Page;
import com.unicam.City_Explore.visual_interface.form_pages.FormPage;

@Component
public class SearchContentByDescriptionPage extends FormPage {
	@Autowired
	private MultimediaContentService contentService;
	
	public SearchContentByDescriptionPage() {
		super("Ricerca Contenuto Multimediale tramite descrizione");
	}

	@Override
	public void startForm(Scanner scanner) {
        System.out.print("Inserisci la descrizione: ");
        String description = scanner.nextLine();
        List<MultimediaContent> contentList = contentService.searchContentByDescription(description);
        if(contentList.isEmpty()) {
            System.out.println("Non e' presente un Contenuto con questa descrizione.");
        } else {
            System.out.println("Elenco Contest con la descrizione cercata:");
            for(MultimediaContent content: contentList) {
                System.out.println(content);
            }
        }
	}

	@Override
	public Page getNext() {
		return this.getPrevious();
	}
}
