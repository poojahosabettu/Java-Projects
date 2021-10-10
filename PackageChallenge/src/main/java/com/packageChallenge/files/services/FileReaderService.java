package com.packageChallenge.files.services;

import com.packageChallenge.Exception.PackageChallengeBusinessException;
import com.packageChallenge.files.domain.PackageFile;

public interface FileReaderService {

	PackageFile readFile(String fileName) throws PackageChallengeBusinessException;

}
