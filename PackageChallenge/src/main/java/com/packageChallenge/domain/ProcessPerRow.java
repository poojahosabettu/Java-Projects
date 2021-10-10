package com.packageChallenge.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProcessPerRow {
	
	private BigDecimal weightLimit;
	private List<LineItem> lineItems;

	public ProcessPerRow() {
		this.weightLimit = BigDecimal.ZERO;
		this.lineItems = new ArrayList<LineItem>();
	}

	public ProcessPerRow(BigDecimal weightLimit, List<LineItem> lineItems) {
		this.weightLimit = weightLimit;
		this.lineItems = lineItems;
	}

	public BigDecimal getWeightLimit() {
		return weightLimit;
	}

	public void setWeightLimit(BigDecimal weightLimit) {
		this.weightLimit = weightLimit;
	}

	public List<LineItem> getLineItems() {
		return lineItems;
	}

	public void setLineItems(List<LineItem> lineItems) {
		this.lineItems = lineItems;
	}

	@Override
	public String toString() {
		return "ProcessPerRow [weightLimit=" + weightLimit + ", lineItems=" + lineItems + "]";
	}

}
