package com.marsRover;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class MarsRoverTest {
	private MarsRover marsRover;
	private Coordinate coordinate;
	private Point pointX;
	private Point pointY;
	private Direction direction;
	
	@Before
	public void before() {
		pointX = new Point(4);
		pointY = new Point(2);
		direction = Direction.EAST;
		coordinate = new Coordinate(pointX, pointY, direction);
		marsRover = new MarsRover(coordinate);
	}
	
	@Test
	public void testNewInstanceOfMarsRover() {
		assertEquals(coordinate, marsRover.getCoordinate());
		assertEquals(pointX, marsRover.getCoordinate().getPointX());
		assertEquals(pointY, marsRover.getCoordinate().getPointY());
		assertEquals(direction, marsRover.getCoordinate().getDirection());
		
	}
	
	@Test()
	public void testPositiveCommandDirection() throws BadCommandException{
		marsRover.executeCommand("FLFFFRFLB");
		Point pointX = new Point(6);
		Point pointY = new Point(4);
		Direction direction = Direction.NORTH;
		Coordinate coordinate = new Coordinate(pointX, pointY, direction);
		assertEquals(coordinate.getDirection(), marsRover.getCoordinate().getDirection());
	}
	
	@Test()
	public void testPositiveCommandPointX() throws BadCommandException{
		marsRover.executeCommand("FLFFFRFLB");
		Point pointX = new Point(6);
		Point pointY = new Point(4);
		Direction direction = Direction.NORTH;
		Coordinate coordinate = new Coordinate(pointX, pointY, direction);
		assertEquals(coordinate.getPointX().getValue(), marsRover.getCoordinate().getPointX().getValue());
	}
	
	@Test()
	public void testPositiveCommandPointY() throws BadCommandException{
		marsRover.executeCommand("FLFFFRFLB");
		Point pointX = new Point(6);
		Point pointY = new Point(4);
		Direction direction = Direction.NORTH;
		Coordinate coordinate = new Coordinate(pointX, pointY, direction);
		assertEquals(coordinate.getPointY().getValue(), marsRover.getCoordinate().getPointY().getValue());
	}
	
	@Test(expected = BadCommandException.class)
	public void testNegativeCommandDirection() throws BadCommandException{
		marsRover.executeCommand("Z");
	}

}
