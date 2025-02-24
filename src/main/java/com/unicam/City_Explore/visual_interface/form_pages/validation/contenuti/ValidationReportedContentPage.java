package com.unicam.City_Explore.visual_interface.form_pages.validation.contenuti;

import com.unicam.City_Explore.elementi.Status;
import com.unicam.City_Explore.segnalazione.MediaReport;
import com.unicam.City_Explore.validazione.ValidationService;
import com.unicam.City_Explore.visual_interface.Page;
import com.unicam.City_Explore.visual_interface.form_pages.FormPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class ValidationReportedContentPage extends FormPage {

    @Autowired
    private ValidationService validationService;

    public ValidationReportedContentPage() {
        super("Valida Contenuti Multimediali Segnalati");
    }

    @Override
    public void startForm(Scanner scanner) {
        // TODO Auto-generated method stub
        System.out.println("=== Validazione Contenuti Multimediali Segnalati ===");

        List<MediaReport> reportList = validationService.getAllReportedMultimediaContent();
        for(MediaReport report: reportList) {
            System.out.println(report);
            System.out.print("1 per aggiornare o 2 per eliminare: ");
            int num = scanner.nextInt();
            if(num == 1) {
                validationService.handleReportMultimediaContent(report, Status.UPDATED,"");
                System.out.println("Contenuto in stato di aggiornamento.");
            } else if(num == 2) {
                System.out.print("Inserisci una motivazione per il rifiuto: ");
                String reason = scanner.nextLine();
                validationService.handleReportMultimediaContent(report, Status.REJECTED, reason);
                System.out.println("Contenuto Rifiutato " + reason + ".");
            } else {
                System.out.println("Opzione inesistente, il Contenuto e' ancora segnalato.");
            }
        }
    }

    @Override
    public Page getNext() {
        return this.getPrevious();
    }
}