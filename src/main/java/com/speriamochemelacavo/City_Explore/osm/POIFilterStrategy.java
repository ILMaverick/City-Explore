package osm;

import java.util.HashMap;
import java.util.Map;

public class POIFilterStrategy {
    private static final Map<String, String> filters = new HashMap<>();

    static {
        filters.put("ristoranti", "[\"amenity\"=\"restaurant\"]");
        filters.put("monumenti", "[\"historic\"=\"monument\"]");
        filters.put("musei", "[\"tourism\"=\"museum\"]");
        filters.put("scuole", "[\"amenity\"=\"school\"]");
        filters.put("università", "[\"amenity\"=\"university\"]");
        filters.put("ospedali", "[\"amenity\"=\"hospital\"]");
        filters.put("farmacie", "[\"amenity\"=\"pharmacy\"]");
        filters.put("parchi", "[\"leisure\"=\"park\"]");
        filters.put("foreste", "[\"landuse\"=\"forest\"]");
        filters.put("teatri", "[\"amenity\"=\"theatre\"]");
        filters.put("cinema", "[\"amenity\"=\"cinema\"]");
        filters.put("mercati", "[\"amenity\"=\"marketplace\"]");
        filters.put("luoghi_culto", "[\"amenity\"=\"place_of_worship\"]");
        filters.put("quartieri_storici", "[\"historic\"=\"district\"]");
        filters.put("zone_pedonali", "[\"highway\"=\"pedestrian\"]");
        filters.put("vigneti", "[\"landuse\"=\"vineyard\"]");
        filters.put("spiagge", "[\"natural\"=\"beach\"]");
        filters.put("planetari", "[\"amenity\"=\"planetarium\"]");
        filters.put("vette", "[\"natural\"=\"peak\"]");
        filters.put("hotels", "[\"tourism\"=\"hotel\"]");
        filters.put("motels", "[\"tourism\"=\"motel\"]");
        filters.put("ostelli", "[\"tourism\"=\"hostel\"]");
        filters.put("guest_house", "[\"tourism\"=\"guest_house\"]");
    }

    public static String getFilter(String poi) {
        return filters.getOrDefault(poi.toLowerCase(), "[\"amenity\"=\"" + poi.toLowerCase() + "\"]");
    }
}
