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
                        LineStringGenerator pg = new LineStringGenerator();
                        pg.setGeometryFactory(geometryFactory);
                        pg.setNumberPoints(lineString.getCoordinates().length);

                        Random rn = new Random();
                        int parts = rn.nextInt(1);

                        Envelope disjEnv = generateDisjointEnvelope(lineString, 3); //parts instead of 3

//                        System.out.println("null? " + disjEnv.isNull());
//                        System.out.println("disjEnv " + disjEnv);
//                        System.out.println("intersect? " + env.intersects(disjEnv));
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
        Envelope env = geo.getEnvelopeInternal();
        System.out.println("env " + env);
        ArrayList<Envelope> envelopes = cutGeometryEnvelope(geo, parts);

        System.out.println("envelopes " + envelopes);
        Map<Integer, Integer> envelopeCases;
        Random coin = new Random();

        double minX_ = envelopes.get(0).getMinX();
        double maxX_ = envelopes.get(0).getMaxX();
        double minY_ = envelopes.get(0).getMinY();
        double maxY_ = envelopes.get(0).getMaxY();

        boolean rollBack = true;
        while (rollBack) {
            for (int i = 0; i < envelopes.size(); i++) {
                double minX = envelopes.get(i).getMinX();
                double maxX = envelopes.get(i).getMaxX();
                double minY = envelopes.get(i).getMinY();
                double maxY = envelopes.get(i).getMaxY();

                minX_ = minX;
                maxX_ = maxX;
                minY_ = minY;
                maxY_ = maxY;

                double leftBound = 0d;
                double rightBound = 0d;
                double upBound = 0d;
                double downBound = 0d;
                int cases = coin.nextInt(4);
                System.out.println("case " + cases);
                switch (cases) {
                    case 0:
                        //right
                        if (maxX < 180) { //check if on boundary
                            System.out.println("minX' > maxX");
                            leftBound = randomDouble(maxX, maxX + (180.0 - maxX) / 2);
                            rightBound = randomDouble(maxX, 180.0);
                            minX_ = leftBound;
                            maxX_ = rightBound;
                        }
                        else {
                            i--;
                        }
                        break;
                    case 1:
                        //left
                        if (minX > -180) { //check if on boundary
                            System.out.println("maxX' < minX");
                            leftBound = randomDouble(-180, ((-180 - minX) / 2));
                            rightBound = randomDouble(((-180 - minX) / 2), minX);
                            maxX_ = leftBound;
                            minX_ = rightBound;
                        }
                        else {
                            i--;
                        }
                        break;
                    case 2:
                        //up
                        if (maxY < 90) { //check if on boundary
                            System.out.println("minY' > maxY");
                            downBound = randomDouble(maxY, maxY + (90.0 - maxY) / 2);
                            upBound = randomDouble(maxY, 90.0);
                            minY_ = downBound;
                            maxY_ = upBound;
                        }
                        else {
                            i--;
                        }
                        break;
                    case 3:
                        //down
                        if (minY > -90) {
                            System.out.println("maxY' < minY"); //check if on boundary
                            upBound = randomDouble(-90, ((-90 - minY) / 2));
                            downBound = randomDouble(((-90 - minY) / 2), minY);
                            minY_ = upBound;
                            maxY_ = downBound;
                        }
                        else {
                            i--;
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
            System.out.println("disjointEnv " + disjointEnv);
//            also check if the return envelope is null ? 
        }

        return disjointEnv;
    }

    protected double randomDouble(double start, double end) {
        double random = new Random().nextDouble();
        double randDouble = start + (random * (end - start));
        return randDouble;
    }
}
