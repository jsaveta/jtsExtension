/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vividsolutions.jts.geom.create;

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

/**
 *
 * @author jsaveta
 */
public class CreateEqualGeometryObject extends GeometryType {

    protected Geometry given;
    protected Geometry returned;
    protected int chunk;
    protected Class<?> returnedGeometry;

    public CreateEqualGeometryObject(Geometry givenGeometry, GeometryTypes geometry) {
        this.chunk = 2;
        this.given = givenGeometry;
        this.returnedGeometry = selectGeometryType(geometry);

    }

    public Geometry generateGeometry() {
        String givenGeometryType = this.given.getGeometryType();
        
	GeometryFactory geometryFactory = new GeometryFactory();
        this.returned = this.given; //in case that there is nothing to return

        switch (givenGeometryType) {
            case "Point":
                Point point = (Point) this.given;
                switch (this.returnedGeometry.getSimpleName()) {
                    case "MultiPoint":
                        MultiPoint multiPoint = geometryFactory.createMultiPoint(point.getCoordinates());
                        this.returned = multiPoint;
                        break;
                    case "GeometryCollection":
                        //equalsTopo does not support GeometryCollection arguments
                        //do we still want to generate geometry collections?
                        Geometry[] geoArray = new Geometry[1];
                        geoArray[0] = point;
                        GeometryCollection geoCol = geometryFactory.createGeometryCollection(geoArray);
                        this.returned = geoCol;
                        break;
                    default: //"Point" is also default
                        this.returned = point;
                        break;
                }
                break;
            case "MultiPoint":
                MultiPoint multipoint = (MultiPoint) this.given;
                switch (this.returnedGeometry.getSimpleName()) {
                    case "Point":
                        //return a single point if the multipoint has one point
                        if (multipoint.getCoordinates().length == 1) {
                            Point p = geometryFactory.createPoint(multipoint.getCoordinate());
                            this.returned = p;
                        }
                        break;
                    case "GeometryCollection":                        
                        //equalsTopo does not support GeometryCollection arguments
                        Geometry[] geoArray = new Geometry[multipoint.getCoordinates().length];
                        for (int i = 0; i < multipoint.getCoordinates().length; i++) {
                            Point p = geometryFactory.createPoint(multipoint.getCoordinates()[i]);
                            geoArray[i] = p;
                        }
                        GeometryCollection geoCol = geometryFactory.createGeometryCollection(geoArray);
                        this.returned = geoCol;
                        break;
                    default: //"MultiPoint" is also default
                        this.returned = this.given;
                        break;
                }
                break;
            case "LineString":
                LineString lineString = (LineString) this.given;

                switch (this.returnedGeometry.getSimpleName()) {
                    case "LinearRing":
                        /*Models an OGC SFS LinearRing. A LinearRing is a LineString which is both closed and simple.
                        In other words, the first and last coordinate in the ring must be equal, and the interior 
                        of the ring must not self-intersect. Either orientation of the ring is allowed.
                        A ring must have either 0 or 4 or more points.*/
                        if (lineString.isRing()) { //does this checks if the linestring have either 0 or 4 or more points???
                            LinearRing ring = geometryFactory.createLinearRing(lineString.getCoordinates());
                            this.returned = ring;
                        }
                        break;
                    case "MultiLineString":

                        //I split a line to multilines 
                        //how can i split this randomly?
                        LineString[] lineArray = cutLineString(lineString, this.chunk);
                        MultiLineString multiline = geometryFactory.createMultiLineString(lineArray);
                        this.returned = multiline;

                        break;
                    case "Polygon":
                        //if the given line string is a ring then create an empty polygon
                        //Seems that a LineString and a Polygon are not comparable
                        if (lineString.isRing()) {
                            LinearRing ring = new GeometryFactory().createLinearRing(lineString.getCoordinates());
                            Polygon polygon = new GeometryFactory().createPolygon(ring, null);
                            this.returned = polygon;
                        }
                        break;

                    case "GeometryCollection":
                        //equalsTopo does not support GeometryCollection arguments
                        LineString[] lineArrayC = cutLineString(lineString, this.chunk);
                        GeometryCollection geoCol = geometryFactory.createGeometryCollection(lineArrayC);
                        this.returned = geoCol;

                        break;
                    default: //"LineString" is also default
                        this.returned = this.given;
                        break;
                }
                break;
            case "LinearRing":
                LinearRing linearRing = (LinearRing) this.given;
                switch (this.returnedGeometry.getSimpleName()) {
                    case "LineString":
                        LineString line = geometryFactory.createLineString(linearRing.getCoordinates());
                        this.returned = line;
                        break;
                    case "MultiLineString":
                        LineString lineM = geometryFactory.createLineString(linearRing.getCoordinates());
                        LineString[] lineArray = cutLineString(lineM, this.chunk);
                        MultiLineString multiline = geometryFactory.createMultiLineString(lineArray);
                        this.returned = multiline;
                        break;
                    case "Polygon":
                        //create an empty polygon from the given polygon but
                        //Seems that a LinearRing and a Polygon are not comparable
                        Polygon polygon = new GeometryFactory().createPolygon(linearRing, null);
                        this.returned = polygon;
                        break;
                    case "GeometryCollection":
                        //equalsTopo does not support GeometryCollection arguments
                        LineString lineGC = geometryFactory.createLineString(linearRing.getCoordinates());
                        LineString[] lineArrayC = cutLineString(lineGC, this.chunk);
                        GeometryCollection geoCol = geometryFactory.createGeometryCollection(lineArrayC);
                        this.returned = geoCol;

                        break;
                    default:
                        this.returned = this.given; //"LinearRing" is also default
                        break;
                }
                break;
            case "MultiLineString":
                MultiLineString multiLine = (MultiLineString) this.given;
                switch (this.returnedGeometry.getSimpleName()) {
                    case "Polygon":
                        //not sure that this is valid
                        if (multiLine.isClosed() && multiLine.isSimple()) { //num of points??
                            LinearRing ring = geometryFactory.createLinearRing(multiLine.getCoordinates());
                            Polygon polygon = geometryFactory.createPolygon(ring, null);
                            this.returned = polygon;

                        }
                        break;
                    case "MultiPolygon":
                        //same as above
                        //only if all linestrings are rings
                        break;
                    case "GeometryCollection":
                        //equalsTopo does not support GeometryCollection arguments
                        Geometry[] geometries = new Geometry[multiLine.getNumGeometries()];
                        for (int i = 0; i < multiLine.getNumGeometries(); i++) {
                            geometries[i] = multiLine.getGeometryN(i);
                        }
                        GeometryCollection geoCol = geometryFactory.createGeometryCollection(geometries);
                        this.returned = geoCol;

                        break;
                    default:
                        this.returned = this.given; //"MultiLineString" is also default
                        break;
                }
                break;
            case "Polygon":
                Polygon poly = (Polygon) this.given;
                switch (this.returnedGeometry.getSimpleName()) {
                    case "LineString":
                        //Seems that a LineString and a Polygon are not comparable
                        if (poly.getNumInteriorRing() != 0) { //how to check if polygon is empty?
                            LineString line = geometryFactory.createLineString(poly.getBoundary().getCoordinates());
                            this.returned = line;
                        }
                        break;
                    case "LinearRing":
                        //Seems that a LinearRing and a Polygon are not comparable
                        if (poly.getNumInteriorRing() != 0 && poly.isSimple()) { //how to check if polygon is empty?
                            LinearRing ring = geometryFactory.createLinearRing(poly.getBoundary().getCoordinates());
                            this.returned = ring;
                        }
                        break;
                    case "MultiLineString":
                         //Not sure if comparable 
                        if (poly.getNumInteriorRing() != 0) {
                            // Polygon has holes, also several boundaries. 
                            // Simply run getBoundary(), it will return a multilinestring
                            this.returned = poly.getBoundary();
                        }
                        break;
                    case "GeometryCollection":
                        //collection of linestring if the polygon is empty?
                        break;
                    default:
                        // the polygons in a multipolygon may not overlap and may only touch at a single point
                        //thus,  you cannot split a polygon into a multipolygon 
                        this.returned = this.given; //"Polygon" is also default
                        break;
                }
                break;
            case "MultiPolygon":
                MultiPolygon multiPoly = (MultiPolygon) this.given;
                switch (this.returnedGeometry.getSimpleName()) {
                    case "MultiLineString":
                        //if the multipolygon is empty 
                        // can only the boundaries(as a multilinestring) be equal with the multipolygon?
                        break;
                    case "MultiPolygon":
                        this.returned = this.given;
                        break;
                    case "GeometryCollection":
                        //equalsTopo does not support GeometryCollection arguments
                        Geometry[] geometries = new Geometry[multiPoly.getNumGeometries()];
                        for (int i = 0; i < multiPoly.getNumGeometries(); i++) {
                            geometries[i] = multiPoly.getGeometryN(i);
                        }
                        GeometryCollection geoCol = geometryFactory.createGeometryCollection(geometries);
//                        System.out.println("geoCol " + geoCol);
                        this.returned = geoCol;
                        break;
                    default:
                        this.returned = this.given; //"MultiPolygon" is also default
                        break;
                }
                break;
            case "GeometryCollection":
                //equalsTopo does not support GeometryCollection arguments

                MultiPolygon geo = (MultiPolygon) this.given;
                switch (this.returnedGeometry.getSimpleName()) {
                    case "Point":
                        if (geo.getDimension() == 1) {
                            if (geo.getGeometryN(0) instanceof Point) {
                                this.returned = geo.getGeometryN(0);
                            }
                        }
                        break;
                    case "MultiPoint":
                        if (geo.getDimension() == 1) {
                            if (geo.getGeometryN(0) instanceof MultiPoint) {
                                this.returned = geo.getGeometryN(0);
                            }
                        }
                        break;
                    case "LineString":
                        if (geo.getDimension() == 1) {
                            if (geo.getGeometryN(0) instanceof LineString) {
                                this.returned = geo.getGeometryN(0);
                            }
                        }
                        break;
                    case "LinearRing":
                        if (geo.getDimension() == 1) {
                            if (geo.getGeometryN(0) instanceof LinearRing) {
                                this.returned = geo.getGeometryN(0);
                            }
                        }
                        break;
                    case "MultiLineString":
                        if (geo.getDimension() == 1) {
                            if (geo.getGeometryN(0) instanceof MultiLineString) {
                                this.returned = geo.getGeometryN(0);
                            }
                        }
                        break;
                    case "Polygon":
                        if (geo.getDimension() == 1) {
                            if (geo.getGeometryN(0) instanceof Polygon) {
                                this.returned = geo.getGeometryN(0);
                            }
                        }
                        break;
                    case "MultiPolygon":
                        if (geo.getDimension() == 1) {
                            if (geo.getGeometryN(0) instanceof MultiPolygon) {
                                this.returned = geo.getGeometryN(0);
                            }
                        }
                        break;
                    case "GeometryCollection":
                        this.returned = this.given;
                        break;
                    default:
                        break;
                }
                break;
        }

        return this.returned;
    }

}
