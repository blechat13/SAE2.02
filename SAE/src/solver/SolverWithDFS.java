package solver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import graph.Node;

public class SolverWithDFS extends SolverGeneric{

	private int step = 0;
	
	private ArrayList<Node> marquer;
	
	private ArrayList<Node> solution;
	
	private Map<Node, Node> parent;
	
	
	public SolverWithDFS(Node node1, Node node2) {
		super(node1, node2);
		marquer = new ArrayList<>();
	    solution = new ArrayList<>();
	    parent = new HashMap<>();
	}

	@Override
	protected void resolve() {
		parent.put(getStartingNode(), null);
		
		Prof(getStartingNode());
		
		if(parent.containsKey(getEndingNode())) {// si il y a la sortie dans les enfants d'un noeud
			Node x = getEndingNode();
			
			while(x!=null) {//tant qu'on est pas remonté jusqu'à l'entrée
				step++;
				solution.add(x);//on ajoute le noeud qu'on observe dans la solution
				x = parent.get(x); //on prend le parent du noeud qu'on vient de parcourir
			}
			solution.reversed();
		}
	}
	
	public void Prof(Node n) {//algo profondeur
		marquer.add(n);//on marque le noeud
		for(Node v : n.neighbors()) {//pour chaque voisin
			this.step ++;
			
			if(!marquer.contains(v)) {//si le voisin n'est pas marqué
				parent.put(v,n);//on qualifie n comme le parent de v
				
				if(!v.equals(getEndingNode())) {//si on est pas à la sortie
					Prof(v);//on continue l'algo
				}
			}
		}
	}
	
}
