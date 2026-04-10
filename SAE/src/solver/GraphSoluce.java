package solver;

import java.util.ArrayList;
import java.util.List;
import graph.Node;

public class GraphSoluce {
	
	private List<Node> soluce = new ArrayList<Node>();

	public GraphSoluce() {

	}
	
	public void add(Node node) { // ajouter les noeud qui sont dans le chemin a la liste du resultat
		
		soluce.add(node);
	}
	
	public List<Node> getSoluce(){
		
		return soluce;
	}
	
	public void clear() { //méthode utilisé pour vidé la liste de solution
		soluce.clear();
	}
}
