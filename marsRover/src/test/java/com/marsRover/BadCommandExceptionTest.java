package com.marsRover;

import org.junit.Before;
import org.junit.Test;

public class BadCommandExceptionTest {
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
	
	@Test(expected = BadCommandException.class)
	public void testBadCommandException() throws BadCommandException {
		marsRover.executeCommand("Z");
	}
}
