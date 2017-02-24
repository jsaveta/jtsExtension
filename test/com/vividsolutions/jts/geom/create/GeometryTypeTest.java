/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vividsolutions.jts.geom.create;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.io.ParseException;
import java.util.ArrayList;
import junit.framework.TestCase;
import static test.jts.junit.GeometryUtils.reader;

/**
 *
 * @author jsaveta
 */
public class GeometryTypeTest extends TestCase {
    
    public GeometryTypeTest(String testName) {
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

//    /**
//     * Test of selectGeometryType method, of class GeometryType.
//     */
//    public void testSelectGeometryType() {
//        System.out.println("selectGeometryType");
//        GeometryType.GeometryTypes geometry = null;
//        GeometryType instance = new GeometryTypeImpl();
//        Class expResult = null;
//        Class result = instance.selectGeometryType(geometry);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of chunkArray method, of class GeometryType.
//     */
//    public void testChunkArray() {
//        System.out.println("chunkArray");
//        Coordinate[] array = null;
//        int chunkSize = 0;
//        Coordinate[][] expResult = null;
//        Coordinate[][] result = GeometryType.chunkArray(array, chunkSize);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getLineStringArray method, of class GeometryType.
//     */
//    public void testGetLineStringArray() {
//        System.out.println("getLineStringArray");
//        LineString line = null;
//        int chunk = 0;
//        GeometryType instance = new GeometryTypeImpl();
//        LineString[] expResult = null;
//        LineString[] result = instance.getLineStringArray(line, chunk);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    public class GeometryTypeImpl extends GeometryType {
//    }
//
//    /**
//     * Test of concatenate method, of class GeometryType.
//     */
//    public void testConcatenate() {
//        System.out.println("concatenate");
//        Object[] a = null;
//        Object[] b = null;
//        GeometryType instance = new GeometryTypeImpl();
//        Object[] expResult = null;
//        Object[] result = instance.concatenate(a, b);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of cutEnvelope method, of class GeometryType.
     */
    public void testCutEnvelope() throws ParseException {
        System.out.println("cutEnvelope");
        Geometry geo  = (LineString) reader.read("LINESTRING (-3.99356315819856 2.9175708333333334, -4.49890125 2.042299583333333, -5.004239341801441 2.9175708333333334, -5.998535 6.521366, 18.918457 3.501085, 19.045658 3.531829, 19.164791 3.56404, 19.281864 3.612011, 26.241148174308243 14.218089314583159, 27.45205238765051 10.491309352083508, 24.281864 8.188032666666667, 21.111675612349487 10.491309352083508)");

        int parts = 3;
        GeometryType instance = new GeometryTypeImpl();
        ArrayList <Envelope> result = instance.cutGeometryEnvelope(geo, parts);
        System.out.println("result " +result);
    }

    public class GeometryTypeImpl extends GeometryType {
    }
    
}
