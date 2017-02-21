/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vividsolutions.jts.geom.create;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.LineString;
import junit.framework.TestCase;

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

    /**
     * Test of selectGeometryType method, of class GeometryType.
     */
    public void testSelectGeometryType() {
        System.out.println("selectGeometryType");
        GeometryType.GeometryTypes geometry = null;
        GeometryType instance = new GeometryTypeImpl();
        Class expResult = null;
        Class result = instance.selectGeometryType(geometry);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of chunkArray method, of class GeometryType.
     */
    public void testChunkArray() {
        System.out.println("chunkArray");
        Coordinate[] array = null;
        int chunkSize = 0;
        Coordinate[][] expResult = null;
        Coordinate[][] result = GeometryType.chunkArray(array, chunkSize);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLineStringArray method, of class GeometryType.
     */
    public void testGetLineStringArray() {
        System.out.println("getLineStringArray");
        LineString line = null;
        int chunk = 0;
        GeometryType instance = new GeometryTypeImpl();
        LineString[] expResult = null;
        LineString[] result = instance.getLineStringArray(line, chunk);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class GeometryTypeImpl extends GeometryType {
    }
    
}
