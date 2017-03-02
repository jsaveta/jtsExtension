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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
                        Geometry bound = lineString.getBoundary();
                        Coordinate boundCoords[] = bound.getCoordinates();

                        System.out.println("boundCoords " + Arrays.toString(boundCoords));

                        //keep more than one boundaries?
                        //ta shuffle xalane auto pou dinetai 
                        Coordinate[] selectedBoundCoord = new Coordinate[1];

                        selectedBoundCoord[0] = boundCoords[coin.nextInt(boundCoords.length)];
                        System.out.println("selectedBoundCoord[0] " + selectedBoundCoord[0]);

                        int coordsToGenerate = lineString.getNumPoints() - 1; //boundCoords.length;
                        if (coordsToGenerate < 2) {
                            coordsToGenerate = 2;
                        }
                        LineStringGenerator pg = new LineStringGenerator();
                        pg.setGeometryFactory(geometryFactory);
                        //default is ARC and need more points - check this
                        pg.setGenerationAlgorithm(LineStringGenerator.HORZ);
                        pg.setNumberPoints(coordsToGenerate);
                        Coordinate[] coords;

                        int cases = 2;//coin.nextInt(2); //coin.nextInt(3);
                        System.out.println("BB BI IB " + cases);
                        switch (cases) {
                            //BB
                            case 0:

                                pg.setBoundingBox(generateTouchesEnvelope(lineString, selectedBoundCoord[0]));
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
                                pg.setBoundingBox(generateTouchesEnvelope(lineString, selectedBoundCoord[0]));

                                LineString pt_1 = (LineString) pg.create();
                                Coordinate[] generatedCoords_1 = pt_1.getCoordinates();

                                coords = concatenate(selectedBoundCoord, generatedCoords_1);
                                Collections.shuffle(Arrays.asList(coords));
                                this.returned = geometryFactory.createLineString(coords);
                                break;

                            //IB
                            case 2:
                                Coordinate[] lineCoord = lineString.getCoordinates();
                                Coordinate[] internal = new Coordinate[1];
//                                Coordinate extraCoord = null;
                                internal[0] = lineCoord[coin.nextInt(lineCoord.length)];
                                System.out.println("internal[0] " + internal[0]);
                                Envelope touches = generateTouchesEnvelope(lineString, internal[0]);
                                pg.setBoundingBox(touches);

                                LineString pt_2 = (LineString) pg.create();
                                Coordinate[] generatedCoords_2 = pt_2.getCoordinates();
//                                if (side == 0) { //rightDownEnv
////                                    extraCoord = new Coordinate(touches.getMaxX(), touches.getMinY());
//                                    extraCoord = new Coordinate(internal[0].x + 0.005, internal[0].y - 0.005);
//                                }
//                                if (side == 1) { //rightUpEnv
////                                    extraCoord = new Coordinate(touches.getMaxX(), touches.getMaxY());
//                                    extraCoord = new Coordinate(internal[0].x + 0.005, internal[0].y + 0.005);
//                                }
//                                if (side == 2) { //leftDownEnv
////                                    extraCoord = new Coordinate(touches.getMinX(), touches.getMinY());
//                                    extraCoord = new Coordinate(internal[0].x - 0.005, internal[0].y - 0.005);
//                                }
//                                if (side == 3) { //leftUpEnv
////                                    extraCoord = new Coordinate(touches.getMinX(), touches.getMaxY());
//                                    extraCoord = new Coordinate(internal[0].x - 0.005, internal[0].y + 0.005);
//                                }
//                                System.out.println("extraCoord " + extraCoord);
                                int ord = 1;//coin.nextInt(2);
                                switch (ord) {
                                    case 0:
                                        //internal[0] = lineCoord[coin.nextInt(lineCoord.length)];
//                                        internal[1] = extraCoord;

                                        coords = concatenate(internal, generatedCoords_2);
                                        this.returned = geometryFactory.createLineString(coords);
                                        break;
                                    case 1:
//                                        internal[1] = internal[0];
//                                        internal[0] = extraCoord;
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

//        boolean rollBack = true;
//        while (rollBack) {
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
        if (coordX == maxX) { //right
            cases.add(0);
        }
        if (coordX == minX) { //left
            cases.add(1);
        }

        if (coordY == maxY) { //up
            cases.add(2);
        }
        if (coordY == minY) { //down
            cases.add(3);
        }

        Random coin = new Random();
        if (cases.size() > 0) {
            int c = coin.nextInt(cases.size());
            System.out.println("cases.get(c) " + cases.get(c));
            switch (cases.get(c)) {
                case 0:
                    //right
                    if (maxX < 180) { //check if on boundary
                        //System.out.println("minX' > maxX");
                        leftBound = randomDouble(maxX, maxX + (180.0 - maxX) / 2);
                        rightBound = randomDouble(maxX, 180.0);
                        minX_ = leftBound;
                        maxX_ = rightBound;
                    }
                    break;
                case 1:
                    //left
                    if (minX > -180) { //check if on boundary
                        //System.out.println("maxX' < minX");
                        leftBound = randomDouble(-180, ((-180 - minX) / 2));
                        rightBound = randomDouble(((-180 - minX) / 2), minX);
                        maxX_ = leftBound;
                        minX_ = rightBound;
                    }
                    break;
                case 2:
                    //up
                    if (maxY < 90) { //check if on boundary
                        //System.out.println("minY' > maxY");
                        downBound = randomDouble(maxY, maxY + (90.0 - maxY) / 2);
                        upBound = randomDouble(maxY, 90.0);
                        minY_ = downBound;
                        maxY_ = upBound;
                    }
                    break;
                case 3:
                    //down
                    if (minY > -90) { //check if on boundary
                        //System.out.println("maxY' < minY");
                        upBound = randomDouble(-90, ((-90 - minY) / 2));
                        downBound = randomDouble(((-90 - minY) / 2), minY);
                        minY_ = upBound;
                        maxY_ = downBound;
                    }
                    break;

            }

//                if (minX_ <= maxX_ && minY_ <= maxY_) {
//                    rollBack = false;
            touchesEnv = new Envelope(minX_, maxX_, minY_, maxY_);
//                }
        } else {
            //here cut the boundary boxes
            System.out.println("den eimai akri");
            //kopse prota 2 meta 3 ktl mexri na ginei akri

            List<Double> minXes = new ArrayList<Double>();
            List<Double> maxXes = new ArrayList<Double>();
            List<Double> minYes = new ArrayList<Double>();
            List<Double> maxYes = new ArrayList<Double>();

            ArrayList<Envelope> envelopes = cutGeometryEnvelope(geo, geo.getCoordinates().length - 1); //fix parts 

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

            System.out.println("minXes " + minXes);
            System.out.println("maxXes " + maxXes);
            System.out.println("minYes " + minYes);
            System.out.println("maxYes " + maxYes);

            //down + right
            minX_ = coordX;

            for (int j = 0; j < minXes.size(); j++) {
                if (coordX < minXes.get(j)) {
                    maxX_ = minXes.get(j);
                    break;
                } else {
                    maxX_ = 180;
                }
            }

            for (int j = 0; j < maxYes.size(); j++) {
                if (coordY > maxYes.get(j)) {
                    minY_ = maxYes.get(j);
                } else {
                    minY_ = -90;
                }
            }
            maxY_ = coordY;

            Envelope rightDownEnv = new Envelope(minX_, maxX_, minY_, maxY_);

            //up + right
            minX_ = coordX;
            minY_ = coordY;

            for (int j = 0; j < minXes.size(); j++) {
                if (coordX < minXes.get(j)) {
                    maxX_ = minXes.get(j);
                    break;
                } else {
                    maxX_ = 180;
                }
            }

            for (int j = 0; j < minYes.size(); j++) {
                if (coordY < minYes.get(j)) {
                    maxY_ = minYes.get(j);
                    break;
                } else {
                    maxY_ = 90;
                }
            }

            Envelope rightUpEnv = new Envelope(minX_, maxX_, minY_, maxY_);

            //down + left
            maxX_ = coordX;
            maxY_ = coordY;
            for (int j = 0; j < maxXes.size(); j++) {
                if (coordX > maxXes.get(j)) {
                    minX_ = maxXes.get(j);
                } else {
                    minX_ = -180;
                }
            }

            for (int j = 0; j < maxYes.size(); j++) {
                if (coordY > maxYes.get(j)) {
                    minY_ = maxYes.get(j);
                } else {
                    minY_ = -90;
                }
            }

            Envelope leftDownEnv = new Envelope(minX_, maxX_, minY_, maxY_);

            //up + left
            maxX_ = coordX;
            minY_ = coordY;
            for (int j = 0; j < maxXes.size(); j++) {
                if (coordX > maxXes.get(j)) {
                    minX_ = maxXes.get(j);
                } else {
                    minX_ = -180;
                }
            }

            for (int j = 0; j < minYes.size(); j++) {
                if (coordY < minYes.get(j)) {
                    maxY_ = minYes.get(j);
                    break;
                } else {
                    maxY_ = 90;
                }
            }

            Envelope leftUpEnv = new Envelope(minX_, maxX_, minY_, maxY_);

//            System.out.println("rightDownEnv " + rightDownEnv);
//            System.out.println("rightUpEnv " + rightUpEnv);
//            System.out.println("leftDownEnv " + leftDownEnv);
//            System.out.println("leftUpEnv " + leftUpEnv);
//
//            for (Envelope envelope : envelopes) {
//                System.out.println("envelope " + envelope);
//                System.out.println("inters rightDownEnv " + rightDownEnv.intersects(envelope));
//                System.out.println("inters rightUpEnv " + rightUpEnv.intersects(envelope));
//                System.out.println("inters leftDownEnv " + leftDownEnv.intersects(envelope));
//                System.out.println("inters leftUpEnv " + leftUpEnv.intersects(envelope));
//
//            }
            //if only intersects with the touchCoord
            for (Coordinate coordinate : geo.getCoordinates()) {

                if (coordinate != touchCoord) {
                    if (rightDownEnv != null && rightDownEnv.intersects(coordinate)) {
//                        System.out.println("rightDownEnv intersects with " + coordinate + " ? " + rightDownEnv.intersects(coordinate));
                        rightDownEnv = null;
                    }
                    if (rightUpEnv != null && rightUpEnv.intersects(coordinate)) {
//                        System.out.println("rightUpEnv intersects with " + coordinate + " ? " + rightUpEnv.intersects(coordinate));
                        rightUpEnv = null;
                    }

                    if (leftDownEnv != null && leftDownEnv.intersects(coordinate)) {
//                        System.out.println("leftDownEnv intersects with " + coordinate + " ? " + leftDownEnv.intersects(coordinate));
                        leftDownEnv = null;
                    }

                    if (leftUpEnv != null && leftUpEnv.intersects(coordinate)) {
//                        System.out.println("leftUpEnv intersects with " + coordinate + " ? " + leftUpEnv.intersects(coordinate));
                        leftUpEnv = null;
                    }
                }
            }

            Map<Integer, Envelope> move = new HashMap<Integer, Envelope>();
            if (rightDownEnv != null) {
                move.put(0, rightDownEnv);
            }

            if (rightUpEnv != null) {
                move.put(1, rightUpEnv);
            }

            if (leftDownEnv != null) {
                move.put(2, leftDownEnv);
            }
            if (leftUpEnv != null) {
                move.put(3, leftUpEnv);
            }

            if (!move.isEmpty()) {

                List<Integer> keysAsArray = new ArrayList<Integer>(move.keySet());
                int element = coin.nextInt(keysAsArray.size());
                side = keysAsArray.get(element);
                touchesEnv = move.get(side);

//                int t = coin.nextInt(move.size());
//                touchesEnv = move.get(t);
//                side = 
            }

            System.out.println("move " + move.toString());
        }
//        }

        System.out.println("touchesEnv " + touchesEnv);
        return touchesEnv;
    }
}
