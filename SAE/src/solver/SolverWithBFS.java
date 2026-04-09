package solver;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import graph.Node;

public class SolverWithBFS extends SolverGeneric {

	private Set<Node> marquer;
	private Map<Node, Node> parent;
	private GraphSoluce solutiongraph;
		
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
			for (Node v : s.neighbors()) {
				if (v.equals(getEndingNode())) {
					file.add(v);
					parent.put(v, s);
					incSteps();
					return;
				}
				if (!marquer.contains(v)) {
					marquer.add(v);
					file.add(v);
					incSteps();
				}
			}
		}
			
		Node actuel = getEndingNode();
			
		while (actuel != getStartingNode()) {
			actuel = parent.get(actuel);
		}
		Collections.reverse(chemin);
	}

}

	