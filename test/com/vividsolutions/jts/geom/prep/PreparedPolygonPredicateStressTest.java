/*
 * The JTS Topology Suite is a collection of Java classes that
 * implement the fundamental operations required to validate a given
 * geo-spatial data set to a known topological specification.
 *
 * Copyright (C) 2001 Vivid Solutions
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 * For more information, contact:
 *
 *     Vivid Solutions
 *     Suite #1A
 *     2328 Government Street
 *     Victoria BC  V8T 5G5
 *     Canada
 *
 *     (250)385-6040
 *     www.vividsolutions.com
 */
package com.vividsolutions.jts.geom.prep;

import junit.framework.TestCase;
import junit.textui.TestRunner;

import com.vividsolutions.jts.geom.Geometry;

/**
 * Stress tests {@link PreparedPolygon} for 
 * correctness of 
 * {@link PreparedPolygon#contains(Geometry)}
 * and {@link PreparedPolygon#intersects(Geometry)}
 * operations.
 * 
 * @author Owner
 *
 */
public class PreparedPolygonPredicateStressTest extends TestCase
{

  public static void main(String args[]) {
    TestRunner.run(PreparedPolygonPredicateStressTest.class);
  }

  boolean testFailed = false;

  public PreparedPolygonPredicateStressTest(String name) {
    super(name);
  }

  public void test()
  {
  	PredicateStressTester tester = new PredicateStressTester();
  	tester.run(1000);
  }
  
  class PredicateStressTester 
  extends StressTestHarness
  {
  	public boolean checkResult(Geometry target, Geometry test) {
  		if (! checkIntersects(target, test)) return false;
  		if (! checkContains(target, test)) return false;
  		return true;
  	}
  }
  
  public boolean checkContains(Geometry target, Geometry test) 
  {
	boolean expectedResult = target.contains(test);

    PreparedGeometryFactory pgFact = new PreparedGeometryFactory();
    PreparedGeometry prepGeom = pgFact.create(target);
    
	boolean prepResult = prepGeom.contains(test);

	if (prepResult != expectedResult) {
		return false;
	}
	return true;
  } 

  public boolean checkIntersects(Geometry target, Geometry test) 
  {
	boolean expectedResult = target.intersects(test);
	
	PreparedGeometryFactory pgFact = new PreparedGeometryFactory();
	PreparedGeometry prepGeom = pgFact.create(target);
	
	boolean prepResult = prepGeom.intersects(test);
	
	if (prepResult != expectedResult) {
		return false;
	}
	return true;
  } 

}
