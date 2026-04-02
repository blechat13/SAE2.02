package solver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import graph.Graph;
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
		
		if(parent.containsKey(getEndingNode())) {
			Node x = getEndingNode();
			while(x!=null) {
				solution.add(x);
				x = parent.get(x);
			}
		}
	}
	
	public void Prof(Node n) {
		marquer.add(n);
		for(Node v : n.getNeighbors()) {
			step ++;
			if(!marquer.contains(v)) {
				parent.put(v,n);
				if(!v.equals(getEndingNode())) {
					Prof(v);
				}
			}
		}
	}
}
