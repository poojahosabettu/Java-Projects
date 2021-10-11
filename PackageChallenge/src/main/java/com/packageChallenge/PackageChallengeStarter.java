package com.packageChallenge;

import com.packageChallenge.Exception.PackageChallengeBusinessException;
import com.packageChallenge.files.domain.PackageFile;
import com.packageChallenge.files.services.FileProcessorService;
import com.packageChallenge.files.services.FileReaderService;
import com.packageChallenge.files.services.impl.PackageFileProcessorServiceImpl;
import com.packageChallenge.files.services.impl.PackageItemFileReaderServiceImpl;

public class PackageChallengeStarter {
	
	private static FileReaderService fileReaderService =  PackageItemFileReaderServiceImpl.getInstance();
	private static FileProcessorService  fileProcessorService =  PackageFileProcessorServiceImpl.getInstance();
	
	public static void main(String[] args) throws PackageChallengeBusinessException {
		String fileName = args[0];
		try {
			PackageFile packageFile =  fileReaderService.readFile(fileName);
			System.out.println(fileProcessorService.processFile(packageFile));
		} catch (PackageChallengeBusinessException e) {
			throw new PackageChallengeBusinessException(e.getMessage());
		}
	}

}
