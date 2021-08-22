package com.marsRover;

public class MarsRover {
	private Coordinate coordinate;
	
	public MarsRover(Coordinate coordinate) {
		super();
		this.coordinate = coordinate;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}


	public void executeCommand(String commands) throws BadCommandException {
		for (char command : commands.toCharArray()) {
			execute(command);
		}
	}

	private void execute(char command) throws BadCommandException {
		switch (command) {
		case BaseConstants.FORWARD:
			this.coordinate.moveForward();
			break;
		case BaseConstants.BACKWARD:
			this.coordinate.moveBackWard();
			break;
		case BaseConstants.LEFT:
			this.coordinate.rotateLeft();
			break;
		case BaseConstants.RIGHT:
			this.coordinate.rotateRight();

			break;
		default:
			throw new BadCommandException("Illegal Command "+command);
		}
	}
	
	public String displayRoverPosition() {
		return this.coordinate.toString();
	}

}
