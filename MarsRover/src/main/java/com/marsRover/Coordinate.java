package com.marsRover;

public class Coordinate {
	private Point pointX;
	private Point pointY;
	private Direction direction;
	
	
	public Coordinate(Point pointX, Point pointY, Direction direction) {
		super();
		this.pointX = pointX;
		this.pointY = pointY;
		this.direction = direction;
	}

	public Point getPointX() {
		return pointX;
	}
	public void setPointX(Point pointX) {
		this.pointX = pointX;
	}
	public Point getPointY() {
		return pointY;
	}
	public void setPointY(Point pointY) {
		this.pointY = pointY;
	}
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	private void move(Direction direction) {
		switch (direction) {
		case NORTH:
			this.pointY.moveForward();
			break;
		case EAST:
			this.pointX.moveForward();
			break;
		case SOUTH:
			this.pointY.moveBackward();
			break;
		case WEST:
			this.pointX.moveBackward();
			break;
		default:
			break;
		}
	}
	
	public void rotateRight() {
		this.setDirection(this.direction.rotateRight());
	}
	
	public void rotateLeft() {
		this.setDirection(this.direction.rotateLeft());
	}
	
	public void moveBackWard() {
		move(this.direction.goBackward());
	}
	
	public void moveForward() {
		move(this.direction);
	}
	
	@Override
	public String toString() {
		return "( " + pointX.getValue() + "," + pointY.getValue() + ")" + direction.toString();
	}
}
