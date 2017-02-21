/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vividsolutions.jts.geom.create;

import com.vividsolutions.jts.geom.Geometry;
import junit.framework.TestCase;

/**
 *
 * @author jsaveta
 */
public class CreateCrossesGeometryObjectTest extends TestCase {
    
    public CreateCrossesGeometryObjectTest(String testName) {
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
     * Test of generateGeometry method, of class CreateCrossesGeometryObject.
     */
    public void testGenerateGeometry() {
        System.out.println("generateGeometry");
        CreateCrossesGeometryObject instance = null;
        Geometry expResult = null;
        Geometry result = instance.generateGeometry();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
