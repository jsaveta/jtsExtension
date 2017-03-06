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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author jsaveta
 */
public class CreateTouchesGeometryObject extends GeometryType {

    protected Geometry given;
    protected Geometry returned;
    protected int chunk;
    protected Class<?> returnedGeometry;
    protected int side = 0;

    public CreateTouchesGeometryObject(Geometry givenGeometry, GeometryType.GeometryTypes geometry) {
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
                //points cannot touch to each other
                Point point = (Point) this.given;
                switch (this.returnedGeometry.getSimpleName()) {
                    case "LineString":
                        //if the point is one of the boundaries of the linestring
                        //line should not be closed
                        //thus, create a line with the point as boundary
                        //num of points?
                        break;
                    case "LinearRing":
                        //not possible?
                        break;
                    case "MultiLineString":
                        //if the point touches one boundary and the multiline is not closed
                        break;
                    case "Polygon":
                        //if the point is one of the points of the polygons boundaries
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
                        Random coin = new Random();
                        Envelope touchesEnv = null;
                        Coordinate[] selectedBoundCoord = null;
                        Coordinate[] internal = new Coordinate[1];
                        int cases = 1;
                        while (touchesEnv == null) {
                            cases = coin.nextInt(3);
                            if (cases == 2) {
                                Coordinate[] lineCoord = lineString.getCoordinates();
                                //check this
                                internal[0] = lineCoord[coin.nextInt(lineCoord.length - 2) + 1];
                                touchesEnv = generateTouchesEnvelope(lineString, internal[0]);
                            } else {
                                Geometry bound = lineString.getBoundary();
                                Coordinate boundCoords[] = bound.getCoordinates();
                                selectedBoundCoord = new Coordinate[1];
                                selectedBoundCoord[0] = boundCoords[coin.nextInt(boundCoords.length)];
                                touchesEnv = generateTouchesEnvelope(lineString, selectedBoundCoord[0]);
                            }
                        }

                        int coordsToGenerate = lineString.getNumPoints() - 1;
                        if (coordsToGenerate < 2) {
                            coordsToGenerate = 2;
                        }
                        LineStringGenerator pg = new LineStringGenerator();
                        pg.setGeometryFactory(geometryFactory);
                        //default is ARC and need more points - check this
                        pg.setGenerationAlgorithm(LineStringGenerator.HORZ);
                        pg.setNumberPoints(coordsToGenerate);
                        Coordinate[] coords;

                        switch (cases) {
                            //BB
                            case 0:

                                pg.setBoundingBox(touchesEnv);
                                LineString pt_0 = (LineString) pg.create();
                                Coordinate[] generatedCoords_0 = pt_0.getCoordinates();

                                int order = coin.nextInt(2);
                                switch (order) {
                                    case 0:
                                        coords = concatenate(selectedBoundCoord, generatedCoords_0);
                                        this.returned = geometryFactory.createLineString(coords);
                                        break;
                                    case 1:
                                        coords = concatenate(generatedCoords_0, selectedBoundCoord);
                                        this.returned = geometryFactory.createLineString(coords);
                                        break;
                                }

                                break;
                            //BI
                            case 1:
                                pg.setBoundingBox(touchesEnv);

                                LineString pt_1 = (LineString) pg.create();
                                Coordinate[] generatedCoords_1 = pt_1.getCoordinates();

                                coords = concatenate(selectedBoundCoord, generatedCoords_1);
                                Collections.shuffle(Arrays.asList(coords));
                                this.returned = geometryFactory.createLineString(coords);
                                break;

                            //IB
                            case 2:
                                pg.setBoundingBox(touchesEnv);
                                LineString pt_2 = (LineString) pg.create();
                                Coordinate[] generatedCoords_2 = pt_2.getCoordinates();
                                int ord = coin.nextInt(2);
                                switch (ord) {
                                    case 0:
                                        coords = concatenate(internal, generatedCoords_2);
                                        this.returned = geometryFactory.createLineString(coords);
                                        break;
                                    case 1:
                                        coords = concatenate(generatedCoords_2, internal);
                                        this.returned = geometryFactory.createLineString(coords);
                                        break;
                                }
                                break;
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

    protected Envelope generateTouchesEnvelope(Geometry geo, Coordinate touchCoord) {
        Envelope touchesEnv = null;
        Envelope env = geo.getEnvelopeInternal();
        double coordX = touchCoord.x;
        double coordY = touchCoord.y;

        double minX = env.getMinX();
        double maxX = env.getMaxX();
        double minY = env.getMinY();
        double maxY = env.getMaxY();

        double minX_ = minX;
        double maxX_ = maxX;
        double minY_ = minY;
        double maxY_ = maxY;

        double leftBound = 0d;
        double rightBound = 0d;
        double upBound = 0d;
        double downBound = 0d;

        //if touchCoord has a x or y that is the minimum or maximm of all
        ArrayList<Integer> cases = new ArrayList<Integer>();
        //e.g. if coordX is the maximum, then we can keep the right boundary
        if (coordX == maxX && maxX < 180) { //right
            cases.add(0);
        }
        if (coordX == minX && minX > -180) { //left
            cases.add(1);
        }

        if (coordY == maxY && maxY < 90) { //up
            cases.add(2);
        }
        if (coordY == minY && minY > -90) { //down
            cases.add(3);
        }
        Random coin = new Random();
        if (cases.size() > 0) {
            int c = coin.nextInt(cases.size());
            switch (cases.get(c)) {
                case 0:
                    //right
                    //System.out.println("minX' > maxX");
                    leftBound = randomDouble(maxX, maxX + (180.0 - maxX) / 2);
                    rightBound = randomDouble(maxX, 180.0);
                    minX_ = leftBound;
                    maxX_ = rightBound;
                    break;
                case 1:
                    //left
                    //System.out.println("maxX' < minX");
                    leftBound = randomDouble(-180, -180 - ((-180 - minX) / 2));
                    rightBound = randomDouble(-180 - ((-180 - minX) / 2), minX);
                    maxX_ = leftBound;
                    minX_ = rightBound;
                    break;
                case 2:
                    //up
                    //System.out.println("minY' > maxY");
                    downBound = randomDouble(maxY, maxY + (90.0 - maxY) / 2);
                    upBound = randomDouble(maxY, 90.0);
                    minY_ = downBound;
                    maxY_ = upBound;
                    break;
                case 3:
                    //down
                    //System.out.println("maxY' < minY");
                    upBound = randomDouble(-90, -90 - ((-90 - minY) / 2));
                    downBound = randomDouble(-90 - ((-90 - minY) / 2), minY);
                    minY_ = upBound;
                    maxY_ = downBound;
                    break;

            }

            touchesEnv = new Envelope(minX_, maxX_, minY_, maxY_);

        } else {
            //here cut the boundary boxes

            List<Double> minXes = new ArrayList<Double>();
            List<Double> maxXes = new ArrayList<Double>();
            List<Double> minYes = new ArrayList<Double>();
            List<Double> maxYes = new ArrayList<Double>();

            //TODO : fix parts 
            ArrayList<Envelope> envelopes = cutGeometryEnvelope(geo, geo.getCoordinates().length - 1);

            for (Envelope envelope : envelopes) {
                minXes.add(envelope.getMinX());
                maxXes.add(envelope.getMaxX());
                minYes.add(envelope.getMinY());
                maxYes.add(envelope.getMaxY());
            }

            Collections.sort(minXes);
            Collections.sort(maxXes);
            Collections.sort(minYes);
            Collections.sort(maxYes);

            double e = 0.01;
            //down + right
//            System.out.println("down + right");
            minX_ = coordX + e;
            maxY_ = coordY - e;

            for (int j = 0; j < minXes.size(); j++) {
                if (coordX < minXes.get(j)) {
                    maxX_ = minXes.get(j) - e;
                    break;
                } else {
                    maxX_ = 180;
                }
            }

            for (int j = 0; j < maxYes.size(); j++) {
                if (coordY > maxYes.get(j)) {
                    minY_ = maxYes.get(j) + e;
                } else {
                    minY_ = -90;
                }
            }

            Envelope rightDownEnv = new Envelope(minX_, maxX_, minY_, maxY_);

            //up + right
//            System.out.println("up + right");
            minX_ = coordX + e;
            minY_ = coordY + e;

            for (int j = 0; j < minXes.size(); j++) {
                if (coordX < minXes.get(j)) {
                    maxX_ = minXes.get(j) - e;
                    break;
                } else {
                    maxX_ = 180;
                }
            }

            for (int j = 0; j < minYes.size(); j++) {
                if (coordY < minYes.get(j)) {
                    maxY_ = minYes.get(j) - e;
                    break;
                } else {
                    maxY_ = 90;
                }
            }

            Envelope rightUpEnv = new Envelope(minX_, maxX_, minY_, maxY_);

            //down + left
//            System.out.println("down + left");
            maxX_ = coordX - e;
            maxY_ = coordY - e;
            for (int j = 0; j < maxXes.size(); j++) {
                if (coordX > maxXes.get(j)) {
                    minX_ = maxXes.get(j) + e;
                } else {
                    minX_ = -180;
                }
            }

            for (int j = 0; j < maxYes.size(); j++) {
                if (coordY > maxYes.get(j)) {
                    minY_ = maxYes.get(j) + e;
                } else {
                    minY_ = -90;
                }
            }

            Envelope leftDownEnv = new Envelope(minX_, maxX_, minY_, maxY_);

            //up + left
//            System.out.println("up + left");
            maxX_ = coordX - e;
            minY_ = coordY + e;
            for (int j = 0; j < maxXes.size(); j++) {
                if (coordX > maxXes.get(j)) {
                    minX_ = maxXes.get(j) + e;
                } else {
                    minX_ = -180;
                }
            }

            for (int j = 0; j < minYes.size(); j++) {
                if (coordY < minYes.get(j)) {
                    maxY_ = minYes.get(j) - e;
                    break;
                } else {
                    maxY_ = 90;
                }
            }

            Envelope leftUpEnv = new Envelope(minX_, maxX_, minY_, maxY_);

            //upologizo ola ta env kai meta vlepo an kanoun intersect gia na ta aporipso
            //if only intersects with the touchCoord
            for (Envelope envelope : envelopes) {

                if (rightDownEnv != null && rightDownEnv.intersects(envelope)) {
//                        System.out.println("rightDownEnv intersects with " + coordinate + " ? " + rightDownEnv.intersects(coordinate));
                    rightDownEnv = null;
                }
                if (rightUpEnv != null && rightUpEnv.intersects(envelope)) {
//                        System.out.println("rightUpEnv intersects with " + coordinate + " ? " + rightUpEnv.intersects(coordinate));
                    rightUpEnv = null;
                }

                if (leftDownEnv != null && leftDownEnv.intersects(envelope)) {
//                        System.out.println("leftDownEnv intersects with " + coordinate + " ? " + leftDownEnv.intersects(coordinate));
                    leftDownEnv = null;
                }

                if (leftUpEnv != null && leftUpEnv.intersects(envelope)) {
//                        System.out.println("leftUpEnv intersects with " + coordinate + " ? " + leftUpEnv.intersects(coordinate));
                    leftUpEnv = null;
                }
            }

            //make move an arraylist again
            ArrayList<Envelope> move = new ArrayList<Envelope>();
            if (rightDownEnv != null) {
                move.add(rightDownEnv);
            }

            if (rightUpEnv != null) {
                move.add(rightUpEnv);
            }

            if (leftDownEnv != null) {
                move.add(leftDownEnv);
            }
            if (leftUpEnv != null) {
                move.add(leftUpEnv);
            }

            if (!move.isEmpty()) {
                int element = coin.nextInt(move.size());
                touchesEnv = move.get(element);

            }
        }

        return touchesEnv;
    }
}
