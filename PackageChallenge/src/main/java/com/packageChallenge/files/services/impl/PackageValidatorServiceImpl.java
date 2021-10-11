package com.packageChallenge.files.services.impl;

import java.math.BigDecimal;
import java.util.List;

import com.packageChallenge.Exception.PackageChallengeBusinessException;
import com.packageChallenge.domain.LineItem;
import com.packageChallenge.domain.ProcessPerRow;
import com.packageChallenge.files.services.FileValidatorService;

public class PackageValidatorServiceImpl implements FileValidatorService{
	
	public static final String ITEM_INDEX_RANGE = "Item index not in range of 1-15";
	public static final String MAXIMUM_PACKAGE_WEIGHT_LIMIT = "Exceeded maximum package weight limit of 100";
	public static final String COST_LIMIT_ALLOWED_PER_LINE_ITEM = "Exceeded cost limit allowed per line item";
	public static final String WEIGHT_LIMIT_ALLOWED_PER_LINE_ITEM = "Exceeded weight limit allowed per line item";
	public static final String TOTAL_LINE_ITEMS_ALLOWED_PER_PACKAGE = "Exceeded total line items allowed per package";
	private static final BigDecimal MAX_WEIGHT = new BigDecimal(100);
	private static final BigDecimal MAX_WEIGHT_PER_ITEM = new BigDecimal(100);
	private static final BigDecimal MAX_COST_PER_ITEM = new BigDecimal(100);
	private static final Integer MAX_LINE_ITEMS_ALLOWED = 15;
	private static FileValidatorService fileValidatorService = null;
	
	private PackageValidatorServiceImpl() {
		
	}

	@Override
	public boolean valdate(ProcessPerRow perRow) throws PackageChallengeBusinessException {
		return validate(perRow.getWeightLimit()) && validate(perRow.getLineItems());
	}

	private boolean validate(List<LineItem> lineItems) throws PackageChallengeBusinessException {
		if(MAX_LINE_ITEMS_ALLOWED<lineItems.size())
			throw new PackageChallengeBusinessException(TOTAL_LINE_ITEMS_ALLOWED_PER_PACKAGE);
		Boolean validatedValue = true;
		for(LineItem item:lineItems)
			validatedValue = validatedValue && validate(item);
		return validatedValue;
	}

	private boolean validate(LineItem item) throws PackageChallengeBusinessException {
		if(MAX_WEIGHT_PER_ITEM.compareTo(item.getWeight())<0)
			throw new PackageChallengeBusinessException(WEIGHT_LIMIT_ALLOWED_PER_LINE_ITEM);
		if(MAX_COST_PER_ITEM.compareTo(item.getCost())<0)
			throw new PackageChallengeBusinessException(COST_LIMIT_ALLOWED_PER_LINE_ITEM);
		if(MAX_LINE_ITEMS_ALLOWED<=item.getIndex() &&  item.getIndex()>0)
			throw new PackageChallengeBusinessException(ITEM_INDEX_RANGE);
		return true;
	}

	private boolean validate(BigDecimal weightLimit) throws PackageChallengeBusinessException {
		
		if(MAX_WEIGHT.compareTo(weightLimit)<0)
			throw new PackageChallengeBusinessException(MAXIMUM_PACKAGE_WEIGHT_LIMIT);
		return true;
	}

	public static FileValidatorService getInstance() {
		if(fileValidatorService == null)
			fileValidatorService = new PackageValidatorServiceImpl();
		return fileValidatorService;
		
	}

}
