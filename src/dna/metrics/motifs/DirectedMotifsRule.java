package dna.metrics.motifs;

import dna.series.data.DistributionLong;

public interface DirectedMotifsRule {
	public void execute(DistributionLong motifs, boolean add);
}
