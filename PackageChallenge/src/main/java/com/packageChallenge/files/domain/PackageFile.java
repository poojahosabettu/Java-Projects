package com.packageChallenge.files.domain;

import java.util.ArrayList;
import java.util.List;

import com.packageChallenge.domain.ProcessPerRow;

public class PackageFile {
	private List<ProcessPerRow>  processPerRow;

	public PackageFile(List<ProcessPerRow> processPerRow) {
		this.processPerRow = processPerRow;
	}

	public PackageFile() {
		this.processPerRow = new ArrayList<ProcessPerRow>();
	}

	public List<ProcessPerRow> getProcessPerRow() {
		return processPerRow;
	}

	public void setProcessPerRow(List<ProcessPerRow> processPerRow) {
		this.processPerRow = processPerRow;
	}

	@Override
	public String toString() {
		return "PackageFile [processPerRow=" + processPerRow + "]";
	}
	
}
