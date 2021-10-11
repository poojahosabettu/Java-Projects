package com.packageChallenge.files.services;

import com.packageChallenge.Exception.PackageChallengeBusinessException;
import com.packageChallenge.domain.ProcessPerRow;

public interface FileValidatorService {
	public boolean valdate(ProcessPerRow perRow) throws PackageChallengeBusinessException;
}
