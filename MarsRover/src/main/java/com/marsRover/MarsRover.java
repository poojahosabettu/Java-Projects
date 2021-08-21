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
		case 'F':
			this.coordinate.moveForward();
			break;
		case 'B':
			this.coordinate.moveBackWard();
			break;
		case 'L':
			this.coordinate.rotateLeft();
			break;
		case 'R':
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
