package com.unicam.City_Explore.segnalazione;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unicam.City_Explore.contenuti.MultimediaContent;
import com.unicam.City_Explore.elementi.Status;
import com.unicam.City_Explore.user.User;
import com.unicam.City_Explore.notifica.NotificationListener;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MediaReportService {

    private final List<MediaReport> mediaReportList;
    @Autowired
    private NotificationListener notificationListener;

    public MediaReportService(List<MediaReport> mediaReportList, NotificationListener notificationListener) {
        this.mediaReportList = mediaReportList;
        this.notificationListener = notificationListener;
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
        notificationListener.handleReportedMultimediaContent(report);
    }

    public List<MediaReport> getMediaReportList() {
        return mediaReportList;
    }

    public void deleteReport(MediaReport report) {
        mediaReportList.remove(report);
    }
}
