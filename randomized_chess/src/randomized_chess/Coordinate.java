package randomized_chess;

import java.util.Objects;

//simple class for storing two ints for the chess board
public class Coordinate {
	private int x;
	private int y;
	
	//default coordinates are 0,0
	{x = 0; y = 0;}
	
	//constructor
	public Coordinate(int x, int y) {
		this.y = y;
		this.x = x;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	//checks if the x and y coordinates are between 1 and the size of the chess board (8 by default)
	public boolean isValid() {
		if(this.x < 1 | this.x > 8 | this.y < 1 | this.y > 8) {
			return false;
		}
		return true;
	}

	//we override the hashcode and equals methods to be able to compare by value
	
	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		return x == other.x && y == other.y;
	}
	
	@Override
	public String toString() {
		return this.x + "," + this.y;
	}
}
