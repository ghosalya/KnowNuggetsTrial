package com.example.kygrykhon.knownuggettrial;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Kygrykhon on 5/11/2017.
 */

public class Coordinate {

    @SerializedName("latitude")
    private String latitude;

    @SerializedName("longitude")
    private String longitude;


    public final double _latitude, _longitude;
    public static int R = 6371;

    public Coordinate(String lat, String longg) {
        latitude = lat;
        longitude = longg;
        _latitude = Double.parseDouble(lat);
        _longitude = Double.parseDouble(longg);
    }

    public double distanceTo(Coordinate b) {
        return getDistance(this, b);
    }

    public static double getDistance(Coordinate a, Coordinate b) {
        double dLat = Math.toRadians(a._latitude - b._latitude);
        double dLong = Math.toRadians(a._longitude - b._longitude);
        double m = Math.pow(Math.sin(dLat/2),2)
                + Math.cos(Math.toRadians(a._latitude))*Math.cos(Math.toRadians(b._latitude))
                * Math.pow(Math.sin(dLong/2),2);
        double c = 2 * Math.atan2(Math.sqrt(m), Math.sqrt(1-m));
        double distance = R*c;
        return distance;
    }
}
