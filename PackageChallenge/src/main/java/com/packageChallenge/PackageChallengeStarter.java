package com.packageChallenge;

import com.packageChallenge.Exception.PackageChallengeBusinessException;
import com.packageChallenge.files.domain.PackageFile;
import com.packageChallenge.files.services.FileProcessorService;
import com.packageChallenge.files.services.FileReaderService;
import com.packageChallenge.files.services.impl.PackageFileProcessorServiceImpl;
import com.packageChallenge.files.services.impl.PackageItemFileReaderServiceImpl;

public class PackageChallengeStarter {
	
	private static FileReaderService fileProcessorService =  PackageItemFileReaderServiceImpl.getInstace();
	private static FileProcessorService packageProcessService =  PackageFileProcessorServiceImpl.getInstance();
	
	public static void main(String[] args) {
		String fileName = args[0];
		try {
			PackageFile packageFile =  fileProcessorService.readFile(fileName);
			packageProcessService.processFile(packageFile);
		} catch (PackageChallengeBusinessException e) {
			e.printStackTrace();
		}
	}
	
	

}
