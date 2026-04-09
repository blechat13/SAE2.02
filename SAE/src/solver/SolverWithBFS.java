package solver;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import graph.Node;

public class SolverWithBFS extends SolverGeneric {

	private Set<Node> marquer;
	private Map<Node, Node> parent;
	private Queue<Node> file;
		
	public SolverWithBFS(Node node1, Node node2) {
		super(node1, node2);
		
		marquer = new HashSet();
		parent = new HashMap();
		file = new ArrayDeque();
	}
	
	@Override
	public void initializeResolution() {
	    super.initializeResolution(); 
	    marquer.clear();
	    parent.clear();
	    file.clear();
	}

	@Override
	protected void resolve() {
	    marquer.add(getStartingNode());
	    file.add(getStartingNode());

	    bouclewhile:
	    while (!file.isEmpty()) {
	        Node s = file.remove();
	        incSteps();
	        
	        for (Node v : s.neighbors()) {
	            if (!marquer.contains(v)) {
	                marquer.add(v);
	                parent.put(v, s); // ← toujours enregistrer le parent

	                if (v.equals(getEndingNode())) {
	                    break bouclewhile; 
	                }
	                file.add(v);
	            }
	        }
	    }

	    // Reconstruction du chemin
	    Node actuel = getEndingNode();
	    while (actuel != null) {
	        getGraphSoluce().add(actuel);
	        actuel = parent.get(actuel);
	    }
	    Collections.reverse(getGraphSoluce().getSoluce());
	}
}

	