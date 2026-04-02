package solver;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import graph.Node;

public class SolverWithBFS extends SolverGeneric {
	
	private List marquer = new ArrayList();

	public SolverWithBFS(Node node1, Node node2) {
		super(node1, node2);
	}

	@Override
	protected void resolve() {
		
		Queue<Node> file = new ArrayDeque();
		
		marquer.add(getStartingNode());
		
		file.add(getStartingNode());
		
		while (!file.isEmpty()) {
			
			Node s = file.remove();
		}
	}

}
