package com.unicam.City_Explore.segnalazione;

import java.time.LocalDateTime;

import com.unicam.City_Explore.contenuti.MultimediaContent;
import com.unicam.City_Explore.user.User;

public class MediaReport {

    private String reason;

    private User reporter;

    private MultimediaContent multimediaContent;

    private LocalDateTime localDateTime;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public User getReporter() {
        return reporter;
    }

    public void setReporter(User reporter) {
        this.reporter = reporter;
    }

    public MultimediaContent getMultimediaContent() {
        return multimediaContent;
    }

    public void setMultimediaContent(MultimediaContent multimediaContent) {
        this.multimediaContent = multimediaContent;
    }

    public LocalDateTime getLocalDateTime() {
        return this.localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public String toString() {
        return "MediaReport {" +
                ",\n reason='" + reason + '\'' +
                ",\n reporter=" + reporter +
                ",\n multimediaContent=" + multimediaContent +
                ",\n localDateTime=" + localDateTime +
                '}';
    }
}
