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
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jsaveta
 */
public class CreateTouchesGeometryObjectTest {

    public CreateTouchesGeometryObjectTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of generateGeometry method, of class CreateTouchesGeometryObject.
     */
    @Test
    public void testGenerateGeometry() throws ParseException {
        System.out.println("generateGeometry");
        GeometryFactory geometryFactory = new GeometryFactory();
        WKTReader reader = new WKTReader(geometryFactory);

        //test EQUALS for a given POINT
        Coordinate coord = new Coordinate(-5.998535, 6.521366);
        Coordinate coord1 = new Coordinate(18.918457, 3.501085);
        //point
        Point point = geometryFactory.createPoint(coord);
        Point point1 = geometryFactory.createPoint(coord1);

        LineString line = (LineString) reader.read("LINESTRING( 18.918457 3.501085, 19.045658 3.531829, 19.164791 3.564040, 19.281864 3.612011, -5.998535 6.521366)");
        LineString line1 = (LineString) reader.read("LINESTRING( 19.045658 3.531829, 30.871582 2.316111)");
        
        LinearRing ring = (LinearRing) reader.read("LINEARRING(-5.998535 6.521366, 18.918457 3.501085, 19.045658 3.531829, 19.164791 3.564040, 19.281864 3.612011, -5.998535 6.521366)");
        MultiLineString multiline = (MultiLineString) reader.read("MULTILINESTRING((-5.998535 6.521366, 18.918457 3.501085), (18.918457 3.501085, 19.045658 3.531829), (19.045658 3.531829, 19.164791 3.564040),  (19.164791 3.564040, 19.281864 3.612011))");
        ring = (LinearRing) reader.read("LINEARRING(-5.998535 6.521366, 18.918457 3.501085, 19.045658 3.531829, -5.998535 6.521366)");
        Polygon poly = geometryFactory.createPolygon(ring, null);
        
        
        
        assertTrue(point.touches(line));
//        assertTrue(point1.touches(ring));
        assertTrue(point.touches(multiline));
        assertTrue(point.touches(poly));
        assertTrue(point1.touches(poly));
        assertTrue(line1.touches(line));

    }

}
