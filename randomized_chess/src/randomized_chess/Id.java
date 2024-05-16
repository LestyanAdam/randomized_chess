package randomized_chess;

import java.util.Objects;

public class Id {
	Colour colour;
	Type type;
	int number;
	
	public Id(Colour colour, Type type, int id) {
		this.colour = colour;
		this.type = type;
		this.number = id;
	}
	
	public int getNumber() {
		return number;
	}


	@Override
	public int hashCode() {
		return Objects.hash(colour, number, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Id other = (Id) obj;
		return colour == other.colour && number == other.number && type == other.type;
	}
	
	@Override
	public String toString() {
		return colour.toString() + type.toString() + number;
	}
}
