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
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import com.vividsolutions.jts.io.gml2.LineStringGenerator;
import java.util.Random;
import static junit.framework.Assert.assertTrue;
import junit.framework.TestCase;

/**
 *
 * @author jsaveta
 */
public class CreateOverlapsGeometryObjectTest extends TestCase {

    public CreateOverlapsGeometryObjectTest(String testName) {
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
     * Test of generateGeometry method, of class CreateOverlapsGeometryObject.
     */
    public void testGenerateGeometry() throws ParseException {
        GeometryFactory geometryFactory = new GeometryFactory();
        WKTReader reader = new WKTReader(geometryFactory);
        Random rand = new Random();
        for (int i = 0; i < 100000; i++) {
            LineStringGenerator pg = new LineStringGenerator();
            pg.setGeometryFactory(geometryFactory);
            pg.setBoundingBox(new Envelope(-180, 180, -90, 90));

            //10 to 350 points (check generation of larger linestrings)
            int numPoints = rand.nextInt(350) + 10;
//            System.out.println("numPoints " + numPoints);
            pg.setNumberPoints(numPoints);
//            pg.setGenerationAlgorithm(LineStringGenerator.HORZ);
            LineString line = (LineString) pg.create();

            System.out.println("line: " + line + " size " + line.getCoordinates().length);

            CreateOverlapsGeometryObject instanceL1 = new CreateOverlapsGeometryObject(line, GeometryType.GeometryTypes.LineString);
            Geometry resultL1 = instanceL1.generateGeometry();
            System.out.println("result: " + resultL1 + " size " + resultL1.getCoordinates().length);

//            LineString line = (LineString) reader.read("LINESTRING( 18.918457 3.501085, 19.045658 3.531829, 19.164791 3.564040, 19.281864 3.612011, -5.998535 6.521366, 1 1)");
//            LineString resultL1 = (LineString) reader.read("LINESTRING(-5.998535 6.521366, 18.918457 3.501085, 19.045658 3.531829, 19.164791 3.564040, 19.281864 3.612011, -5.998535 6.521366)");
            assertTrue(resultL1.overlaps(line));
            assertTrue(resultL1.isValid());
//            assertTrue(line.overlaps(resultL1));

        }
    }

}
