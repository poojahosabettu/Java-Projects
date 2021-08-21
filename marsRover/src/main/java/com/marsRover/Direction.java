package com.marsRover;

public enum Direction {
	NORTH(0,'N'),
	EAST(1,'E'),
	SOUTH(2,'S'),
	WEST(3,'W');
	
	private int value;
	private char code;
	
	private static Direction[] directions = Direction.values();
	
	Direction(int value, char code){
		this.value= value;
		this.code = code;
	}

	public int getValue() {
		return value;
	}
	
	public char getCode() {
		return code;
	}

	private int getIndex(int direction , int value) {
		return (direction+value+4)%4;
	}
	public Direction rotateLeft() {
		return directions[getIndex(-1,this.value)];
	}

	public Direction rotateRight() {
		return directions[getIndex(1,this.value)];
	}
	
	public Direction goBackward() {
		return directions[(this.value+2)%4];
	}
	
	
}
