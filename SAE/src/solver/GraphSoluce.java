package solver;

import java.util.ArrayList;
import java.util.List;
import graph.Node;

public class GraphSoluce {
	
	private List<Node> soluce = new ArrayList<Node>();

	public GraphSoluce() {

	}
	
	public void add(Node node) {
		
		soluce.add(node);
	}
	
	public List<Node> getSoluce(){
		
		return soluce;
	}
	
	public void clear() {
		soluce.clear();
	}
}
