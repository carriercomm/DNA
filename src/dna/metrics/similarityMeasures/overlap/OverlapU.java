package dna.metrics.similarityMeasures.overlap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import dna.graph.IElement;
import dna.graph.edges.DirectedEdge;
import dna.graph.edges.DirectedWeightedEdge;
import dna.graph.edges.Edge;
import dna.graph.edges.UndirectedEdge;
import dna.graph.edges.UndirectedWeightedEdge;
import dna.graph.nodes.DirectedNode;
import dna.graph.nodes.Node;
import dna.graph.nodes.UndirectedNode;
import dna.graph.weights.DoubleWeight;
import dna.metrics.algorithms.IAfterUpdates;
import dna.metrics.algorithms.IAfterUpdatesWeighted;
import dna.metrics.algorithms.IBeforeUpdates;
import dna.metrics.algorithms.IBeforeUpdatesWeighted;
import dna.metrics.algorithms.IDynamicAlgorithm;
import dna.updates.update.EdgeAddition;
import dna.updates.update.EdgeRemoval;
import dna.updates.update.EdgeWeight;
import dna.updates.update.NodeAddition;
import dna.updates.update.NodeRemoval;
import dna.updates.update.NodeWeight;

/**
 * {@link IDynamicAlgorithm} of {@link Overlap}.
 */
