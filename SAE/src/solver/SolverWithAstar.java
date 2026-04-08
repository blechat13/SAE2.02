package solver;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import graph.Node;

public class SolverWithAstar extends SolverGeneric{
	
	Queue<Node> closedList;
	Queue<Node> openList;

	public SolverWithAstar(Node node1, Node node2) {
		super(node1, node2);
		
		GraphSoluce graphsoluce = new GraphSoluce();
		
		openList = new PriorityQueue<Node>(new Comparator<Node>() {
			@Override
			public int compare(Node s1, Node s2) {
				
			}
			
			
		});
		closedList = new ArrayDeque();
	}
	

	@Override
	protected void resolve() {
		
		openList.add(getStartingNode());
		
		while (!openList.isEmpty()) {
			
			Node u = openList.remove();
			
			if (u.getCoord().getX() == getEndingNode().getCoord().getX() && u.getCoord().getY() == getEndingNode().getCoord().getY()) {
				
				:::
				incSteps();
				return;
			}
			for (Node v : u.neighbors()) {
				
				if( !closedList.contains(v) || (!openList.contains(v) )) {
					
					:::
					:::
					openList.add(v);
					incSteps();
				}
				
				
			}
			closedList.add(u);
		}
		
	}
	
	

}
