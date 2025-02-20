package segnalazione;

import contenuti.MultimediaContent;
import element.Status;
import user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MediaReportService {

    @Autowired
    private final List<MediaReport> mediaReportList;

    public MediaReportService(List<MediaReport> mediaReportList) {
        this.mediaReportList = mediaReportList;
    }

    public void createReport(String reason, User reporter, MultimediaContent multimediaContent) {
        MediaReport report = new MediaReport();
        report.setReason(reason);
        report.setReporter(reporter);
        report.setMultimediaContent(multimediaContent);
        report.setLocalDateTime(LocalDateTime.now());
        mediaReportList.add(report);
        sendReportNotification(report);
        multimediaContent.setStatus(Status.REPORTED);
    }

    private void sendReportNotification(MediaReport report) {
        //TODO: Logica per inviare la notifica della segnalazione
        System.out.println("Segnalazione per il Contenuto " + report.getMultimediaContent() + "\nda parte di " + report.getReporter()
                + ": " + report.getReason());
    }

    public List<MediaReport> getMediaReportList() {
        return mediaReportList;
    }

    public void deleteReport(MediaReport report) {
        mediaReportList.remove(report);
    }
}
