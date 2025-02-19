package segnalazione;

import contenuti.MultimediaContent;
import user.User;

import java.time.LocalDateTime;

public class MediaReport {
    private int id;
    private String message;
    private User author;
    private MultimediaContent multimediaContent;
    private LocalDateTime localDateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
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
                "\n id=" + id +
                ",\n message='" + message + '\'' +
                ",\n author=" + author +
                ",\n multimediaContent=" + multimediaContent +
                ",\n localDateTime=" + localDateTime +
                '}';
    }
}
