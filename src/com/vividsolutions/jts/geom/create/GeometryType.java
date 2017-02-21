/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vividsolutions.jts.geom.create;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryCollection;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;

/**
 *
 * @author jsaveta
 */
public abstract class GeometryType {

    /**
     * enum if all Geometry types
     */
    protected static enum GeometryTypes {
        Point, MultiPoint, LineString, LinearRing, MultiLineString, Polygon, MultiPolygon, GeometryCollection
    }

    protected Class<?> selectGeometryType(GeometryTypes geometry) {
        try {
            switch (geometry) {
                case Point:
                    return Point.class;
                case MultiPoint:
                    return MultiPoint.class;
                case LineString:
                    return LineString.class;
                case LinearRing:
                    return LinearRing.class;
                case MultiLineString:
                    return MultiLineString.class;
                case Polygon:
                    return Polygon.class;
                case MultiPolygon:
                    return MultiPolygon.class;
                case GeometryCollection:
                    return GeometryCollection.class;
            }
        } catch (IllegalArgumentException iae) {
            System.err.println("The chosen GeometryType is wrong.\nPlease choose one of: Point, MultiPoint, LineString, LinearRing, MultiLineString, Polygon, MultiPolygon, GeometryCollection");
        }
        return null;
    }


    /**
     * Cut an array of Coordinates into multiple arrays. Each array has the first
     * point common with the last point of the previous array
     * @param array array of Coordinates 
     * @param chunkSize defines how many instances of coordinates will be in each smaller array
     * @return array of Coordinate array
     */
    protected static Coordinate[][] chunkArray(Coordinate[] array, int chunkSize) {
        int numOfChunks = (int) Math.ceil((double) array.length / chunkSize);
        if (chunkSize == 2) {
            numOfChunks = array.length - 1;
        }
        Coordinate[][] output = new Coordinate[numOfChunks][];
        for (int i = 0; i < numOfChunks; ++i) {
            int start = i * chunkSize;
            if (start > 0) {
                start -= i;
            }

            int length = Math.min(array.length - start, chunkSize);
            Coordinate[] temp = new Coordinate[length];
            System.arraycopy(array, start, temp, 0, length);
            output[i] = temp;
        }
        return output;
    }

    /**
     * Cut a LineString into smaller LineStrings
     * @param line the input LineString
     * @param chunk the number of Coordinates of each smaller line
     * @return a Linestring[] of the smaller LineString
     */
    protected LineString[] getLineStringArray(LineString line, int chunk) {
        GeometryFactory geometryFactory = new GeometryFactory();
        Coordinate[] coord = line.getCoordinates();
        Coordinate[][] chunckedArray = chunkArray(coord, chunk);
        LineString[] lineArray = new LineString[chunckedArray.length];

        for (int i = 0; i < chunckedArray.length; i++) {
            Coordinate[] coordTemp = new Coordinate[chunckedArray[i].length];
            System.arraycopy(chunckedArray[i], 0, coordTemp, 0, chunckedArray[i].length);
            lineArray[i] = geometryFactory.createLineString(coordTemp);
        }
        return lineArray;
    }
}
