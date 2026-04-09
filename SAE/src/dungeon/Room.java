package dungeon;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Room {

	private String name;
	private Map<Direction, Room> nextRooms;
	private Coord coords;

	public Room(String name, Coord coords) {
		super();
		this.coords = coords;
		this.name = name;
		nextRooms = new HashMap<>();
	}

	public void addRoomToDirection(Room otherRoom, Direction direction) {
		if (nextRooms.get(direction) != null)
			return;
		nextRooms.put(direction, otherRoom);
		otherRoom.addRoomToDirection(this, direction.oppositeDirection());
	}

	@Override
	public String toString() {
		return "Room " + name + " " + coords + " [adjacent rooms: "
				+ nextRooms.values().size() + "]";
	}

	public String getName() {
		return name;
	}

	public Coord getCoords() {
		return coords;
	}

	public Map<Direction, Room> getNextRooms() {
		return nextRooms;
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (!(obj instanceof Room)) return false;
	    Room other = (Room) obj;
	    return this.name.equals(other.name) && this.coords.equals(other.coords);
	}

	@Override
	public int hashCode() {
	    return Objects.hash(name, coords);
	}

}
