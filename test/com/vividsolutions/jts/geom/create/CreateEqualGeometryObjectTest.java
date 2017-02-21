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
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.geom.create.GeometryType.GeometryTypes;
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
public class CreateEqualGeometryObjectTest {

    public CreateEqualGeometryObjectTest() {
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
     * Test of generateGeometry method, of class CreateEqualGeometryObject.
     */
    @Test
    public void testGenerateGeometry() throws ParseException {
        System.out.println("generateGeometry");
        GeometryFactory geometryFactory = new GeometryFactory();
        WKTReader reader = new WKTReader(geometryFactory);

        //test EQUALS for a given POINT
        Coordinate coord = new Coordinate(-5.998535, 6.521366);
        //point
        Point point = geometryFactory.createPoint(coord);

        //point -> point
        CreateEqualGeometryObject p1 = new CreateEqualGeometryObject(point, GeometryTypes.Point);
        Geometry expResultP1 = reader.read("POINT (-5.998535 6.521366)");
        Geometry resultP1 = p1.generateGeometry();
        System.out.println("expResult Point: " + expResultP1);
        System.out.println("result Point: " + resultP1);
        assertEquals(resultP1, expResultP1);

        System.out.println("Is equals point -> point: " + point.equalsTopo(resultP1));
        assertTrue(point.equalsTopo(resultP1));
        
        

        //point -> multipoint
        CreateEqualGeometryObject instanceMP1 = new CreateEqualGeometryObject(point, GeometryTypes.MultiPoint);
        Geometry expResultMP1 = reader.read("MULTIPOINT ((-5.998535 6.521366))");
        Geometry resultMP1 = instanceMP1.generateGeometry();
        System.out.println("expResult MultiPoint: " + expResultMP1);
        System.out.println("result MultiPoint: " + resultMP1);
        assertEquals(resultMP1, expResultMP1);

        System.out.println("Is equals point -> multipoint: " + point.equalsTopo(resultMP1));
        assertTrue(point.equalsTopo(resultMP1));
        

        //point -> GeometryCollection
//        CreateEqualGeometryObject instanceGeometryCollection = new CreateEqualGeometryObject(point, GeometryTypes.GeometryCollection);
//        Geometry expResultGeometryCollection = reader.read("GEOMETRYCOLLECTION (POINT (-5.998535 6.521366))");
//        Geometry resultGeometryCollection = instanceGeometryCollection.generateGeometry();
//        System.out.println("expResult GeometryCollection: " + expResultGeometryCollection);
//        System.out.println("result GeometryCollection: " + resultGeometryCollection);     
//        assertEquals(resultGeometryCollection, expResultGeometryCollection);
//        
//        System.out.println("Is equals: "+ point.equalsTopo(resultGeometryCollection)); 
//        assertTrue(point.equalsTopo(resultGeometryCollection));
        //test EQUALS for a given MULTIPOINT
        //multipoint with one point
        Point[] pointArray = new Point[1];
        pointArray[0] = point;
        MultiPoint multipointOne = geometryFactory.createMultiPoint(pointArray);

        //multipoint (with one point) -> point
        CreateEqualGeometryObject instanceP2 = new CreateEqualGeometryObject(multipointOne, GeometryTypes.Point);
        Geometry expResultP2 = reader.read("POINT (-5.998535 6.521366)");
        Geometry resultP2 = instanceP2.generateGeometry();
        System.out.println("expResult Point: " + expResultP2);
        System.out.println("result Point: " + resultP2);
        assertEquals(resultP2, expResultP2);

        System.out.println("Is equals multipoint (with one point) -> point: " + multipointOne.equalsTopo(resultP2));
        assertTrue(multipointOne.equalsTopo(resultP2));

//        Point[] pointArray2 = new Point[2];
//        pointArray2[0] = point;
//        Coordinate coord2 = new Coordinate(18.918457, 3.501085);
//        Point point2 = geometryFactory.createPoint( coord2 );
//        pointArray2[1] = point2; 
//        MultiPoint multipointMany = geometryFactory.createMultiPoint(pointArray2);
//        
//        //multipoint (with many points) -> GeometryCollection
//        CreateEqualGeometryObject instanceP3 = new CreateEqualGeometryObject(multipointMany, GeometryTypes.GeometryCollection);
//        Geometry expResultP3 = reader.read("GEOMETRYCOLLECTION (POINT (-5.998535 6.521366), POINT (18.918457 3.501085))");
//        Geometry resultP3 = instanceP3.generateGeometry();
//        System.out.println("expResult Point: " + expResultP3);
//        System.out.println("result Point: " + resultP3);   
//        assertEquals(resultP3, expResultP3);
//        
//        System.out.println("Is equals multipoint (with many points) -> GeometryCollection: "+ multipointMany.equalsTopo(resultP3)); 
//        assertTrue(multipointMany.equalsTopo(resultP3));
        //test EQUALS for a given LINESTRING
        //LINESTRING -> LINEARRING
        LineString line = (LineString) reader.read("LINESTRING(-5.998535 6.521366, 18.918457 3.501085, 19.045658 3.531829, 19.164791 3.564040, 19.281864 3.612011, -5.998535 6.521366)");

        CreateEqualGeometryObject instanceL1 = new CreateEqualGeometryObject(line, GeometryTypes.LinearRing);
        Geometry expResultL1 = reader.read("LINEARRING(-5.998535 6.521366, 18.918457 3.501085, 19.045658 3.531829, 19.164791 3.564040, 19.281864 3.612011, -5.998535 6.521366)");
        Geometry resultL1 = instanceL1.generateGeometry();
        System.out.println("expResult: " + expResultL1);
        System.out.println("result: " + resultL1);
        assertEquals(resultL1, expResultL1);

        System.out.println("Is equals LINESTRING -> LINEARRING: " + line.equalsTopo(resultL1));
        assertTrue(line.equalsTopo(resultL1));

        //LINESTRING -> MULTILINESTRING
        line = (LineString) reader.read("LINESTRING(-5.998535 6.521366, 18.918457 3.501085, 19.045658 3.531829, 19.164791 3.564040)");
        CreateEqualGeometryObject instanceL2 = new CreateEqualGeometryObject(line, GeometryTypes.MultiLineString);
        //for chunk = 2
        Geometry expResultL2 = reader.read("MULTILINESTRING((-5.998535 6.521366, 18.918457 3.501085), (18.918457 3.501085, 19.045658 3.531829), (19.045658 3.531829, 19.164791 3.564040))");

        //chunk = 3
        //Geometry expResultL2 = reader.read("MULTILINESTRING((-5.998535 6.521366, 18.918457 3.501085, 19.045658 3.531829), (19.045658 3.531829, 19.164791 3.564040))");
        // chunk = 4 or more
        //Geometry expResultL2 = reader.read("MULTILINESTRING((-5.998535 6.521366, 18.918457 3.501085, 19.045658 3.531829, 19.164791 3.564040))");
        Geometry resultL2 = instanceL2.generateGeometry();
        System.out.println("expResult: " + expResultL2);
        System.out.println("result: " + resultL2);
        assertEquals(resultL2, expResultL2);

        System.out.println("Is equals LINESTRING -> MULTILINESTRING: " + line.equalsTopo(resultL2));
        assertTrue(line.equalsTopo(resultL2));
        
        

        //LINESTRING -> POLYGON
//        line = (LineString) reader.read("LINESTRING(-5.998535 6.521366, 18.918457 3.501085, 19.045658 3.531829, 19.164791 3.564040, 19.281864 3.612011, -5.998535 6.521366)");
//        
//        CreateEqualGeometryObject instanceL3 = new CreateEqualGeometryObject(line, GeometryTypes.Polygon);
//        Geometry expResultL3 = reader.read("POLYGON((-5.998535 6.521366, 18.918457 3.501085, 19.045658 3.531829, 19.164791 3.564040, 19.281864 3.612011, -5.998535 6.521366))");
//        Geometry resultL3 = instanceL3.generateGeometry();
//        System.out.println("expResult: " + expResultL3);
//        System.out.println("result: " + resultL3);
//        assertEquals(resultL3, expResultL3);
////
//        System.out.println("Is equals LINESTRING -> POLYGON: " + line.equalsTopo(resultL3));
//        assertTrue(line.equalsTopo(resultL3));
        
        //LINESTRING -> GEOMETRYCOLLECTION
//        line = (LineString) reader.read("LINESTRING(-5.998535 6.521366, 18.918457 3.501085, 19.045658 3.531829, 19.164791 3.564040, 19.281864 3.612011, -5.998535 6.521366)");
//        CreateEqualGeometryObject instanceL4 = new CreateEqualGeometryObject(line, GeometryTypes.GeometryCollection);
//        Geometry resultL4 = instanceL4.generateGeometry();
        //test EQUALS for a given LINEARRING
        // LINEARRING -> LINESTRING 
        LinearRing ring = (LinearRing) reader.read("LINEARRING(-5.998535 6.521366, 18.918457 3.501085, 19.045658 3.531829, 19.164791 3.564040, 19.281864 3.612011, -5.998535 6.521366)");

        
        CreateEqualGeometryObject instanceL5 = new CreateEqualGeometryObject(ring, GeometryTypes.LineString);
        Geometry expResultL5 = reader.read("LINESTRING(-5.998535 6.521366, 18.918457 3.501085, 19.045658 3.531829, 19.164791 3.564040, 19.281864 3.612011, -5.998535 6.521366)");
        Geometry resultL5 = instanceL5.generateGeometry();
        System.out.println("expResult: " + expResultL5);
        System.out.println("result: " + resultL5);
        assertEquals(resultL5, expResultL5);

        System.out.println("Is equals LINEARRING -> LINESTRING: " + ring.equalsTopo(resultL5));
        assertTrue(ring.equalsTopo(resultL5));

        //LINEARRING -> MULTILINESTRING
        CreateEqualGeometryObject instanceL6 = new CreateEqualGeometryObject(ring, GeometryTypes.MultiLineString);
        //for chunk = 2
        Geometry expResultL6 = reader.read("MULTILINESTRING((-5.998535 6.521366, 18.918457 3.501085), (18.918457 3.501085, 19.045658 3.531829), (19.045658 3.531829, 19.164791 3.564040),  (19.164791 3.564040, 19.281864 3.612011), (19.281864 3.612011, -5.998535 6.521366))");

        Geometry resultL6 = instanceL6.generateGeometry();
        System.out.println("expResult: " + expResultL6);
        System.out.println("result: " + resultL6);
        assertEquals(resultL6, expResultL6);

        System.out.println("Is equals LINEARRING -> MULTILINESTRING: " + ring.equalsTopo(resultL6));
        assertTrue(ring.equalsTopo(resultL6));

        //LINEARRING -> POLYGON        
//        CreateEqualGeometryObject instanceL7 = new CreateEqualGeometryObject(ring, GeometryTypes.Polygon);
//        Geometry expResultL7 = reader.read("POLYGON((-5.998535 6.521366, 18.918457 3.501085, 19.045658 3.531829, 19.164791 3.564040, 19.281864 3.612011, -5.998535 6.521366))");
//        Geometry resultL7 = instanceL7.generateGeometry();
//        System.out.println("expResult: " + expResultL7);
//        System.out.println("result: " + resultL7);
//        assertEquals(resultL7, expResultL7);
//
//        System.out.println("Is equals LINEARRING -> POLYGON: " + line.equalsTopo(resultL7));
//        assertTrue(line.equalsTopo(resultL7));


        //LINEARRING -> GEOMETRYCOLLECTION
//        ring = (LinearRing) reader.read("LINEARRING(-5.998535 6.521366, 18.918457 3.501085, 19.045658 3.531829, 19.164791 3.564040, 19.281864 3.612011, -5.998535 6.521366)");
//        CreateEqualGeometryObject instanceL8 = new CreateEqualGeometryObject(ring, GeometryTypes.GeometryCollection);
//        Geometry resultL8 = instanceL8.generateGeometry();


        MultiLineString multiline = (MultiLineString) reader.read("MULTILINESTRING((-5.998535 6.521366, 18.918457 3.501085), (18.918457 3.501085, 19.045658 3.531829), (19.045658 3.531829, 19.164791 3.564040),  (19.164791 3.564040, 19.281864 3.612011), (19.281864 3.612011, -5.998535 6.521366))");
        
//        MULTILINESTRING -> POLYGON
//        CreateEqualGeometryObject instanceL8 = new CreateEqualGeometryObject(multiline, GeometryTypes.Polygon);        
//        Geometry expResultL8 = reader.read("POLYGON((-5.998535 6.521366, 18.918457 3.501085, 19.045658 3.531829, 19.164791 3.564040, 19.281864 3.612011, -5.998535 6.521366))");
//        Geometry resultL8 = instanceL8.generateGeometry();
//        System.out.println("expResult: " + expResultL8);
//        System.out.println("result: " + resultL8);
//        assertEquals(resultL8, expResultL8);
//
//        System.out.println("Is equals MULTILINESTRING -> POLYGON: " + multiline.equalsTopo(resultL8));
//        assertTrue(multiline.equalsTopo(resultL8));
        
        CreateEqualGeometryObject instanceL9 = new CreateEqualGeometryObject(multiline, GeometryTypes.GeometryCollection);
        Geometry resultL9 = instanceL9.generateGeometry();
        
        ring = (LinearRing) reader.read("LINEARRING(-5.998535 6.521366, 18.918457 3.501085, 19.045658 3.531829, -5.998535 6.521366)");
        Polygon poly = geometryFactory.createPolygon(ring, null);
        
//        CreateEqualGeometryObject instanceL10 = new CreateEqualGeometryObject(poly, GeometryTypes.LineString);
//        Geometry expResultL10 = reader.read("LINESTRING(-5.998535 6.521366, 18.918457 3.501085, 19.045658 3.531829, -5.998535 6.521366)");
//        Geometry resultL10 = instanceL10.generateGeometry();
//        System.out.println("expResult: " + expResultL10);
//        System.out.println("result: " + resultL10);
//        assertEquals(resultL5, expResultL10);
//
//        System.out.println("Is equals POLYGON -> LINESTRING: " + poly.equalsTopo(resultL10));
//        assertTrue(poly.equalsTopo(resultL10));

//        CreateEqualGeometryObject instanceL10 = new CreateEqualGeometryObject(poly, GeometryTypes.MultiLineString);
//        Geometry expResultL10 = reader.read("MULTILINESTRING((-5.998535 6.521366, 18.918457 3.501085), (18.918457 3.501085, 19.045658 3.531829), (19.045658 3.531829, -5.998535 6.521366))");
//        Geometry resultL10 = instanceL10.generateGeometry();
//        System.out.println("expResult: " + expResultL10);
//        System.out.println("result: " + resultL10);
//        assertEquals(resultL10, expResultL10);
//
//        System.out.println("Is equals POLYGON -> MULTILINESTRING: " + poly.equalsTopo(resultL10));
//        assertTrue(poly.equalsTopo(resultL10));

    }

}
