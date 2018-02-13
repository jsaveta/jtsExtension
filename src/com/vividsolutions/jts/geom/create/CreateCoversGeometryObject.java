/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vividsolutions.jts.geom.create;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import java.util.Random;

/**
 *
 * @author jsaveta
 */
public class CreateCoversGeometryObject extends GeometryType {

    protected Geometry given;
    protected Geometry returned;
    protected Class<?> returnedGeometry;

    public CreateCoversGeometryObject(Geometry givenGeometry, GeometryType.GeometryTypes geometry) {
        this.given = givenGeometry;
        this.returnedGeometry = selectGeometryType(geometry);

    }

    public Geometry generateGeometry() {
        String givenGeometryType = this.given.getGeometryType();
        GeometryFactory geometryFactory = new GeometryFactory();
        this.returned = this.given; //in case that there is nothing to return

        switch (givenGeometryType) {
            case "Point":
                //points cannot touch to each other
                Point point = (Point) this.given;
                switch (this.returnedGeometry.getSimpleName()) {
                    case "Point":
                        break;
                    case "MultiPoint":
                        break;
                    case "LineString":
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
                    default:
                        break;
                }
                break;
            case "MultiPoint":
                MultiPoint multipoint = (MultiPoint) this.given;
                switch (this.returnedGeometry.getSimpleName()) {
                    case "Point":
                        break;
                    case "MultiPoint":
                        break;
                    case "LineString":
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
                    default:
                        break;
                }
                break;
            case "LineString":
                LineString lineString = (LineString) this.given;

                switch (this.returnedGeometry.getSimpleName()) {
                    case "Point":
                        break;
                    case "MultiPoint":
                        break;
                    case "LineString":

                        Random randomGenerator = new Random();
                        int r1 = lineString.getCoordinates().length - 2;
                        if (r1 <= 0) {
                            r1 = 1;
                        }

                        //cut the line into pieces in order to keep a small part of it
                        int chunk = randomGenerator.nextInt(r1) + 2;
                        LineString[] lineArray = cutLineString(lineString, chunk);

                        int r2 = lineArray.length;
                        if (r2 <= 0) {
                            r2 = 1;
                        }
                        //keep a random smaller part of the linestring
                        int randomInt = randomGenerator.nextInt(r2);
                        LineString line = lineArray[randomInt];

                        //if the small part is not contained into the linestring (does not follow the definition of contains)
                        //cut and pick again                             
                        while (!lineString.contains(line)) {
                            r1 = r1 - (int) (r1 * 0.2); //reduce linstring part 20%
                            if (r1 <= 0) {
                                r1 = 1;
                            }
                            chunk = randomGenerator.nextInt(r1) + 2;
                            lineArray = cutLineString(lineString, chunk);

                            r2 = lineArray.length;
                            if (r2 <= 0) {
                                r2 = 1;
                            }
                            randomInt = randomGenerator.nextInt(r2);
                            line = lineArray[randomInt];

                            if (!lineString.covers(line)) {
                                for (LineString lineArray1 : lineArray) {
                                    if (lineString.contains(lineArray1)) {
                                        this.returned = lineArray1;
                                        break;
                                    }
                                }
                            }
                        }
                        this.returned = line;
                        break;
                    case "LinearRing":
                        break;
                    case "MultiLineString":
                        break;
                    case "Polygon":
                        
                        Coordinate[] newCoords = new Coordinate[lineString.getCoordinates().length + 1];
                        System.arraycopy(lineString.getCoordinates(), 0, newCoords, 0, newCoords.length - 1);
                        newCoords[newCoords.length - 1] = lineString.getCoordinates()[0];
                        Polygon poly = geometryFactory.createPolygon(newCoords);
                        poly = (Polygon) poly.convexHull().buffer(1);
                        
                        if (poly instanceof Polygon) {
                            this.returned = poly;
                        } 

                        break;
                    case "MultiPolygon":
                        break;
                    case "GeometryCollection":
                        break;
                    default:
                        break;
                }
                break;
            case "LinearRing":
                LinearRing linearRing = (LinearRing) this.given;
                switch (this.returnedGeometry.getSimpleName()) {
                    case "Point":
                        break;
                    case "MultiPoint":
                        break;
                    case "LineString":
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
                    default:
                        break;
                }
                break;
            case "MultiLineString":
                MultiLineString multiLine = (MultiLineString) this.given;
                switch (this.returnedGeometry.getSimpleName()) {
                    case "Point":
                        break;
                    case "MultiPoint":
                        break;
                    case "LineString":
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
                    default:
                        break;
                }
                break;
            case "Polygon":
                Polygon poly = (Polygon) this.given;
                switch (this.returnedGeometry.getSimpleName()) {
                    case "Point":
                        break;
                    case "MultiPoint":
                        break;
                    case "LineString":
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
                    default:
                        break;
                }
                break;
            case "MultiPolygon":
                MultiPolygon multiPoly = (MultiPolygon) this.given;
                switch (this.returnedGeometry.getSimpleName()) {
                    case "Point":
                        break;
                    case "MultiPoint":
                        break;
                    case "LineString":
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
                    default:
                        break;
                }
                break;
            case "GeometryCollection":
                switch (this.returnedGeometry.getSimpleName()) {
                    case "Point":
                        break;
                    case "MultiPoint":
                        break;
                    case "LineString":
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
                    default:
                        break;
                }
                break;
        }

        return this.returned;
    }

}
