/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vividsolutions.jts.geom.create;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.io.ParseException;
import static junit.framework.Assert.assertTrue;
import junit.framework.TestCase;
import static test.jts.junit.GeometryUtils.reader;

/**
 *
 * @author jsaveta
 */
public class CreateDisjointGeometryObjectTest extends TestCase {

    public CreateDisjointGeometryObjectTest(String testName) {
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
     * Test of generateGeometry method, of class CreateDisjointGeometryObject.
     */
    public void testGenerateGeometry() throws ParseException {
        LineString line = (LineString) reader.read("LINESTRING (-3.99356315819856 2.9175708333333334, -4.49890125 2.042299583333333, -5.004239341801441 2.9175708333333334, -5.998535 6.521366, 18.918457 3.501085, 19.045658 3.531829, 19.164791 3.56404, 19.281864 3.612011, 26.241148174308243 14.218089314583159, 27.45205238765051 10.491309352083508, 24.281864 8.188032666666667, 21.111675612349487 10.491309352083508)");
//        LineString line = (LineString) reader.read("LINESTRING ( 180 90, -3 -3, -3 -4, -180 -90)");
        CreateDisjointGeometryObject instanceL1 = new CreateDisjointGeometryObject(line, GeometryType.GeometryTypes.LineString);

        for (int i = 0; i < 1000; i++) {
            Geometry resultL1 = instanceL1.generateGeometry();
            System.out.println("given: " + line);
            System.out.println("result: " + resultL1);

            System.out.println("line disjoint result: " + line.disjoint(resultL1));
            assertTrue(line.disjoint(resultL1));
        }
    }

}
