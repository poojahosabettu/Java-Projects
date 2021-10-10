package com.packageChallenge.files.utils;

import java.math.BigDecimal;
import java.util.List;

import com.packageChallenge.Exception.PackageChallengeBusinessException;
import com.packageChallenge.domain.LineItem;
import com.packageChallenge.domain.ProcessPerRow;

public class PackageValidator {
	
	private static final BigDecimal MAX_WEIGHT = new BigDecimal(100);
	private static final BigDecimal MAX_WEIGHT_PER_ITEM = new BigDecimal(100);
	private static final BigDecimal MAX_COST_PER_ITEM = new BigDecimal(100);
	private static final Integer MAX_LINE_ITEMS_ALLOWED = 15;

	public static void valdate(ProcessPerRow perRow) throws PackageChallengeBusinessException {
		validate(perRow.getWeightLimit());
		validate(perRow.getLineItems());
	}

	private static void validate(List<LineItem> lineItems) throws PackageChallengeBusinessException {
		if(MAX_LINE_ITEMS_ALLOWED<lineItems.size())
			throw new PackageChallengeBusinessException("Exceeded total line items allowed per package");
		for(LineItem item:lineItems)
			validate(item);
	}

	private static void validate(LineItem item) throws PackageChallengeBusinessException {
		if(MAX_WEIGHT_PER_ITEM.compareTo(item.getWeight())<0)
			throw new PackageChallengeBusinessException("Exceeded weight limit allowed per line item");
		if(MAX_COST_PER_ITEM.compareTo(item.getCost())<0)
			throw new PackageChallengeBusinessException("Exceeded cost limit allowed per line item");
	}

	private static void validate(BigDecimal weightLimit) throws PackageChallengeBusinessException {
		
		if(MAX_WEIGHT.compareTo(weightLimit)<0)
			throw new PackageChallengeBusinessException("Exceeded maximum package weight limit of 100");
	}

}
