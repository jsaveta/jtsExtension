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

/**
 *
 * @author jsaveta
 */
public abstract class GeometryType {

    /**
     * enum if all Geometry types
     */
    public static enum GeometryTypes {
        Point, MultiPoint, LineString, LinearRing, MultiLineString, Polygon, MultiPolygon, GeometryCollection
    }

    public Class<?> selectGeometryType(GeometryTypes geometry) {
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

    /**
     * Concatenate two arrays
     *
     * @param <T>
     * @param a first array
     * @param b second array
     * @return a new array that contains both a and b
     */
    public <T> T[] concatenate(T[] a, T[] b) {
        int aLen = a.length;
        int bLen = b.length;

        @SuppressWarnings("unchecked")
        T[] c = (T[]) Array.newInstance(a.getClass().getComponentType(), aLen + bLen);
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);

        return c;
    }

    /**
     * Cut the bounding box of a geometry into smaller bounding boxes
     * Implemented for LineStrings
     *
     * @param geo the given geometry to cut
     * @param parts the parts to cut the geometry in order to compute the new
     * bounding boxes
     * @return
     */
    public ArrayList<Envelope> cutGeometryEnvelope(Geometry geo, int parts) {
        GeometryFactory geometryFactory = new GeometryFactory();
        Coordinate[] coord = geo.getCoordinates();
        int chunk = geo.getCoordinates().length / parts;
        if (chunk < 2) {
            chunk = 2;
        }
        Coordinate[][] chunckedArray = chunkArray(coord, chunk);
        ArrayList<Envelope> envelopes = new ArrayList<Envelope>();

        for (int i = 0; i < chunckedArray.length; i++) {
            Coordinate[] coordTemp = new Coordinate[chunckedArray[i].length];
            System.arraycopy(chunckedArray[i], 0, coordTemp, 0, chunckedArray[i].length);
            switch (geo.getGeometryType()) {
                case "MultiPoint":
                    break;
                case "LineString":
                    LineString line = geometryFactory.createLineString(coordTemp);
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
        return envelopes;

    }

    /**
     * Cut the bounding box of a geometry into smaller bounding boxes and in a
     * specific index of the coordinate array of the geometry Implemented for
     * LineStrings
     *
     * @param geo the given geometry to cut
     * @param at index of point to cut
     * @param parts the parts to cut the geometry in order to compute the new
     * bounding boxes
     * @return
     */
    public ArrayList<Envelope> cutGeometryEnvelopeAt(Geometry geo, int at, int parts) {
        GeometryFactory geometryFactory = new GeometryFactory();
        Coordinate[] coord = geo.getCoordinates();

        int chunkAt = at + 1;
        if (chunkAt < 2) {
            chunkAt = 2;
        }

        Coordinate[][] chunckedArray = chunkArray(coord, chunkAt);
        ArrayList<Envelope> envelopes = new ArrayList<Envelope>();

        for (int i = 0; i < chunckedArray.length; i++) {
            Coordinate[] coordTemp = new Coordinate[chunckedArray[i].length];
            System.arraycopy(chunckedArray[i], 0, coordTemp, 0, chunckedArray[i].length);

            int chunk = coordTemp.length / parts;
            if (chunk < 2) {
                chunk = 2;
            }
            Coordinate[][] chunckedArrayFinal = chunkArray(coordTemp, chunk);

            for (int j = 0; j < chunckedArrayFinal.length; j++) {
                coordTemp = new Coordinate[chunckedArrayFinal[j].length];
                System.arraycopy(chunckedArrayFinal[j], 0, coordTemp, 0, chunckedArrayFinal[j].length);
                switch (geo.getGeometryType()) {
                    case "MultiPoint":
                        break;
                    case "LineString":
                        LineString line = geometryFactory.createLineString(coordTemp);
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
        }
        return envelopes;

    }

    /**
     * Pick a random double between two given doubles
     *
     * @param start first double
     * @param end second double
     * @return the selected double between start and end
     */
    protected double randomDouble(double start, double end) {
        double random = new Random().nextDouble();
        double randDouble = start + (random * (end - start));
        return randDouble;
    }

    /**
     * Pick n random elements of a Coordinate array
     *
     * @param array given array to select the Coordinates
     * @param number number of elements to pick
     * @return array of selected Coordinates
     */
    protected Coordinate[] pickNRandom(Coordinate[] array, int number) {
        List<Coordinate> list = new ArrayList<Coordinate>(Arrays.asList(array));
        Collections.shuffle(list);
        list = list.subList(0, number);
        return list.toArray(new Coordinate[number]);
    }

    /**
     * Keep only unique elements of a Coordinate array
     *
     * @param coordinates array of Coordinates
     * @return array of elements that appear only ones
     */
    public Coordinate[] uniqueElements(Coordinate[] coordinates) {
        if (coordinates == null) {
            throw new IllegalArgumentException();
        }

        List<Coordinate> result = new ArrayList<Coordinate>();
        for (Coordinate next : coordinates) {
            if (result.contains(next)) {
                result.remove(next);
            } else {
                result.add(next);
            }
        }
        return result.toArray(new Coordinate[result.size()]);
    }
}
