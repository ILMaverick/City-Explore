package CONTENUTI;

import POI.POIRepository;
import POI.POIService;
import POI.PointOfInterest;
import USER.User;

import java.time.LocalDateTime;
import java.util.List;

public class MultimediaContentService {
    private MultimediaContentRepository multimediaContentRepository;

    public MultimediaContentService(MultimediaContentRepository multimediaContentRepository) {
        this.multimediaContentRepository = multimediaContentRepository;
    }

    public MultimediaContent loadMultimediaContent(String name, String description, User author, FormatFileEnum format, float duration, float dimension, float resolution, PointOfInterest poi) {
        MultimediaContent multimediaContent = new MultimediaContent(name, description, author);
        multimediaContent.setFormatFileEnum(format);
        multimediaContent.setDuration(duration);
        multimediaContent.setDimension(dimension);
        multimediaContent.setResolution(resolution);
        multimediaContent.setPointOfInterest(poi);
        multimediaContent.setDataCreation(LocalDateTime.now());
        multimediaContentRepository.save(multimediaContent);
        return multimediaContent;
    }

    public void saveMultimediaContent(MultimediaContent multimediaContent) {
        multimediaContentRepository.save(multimediaContent);
    }

    public List<MultimediaContent> getAllMultimediaContent() {
        return multimediaContentRepository.findAll();
    }

    public MultimediaContent getMultimediaContentById(String id) {
        return multimediaContentRepository.findById(id);
    }

}
