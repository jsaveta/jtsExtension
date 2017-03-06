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
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import com.vividsolutions.jts.io.gml2.LineStringGenerator;
import java.util.Arrays;
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
        //test fro 100.000 random linestrings
        for (int i = 0; i < 100000; i++) {

            LineStringGenerator pg = new LineStringGenerator();
            pg.setGeometryFactory(geometryFactory);
            pg.setBoundingBox(new Envelope(-80, 80, -45, 40));
            pg.setNumberPoints(100);

            LineString line = (LineString) pg.create();
            System.out.println("given: " + line);

            CreateTouchesGeometryObject instanceL1 = new CreateTouchesGeometryObject(line, GeometryType.GeometryTypes.LineString);
            Geometry resultL1 = instanceL1.generateGeometry();
            System.out.println("i: " + i);

            System.out.println("result: " + resultL1);
            assertTrue(line.touches(resultL1));

        }
    }

}
