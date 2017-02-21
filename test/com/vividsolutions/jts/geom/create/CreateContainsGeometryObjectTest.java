/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vividsolutions.jts.geom.create;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.io.ParseException;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import junit.framework.TestCase;
import static test.jts.junit.GeometryUtils.reader;

/**
 *
 * @author jsaveta
 */
public class CreateContainsGeometryObjectTest extends TestCase {

    public CreateContainsGeometryObjectTest(String testName) {
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
     * Test of generateGeometry method, of class CreateContainsGeometryObject.
     */
    public void testGenerateGeometry() throws ParseException {
        LineString line = (LineString) reader.read("LINESTRING(-5.998535 6.521366, 18.918457 3.501085, 19.045658 3.531829, 19.164791 3.564040, 19.281864 3.612011, -5.998535 6.521366)");

        CreateEqualGeometryObject instanceL1 = new CreateEqualGeometryObject(line, GeometryType.GeometryTypes.LinearRing);
        Geometry resultL1 = instanceL1.generateGeometry();
        System.out.println("result: " + resultL1);
       
        System.out.println("LineString contains LineString: " + line.contains(resultL1));
        assertTrue(line.equalsTopo(resultL1));
    }

}
