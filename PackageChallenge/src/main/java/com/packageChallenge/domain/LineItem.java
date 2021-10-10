package com.packageChallenge.domain;

import java.math.BigDecimal;

public class LineItem {
	private Integer index;
	private BigDecimal weight;
	private BigDecimal cost;

	public LineItem() {
	}

	public LineItem(int index, BigDecimal weigth, BigDecimal cost) {
		super();
		this.index = index;
		this.weight = weigth;
		this.cost = cost;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "["+index + ", " + weight + ", " + cost+"]";
	}

}
