package dna.metrics.workload;

import dna.graph.Graph;
import dna.graph.edges.Edge;
import dna.graph.nodes.Node;
import dna.util.parameters.IntParameter;

public abstract class RandomElementWorkload extends Operation {

	protected Node[] nodes;

	protected int nodesIndex;

	protected Edge[] edges;

	protected int edgesIndex;

	public RandomElementWorkload(String name, ListType list, int times,
			int nodeSamples, int edgeSamples) {
		super(name, list, times, new IntParameter("nodeSamples", nodeSamples),
				new IntParameter("edgeSamples", edgeSamples));
		this.nodes = new Node[nodeSamples];
		this.nodesIndex = 0;
		this.edges = new Edge[edgeSamples];
		this.edgesIndex = 0;
	}

	@Override
	protected void init(Graph g) {
		for (int i = 0; i < this.nodes.length; i++) {
			this.nodes[i] = g.getRandomNode();
		}
		for (int i = 0; i < this.edges.length; i++) {
			this.edges[i] = g.getRandomEdge();
		}
	}

	protected Node getSampleNode() {
		return this.nodes[(this.nodesIndex++) % this.nodes.length];
	}

	protected Edge getSampleEdge() {
		return this.edges[(this.edgesIndex++) % this.edges.length];
	}

}
