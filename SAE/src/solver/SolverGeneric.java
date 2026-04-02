package solver;

import graph.Node;

public abstract class SolverGeneric implements Solver{
	
	private GraphSoluce graphSoluce;
	private Node startingNode;
	private Node endingNode;
	private int steps = 0;
	
	
	
	public SolverGeneric(Node node1, Node node2) {
		
		this.startingNode = node1;
		this.endingNode = node2;
		
	}
	
	@Override
	public GraphSoluce getGraphSoluce() {
		
		return graphSoluce;
	}
	
	@Override
	public int getSteps() {
		
		return steps;
	}
	
	public void incSteps() {
		
		steps++;
	}
	
	public Node getStartingNode() {
		
		return startingNode;
	}
	
	public Node getEndingNode() {
		
		return endingNode;
	}

	@Override
	public void solve() {
		
		resolve();
		initializeResolution();
	}
	
	protected abstract void resolve();
	
	private void initializeResolution() {
		
		steps = 0;
	}
	

}
