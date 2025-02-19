package segnalazione;

import contenuti.MultimediaContent;
import user.User;

import java.time.LocalDateTime;


public class MediaReport {

    private String reason;

    private User reporter;

    private MultimediaContent multimediaContent;

    private LocalDateTime localDateTime;

    public MediaReport(String reason, User reporter, MultimediaContent multimediaContent) {
        this.reason = reason;
        this.reporter = reporter;
        this.multimediaContent = multimediaContent;
        this.localDateTime = LocalDateTime.now();
    }

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
