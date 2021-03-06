package dna.graph.datastructures.zalando;

import dna.graph.datastructures.DHashTable;
import dna.graph.datastructures.DataStructure.ListType;
import dna.graph.edges.DirectedWeightedEdge;
import dna.graph.edges.UndirectedWeightedEdge;
import dna.graph.nodes.zalando.DirectedZalandoNode;
import dna.graph.nodes.zalando.UndirectedZalandoNode;
import dna.graph.weights.DoubleWeight;
import dna.graph.weights.IntWeight;
import dna.graph.weights.Weight.WeightSelection;

public class DefaultZalandoGraphDataStructures {

	public static final ZalandoGraphDataStructure CUSTOMERS = new ZalandoGraphDataStructure(
			ZalandoGraphDataStructure.getList(ListType.GlobalNodeList,
					DHashTable.class, ListType.GlobalEdgeList,
					DHashTable.class, ListType.LocalEdgeList, DHashTable.class),
			UndirectedZalandoNode.class, UndirectedWeightedEdge.class, null,
			null, DoubleWeight.class, WeightSelection.One);

	public static final ZalandoGraphDataStructure CUSTOMERS_ABSOLUTE = new ZalandoGraphDataStructure(
			ZalandoGraphDataStructure.getList(ListType.GlobalNodeList,
					DHashTable.class, ListType.GlobalEdgeList,
					DHashTable.class, ListType.LocalEdgeList, DHashTable.class),
			UndirectedZalandoNode.class, UndirectedWeightedEdge.class, null,
			null, IntWeight.class, WeightSelection.One);

	public static final ZalandoGraphDataStructure PRODUCTS = new ZalandoGraphDataStructure(
			ZalandoGraphDataStructure.getList(ListType.GlobalNodeList,
					DHashTable.class, ListType.GlobalEdgeList,
					DHashTable.class, ListType.LocalEdgeList, DHashTable.class),
			UndirectedZalandoNode.class, UndirectedWeightedEdge.class, null,
			null, DoubleWeight.class, WeightSelection.One);

	public static final ZalandoGraphDataStructure PRODUCTS_ABSOLUTE = new ZalandoGraphDataStructure(
			ZalandoGraphDataStructure.getList(ListType.GlobalNodeList,
					DHashTable.class, ListType.GlobalEdgeList,
					DHashTable.class, ListType.LocalEdgeList, DHashTable.class),
			UndirectedZalandoNode.class, UndirectedWeightedEdge.class, null,
			null, IntWeight.class, WeightSelection.One);

	public static final ZalandoGraphDataStructure CUSTOMERS_PRODUCTS = new ZalandoGraphDataStructure(
			ZalandoGraphDataStructure.getList(ListType.GlobalNodeList,
					DHashTable.class, ListType.GlobalEdgeList,
					DHashTable.class, ListType.LocalEdgeList, DHashTable.class),
			UndirectedZalandoNode.class, UndirectedWeightedEdge.class, null,
			null, IntWeight.class, WeightSelection.One);

	public static final ZalandoGraphDataStructure CUSTOMERS_BRANDS = new ZalandoGraphDataStructure(
			ZalandoGraphDataStructure.getList(ListType.GlobalNodeList,
					DHashTable.class, ListType.GlobalEdgeList,
					DHashTable.class, ListType.LocalEdgeList, DHashTable.class),
			UndirectedZalandoNode.class, UndirectedWeightedEdge.class, null,
			null, IntWeight.class, WeightSelection.One);

	public static final ZalandoGraphDataStructure CUSTOMERS_ACTIONS = new ZalandoGraphDataStructure(
			ZalandoGraphDataStructure.getList(ListType.GlobalNodeList,
					DHashTable.class, ListType.GlobalEdgeList,
					DHashTable.class, ListType.LocalEdgeList, DHashTable.class),
			UndirectedZalandoNode.class, UndirectedWeightedEdge.class, null,
			null, DoubleWeight.class, WeightSelection.One);

	public static final ZalandoGraphDataStructure PRODUCTS_ACTIONS = new ZalandoGraphDataStructure(
			ZalandoGraphDataStructure.getList(ListType.GlobalNodeList,
					DHashTable.class, ListType.GlobalEdgeList,
					DHashTable.class, ListType.LocalEdgeList, DHashTable.class),
			UndirectedZalandoNode.class, UndirectedWeightedEdge.class, null,
			null, DoubleWeight.class, WeightSelection.One);

	public static final ZalandoGraphDataStructure CUSTOMERS_CHRONOLOGY = new ZalandoGraphDataStructure(
			ZalandoGraphDataStructure.getList(ListType.GlobalNodeList,
					DHashTable.class, ListType.GlobalEdgeList,
					DHashTable.class, ListType.LocalEdgeList, DHashTable.class),
			DirectedZalandoNode.class, DirectedWeightedEdge.class, null, null,
			IntWeight.class, WeightSelection.One);

	public static final ZalandoGraphDataStructure PRODUCTS_ACTIONS_CHRONOLOGY = new ZalandoGraphDataStructure(
			ZalandoGraphDataStructure.getList(ListType.GlobalNodeList,
					DHashTable.class, ListType.GlobalEdgeList,
					DHashTable.class, ListType.LocalEdgeList, DHashTable.class),
			DirectedZalandoNode.class, DirectedWeightedEdge.class, null, null,
			IntWeight.class, WeightSelection.One);

	public static final ZalandoGraphDataStructure SESSIONS_CATEGORY4 = new ZalandoGraphDataStructure(
			ZalandoGraphDataStructure.getList(ListType.GlobalNodeList,
					DHashTable.class, ListType.GlobalEdgeList,
					DHashTable.class, ListType.LocalEdgeList, DHashTable.class),
			UndirectedZalandoNode.class, UndirectedWeightedEdge.class, null,
			null, IntWeight.class, WeightSelection.One);

	public static final ZalandoGraphDataStructure CUSTOMERS_CATEGORY4 = new ZalandoGraphDataStructure(
			ZalandoGraphDataStructure.getList(ListType.GlobalNodeList,
					DHashTable.class, ListType.GlobalEdgeList,
					DHashTable.class, ListType.LocalEdgeList, DHashTable.class),
			UndirectedZalandoNode.class, UndirectedWeightedEdge.class, null,
			null, IntWeight.class, WeightSelection.One);

}
