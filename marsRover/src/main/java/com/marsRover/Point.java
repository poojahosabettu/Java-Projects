package com.marsRover;

public class Point {

	private int value;

	public Point(int value) {
		super();
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int moveForward() {
		this.value = this.value + 1;
		return this.value;
	}

	public int moveBackward() {
		this.value = this.value - 1;
		return this.value;
	}

	
}
