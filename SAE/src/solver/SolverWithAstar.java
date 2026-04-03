package solver;

import java.util.PriorityQueue;
import java.util.Queue;

import graph.Node;

public class SolverWithAstar extends SolverGeneric{

	public SolverWithAstar(Node node1, Node node2) {
		super(node1, node2);
	}

	@Override
	protected void resolve() {
		
		Queue<Node> closedList = new Queue();
		Queue<Node> openList = new PriorityQueue();
		
		openList.add(getStartingNode());
		
		while (!openList.isEmpty()) {
			
			Node u = openList.remove();
			
			if (u.getCoord().getX() == getEndingNode().getCoord().getX() && u.getCoord().getY() == getEndingNode().getCoord().getY()) {
				
				:::
				return;
			}
			for (Node v : u.neighbors()) {
				
				if( !closedList.contains(v) || :::) {
					
					:::
					:::
					openList.add(v);
				}
				
				
			}
			closedList.add(u);
		}
		
	}

}
