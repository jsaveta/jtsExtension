/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vividsolutions.jts.geom.create;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import static junit.framework.Assert.assertTrue;
import junit.framework.TestCase;

/**
 *
 * @author jsaveta
 */
public class CreateTouchesGeometryObjectTest extends TestCase {

    public CreateTouchesGeometryObjectTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of generateGeometry method, of class CreateTouchesGeometryObject.
     */
    public void testGenerateGeometry() throws ParseException {
        System.out.println("generateGeometry");
        GeometryFactory geometryFactory = new GeometryFactory();
        WKTReader reader = new WKTReader(geometryFactory);

        //test EQUALS for a given POINT
//        Coordinate coord = new Coordinate(-5.998535, 6.521366);
//        Coordinate coord1 = new Coordinate(18.918457, 3.501085);
//        point
//        Point point = geometryFactory.createPoint(coord);
//        Point point1 = geometryFactory.createPoint(coord1);
//        LineString line = (LineString) reader.read("LINESTRING( 18.918457 3.501085, 19.045658 3.531829, 19.164791 3.564040, 19.281864 3.612011, -5.998535 6.521366)");
        LineString line = (LineString) reader.read("LINESTRING( 19.045658 3.531829, 30.871582 2.316111)");
//
//        LinearRing ring = (LinearRing) reader.read("LINEARRING(-5.998535 6.521366, 18.918457 3.501085, 19.045658 3.531829, 19.164791 3.564040, 19.281864 3.612011, -5.998535 6.521366)");
//        MultiLineString multiline = (MultiLineString) reader.read("MULTILINESTRING((-5.998535 6.521366, 18.918457 3.501085), (18.918457 3.501085, 19.045658 3.531829), (19.045658 3.531829, 19.164791 3.564040),  (19.164791 3.564040, 19.281864 3.612011))");
//        ring = (LinearRing) reader.read("LINEARRING(-5.998535 6.521366, 18.918457 3.501085, 19.045658 3.531829, -5.998535 6.521366)");
//        Polygon poly = geometryFactory.createPolygon(ring, null);
//
//        assertTrue(point.touches(line));
////        assertTrue(point1.touches(ring));
//        assertTrue(point.touches(multiline));
//        assertTrue(point.touches(poly));
//        assertTrue(point1.touches(poly));
//        assertTrue(line1.touches(line));

//          LineString line = (LineString) reader.read("LINESTRING (-1 1, 2 2, -3 3, 3 4)");
//            LineString line = (LineString)  reader.read("LINESTRING (20.577798195251386 45.15289614814815, 1.0260206358829747 11.288224037037036, -18.525756923485442 45.15289614814815, -73.68333651101867 60, -99.66409862455183 15, -180 60, -19.328197249103656 0, -19.328197249103656 0.5782753588639962, -19.328197249103656 0.5835141666666666, -10.998535 0.5835141666666666, -12.009211183602881 2.3340566666666667, -5.998535 6.521366, 18.918457 3.501085, 19.045658 3.531829, 19.164791 3.56404, 19.281864 3.612011, 27.168615345948126 13.188032666666668, 24.281864 8.188032666666667, 36.819896694713364 13.188032666666668)");

//            LineString line1 = (LineString) reader.read("LINESTRING ( -180 3.5074674033245206, 13.044388741869454 3.5074674033245206, 13.11371727221631 3.5074674033245206, 14.663017119019297 3.5074674033245206, 16.373355462453823 3.5074674033245206, 16.805309909381798 3.5074674033245206, 17.630603046619015 3.5074674033245206, 17.715722280443416 3.5074674033245206, 17.80088971430383 3.5074674033245206, 17.889626622606645 3.5074674033245206, 18.72852756739102 3.5074674033245206, 18.911799940254742 3.5074674033245206, 18.914769390288274 3.5074674033245206, 18.918301366259847 3.5074674033245206, 18.91835502766744 3.5074674033245206, 18.918405671754456 3.5074674033245206, 18.91842597185658 3.5074674033245206, 18.91845699999999 3.5074674033245206, 18.918457 3.501085)");
            
        for (int i = 0; i < 10000; i++) {

            CreateTouchesGeometryObject instanceL1 = new CreateTouchesGeometryObject(line, GeometryType.GeometryTypes.LineString);
            Geometry resultL1 = instanceL1.generateGeometry();
//            System.out.println("i: " + i);
            System.out.println("given: " + line);
            System.out.println("result: " + resultL1);
            assertTrue(line.touches(resultL1));
            assertTrue(resultL1.touches(line));
          
        }
    }

}
