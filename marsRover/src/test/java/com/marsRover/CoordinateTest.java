package com.marsRover;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CoordinateTest {
	private Direction direction;
	private Point pointX;
	private Point pointY;
	private Coordinate coordinate;
	
	@Before
	public void before() {
		pointX = new Point(4);
		pointY = new Point(2);
		direction = Direction.NORTH;
		coordinate = new Coordinate(pointX, pointY, direction);
	}
	
	@Test
	public void testNewInstanceOfCoordinates() {
		assertEquals(pointX, coordinate.getPointX());
		assertEquals(pointY, coordinate.getPointY());
		assertEquals(direction, coordinate.getDirection());
	}
	
	@Test
	public void testMovementFromNorthToForward() {
		Point temp = new Point(coordinate.getPointY().getValue()+1);
		coordinate.moveForward();
		assertEquals(temp.getValue(), coordinate.getPointY().getValue());
		
	}
	
	@Test
	public void testMovementFromNorthToBackward() {
		Point temp = new Point(coordinate.getPointY().getValue()-1);
		coordinate.moveBackWard();
		assertEquals(temp.getValue(), coordinate.getPointY().getValue());
		assertEquals(Direction.NORTH, coordinate.getDirection());
		
	}
	
	@Test
	public void testMovementFromNorthToLeft() {
		coordinate.rotateLeft();
		assertEquals(Direction.WEST, coordinate.getDirection());
	}
	
	@Test
	public void testMovementFromNorthToRight() {
		coordinate.rotateRight();
		assertEquals(Direction.EAST, coordinate.getDirection());
	}
	
	@Test
	public void testDisplayCoordinate() {
		String temp = "( " + pointX.getValue() + "," + pointY.getValue() + ")" + direction.toString();
		assertEquals(temp, coordinate.toString());
	}

}
