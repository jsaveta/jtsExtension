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
import com.vividsolutions.jts.geom.PrecisionModel;
import com.vividsolutions.jts.io.gml2.LineStringGenerator;
import java.util.Random;
import static junit.framework.Assert.assertTrue;
import junit.framework.TestCase;

/**
 *
 * @author jsaveta
 */
public class CreateCoversGeometryObjectTest extends TestCase {

    public CreateCoversGeometryObjectTest(String testName) {
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
     * Test of generateGeometry method, of class CreateCoversGeometryObject.
     */
    public void testGenerateGeometry() {

        GeometryFactory geometryFactory = new GeometryFactory();
        Random rand = new Random();
        for (int i = 0; i < 1000; i++) {
            LineStringGenerator pg = new LineStringGenerator();
            pg.setGeometryFactory(geometryFactory);
            pg.setBoundingBox(new Envelope(-180, 180, -90, 90));

            //10 to 350 points (check generation of larger linestrings)
            int numPoints = rand.nextInt(350) + 10;
            pg.setNumberPoints(numPoints);

            LineString line = (LineString) pg.create();
            CreateCoversGeometryObject instanceL1 = new CreateCoversGeometryObject(line, GeometryType.GeometryTypes.LineString);

            Geometry resultL1 = instanceL1.generateGeometry();
            System.out.println("line: " + line);
            System.out.println("result: " + resultL1);
            assertTrue(resultL1.isValid());
//            System.out.println("intersection "+ line.intersection(resultL1));
            assertTrue(line.covers(resultL1));
        }
    }

}
