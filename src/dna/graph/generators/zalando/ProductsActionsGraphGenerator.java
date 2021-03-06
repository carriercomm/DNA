package dna.graph.generators.zalando;

import dna.graph.datastructures.GraphDataStructure;
import dna.graph.datastructures.zalando.ZalandoGraphDataStructure;

public class ProductsActionsGraphGenerator extends
		ZalandoEqualityGraphGenerator {

	/**
	 * Initializes the {@link ProductsActionsGraphGenerator}.
	 * 
	 * @param gds
	 *            The {@link GraphDataStructure} of the graph to generate.
	 * @param timestampInit
	 *            The time right before start creating the graph.
	 * @param maxNumberOfEvents
	 *            The maximum number of {@code Event}s used for graph
	 *            generation. It is the <u>maximum</u> number because the log
	 *            file may have fewer lines.
	 * @param eventsFilepath
	 *            The full path of the Zalando log file. Will be passed to
	 *            {@link EventReader}.
	 */
	public ProductsActionsGraphGenerator(ZalandoGraphDataStructure gds,
			long timestampInit, int maxNumberOfEvents, String eventsFilepath) {
		super("ProductsActions", gds, timestampInit, null, maxNumberOfEvents,
				eventsFilepath, new EventColumn[] { EventColumn.FAMILYSKU,
						EventColumn.AKTION }, false,
				new EventColumn[] { EventColumn.PERMANENTCOOKIEID }, false,
				false);
	}

}
