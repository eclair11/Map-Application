package com.semweb.map.utils;

import java.lang.Math;

import com.semweb.map.model.Coordinate;

import java.awt.geom.AffineTransform;

public class OutilCalcul {

    public static Coordinate metersToLngLat(double mx, double my) {
        double originShift = 2 * Math.PI * 6378137 / 2.0;

        double lon = (mx / originShift) * 180.0;
        double lat = (my / originShift) * 180.0;

        lat = 180 / Math.PI * (2 * Math.atan(Math.exp(lat * Math.PI / 180.0)) - Math.PI / 2.0);

        return new Coordinate(lon, lat);
    }

  public static final double RADIUS = 6378137.0; /* in meters on the equator */

  /* These functions take their length parameter in meters and return an angle in degrees */

  public static double y2lat(double aY) {
    return Math.toDegrees(Math.atan(Math.exp(aY / RADIUS)) * 2 - Math.PI/2);
  }
  public static double x2lon(double aX) {
    return Math.toDegrees(aX / RADIUS);
  }

  /* These functions take their angle parameter in degrees and return a length in meters */

  public static double lat2y(double aLat) {
    return Math.log(Math.tan(Math.PI / 4 + Math.toRadians(aLat) / 2)) * RADIUS;
  }  
  public static double lon2x(double aLong) {
    return Math.toRadians(aLong) * RADIUS;
  }
}
    

