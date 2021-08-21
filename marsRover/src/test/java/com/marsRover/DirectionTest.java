package com.marsRover;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class DirectionTest {
	
	private Direction direction;
	private Direction[] allDirections;
	
	@Before
	public void before() {
		direction = Direction.NORTH;
		allDirections = Direction.values();
	}
	
	@Test
	public void testRotateRight() {
		assertEquals(Direction.EAST, direction.rotateRight());
	}
	
	@Test
	public void testRotateLeft() {
		assertEquals(Direction.WEST, direction.rotateLeft());
	}
	
	@Test
	public void testMoveBackwardFromNorth() {
		direction = Direction.NORTH;
		assertEquals(Direction.SOUTH, direction.goBackward());
	}
	
	@Test
	public void testMoveBackwardFromSouth() {
		direction = Direction.SOUTH;
		assertEquals(Direction.NORTH, direction.goBackward());
	}
	
	@Test
	public void testMoveBackwardFromEast() {
		direction = Direction.EAST;
		assertEquals(Direction.WEST, direction.goBackward());
	}
	
	@Test
	public void testMoveBackwardFromWest() {
		direction = Direction.WEST;
		assertEquals(Direction.EAST, direction.goBackward());
	}
	
	@Test
	public void testAllDirectionsValuesAndCodes() {
		//Test all direction values
		assertEquals(Direction.NORTH.getValue(), allDirections[0].getValue());
		assertEquals(Direction.EAST.getValue(), allDirections[1].getValue());
		assertEquals(Direction.SOUTH.getValue(), allDirections[2].getValue());
		assertEquals(Direction.WEST.getValue(), allDirections[3].getValue());
		
		//Test all direction codes
		assertEquals(Direction.NORTH.getCode(), allDirections[0].getCode());
		assertEquals(Direction.EAST.getCode(), allDirections[1].getCode());
		assertEquals(Direction.SOUTH.getCode(), allDirections[2].getCode());
		assertEquals(Direction.WEST.getCode(), allDirections[3].getCode());
		
	}
}
