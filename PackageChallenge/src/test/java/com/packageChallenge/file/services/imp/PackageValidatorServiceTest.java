package com.packageChallenge.file.services.imp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.packageChallenge.Exception.PackageChallengeBusinessException;
import com.packageChallenge.domain.LineItem;
import com.packageChallenge.domain.ProcessPerRow;
import com.packageChallenge.files.domain.PackageFile;
import com.packageChallenge.files.services.FileReaderService;
import com.packageChallenge.files.services.FileValidatorService;
import com.packageChallenge.files.services.impl.PackageItemFileReaderServiceImpl;
import com.packageChallenge.files.services.impl.PackageValidatorServiceImpl;

public class PackageValidatorServiceTest {
	
	private static FileReaderService fileReaderService;
	private static FileValidatorService fileValidatorService;
	
	@Before
	public void initialize() {
		fileReaderService =  PackageItemFileReaderServiceImpl.getInstance();
		fileValidatorService =  PackageValidatorServiceImpl.getInstance();
	}
	

	@Test(expected = PackageChallengeBusinessException.class)
	public void testInvalidPackageWeight() throws PackageChallengeBusinessException {
		String fileName = "/Users/bharathkumar/Desktop/Pooja_Work/MyWorkspace/PackageChallenge/src/test/resources/sampleInputInvalidPackageWeightFormat.txt";
		PackageFile packageFile =  fileReaderService.readFile(fileName);
	}
	
	@Test
	public void testInvalidPackageWeightExceptionMessage() {
		String fileName = "/Users/bharathkumar/Desktop/Pooja_Work/MyWorkspace/PackageChallenge/src/test/resources/sampleInputInvalidPackageWeightFormat.txt";
		try {
			PackageFile packageFile =  fileReaderService.readFile(fileName);
		} catch (PackageChallengeBusinessException e) {
			assertEquals(e.getMessage(), PackageValidatorServiceImpl.MAXIMUM_PACKAGE_WEIGHT_LIMIT);
		}
	}
	
	@Test(expected = PackageChallengeBusinessException.class)
	public void testInvalidLineItemWeight() throws PackageChallengeBusinessException {
		String fileName = "/Users/bharathkumar/Desktop/Pooja_Work/MyWorkspace/PackageChallenge/src/test/resources/sampleInputInvalidPackageWeightPerLineItem.txt";
		PackageFile packageFile =  fileReaderService.readFile(fileName);
	}
	
	@Test
	public void testInvalidLineItemWeightExceptionMessage() {
		String fileName = "/Users/bharathkumar/Desktop/Pooja_Work/MyWorkspace/PackageChallenge/src/test/resources/sampleInputInvalidPackageWeightPerLineItem.txt";
		try {
			PackageFile packageFile =  fileReaderService.readFile(fileName);
		} catch (PackageChallengeBusinessException e) {
			assertEquals(e.getMessage(), PackageValidatorServiceImpl.WEIGHT_LIMIT_ALLOWED_PER_LINE_ITEM);
		}
	}
	
	@Test(expected = PackageChallengeBusinessException.class)
	public void testInvalidLineItemIndex() throws PackageChallengeBusinessException {
		String fileName = "/Users/bharathkumar/Desktop/Pooja_Work/MyWorkspace/PackageChallenge/src/test/resources/sampleInputInvalidLineItemIndex.txt";
		PackageFile packageFile =  fileReaderService.readFile(fileName);
	}
	
	@Test
	public void testInvalidLineItemIndexExceptionMessage() {
		String fileName = "/Users/bharathkumar/Desktop/Pooja_Work/MyWorkspace/PackageChallenge/src/test/resources/sampleInputInvalidLineItemIndex.txt";
		try {
			PackageFile packageFile =  fileReaderService.readFile(fileName);
		} catch (PackageChallengeBusinessException e) {
			assertEquals(e.getMessage(), PackageValidatorServiceImpl.ITEM_INDEX_RANGE);
		}
	}
	
	@Test(expected = PackageChallengeBusinessException.class)
	public void testInvalidLineItemCost() throws PackageChallengeBusinessException {
		String fileName = "/Users/bharathkumar/Desktop/Pooja_Work/MyWorkspace/PackageChallenge/src/test/resources/sampleInputInvalidLineItemCost.txt";
		PackageFile packageFile =  fileReaderService.readFile(fileName);
	}
	
	@Test
	public void testInvalidLineItemCostExceptionMessage() {
		String fileName = "/Users/bharathkumar/Desktop/Pooja_Work/MyWorkspace/PackageChallenge/src/test/resources/sampleInputInvalidLineItemCost.txt";
		try {
			PackageFile packageFile =  fileReaderService.readFile(fileName);
		} catch (PackageChallengeBusinessException e) {
			assertEquals(e.getMessage(), PackageValidatorServiceImpl.COST_LIMIT_ALLOWED_PER_LINE_ITEM);
		}
	}
	
	@Test
	public void testValidData() throws PackageChallengeBusinessException {
		ProcessPerRow processPerRow = new ProcessPerRow();
		processPerRow.setWeightLimit(new BigDecimal(81));
		LineItem item = new LineItem(1, new BigDecimal(90), new BigDecimal(90));
		processPerRow.getLineItems().add(item);
		assertTrue(fileValidatorService.valdate(processPerRow));
	}
}
