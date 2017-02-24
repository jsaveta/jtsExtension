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
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

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

                        Envelope env = lineString.getEnvelopeInternal();

                        double minX = env.getMinX();
                        double maxX = env.getMaxX();
                        double minY = env.getMinY();
                        double maxY = env.getMaxY();

                        System.out.println("env " + env);

                        LineStringGenerator pg = new LineStringGenerator();
                        pg.setGeometryFactory(geometryFactory);
                        pg.setNumberPoints(lineString.getCoordinates().length);

                        Random rn = new Random();
                        int parts = rn.nextInt(1);

                        Envelope disjEnv = generateDisjointEnvelope(lineString, 3);

//                        Random rn = new Random();
//                        int side = rn.nextInt(1); //0 for left side, 1 for right side
//                        System.out.println("side " + side);
//
//                        // na min einai akrivos sta min kai max
//                        //creates line on the left
//                        if (side == 0) {
//                            if (minX >= 0 && minY >= 0) {
//                                disjEnv = new Envelope(minX, 0, minY, 0);
//                            } else if (minX >= 0 && minY < 0) {
//                                disjEnv = new Envelope(minX, 0, minY, -90);
//                            } else if (minX < 0 && minY >= 0) {
//                                disjEnv = new Envelope(minX, -90, minY, 0);
//                            } else if (minX < 0 && minY < 0) {
//                                disjEnv = new Envelope(minX, -180, minY, -90);
//                            }
//                        }
//
//                        //creates line on the right
//                        if (side == 1) {
//                            if (maxX >= 0 && maxY >= 0) {
//                                disjEnv = new Envelope(maxX, 180, maxY, 90);
//                            } else if (maxX >= 0 && maxY < 0) {
//                                disjEnv = new Envelope(maxX, 180, maxY, -90);
//                            } else if (maxX < 0 && maxY >= 0) {
//                                disjEnv = new Envelope(maxX, -180, maxY, 90);
//                            } else if (maxX < 0 && maxY < 0) {
//                                disjEnv = new Envelope(maxX, -180, maxY, -90);
//                            }
//                        }
                        System.out.println("null? " + disjEnv.isNull());
                        System.out.println("disjEnv " + disjEnv);
                        System.out.println("intersect? " + env.intersects(disjEnv));

                        if (!disjEnv.isNull()) {
                            pg.setBoundingBox(disjEnv);

                            LineString pt = (LineString) pg.create();
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

        ArrayList<Envelope> envelopes = cutGeometryEnvelope(geo, parts);        
        double minX_ = -180d;
        double maxX_ = 180d;
        double minY_ = -90;
        double maxY_ = 90d;

        double maxXSoFar = envelopes.get(0).getMinX();
        double minXSoFar = envelopes.get(0).getMaxX();
        double maxYSoFar = envelopes.get(0).getMinY();
        double minYSoFar = envelopes.get(0).getMaxY();

        double minX = envelopes.get(0).getMinX();
        double maxX = envelopes.get(0).getMaxX();
        double minY = envelopes.get(0).getMinY();
        double maxY = envelopes.get(0).getMaxY();
        
        Map<Integer, Integer> envelopeCases;
        Random coin = new Random();
        

        boolean rollBack = true;
        while (rollBack) {
            envelopeCases = new HashMap<Integer, Integer>();
            for (int i = 0; i < envelopes.size(); i++) {
                envelopeCases.put(i, coin.nextInt(4));
            }
            //System.out.println("map " + envelopeCases.toString());
            for (int i = 0; i < envelopes.size(); i++) {

                int cases = envelopeCases.get(i);
//                System.out.println("case " + cases);
                switch (cases) {
                    case 0:
                        //right
                        System.out.println("minX' > maxX");
                        if (maxXSoFar < maxX) {
                            double leftBound = randomDouble(maxX, maxX + (180.0 - maxX) / 2);
                            double rightBound = randomDouble(maxX, 180.0);
                            minX_ = randomDouble(leftBound, rightBound);
                            minX = minX_;
                            maxXSoFar = maxX;
                        }
                        break;
                    case 1:
                        //left
                        System.out.println("maxX' < minX");
                        if (minXSoFar > minX) {
                            double leftBound = randomDouble(-180, ((-180 - minX) / 2));
                            double rightBound = randomDouble(((-180 - minX) / 2), minX);
                            maxX_ = randomDouble(leftBound, rightBound);
                            maxX = maxX_;
                            minXSoFar = minX;
                        }
                        break;
                    case 2:
                        //up
                        System.out.println("minY' > maxY");
                        if (maxYSoFar < maxY) {
                            double leftBound = randomDouble(maxY, maxY + (90.0 - maxY) / 2);
                            double rightBound = randomDouble(maxY, 90.0);
                            minY_ = randomDouble(leftBound, rightBound);
                            minY = minY_;
                            maxYSoFar = maxY;
                        }
                        break;
                    case 3:
                        //down
                        System.out.println("maxY' < minY");
                        if (minYSoFar > minY) {
                            double leftBound = randomDouble(-90, ((-90 - minY) / 2));
                            double rightBound = randomDouble(((-90 - minY) / 2), minY);
                            maxY_ = randomDouble(leftBound, rightBound);
                            maxY = maxY_;
                            minYSoFar = minY;
                        }
                        break;

                }
                System.out.println("minX_ " + minX_);
                System.out.println("maxX_ " + maxX_);
                System.out.println("minY_ " + minY_);
                System.out.println("maxY_ " + maxY_);
            }

            if (minX_ <= maxX_ && minY_ <= maxY_) {
                rollBack = false;
                disjointEnv = new Envelope(minX_, maxX_, minY_, maxY_);
            }
            //also check if the return envelope is null ? 
        }
        return disjointEnv;
    }

    protected double randomDouble(double start, double end) {
        double random = new Random().nextDouble();
        double randDouble = start + (random * (end - start));
        return randDouble;
    }
}
