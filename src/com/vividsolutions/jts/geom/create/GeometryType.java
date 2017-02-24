/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vividsolutions.jts.geom.create;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryCollection;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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
     * Cut an array of Coordinates into multiple arrays. Each array has the
     * first point common with the last point of the previous array
     *
     * @param array array of Coordinates
     * @param chunkSize defines how many instances of coordinates will be in
     * each smaller array
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
//https://joinup.ec.europa.eu/svn/gvsig-desktop/trunk/libraries/libTopology/src/org/gvsig/jts/LineStringSplitter.java

    /**
     * Cut a LineString into smaller LineStrings
     *
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

    public <T> T[] concatenate(T[] a, T[] b) {
        int aLen = a.length;
        int bLen = b.length;

        @SuppressWarnings("unchecked")
        T[] c = (T[]) Array.newInstance(a.getClass().getComponentType(), aLen + bLen);
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);

        return c;
    }

    public ArrayList<Envelope> cutGeometryEnvelope(Geometry geo, int parts) {
        GeometryFactory geometryFactory = new GeometryFactory();
        Coordinate[] coord = geo.getCoordinates();
        int chunk = geo.getCoordinates().length / parts;
        if(chunk < 2){chunk = 2;} //this is when the geometry is a linestring
        //the other geometries have more restrictions! 
        //TODO solve this
        System.out.println("geo.getCoordinates().length " +geo.getCoordinates().length);
        System.out.println("parts " +parts);
        System.out.println("chunk " +chunk);
        Coordinate[][] chunckedArray = chunkArray(coord, chunk);
        
        ArrayList<Envelope> envelopes = new ArrayList<Envelope>();
        
        for (int i = 0; i < chunckedArray.length; i++) {
            Coordinate[] coordTemp = new Coordinate[chunckedArray[i].length];
            System.arraycopy(chunckedArray[i], 0, coordTemp, 0, chunckedArray[i].length);
            System.out.println(" coordTemp "  +Arrays.toString(coordTemp));
            switch (geo.getGeometryType()) {
                case "MultiPoint":
                    break;
                case "LineString":
                    LineString line =  geometryFactory.createLineString(coordTemp);
                    System.out.println("line " + line);
                    envelopes.add(line.getEnvelopeInternal());
                    break;
                case "LinearRing":
                    break;
                case "MultiLineString":
                    break;
                case "Polygon":
                    break;
                case "MultiPolygon":
                    break;
                case "GeometryCollection":
                    break;
            }

        }
//        System.out.println("coord  " + Arrays.toString(coord));
//        System.out.println("envelopes " + envelopes);
        return envelopes;
        
    }
}
