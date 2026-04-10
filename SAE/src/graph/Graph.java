package graph;

import java.util.HashSet;
import java.util.Set;

public class Graph {

	private Set<Node> listNode = new HashSet<Node>(); //liste de Noeud
	
	public Graph() { //constructeur vide
		
	}
	
	public void addNode(Node node) {//méthode qui ajoute un noeud à listNode
		listNode.add(node);
	}
	
	public void addEdge(Node node1, Node node2) { //créer une arrête entre 2 noeuds
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