public class OverlapU extends Overlap implements IBeforeUpdates, IAfterUpdates,
		IBeforeUpdatesWeighted, IAfterUpdatesWeighted {

	/**
	 * Initializes {@link OverlapU}. Implicitly sets degree type for directed
	 * graphs to outdegree and ignores wedge weights (if any).
	 */
	public OverlapU() {
		super("OverlapU");
	}

	/**
	 * Initializes {@link OverlapU}.
	 * 
	 * @param directedDegreeType
	 *            <i>in</i> or <i>out</i>, determining whether to use in- or
	 *            outdegree for directed graphs. Will be ignored for undirected
	 *            graphs.
	 * @param edgeWeightType
	 *            <i>weighted</i> or <i>unweighted</i>, determining whether to
	 *            use edge weights in weighted graphs or not. Will be ignored
	 *            for unweighted graphs.
	 */
	public OverlapU(DirectedDegreeType directedDegreeType,
			EdgeWeightType edgeWeightType) {
		super("OverlapU", directedDegreeType, edgeWeightType);
	}

	/**
	 * Called after the update is applied to the graph.
	 * 
	 * @param addedEdgeUpdate
	 *            The update from the {@link DirectedEdge} which has been added.
	 * @return true, if successful;
	 */
	private boolean applyAfterEdgeAdditionDirectedUnweighted(EdgeAddition u) {
		final DirectedEdge newEdge = (DirectedEdge) u.getEdge();

		HashSet<Node> neighborsIn = this
				.getNeighborsInDirectedUnweighted(newEdge.getDst());
		HashSet<Node> neighborsOut = this
				.getNeighborsOutDirectedUnweighted(newEdge.getSrc());

		if (isOutgoingMeasure()) {
			this.increaseMatchingUnweighted(neighborsIn, newEdge.getSrc());
			this.increaseAmountOfNeighborsUnweighted(newEdge.getSrc());

		} else {
			this.increaseMatchingUnweighted(neighborsOut, newEdge.getDst());
			this.increaseAmountOfNeighborsUnweighted(newEdge.getDst());

		}

		this.updateDirectedUnweighted(newEdge, neighborsIn, neighborsOut);

		return true;
	}

	/**
	 * Called after the update is applied to the graph.
	 * 
	 * @param addedEdgeUpdate
	 *            The update from the {@link DirectedEdge} which has been added.
	 * @return true, if successful;
	 */
	private boolean applyAfterEdgeAdditionDirectedWeighted(
			DirectedWeightedEdge directedDoubleWeightedEdge) {
		final DirectedWeightedEdge newEdge = (DirectedWeightedEdge) directedDoubleWeightedEdge;

		HashMap<Node, Double> neighborsIn = this
				.getNeighborsInDirectedWeighted(newEdge.getDst());
		HashMap<Node, Double> neighborsOut = this
				.getNeighborsOutDirectedWeighted(newEdge.getSrc());

		if (isOutgoingMeasure()) {
			this.increaseMatchingWeighted(neighborsIn, newEdge.getSrc());
			this.increaseAmountOfNeighborsWeighted(newEdge.getSrc(),
					weight(newEdge.getWeight()));

		} else {
			this.increaseMatchingWeighted(neighborsOut, newEdge.getDst());
			this.increaseAmountOfNeighborsWeighted(newEdge.getDst(),
					weight(newEdge.getWeight()));

		}

		this.updateDirectedWeighted(newEdge, neighborsIn, neighborsOut);

		return true;
	}

	/**
	 * Called after the update is applied to the graph.
	 * 
	 * @param addedEdgeUpdate
	 *            The update from the {@link Edge} which has been added.
	 * @return true, if successful;
	 */
	private boolean applyAfterEdgeAdditionUndirectedUnweighted(EdgeAddition u) {
		final UndirectedEdge newEdge = (UndirectedEdge) u.getEdge();

		HashSet<Node> neighborsNode1 = this
				.getNeighborNodesUndirectedUnweighted(newEdge.getNode1());
		HashSet<Node> neighborsNode2 = this
				.getNeighborNodesUndirectedUnweighted(newEdge.getNode2());

		this.increaseMatchingUnweighted(neighborsNode1, newEdge.getNode2());
		this.increaseMatchingUnweighted(neighborsNode2, newEdge.getNode1());

		this.increaseAmountOfNeighborsUnweighted(newEdge.getNode1());
		this.increaseAmountOfNeighborsUnweighted(newEdge.getNode2());

		this.updateUndirectedUnweighted(newEdge, neighborsNode1, neighborsNode2);

		return true;
	}

	/**
	 * Called after the update is applied to the graph.
	 * 
	 * @param addedEdgeUpdate
	 *            The update from the {@link Edge} which has been added.
	 * @return true, if successful;
	 */
	private boolean applyAfterEdgeAdditionUndirectedWeighted(
			UndirectedWeightedEdge undirectedDoubleWeightedEdge) {
		final UndirectedWeightedEdge newEdge = undirectedDoubleWeightedEdge;

		HashMap<Node, Double> neighborsNode1 = this
				.getNeighborNodesUndirectedWeighted(newEdge.getNode1());
		HashMap<Node, Double> neighborsNode2 = this
				.getNeighborNodesUndirectedWeighted(newEdge.getNode2());

		this.increaseMatchingWeighted(neighborsNode1, newEdge.getNode2());

		this.increaseMatchingWeighted(neighborsNode2, newEdge.getNode1());

		this.increaseAmountOfNeighborsWeighted(newEdge.getNode1(),
				weight(newEdge.getWeight()));
		this.increaseAmountOfNeighborsWeighted(newEdge.getNode2(),
				weight(newEdge.getWeight()));

		this.updateUndirectedWeighted(newEdge, neighborsNode1, neighborsNode2);

		return true;
	}

	@Override
	public boolean applyAfterUpdate(EdgeAddition ea) {
		if (DirectedWeightedEdge.class.isAssignableFrom(this.g
				.getGraphDatastructures().getEdgeType())) {

			if (this.edgeWeightType.equals(EdgeWeightType.USE_WEIGHTS))
				return this
						.applyAfterEdgeAdditionDirectedWeighted(((DirectedWeightedEdge) ((EdgeAddition) ea)
								.getEdge()));
			else if (this.edgeWeightType.equals(EdgeWeightType.IGNORE_WEIGHTS))
				return applyAfterEdgeAdditionDirectedUnweighted((EdgeAddition) ea);

		} else if (UndirectedWeightedEdge.class.isAssignableFrom(this.g
				.getGraphDatastructures().getEdgeType())) {

			if (this.edgeWeightType.equals(EdgeWeightType.USE_WEIGHTS))
				return this
						.applyAfterEdgeAdditionUndirectedWeighted(((UndirectedWeightedEdge) ((EdgeAddition) ea)
								.getEdge()));
			else if (this.edgeWeightType.equals(EdgeWeightType.IGNORE_WEIGHTS))
				return applyAfterEdgeAdditionUndirectedUnweighted((EdgeAddition) ea);

		} else if (DirectedNode.class.isAssignableFrom(this.g
				.getGraphDatastructures().getNodeType())) {

			// directed unweighted graph
			return applyAfterEdgeAdditionDirectedUnweighted((EdgeAddition) ea);

		} else if (UndirectedNode.class.isAssignableFrom(this.g
				.getGraphDatastructures().getNodeType())) {

			// undirected unweighted graph
			return applyAfterEdgeAdditionUndirectedUnweighted((EdgeAddition) ea);

		}

		return false;
	}

	@Override
	public boolean applyAfterUpdate(EdgeRemoval er) {
		return true;
	}

	@Override
	public boolean applyAfterUpdate(EdgeWeight ew) {
		return true;
	}

	@Override
	public boolean applyAfterUpdate(NodeAddition na) {
		for (int i = 0; i < this.g.getNodeCount(); i++)
			this.binnedDistribution.incr(0.0);
		return true;
	}

	@Override
	public boolean applyAfterUpdate(NodeRemoval nr) {
		return true;
	}

	@Override
	public boolean applyAfterUpdate(NodeWeight nw) {
		return true;
	}

	/**
	 * Called before the update is applied to the graph.
	 * 
	 * @param EdgeRemoval
	 *            The update from the {@link Edge} which is to be removed.
	 * @return true, if successful;
	 */
	private boolean applyBeforeEdgeRemovalDirectedUnweighted(EdgeRemoval u) {
		final DirectedEdge edgeToRemove = (DirectedEdge) u.getEdge();
		HashSet<Node> neighborsIn = this
				.getNeighborsInDirectedUnweighted(edgeToRemove.getDst());
		HashSet<Node> neighborsOut = this
				.getNeighborsOutDirectedUnweighted(edgeToRemove.getSrc());

		if (isOutgoingMeasure()) {
			this.decreaseMatchingUnweighted(neighborsIn, edgeToRemove.getSrc());
			this.decreaseAmountOfNeighborsUnweighted(edgeToRemove.getSrc());

		} else {
			this.decreaseMatchingUnweighted(neighborsOut, edgeToRemove.getDst());
			this.decreaseAmountOfNeighborsUnweighted(edgeToRemove.getDst());

		}

		this.updateDirectedUnweighted(edgeToRemove, neighborsIn, neighborsOut);

		return true;
	}

	/**
	 * Called before the update is applied to the graph.
	 * 
	 * @param EdgeRemoval
	 *            The update from the {@link Edge} which is to be removed.
	 * @return true, if successful;
	 */
	private boolean applyBeforeEdgeRemovalDirectedWeighted(
			DirectedWeightedEdge directedDoubleWeightedEdge) {
		final DirectedWeightedEdge edgeToRemove = directedDoubleWeightedEdge;

		HashMap<Node, Double> neighborsIn = this
				.getNeighborsInDirectedWeighted(edgeToRemove.getDst());
		HashMap<Node, Double> neighborsOut = this
				.getNeighborsOutDirectedWeighted(edgeToRemove.getSrc());

		if (isOutgoingMeasure()) {
			this.decreaseMatchingWeighted(neighborsIn, edgeToRemove.getSrc());
			this.decreaseAmountOfNeighborsWeighted(edgeToRemove.getSrc(),
					weight(edgeToRemove.getWeight()));

		} else {
			this.decreaseMatchingWeighted(neighborsOut, edgeToRemove.getDst());
			this.decreaseAmountOfNeighborsWeighted(edgeToRemove.getDst(),
					weight(edgeToRemove.getWeight()));

		}
		this.updateDirectedWeighted(edgeToRemove, neighborsIn, neighborsOut);
		return true;
	}

	/**
	 * Called before the update is applied to the graph.
	 * 
	 * @param EdgeRemoval
	 *            The update from the {@link Edge} which is to be removed.
	 * @return true, if successful
	 */
	private boolean applyBeforeEdgeRemovalUndirectedUnweighted(EdgeRemoval u) {
		final UndirectedEdge edgeToRemove = (UndirectedEdge) u.getEdge();

		HashSet<Node> neighborsNode1 = this
				.getNeighborNodesUndirectedUnweighted(edgeToRemove.getNode1());
		HashSet<Node> neighborsNode2 = this
				.getNeighborNodesUndirectedUnweighted(edgeToRemove.getNode2());

		this.decreaseMatchingUnweighted(neighborsNode1, edgeToRemove.getNode2());
		this.decreaseMatchingUnweighted(neighborsNode2, edgeToRemove.getNode1());

		this.decreaseAmountOfNeighborsUnweighted(edgeToRemove.getNode1());
		this.decreaseAmountOfNeighborsUnweighted(edgeToRemove.getNode2());

		this.updateUndirectedUnweighted(edgeToRemove, neighborsNode1,
				neighborsNode2);

		return true;
	}

	/**
	 * Called before the update is applied to the graph.
	 * 
	 * @param EdgeRemoval
	 *            The update from the {@link Edge} which is to be removed.
	 * @return true, if successful;
	 */
	private boolean applyBeforeEdgeRemovalUndirectedWeighted(
			UndirectedWeightedEdge undirectedDoubleWeightedEdge) {
		final UndirectedWeightedEdge edgeToRemove = undirectedDoubleWeightedEdge;

		HashMap<Node, Double> neighborsNode1 = this
				.getNeighborNodesUndirectedWeighted(edgeToRemove.getNode1());
		HashMap<Node, Double> neighborsNode2 = this
				.getNeighborNodesUndirectedWeighted(edgeToRemove.getNode2());

		this.decreaseMatchingWeighted(neighborsNode1, edgeToRemove.getNode2());
		this.decreaseMatchingWeighted(neighborsNode2, edgeToRemove.getNode1());

		this.decreaseAmountOfNeighborsWeighted(edgeToRemove.getNode1(),
				weight(edgeToRemove.getWeight()));
		this.decreaseAmountOfNeighborsWeighted(edgeToRemove.getNode2(),
				weight(edgeToRemove.getWeight()));

		this.updateUndirectedWeighted(edgeToRemove, neighborsNode1,
				neighborsNode2);
		return true;
	}

	/**
	 * Called before the edge weight update is applied to the graph.
	 * 
	 * @param directedDoubleWeightedEdge
	 *            The {@link Edge} whose edge weight changes.
	 * @param weight
	 *            The new weight of the Edge after the Update.
	 * @return true, if successful;
	 */
	private boolean applyBeforeEdgeWeightUpdateDirectedWeighted(
			DirectedWeightedEdge directedDoubleWeightedEdge, double weight) {
		return applyEdgeWeightedUpdateDirectedWeighted(
				directedDoubleWeightedEdge, weight);

	}

	private boolean applyBeforeEdgeWeightUpdateUndirectedWeighted(
			UndirectedWeightedEdge undirectedDoubleWeightedEdge, double weight) {
		applyEdgeWeightedUpdateUndirectedWeighted(undirectedDoubleWeightedEdge,
				weight);
		return true;
	}

	/**
	 * Called before the update is applied to the graph.
	 * 
	 * @param NodeRemoval
	 *            The update from the {@link Node} which is to be removed.
	 * @return true, if successful;
	 */
	private boolean applyBeforeNodeRemovalDirectedUnweighted(NodeRemoval u) {
		final DirectedNode nodeToRemove = (DirectedNode) u.getNode();
		if (isOutgoingMeasure())
			this.decreaseMatchingNodeRemoveUnweighted(this
					.getNeighborsInDirectedUnweighted(nodeToRemove));
		else
			this.decreaseMatchingNodeRemoveUnweighted(this
					.getNeighborsOutDirectedUnweighted(nodeToRemove));

		// remove the node from the neighborNodes Map
		this.removeFromNeighborNodesDirectedUnweighted(nodeToRemove);

		for (IElement iterable_element : this.g.getNodes()) {
			Node node = (Node) iterable_element;
			if (this.result.get(nodeToRemove, node) == null)
				this.binnedDistribution.decr(0);
			else
				this.binnedDistribution.decr(this.result
						.get(nodeToRemove, node));
		}

		if (isOutgoingMeasure()) {
			this.updateDirectNeighborsMeasureUnweighted(this
					.getNeighborsInDirectedUnweighted(nodeToRemove));
			this.updateNodeRemoveMeasuresOutgoingDirectedUnweighted(nodeToRemove);
		} else {
			this.updateDirectNeighborsMeasureUnweighted(this
					.getNeighborsOutDirectedUnweighted(nodeToRemove));
			this.updateNodeRemoveMeasuresIncomingDirectedUnweighted(nodeToRemove);
		}

		// remove the results of the removed node calculated so far
		this.amountOfNeighbors.remove(nodeToRemove);
		this.matching.removeRow(nodeToRemove);
		this.matching.removeColumn(nodeToRemove);
		this.result.removeRow(nodeToRemove);
		this.result.removeColumn(nodeToRemove);

		return true;
	}

	/**
	 * Called before the update is applied to the graph.
	 * 
	 * @param NodeRemoval
	 *            The update from the {@link Node} which is to be removed.
	 * @return true, if successful;
	 */
	private boolean applyBeforeNodeRemovalDirectedWeighted(NodeRemoval u) {
		final DirectedNode nodeToRemove = (DirectedNode) u.getNode();
		if (isOutgoingMeasure())
			this.decreaseMatchingNodeRemoveWeighted(this
					.getNeighborsInDirectedWeighted(nodeToRemove));
		else
			this.decreaseMatchingNodeRemoveWeighted(this
					.getNeighborsOutDirectedWeighted(nodeToRemove));

		this.decreaseAmountOfNeighborsDirectedWeighted(nodeToRemove);

		for (IElement iterable_element : this.g.getNodes()) {
			Node node = (Node) iterable_element;
			if (this.result.get(nodeToRemove, node) == null)
				this.binnedDistribution.decr(0);
			else
				this.binnedDistribution.decr(this.result
						.get(nodeToRemove, node));
		}

		if (isOutgoingMeasure()) {
			this.updateDirectNeighborsMeasureWeighted(this
					.getNeighborsInDirectedWeighted(nodeToRemove));
			this.updateNodeRemoveMeasuresOutgoingDirectedWeighted(nodeToRemove);
		} else {
			this.updateDirectNeighborsMeasureWeighted(this
					.getNeighborsOutDirectedWeighted(nodeToRemove));
			this.updateNodeRemoveMeasuresIncomingDirectedWeighted(nodeToRemove);
		}

		// remove the results of the removed node calculated so far
		this.amountOfNeighbors.remove(nodeToRemove);
		this.matching.removeRow(nodeToRemove);
		this.matching.removeColumn(nodeToRemove);
		this.result.removeRow(nodeToRemove);
		this.result.removeColumn(nodeToRemove);

		return true;
	}

	/**
	 * Called before the update is applied to the graph.
	 * 
	 * @param nodeRemove
	 *            The update from the {@link Node} which is to be removed.
	 * @return true, if successful
	 */
	private boolean applyBeforeNodeRemovalUndirectedUnweighted(
			NodeRemoval nodeRemove) {
		final UndirectedNode nodeToRemove = (UndirectedNode) nodeRemove
				.getNode();

		this.decreaseMatchingNodeRemoveUnweighted(this
				.getNeighborNodesUndirectedUnweighted(nodeToRemove));

		this.decreaseAmountOfNeighborsUndirectedUnweighted(this
				.getNeighborNodesUndirectedUnweighted(nodeToRemove));

		for (IElement iterable_element : this.g.getNodes()) {
			Node node = (Node) iterable_element;
			if (this.result.get(nodeToRemove, node) == null)
				this.binnedDistribution.decr(0);
			else
				this.binnedDistribution.decr(this.result
						.get(nodeToRemove, node));
		}

		this.updateDirectNeighborsMeasureUnweighted(this
				.getNeighborNodesUndirectedUnweighted(nodeToRemove));

		this.updateNodeRemoveMeasureUndirectedUnweighted(nodeToRemove);

		// remove the results of the removed node calculated so far
		this.amountOfNeighbors.remove(nodeToRemove);
		this.matching.removeRow(nodeToRemove);
		this.matching.removeColumn(nodeToRemove);
		this.result.removeRow(nodeToRemove);
		this.result.removeColumn(nodeToRemove);

		return true;
	}

	/**
	 * Called before the update is applied to the graph.
	 * 
	 * @param NodeRemoval
	 *            The update from the {@link Node} which is to be removed.
	 * @return true, if successful;
	 */
	private boolean applyBeforeNodeRemovalUndirectedWeighted(NodeRemoval u) {
		final UndirectedNode nodeToRemove = (UndirectedNode) u.getNode();

		this.decreaseMatchingNodeRemoveWeighted(this
				.getNeighborNodesUndirectedWeighted(nodeToRemove));

		this.decreaseAmountOfNeighborsUndirectedWeighted(nodeToRemove);

		for (IElement iterable_element : this.g.getNodes()) {
			Node node = (Node) iterable_element;
			if (this.result.get(nodeToRemove, node) == null)
				this.binnedDistribution.decr(0);
			else
				this.binnedDistribution.decr(this.result
						.get(nodeToRemove, node));
		}

		this.updateDirectNeighborsMeasureWeighted(this
				.getNeighborNodesUndirectedWeighted(nodeToRemove));

		this.updateNodeRemoveMeasureUndirectedWeighted(nodeToRemove);

		// remove the results of the removed node calculated so far
		this.amountOfNeighbors.remove(nodeToRemove);
		this.matching.removeRow(nodeToRemove);
		this.matching.removeColumn(nodeToRemove);
		this.result.removeRow(nodeToRemove);
		this.result.removeColumn(nodeToRemove);

		return true;
	}

	@Override
	public boolean applyBeforeUpdate(EdgeAddition ea) {
		return true;
	}

	@Override
	public boolean applyBeforeUpdate(EdgeRemoval er) {

		if (DirectedWeightedEdge.class.isAssignableFrom(this.g
				.getGraphDatastructures().getEdgeType())) {

			if (this.edgeWeightType.equals(EdgeWeightType.USE_WEIGHTS))
				return applyBeforeEdgeRemovalDirectedWeighted(((DirectedWeightedEdge) ((EdgeRemoval) er)
						.getEdge()));
			else if (this.edgeWeightType.equals(EdgeWeightType.IGNORE_WEIGHTS))
				return applyBeforeEdgeRemovalDirectedUnweighted((EdgeRemoval) er);

		} else if (UndirectedWeightedEdge.class.isAssignableFrom(this.g
				.getGraphDatastructures().getEdgeType())) {

			if (this.edgeWeightType.equals(EdgeWeightType.USE_WEIGHTS))
				return applyBeforeEdgeRemovalUndirectedWeighted(((UndirectedWeightedEdge) ((EdgeRemoval) er)
						.getEdge()));
			else if (this.edgeWeightType.equals(EdgeWeightType.IGNORE_WEIGHTS))
				return applyBeforeEdgeRemovalUndirectedUnweighted((EdgeRemoval) er);

		} else if (DirectedNode.class.isAssignableFrom(this.g
				.getGraphDatastructures().getNodeType())) {

			// directed unweighted graph
			return applyBeforeEdgeRemovalDirectedUnweighted((EdgeRemoval) er);

		} else if (UndirectedNode.class.isAssignableFrom(this.g
				.getGraphDatastructures().getNodeType())) {

			// undirected unweighted graph
			return applyBeforeEdgeRemovalUndirectedUnweighted((EdgeRemoval) er);

		}

		return false;
	}

	@Override
	public boolean applyBeforeUpdate(EdgeWeight ew) {
		if (DirectedWeightedEdge.class.isAssignableFrom(this.g
				.getGraphDatastructures().getEdgeType())) {
			DirectedWeightedEdge edgeD = ((DirectedWeightedEdge) ((EdgeWeight) ew)
					.getEdge());
			return applyBeforeEdgeWeightUpdateDirectedWeighted(edgeD,
					weight(ew.getWeight()));
		} else if (UndirectedWeightedEdge.class.isAssignableFrom(this.g
				.getGraphDatastructures().getEdgeType())) {
			UndirectedWeightedEdge edgeD = ((UndirectedWeightedEdge) ((EdgeWeight) ew)
					.getEdge());
			return applyBeforeEdgeWeightUpdateUndirectedWeighted(edgeD,
					weight(ew.getWeight()));
		}
		return false;
	}

	@Override
	public boolean applyBeforeUpdate(NodeAddition na) {
		return true;
	}

	@Override
	public boolean applyBeforeUpdate(NodeRemoval nr) {
		if (DirectedWeightedEdge.class.isAssignableFrom(this.g
				.getGraphDatastructures().getEdgeType())) {

			if (this.edgeWeightType.equals(EdgeWeightType.USE_WEIGHTS))
				return this
						.applyBeforeNodeRemovalDirectedWeighted((NodeRemoval) nr);
			else if (this.edgeWeightType.equals(EdgeWeightType.IGNORE_WEIGHTS))
				return this
						.applyBeforeNodeRemovalDirectedUnweighted((NodeRemoval) nr);

		} else if (UndirectedWeightedEdge.class.isAssignableFrom(this.g
				.getGraphDatastructures().getEdgeType())) {

			if (this.edgeWeightType.equals(EdgeWeightType.USE_WEIGHTS))
				return this
						.applyBeforeNodeRemovalUndirectedWeighted((NodeRemoval) nr);
			else if (this.edgeWeightType.equals(EdgeWeightType.IGNORE_WEIGHTS))
				return applyBeforeNodeRemovalUndirectedUnweighted((NodeRemoval) nr);

		} else if (DirectedNode.class.isAssignableFrom(this.g
				.getGraphDatastructures().getNodeType())) {

			// directed unweighted graph
			return applyBeforeNodeRemovalDirectedUnweighted((NodeRemoval) nr);

		} else if (UndirectedNode.class.isAssignableFrom(this.g
				.getGraphDatastructures().getNodeType())) {

			// undirected unweighted graph
			return applyBeforeNodeRemovalUndirectedUnweighted((NodeRemoval) nr);

		}
		return false;
	}

	@Override
	public boolean applyBeforeUpdate(NodeWeight nw) {
		return true;
	}

	/**
	 * Applied the edge weight update to the graph.
	 * 
	 * @param directedDoubleWeightedEdge
	 *            The {@link Edge} whose edge weight changes.
	 * @param weight
	 *            The new weight of the Edge after the Update.
	 * @return true, if successful;
	 */
	private boolean applyEdgeWeightedUpdateDirectedWeighted(
			DirectedWeightedEdge directedDoubleWeightedEdge, double weight) {
		final DirectedWeightedEdge edgeToBeUpdated = directedDoubleWeightedEdge;
		if (isOutgoingMeasure()) {
			this.decreaseMatchingWeighted(this
					.getNeighborsInDirectedWeighted(edgeToBeUpdated.getDst()),
					edgeToBeUpdated.getSrc());
			this.decreaseAmountOfNeighborsWeighted(edgeToBeUpdated.getSrc(),
					weight(edgeToBeUpdated.getWeight()));

		} else {
			this.decreaseMatchingWeighted(this
					.getNeighborsOutDirectedWeighted(edgeToBeUpdated.getSrc()),
					edgeToBeUpdated.getDst());
			this.decreaseAmountOfNeighborsWeighted(edgeToBeUpdated.getDst(),
					weight(edgeToBeUpdated.getWeight()));

		}
		edgeToBeUpdated.setWeight(new DoubleWeight(weight));

		HashMap<Node, Double> neighborsIn = this
				.getNeighborsInDirectedWeighted(edgeToBeUpdated.getDst());
		HashMap<Node, Double> neighborsOut = this
				.getNeighborsOutDirectedWeighted(edgeToBeUpdated.getSrc());

		if (isOutgoingMeasure()) {
			this.increaseMatchingWeighted(neighborsIn, edgeToBeUpdated.getSrc());
			this.increaseAmountOfNeighborsWeighted(edgeToBeUpdated.getSrc(),
					((DoubleWeight) edgeToBeUpdated.getWeight()).getWeight());

		} else {
			this.increaseMatchingWeighted(neighborsOut,
					edgeToBeUpdated.getDst());
			this.increaseAmountOfNeighborsWeighted(edgeToBeUpdated.getDst(),
					((DoubleWeight) edgeToBeUpdated.getWeight()).getWeight());

		}

		this.updateDirectedWeighted(edgeToBeUpdated, neighborsIn, neighborsOut);
		return true;
	}

	/**
	 * Applied the edge weight update to the graph.
	 * 
	 * @param undirectedDoubleWeightedEdge
	 *            The {@link Edge} whose edge weight changes.
	 * @param weight
	 *            The new weight of the Edge after the Update.
	 * @return true, if successful;
	 */
	private void applyEdgeWeightedUpdateUndirectedWeighted(
			UndirectedWeightedEdge undirectedDoubleWeightedEdge, double weight) {

		final UndirectedWeightedEdge edgeToBeUpdated = undirectedDoubleWeightedEdge;

		this.decreaseMatchingWeighted(
				this.getNeighborNodesUndirectedWeighted(edgeToBeUpdated
						.getNode1()), edgeToBeUpdated.getNode2());
		this.decreaseMatchingWeighted(
				this.getNeighborNodesUndirectedWeighted(edgeToBeUpdated
						.getNode2()), edgeToBeUpdated.getNode1());

		this.decreaseAmountOfNeighborsWeighted(edgeToBeUpdated.getNode1(),
				weight(edgeToBeUpdated.getWeight()));
		this.decreaseAmountOfNeighborsWeighted(edgeToBeUpdated.getNode2(),
				weight(edgeToBeUpdated.getWeight()));

		edgeToBeUpdated.setWeight(new DoubleWeight(weight));

		HashMap<Node, Double> neighborsNode1 = this
				.getNeighborNodesUndirectedWeighted(edgeToBeUpdated.getNode1());
		HashMap<Node, Double> neighborsNode2 = this
				.getNeighborNodesUndirectedWeighted(edgeToBeUpdated.getNode2());

		this.increaseMatchingWeighted(neighborsNode1,
				edgeToBeUpdated.getNode2());

		this.increaseMatchingWeighted(neighborsNode2,
				edgeToBeUpdated.getNode1());

		this.increaseAmountOfNeighborsWeighted(edgeToBeUpdated.getNode1(),
				((DoubleWeight) edgeToBeUpdated.getWeight()).getWeight());
		this.increaseAmountOfNeighborsWeighted(edgeToBeUpdated.getNode2(),
				((DoubleWeight) edgeToBeUpdated.getWeight()).getWeight());

		this.updateUndirectedWeighted(edgeToBeUpdated, neighborsNode1,
				neighborsNode2);
	}

	/**
	 * Decreases the number of neighbors for each node.
	 * 
	 * @see #decreaseAmountOfNeighborsDirectedWeighted(UndirectedNode)
	 */
	private void decreaseAmountOfNeighborsDirectedWeighted(
			DirectedNode nodeToRemove) {
		if (isOutgoingMeasure())
			for (IElement iEdge : nodeToRemove.getIncomingEdges()) {
				decreaseAmountOfNeighborsWeighted(
						((DirectedWeightedEdge) iEdge).getSrc(),
						weight(((DirectedWeightedEdge) iEdge).getWeight()));
			}
		else
			for (IElement iEdge : nodeToRemove.getOutgoingEdges()) {
				decreaseAmountOfNeighborsWeighted(
						((DirectedWeightedEdge) iEdge).getDst(),
						weight(((DirectedWeightedEdge) iEdge).getWeight()));
			}

	}

	/**
	 * Decreases the number of neighbors for each node by 1.
	 * 
	 * @see #decreaseAmountOfNeighborsUndirectedUnweighted(UndirectedNode)
	 */
	private void decreaseAmountOfNeighborsUndirectedUnweighted(
			HashSet<Node> neighborNodes) {
		for (Node undirectedNode : neighborNodes) {
			decreaseAmountOfNeighborsUnweighted(undirectedNode);
		}

	}

	/**
	 * Decreases the number of neighbors for each node.
	 * 
	 * @see #decreaseAmountOfNeighborsUndirectedWeighted(UndirectedNode)
	 */
	private void decreaseAmountOfNeighborsUndirectedWeighted(
			UndirectedNode nodeToRemove) {
		for (IElement iEdge : nodeToRemove.getEdges()) {
			UndirectedWeightedEdge e = ((UndirectedWeightedEdge) iEdge);
			if (e.getNode1().equals(nodeToRemove))
				decreaseAmountOfNeighborsWeighted(e.getNode2(),
						weight(e.getWeight()));
			else
				decreaseAmountOfNeighborsWeighted(e.getNode1(),
						weight(e.getWeight()));
		}

	}

	/**
	 * Decreases the number of neighbors of the given node by 1.
	 */
	private void decreaseAmountOfNeighborsUnweighted(Node node) {
		if (this.amountOfNeighbors.containsKey(node))
			this.amountOfNeighbors.put(node,
					this.amountOfNeighbors.get(node) - 1);
		else
			this.amountOfNeighbors.put(node, 0.0);

	}

	/**
	 * Decreases the number of neighbors of the given node.
	 */
	private void decreaseAmountOfNeighborsWeighted(Node node, double weight) {
		double aoN = this.amountOfNeighbors.get(node) - weight;

		if (aoN < 0.0 && Math.abs(aoN) <= 1.0E-4 || aoN > 0.0 && aoN < 1.0E-6) {
			aoN = 0.0;
		}

		this.amountOfNeighbors.put(node, aoN);
	}

	/**
	 * Increases the number of neighbors of the given node by 1.
	 */
	private void increaseAmountOfNeighborsUnweighted(Node node) {
		if (this.amountOfNeighbors.containsKey(node))
			this.amountOfNeighbors.put(node,
					this.amountOfNeighbors.get(node) + 1);
		else
			this.amountOfNeighbors.put(node, 1.0);

	}

	/**
	 * Increases the number of neighbors of the given node.
	 */
	private void increaseAmountOfNeighborsWeighted(Node node, double weight) {
		if (amountOfNeighbors.containsKey(node))
			this.amountOfNeighbors.put(node, this.amountOfNeighbors.get(node)
					+ weight);
		else
			this.amountOfNeighbors.put(node, weight);
	}

	@Override
	public boolean init() {
		reset_();
		return compute();
	}

	/**
	 * Removes a {@link Node} from the neighborNodes {@link Map}.
	 * 
	 * @param nodeToRemove
	 *            {@link Node} to remove from the {@link Map}
	 */
	private void removeFromNeighborNodesDirectedUnweighted(
			DirectedNode nodeToRemove) {
		this.amountOfNeighbors.remove(nodeToRemove);
		if (isOutgoingMeasure())
			for (Node directedNode : this
					.getNeighborsInDirectedUnweighted(nodeToRemove))
				this.decreaseAmountOfNeighborsUnweighted(directedNode);
		else
			for (Node directedNode : this
					.getNeighborsOutDirectedUnweighted(nodeToRemove))
				this.decreaseAmountOfNeighborsUnweighted(directedNode);
	}

	/**
	 * Updates the overlap similarity measure between the given nodes.
	 */
	@Override
	protected void update(Node node1, Node node2) {
		double fraction;
		if (this.matching.get(node1, node2) == null
				|| this.matching.get(node1, node2) == 0
				|| Math.min(this.amountOfNeighbors.get(node1),
						this.amountOfNeighbors.get(node2)) == 0)
			fraction = 0;
		else
			fraction = (this.matching.get(node1, node2))
					/ Math.min(this.amountOfNeighbors.get(node1),
							this.amountOfNeighbors.get(node2));

		Double overlapG = this.result.get(node1, node2);
		if (overlapG == null)
			this.binnedDistribution.decr(0.0);
		else
			this.binnedDistribution.decr(overlapG);
		this.result.put(node1, node2, fraction);
		this.binnedDistribution.incr(fraction);
	}
}
