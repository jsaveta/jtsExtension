/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vividsolutions.jts.geom.create;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.io.gml2.LineStringGenerator;
import com.vividsolutions.jts.io.gml2.PointGenerator;
import java.util.Random;

/**
 *
 * @author jsaveta
 */
public class CreateOverlapsGeometryObject extends GeometryType {

    protected Geometry given;
    protected Geometry returned;
    protected int chunk;
    protected Class<?> returnedGeometry;

    public CreateOverlapsGeometryObject(Geometry givenGeometry, GeometryType.GeometryTypes geometry) {
        this.chunk = 2;
        this.given = givenGeometry;
        this.returnedGeometry = selectGeometryType(geometry);

    }

    public Geometry generateGeometry() {
        String givenGeometryType = this.given.getGeometryType();

        GeometryFactory geometryFactory = new GeometryFactory();

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
                        int chunck = lineString.getCoordinates().length / 2;
                        if (lineString.getCoordinates().length == 3) {
                            chunk = 2;
                        }

                        LineString[] lineArray = cutLineString(lineString, chunck);

                        int rand = 0;
                        LineString line = lineArray[rand];

                        int length = lineString.getCoordinates().length;
                        int poinsToIntersect = line.getCoordinates().length;
                        int pointsToGenerate = length - poinsToIntersect;

                        Coordinate[] coords;

                        if (pointsToGenerate == 1) {
                            PointGenerator pg = new PointGenerator();
                            pg.setGeometryFactory(geometryFactory);
                            pg.setBoundingBox(new Envelope(-180, 180, -90, 90));
                            Point pt = (Point) pg.create();
                            switch (rand) {
                                case 0:
                                    coords = concatenate(line.getCoordinates(), pt.getCoordinates());
                                    this.returned = geometryFactory.createLineString(coords);
                                    break;
                                case 1:
                                    coords = concatenate(pt.getCoordinates(), line.getCoordinates());
                                    this.returned = geometryFactory.createLineString(coords);
                                    break;
                            }

                        } else {
                            //generate the rest of points 
                            LineStringGenerator pg = new LineStringGenerator();
                            pg.setGeometryFactory(geometryFactory);
                            pg.setBoundingBox(new Envelope(-180, 180, -90, 90));
                            //pg.setGenerationAlgorithm(LineStringGenerator.HORZ);
                            if (pointsToGenerate < 4) {
                                //or HORZ
                                pg.setGenerationAlgorithm(LineStringGenerator.VERT);
                            }
                            pg.setNumberPoints(pointsToGenerate);
                            LineString pt = (LineString) pg.create();
                            //merge all points and create a new linestring
                            switch (rand) {
                                case 0:

                                    coords = concatenate(line.getCoordinates(), pt.getCoordinates());
                                    this.returned = geometryFactory.createLineString(coords);
                                    break;
                                case 1:

                                    coords = concatenate(pt.getCoordinates(), line.getCoordinates());
                                    this.returned = geometryFactory.createLineString(coords);
                                    break;
                            }
                        }
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
