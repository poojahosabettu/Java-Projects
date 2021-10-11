package com.packageChallenge.files.services.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.packageChallenge.Exception.PackageChallengeBusinessException;
import com.packageChallenge.domain.LineItem;
import com.packageChallenge.domain.ProcessPerRow;
import com.packageChallenge.files.domain.PackageFile;
import com.packageChallenge.files.services.FileReaderService;
import com.packageChallenge.files.services.FileValidatorService;

public class PackageItemFileReaderServiceImpl implements FileReaderService {
	
	public static final String INCORRECT_TOTAL_WEIGHT_FORMAT = "Incorrect format of total weight for a package";
	public static final String INCORRECT_FORMAT_OF_DATA = "Could not find items in the format required";
	public static final String EMPTY_FILE_PATH = "The file path is empty, cannot be processed futher";
	private static PackageItemFileReaderServiceImpl fileReaderServiceImpl = null;
	private FileValidatorService fileValidatorService = PackageValidatorServiceImpl.getInstance();
	
	private PackageItemFileReaderServiceImpl() {
		
	}
	
	public static PackageItemFileReaderServiceImpl getInstance() {
		if(fileReaderServiceImpl == null)
			fileReaderServiceImpl = new PackageItemFileReaderServiceImpl();
		return fileReaderServiceImpl;
	}
	
	public PackageFile readFile(String fileName) throws PackageChallengeBusinessException {
		if(fileName == null || fileName.isEmpty())
			throw new PackageChallengeBusinessException(EMPTY_FILE_PATH);
		PackageFile packageFile = new PackageFile();
		
		try (FileReader file = new FileReader(fileName); BufferedReader reader = new BufferedReader(file)) {
			String row;
			while ((row = reader.readLine()) != null) {
				if(!row.isEmpty()) {
					String[] rowData = row.trim().split(":");
					packageFile.getProcessPerRow().add(parRowItems(rowData));
				}
			}
		} catch (Exception e) {
			throw new PackageChallengeBusinessException(e.getMessage());
		}
	
		return packageFile;
		
	}
	private ProcessPerRow parRowItems(String[] rowData) throws PackageChallengeBusinessException {
		ProcessPerRow perRow = new ProcessPerRow();
		if(!rowData[0].trim().matches("[0-9]*"))
			throw new PackageChallengeBusinessException(INCORRECT_TOTAL_WEIGHT_FORMAT);
		perRow.setWeightLimit(BigDecimal.valueOf(Double.valueOf(rowData[0].trim())));
		perRow.getLineItems().addAll(parseLineItems(rowData));
		fileValidatorService.valdate(perRow);
		return perRow;
	}
	
	private List<LineItem> parseLineItems(String[] rowData) throws PackageChallengeBusinessException {
		Pattern pattern = Pattern.compile("(\\d+),(\\d+.*\\d+),€(\\d+)");
		String[] lineItemString = rowData[1].trim().split("\\)\\s*\\(");
		List<LineItem> lineItems = new ArrayList<LineItem>();
		
		for (String itemData : lineItemString) {
			Matcher matcher = pattern.matcher(itemData);
			if (matcher.find()) {
				Integer index = Integer.parseInt(matcher.group(1));
				BigDecimal weight = BigDecimal.valueOf(Double.valueOf(matcher.group(2)));
				BigDecimal cost  = BigDecimal.valueOf(Double.valueOf(matcher.group(3)));
				LineItem lineItem = new LineItem(index,weight,cost);
				lineItems.add(lineItem);
				
			} else {
				throw new PackageChallengeBusinessException(INCORRECT_FORMAT_OF_DATA);
			}
		}

		return lineItems;
		
	}

}
