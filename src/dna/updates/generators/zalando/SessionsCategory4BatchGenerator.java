package dna.updates.generators.zalando;

import dna.graph.Graph;
import dna.graph.datastructures.GraphDataStructure;
import dna.graph.datastructures.zalando.ZalandoGraphDataStructure;
import dna.graph.generators.zalando.Event;
import dna.graph.generators.zalando.EventColumn;
import dna.graph.generators.zalando.EventReader;
import dna.graph.nodes.Node;
import dna.graph.nodes.zalando.ZalandoNode;

public class SessionsCategory4BatchGenerator extends
		ZalandoEqualityBatchGenerator {

	/**
	 * Initializes the {@link SessionsCategory4BatchGenerator}.
	 * 
	 * @param gds
	 *            The {@link GraphDataStructure} of the graph to generate.
	 * @param timestampInit
	 *            The time right before start creating the graph.
	 * @param numberOfLinesPerBatch
	 *            The maximum number of {@code Event}s used for each batch. It
	 *            is the <u>maximum</u> number because the log file may have
	 *            fewer lines.
	 * @param eventsFilepath
	 *            The full path of the Zalando log file. Will be passed to
	 *            {@link EventReader}.
	 */
	public SessionsCategory4BatchGenerator(ZalandoGraphDataStructure gds,
			long timestampInit, int numberOfLinesPerBatch, String eventsFilepath) {
		super("SessionsCategory4", gds, timestampInit, null,
				numberOfLinesPerBatch, eventsFilepath, new EventColumn[] {
						EventColumn.SESSIONID, EventColumn.WARENGRUPPE4 },
				true, new EventColumn[] { EventColumn.SESSIONID,
						EventColumn.WARENGRUPPE4 }, true, false);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * <b>Because the "sessions category4 graph" is bipartite, only edges from
	 * sessions to category4 are added, not between sessions or between
	 * category4.</b>
	 */
	@Override
	void addEdgesForColumns(Graph g, Event event) {
		
		
		int nodeForEventIndex, mappingForColumnGroup;
		nodeForEventIndex = this.mappings.getMapping(
				this.columnGroupsToAddAsNodes[0], event);
		
		for (EventColumn[] columnGroup : this.columnGroupsToCheckForEquality) {
			mappingForColumnGroup = this.mappings.getMapping(columnGroup,
					event);

			for (int otherNodeIndex : this.nodesSortedByColumnGroupsToCheckForEquality
					.getNodes(mappingForColumnGroup, nodeForEventIndex)) {
				// add edges only between nodes of different columns (bipartite
				// graph)

				final Node nodeForEvent = this.nodesAdded.get(nodeForEventIndex);
				final Node otherNode = this.nodesAdded.get(otherNodeIndex);
				if (!ZalandoNode.equalType(nodeForEvent, otherNode))
					this.addBidirectionalEdge(g, nodeForEvent, otherNode, 1);
			}
		}
	}

}
