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
import com.vividsolutions.jts.io.gml2.LineStringGenerator;
import java.util.Random;
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
        GeometryFactory geometryFactory = new GeometryFactory();
        Random rand = new Random();
        //test for 100.000 random linestrings
        for (int i = 0; i < 100000; i++) {

            LineStringGenerator pg = new LineStringGenerator();
            pg.setGeometryFactory(geometryFactory);

            pg.setBoundingBox(new Envelope(-180, 180, -90, 90));

            //10 to 350 points (check generation of larger linestrings)
            int numPoints = rand.nextInt(350) + 10;
            System.out.println("numPoints " + numPoints);
            pg.setNumberPoints(numPoints);

            LineString line = (LineString) pg.create();
//            LineString line = (LineString) reader.read("LINESTRING ( -180 3.5074674033245206, 13.044388741869454 3.5074674033245206, 13.11371727221631 3.5074674033245206, 14.663017119019297 3.5074674033245206, 16.373355462453823 90, 16.805309909381798 -90, 17.630603046619015 3.5074674033245206, 17.715722280443416 3.5074674033245206, 17.80088971430383 3.5074674033245206, 17.889626622606645 3.5074674033245206, 18.911799940254742 3.5074674033245206, 18.914769390288274 3.5074674033245206, 18.918301366259847 3.5074674033245206, 18.91835502766744 3.5074674033245206, 18.918405671754456 3.5074674033245206, 18.91842597185658 3.5074674033245206, 18.91845699999999 3.5074674033245206, 18.918457 3.501085)");

            System.out.println("line : " + line);

            CreateTouchesGeometryObject instanceL1 = new CreateTouchesGeometryObject(line, GeometryType.GeometryTypes.LineString);
            Geometry resultL1 = instanceL1.generateGeometry();

            System.out.println("result: " + resultL1);
            if (resultL1 != null) {
                assertTrue(resultL1.isValid());
                assertTrue(line.touches(resultL1));
            }

        }
    }
}
