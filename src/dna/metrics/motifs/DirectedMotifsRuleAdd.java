package dna.metrics.motifs;

import dna.metrics.motifs.DirectedMotifs.DirectedMotifType;
import dna.series.data.DistributionLong;

public class DirectedMotifsRuleAdd implements DirectedMotifsRule {

	public DirectedMotifType m;
	public int i;

	public DirectedMotifsRuleAdd(DirectedMotifType m) {
		this.m = m;
		this.i = DirectedMotifs.getIndex(this.m);
	}

	@Override
	public void execute(DistributionLong motifs, boolean add) {
		if (add) {
			motifs.incr(this.i);
		} else {
			motifs.decr(this.i);
		}
	}

	public String toString() {
		return "+(" + this.m + ")";
	}

}
