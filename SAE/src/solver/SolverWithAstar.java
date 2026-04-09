package solver;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import graph.Node;

public class SolverWithAstar extends SolverGeneric{
	
	private Queue<Node> closedList;
	private Queue<Node> openList;
	private Map<Node, Integer> heuristique;
	private Map<Node, Integer> cout;
	private Map<Node, Node> parent;

	public SolverWithAstar(Node node1, Node node2) {
		super(node1, node2);
		
		openList = new PriorityQueue<Node>(new Comparator<Node>() {
			
			@Override
			public int compare(Node s1, Node s2) {
				
				int heur1;
				int heur2;
				
				if (heuristique.containsKey(s1)) {
					heur1 = heuristique.get(s1);
				}
				else {
					return 1;
				}
				
				if (heuristique.containsKey(s2)) {
					heur2 = heuristique.get(s2);
				}
				else {
					return -1;
				}
				
				if (heur1 < heur2) {
					return -1;
				}
				else if (heur1 > heur2) {
					return 1;
				}
				else {
					return 0;
				}
			}
		});
		closedList = new ArrayDeque();
		heuristique = new HashMap();
		cout = new HashMap();
		parent = new HashMap();
	}
	

	@Override
	protected void resolve() {
		
		openList.add(getStartingNode());
		
		while (!openList.isEmpty()) {
			
			Node u = openList.remove();
			
			if (u.getCoord().getX() == getEndingNode().getCoord().getX() && u.getCoord().getY() == getEndingNode().getCoord().getY()) {
				
				Node actuel = getEndingNode();
				
				while (actuel != null) {
					getGraphSoluce().add(actuel);
					actuel = parent.get(actuel);
				}
				Collections.reverse(getGraphSoluce().getSoluce());
				
				incSteps();
				return;
			}
			for (Node v : u.neighbors()) {
				
				if( !closedList.contains(v) || (!openList.contains(v) || cout.get(u) + 1 < cout.get(v))) {
					
					cout.put(v, cout.get(u) + 1);
					heuristique.put(v, (cout.get(u) + 1) + manhattan(v, getEndingNode()));
					openList.add(v);
				}
				incSteps();	
			}
			closedList.add(u);
		}
		
	}
	
	
	private int manhattan(Node n1, Node n2) {
        return Math.abs(n2.getCoord().getX() - n1.getCoord().getX()) + Math.abs(n2.getCoord().getY() - n1.getCoord().getY());
    }
	
}
