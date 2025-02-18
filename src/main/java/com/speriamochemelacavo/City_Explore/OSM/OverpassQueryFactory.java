package com.speriamochemelacavo.City_Explore.OSM;

public class OverpassQueryFactory {
	public static String createQuery(String city, String poiFilter) {
	        return "[out:json][timeout:25];\n" +
	               "area[\"name\"=\"" + city + "\"][\"boundary\"=\"administrative\"]->.searchArea;\n" +
	               "(\n" +
	               "  node" + poiFilter + "(area.searchArea);\n" +
	               "  way" + poiFilter + "(area.searchArea);\n" +
	               "  relation" + poiFilter + "(area.searchArea);\n" +
	               ");\n" +
	               "out body;\n" +
	               ">;\n" +
	               "out skel qt;";
	    }

}
