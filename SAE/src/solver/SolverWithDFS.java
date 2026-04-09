package solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import graph.Node;


public class SolverWithDFS extends SolverGeneric{
	
	private Set<Node> marquer;
	
	private Map<Node, Node> parent;
	
	
	public SolverWithDFS(Node node1, Node node2) {
		super(node1, node2);
		marquer = new HashSet<>();
	    parent = new HashMap<>();
	}
	
	@Override
	public void initializeResolution() {
	    super.initializeResolution(); 
	    marquer.clear();
	    parent.clear();
	}

	@Override
	protected void resolve() {
		if (getStartingNode() == null || getEndingNode() == null) {
	        System.err.println("Erreur : nœud de départ ou d'arrivée null !");
	        return;
	    }
		
		parent.put(getStartingNode(), null);
		
		Prof(getStartingNode());
		
		if(parent.containsKey(getEndingNode())) {// si il y a la sortie dans les enfants d'un noeud
			Node x = getEndingNode();
			
			while(x!=null) {//tant qu'on est pas remonté jusqu'à l'entrée
				getGraphSoluce().add(x);//on ajoute le noeud qu'on observe dans la solution
				x = parent.get(x); //on prend le parent du noeud qu'on vient de parcourir
			}
			Collections.reverse(getGraphSoluce().getSoluce());
		}
	}
	
	public void Prof(Node n) {//algo profondeur
		marquer.add(n);//on marque le noeud
		
		for(Node v : n.neighbors()) {//pour chaque voisin
			incSteps();
			
			if(!marquer.contains(v)) {//si le voisin n'est pas marqué
				parent.put(v,n);//on qualifie n comme le parent de v
				
				if(!v.equals(getEndingNode())) {//si on est pas à la sortie
					Prof(v);//on continue l'algo
				}
			}
		}
	}
	
}
