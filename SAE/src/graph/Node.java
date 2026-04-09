package graph;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import dungeon.Coord;

public class Node {

	private String name;
	
	private Set<Node> neighbors = new HashSet<Node>();
	
	private Coord coord;
	
	private int heuristique;

	public Node(String name, Coord coord) {
		this.name = name;
		this.coord = coord;
	}
	
	public Set<Node> neighbors() {
		return neighbors;
	}
	
	public void addNeigbour(Node node) {
		neighbors.add(node);
	}
	
	public String toString() {
		String text;
		text = "Nom : "+name
				+" Voisin(s) : ";
		
		for(Node node : neighbors) {
			text+= node.name+", ";
		}
		text += "coord : "+coord;
		
		return text;
	}
	
	public String getName() {
		return name;
	}
	
	public Coord getCoord() {
		return coord;
	}
	
	@Override
	public boolean equals(Object object) {
	    if (this == object) return true;
	    if (object == null || !(object instanceof Node)) return false;

	    Node otherNode = (Node) object;

	    return otherNode.name.equals(this.name) 
	        && otherNode.coord.equals(this.coord);
	}

	@Override
	public int hashCode() {
	    return Objects.hash(name, coord);
	}
}
