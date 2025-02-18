package com.speriamochemelacavo.City_Explore.CONTENUTI;

public enum FormatFileEnum {
    Document,
    Image,
    Audio,
    Video,
    Other;

    public static FormatFileEnum formatFile(String format) {
        switch (format.toLowerCase()) {
            //Categoria Documenti: pdf, doc, txt, odt
            case "pdf":
            case "doc":
            case "txt":
            case "odt":
                return Document;

            //Categoria Immagini: jpeg, jpg, png, gif
            case "jpeg":
            case "jpg":
            case "png":
            case "gif":
                return Image;

            //Categoria Audio: mp3, wav, aac, flac
            case "mp3":
            case "wav":
            case "aac":
            case "flac":
                return Audio;

            //Categoria Video: mp4, avi, mkv, mov
            case "mp4":
            case "avi":
            case "mkv":
            case "mov":
                return Video;

            default:
                // Se non viene riconosciuto, si può scegliere un default
                return Other;
        }
    }
}
