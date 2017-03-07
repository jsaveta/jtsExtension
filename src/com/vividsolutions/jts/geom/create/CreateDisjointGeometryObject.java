/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vividsolutions.jts.geom.create;

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
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author jsaveta
 */
public class CreateDisjointGeometryObject extends GeometryType {

    protected Geometry given;
    protected Geometry returned;
    protected int chunk;
    protected Class<?> returnedGeometry;

    public CreateDisjointGeometryObject(Geometry givenGeometry, GeometryType.GeometryTypes geometry) {
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

                        LineStringGenerator pg = new LineStringGenerator();
                        pg.setGeometryFactory(geometryFactory);

                        //check Too few points for Arc exception
                        //check if num of points more than ~ 350                         
                        int coordsToGenerate = lineString.getCoordinates().length;
                        if (coordsToGenerate < 4) {
                            coordsToGenerate = 4;
                        }
                        if (coordsToGenerate > 350) {
                            coordsToGenerate = 350;
                        }

                        pg.setNumberPoints(coordsToGenerate);

                        //or at least for horx etc
                        Random rn = new Random();
                        int parts = rn.nextInt(1);
                        Envelope disjEnv = generateDisjointEnvelope(lineString, 3); //parts instead of 3
                        if (!disjEnv.isNull()) {
                            pg.setBoundingBox(disjEnv);
                            LineString pt = (LineString) pg.create();
//                            if (pt == null) {
//                                throw new NullPointerException("NullPointerException caught");
//                            }
                            this.returned = pt;
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

    protected Envelope generateDisjointEnvelope(Geometry geo, int parts) {
        Envelope disjointEnv = null;
        Envelope env = geo.getEnvelopeInternal();
        System.out.println("env " + env);
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

        Random coin = new Random();

        //if touchCoord has a x or y that is the minimum or maximum of all
        ArrayList<Integer> moves = new ArrayList<Integer>();
        //e.g. if coordX is the maximum, then we can keep the right boundary
        if (maxX < 180) { //right
            moves.add(0);
        }
        if (minX > -180) { //left
            moves.add(1);
        }

        if (maxY < 90) { //up
            moves.add(2);
        }
        if (minY > -90) { //down
            moves.add(3);
        }
        if (moves.size() > 0) {
            System.out.println("IF");
            int c = coin.nextInt(moves.size());
            switch (moves.get(c)) {
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

            disjointEnv = new Envelope(minX_, maxX_, minY_, maxY_);

        } else { //if moving just up, down, left or right is not possible
        System.out.println("ELSE");
        ArrayList<Envelope> envelopes = cutGeometryEnvelope(geo, parts);

//        System.out.println("envelopes " + envelopes);

        List<Double> minXes = new ArrayList<Double>();
        List<Double> maxXes = new ArrayList<Double>();
        List<Double> minYes = new ArrayList<Double>();
        List<Double> maxYes = new ArrayList<Double>();

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

        for (int i = 0; i < envelopes.size(); i++) {
            System.out.println("envelopes.get(i) " +envelopes.get(i));
            minX = envelopes.get(i).getMinX();
            maxX = envelopes.get(i).getMaxX();
            minY = envelopes.get(i).getMinY();
            maxY = envelopes.get(i).getMaxY();

            int cases = coin.nextInt(4);
                System.out.println("cases " + cases);
            switch (cases) {
                case 0:
                    //right + down
                    minX_ = maxX;
                    maxY_ = minY;

                    minY_ = -90;
                    maxX_ = 180;

                    for (int j = 0; j < minYes.size(); j++) {
                        if (maxY > minYes.get(j)) {
                            maxY_ = minYes.get(j);
                            break;
                        }
                    }
                    for (int j = 0; j < maxXes.size(); j++) {
                        if (minX > maxXes.get(j)) {
                            minX_ = maxXes.get(j);
                        }
                    }

                    if (maxX < 180) { //check if on boundary
                        for (int j = 0; j < minXes.size(); j++) {
                            if (maxX < minXes.get(j)) {
                                maxX_ = minXes.get(j);
                                break;
                            }
                        }
                    }
                    if (minY > -90) { //check if on boundary
                        for (int j = 0; j < maxYes.size(); j++) {
                            if (minY > maxYes.get(j)) {
                                minY_ = maxYes.get(j);
                            }
                        }
                    }

                    break;
                case 1:
                    //up + right
                    minX_ = maxX;
                    minY_ = maxY;

                    maxX_ = 180;
                    maxY_ = 90;

                    for (int j = 0; j < maxXes.size(); j++) {
                        if (minX > maxXes.get(j)) {
                            minX_ = maxXes.get(j);
                        }
                    }

                    for (int j = 0; j < maxYes.size(); j++) {
                        if (minY < maxYes.get(j)) {
                            minY_ = maxYes.get(j);
                        }
                    }

                    if (maxX < 180) { //check if on boundary
                        for (int j = 0; j < minXes.size(); j++) {
                            if (maxX < minXes.get(j)) {
                                maxX_ = minXes.get(j);
                                break;
                            }
                        }
                    }
                    if (maxY < 90) { //check if on boundary
                        for (int j = 0; j < minYes.size(); j++) {
                            if (maxY < minYes.get(j)) {
                                maxY_ = minYes.get(j);
                                break;
                            }
                        }
                    }
                    break;

                case 2:
                    //down + left
                    maxX_ = minX;
                    maxY_ = minY;

                    minX_ = -180;
                    minY_ = -90;

                    for (int j = 0; j < minYes.size(); j++) {
                        if (maxY > minYes.get(j)) {
                            maxY_ = minYes.get(j);
                            break;
                        }
                    }

                    for (int j = 0; j < minXes.size(); j++) {
                        if (maxX > minXes.get(j)) {
                            maxX_ = minXes.get(j);
                            break;
                        }
                    }

                    if (minX > -180) { //check if on boundary
                        for (int j = 0; j < maxXes.size(); j++) {
                            if (minX > maxXes.get(j)) {
                                minX_ = maxXes.get(j);
                            }
                        }
                    }
                    if (minY > -90) { //check if on boundary
                        for (int j = 0; j < maxYes.size(); j++) {
                            if (minY > maxYes.get(j)) {
                                minY_ = maxYes.get(j);
                            }
                        }
                    }
                    break;

                case 3:
                    //up + left
                    maxX_ = minX;
                    minY_ = maxY;

                    minX_ = -180;
                    maxY_ = 90;

                    for (int j = 0; j < minXes.size(); j++) {
                        if (maxX > minXes.get(j)) {
                            maxX_ = minXes.get(j);
                            break;
                        }
                    }
                    for (int j = 0; j < maxYes.size(); j++) {
                        if (minY < maxYes.get(j)) {
                            minY_ = maxYes.get(j);
                        }
                    }
                    if (minX > -180) { //check if on boundary
                        for (int j = 0; j < maxXes.size(); j++) {
                            if (minX > maxXes.get(j)) {
                                minX_ = maxXes.get(j);
                            }
                        }
                    }
                    if (maxY < 90) { //check if on boundary
                        for (int j = 0; j < minYes.size(); j++) {
                            if (maxY < minYes.get(j)) {
                                maxY_ = minYes.get(j);
                                break;
                            }
                        }
                    }
                    break;

            }
        }
        disjointEnv = new Envelope(minX_, maxX_, minY_, maxY_);
        }
        System.out.println("disjointEnv " + disjointEnv);
        return disjointEnv;
    }
}
