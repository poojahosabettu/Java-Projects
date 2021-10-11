package com.packageChallenge.file.services.imp;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.packageChallenge.Exception.PackageChallengeBusinessException;
import com.packageChallenge.files.domain.PackageFile;
import com.packageChallenge.files.services.FileReaderService;
import com.packageChallenge.files.services.impl.PackageFileProcessorServiceImpl;
import com.packageChallenge.files.services.impl.PackageItemFileReaderServiceImpl;

public class PackageItemFileReaderServiceTest {
	
	private static FileReaderService fileReaderService;
	
	@Before
	public void initialize() {
		fileReaderService =  PackageItemFileReaderServiceImpl.getInstance();
	}
	
	@Test(expected = PackageChallengeBusinessException.class)
	public void testEmptyFileName() throws PackageChallengeBusinessException {
		String fileName = "";
		fileReaderService.readFile(fileName);
	}
	
	@Test
	public void testEmptyFileNameExceptionMessage(){
		String fileName = "";
		try {
			fileReaderService.readFile(fileName);
		} catch (PackageChallengeBusinessException e) {
			assertEquals(e.getMessage(),PackageItemFileReaderServiceImpl.EMPTY_FILE_PATH);
			
		}
	}
	
	@Test
	public void testValidPathofFilePackageFile() throws PackageChallengeBusinessException {
		String fileName = "/Users/bharathkumar/Desktop/Pooja_Work/MyWorkspace/PackageChallenge/src/main/resources/sampleInput.txt";
		PackageFile packageFile = fileReaderService.readFile(fileName);
		assertEquals(packageFile.getProcessPerRow().size(), 7);
	}
	
	@Test
	public void testInvalidWeightFormat(){
		String fileName = "/Users/bharathkumar/Desktop/Pooja_Work/MyWorkspace/PackageChallenge/src/test/resources/sampleInputInvalidWeightFormat.txt";
		PackageFile packageFile;
		try {
			packageFile = fileReaderService.readFile(fileName);
		} catch (PackageChallengeBusinessException e) {
			assertEquals(PackageItemFileReaderServiceImpl.INCORRECT_TOTAL_WEIGHT_FORMAT, e.getMessage());
		}
	}

}
