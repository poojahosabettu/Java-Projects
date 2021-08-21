package com.marsRover;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PointTest {
	
	private Point point;
	private int value;
	
	@Before
	public void before() {
		value = 5;
		point = new Point(5);
	}
	
	@Test
	public void testInstanceOfPoint() {
		assertEquals(value, point.getValue());
	}
	
	@Test
	public void testForwardMovement() {
		value = value+1;
		assertEquals(value, point.moveForward());
	}
	
	@Test
	public void testBackwardMovement() {
		value = value-1;
		assertEquals(value, point.moveBackward());
	}

}
