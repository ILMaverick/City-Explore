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
public class SearchContentByNamePage extends FormPage {
	@Autowired
	private MultimediaContentService contentService;

	public SearchContentByNamePage() {
		super("Ricerca Contenuto Multimediale tramite nome");
	}

	@Override
	public void startForm(Scanner scanner) {
		System.out.print("Inserisci il nome: ");
        String name = scanner.nextLine();
        List<MultimediaContent> contentList = contentService.searchContentByName(name);
        if(contentList.isEmpty()) {
            System.out.println("Non e' presente un Contenuto con questo nome.");
        } else {
            System.out.println("Elenco Contest con il nome cercato:");
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
