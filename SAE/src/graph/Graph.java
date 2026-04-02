package graph;

import java.util.Set;

public class Graph {

	private Set<Node> listNode;
	
	public void Graph() {
		
	}
	
	public void addNode(Node node) {
		listNode.add(node);
	}
	
	public void addEdge(Node node1, Node node2) {
		node1.addNeigbour(node2);
		node2.addNeigbour(node1);
	}
	
	public String toString() {
		String text = "Noeud : ";
		for(Node node : listNode) {
			text += node.getName()+" ";
		}
		
		return text;
	}
}
