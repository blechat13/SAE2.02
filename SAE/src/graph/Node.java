package graph;

import java.util.Set;

import dungeon.Coord;

public class Node {
	//VARIABLE
	private String name;
	
	private Set<Node> neighbors;
	
	private Coord coord;
	
	//METHODE
	public Node(String name, Coord coord) {
		this.name = name;
		this.coord = coord;
	}
	
	public Set<Node> neigbors() {
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
	
	public boolean equals(Object object) {
		if(this == object) {
			return true;
		}
		if(object==null || !(object instanceof Node)) {
			return false;
		}
		
		Node otherNode = (Node) object;
		
		return otherNode.name == this.name && otherNode.coord == this.coord && otherNode.neighbors.equals(this.neighbors);
	}
}
