package com.packageChallenge.files.services.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.packageChallenge.domain.LineItem;
import com.packageChallenge.domain.ProcessPerRow;
import com.packageChallenge.files.domain.PackageFile;
import com.packageChallenge.files.services.FileProcessorService;

public class PackageFileProcessorServiceImpl implements FileProcessorService {
	
	
	private static PackageFileProcessorServiceImpl fileProcessorServiceImpl = null;
	
	private PackageFileProcessorServiceImpl() {
		
	}
	
	public static PackageFileProcessorServiceImpl getInstance() {
		if(fileProcessorServiceImpl == null)
			fileProcessorServiceImpl = new PackageFileProcessorServiceImpl();
		return fileProcessorServiceImpl;
	}
	

	@Override
	public void processFile(PackageFile packageFile) {
		for(ProcessPerRow processPerRow:packageFile.getProcessPerRow()) {
			processPackage(processPerRow);
		}
		
	}

	private void processPackage(ProcessPerRow processPerRow) {
		int weight = round(processPerRow.getWeightLimit());
		int size = processPerRow.getLineItems().size();
		
		BigDecimal[][] cost = new BigDecimal[size+1][weight+1];
		
		Collections.sort(processPerRow.getLineItems(),(i1,i2)->{
			if(i1.getCost() == i2.getCost())
				return i2.getWeight().compareTo(i1.getWeight());
			return i1.getWeight().compareTo(i2.getWeight());
		});
		
		//Collections.reverse(processPerRow.getLineItems());
		
		List<LineItem> lineItems = processPerRow.getLineItems();
		
		System.out.println(processPerRow.getLineItems());
		
		for(int i = 0;i<=size;++i) {
			for(int j = 0;j<=weight;++j) {
				if(i == 0||j == 0)
					cost[i][j] = BigDecimal.ZERO;
				else {
					LineItem lineItem = lineItems.get(i-1);
					cost[i][j] = cost[i-1][j];
					if(lineItem.getWeight().compareTo(new BigDecimal(j))<=0) {
						BigDecimal currentValue = cost[i-1][j - round(lineItem.getWeight())].add(lineItem.getCost());
						cost[i][j] = currentValue.compareTo(cost[i][j])>0?currentValue:cost[i][j];
					}
				}
			}
		}
		
		
		weight = round(processPerRow.getWeightLimit());
		
		BigDecimal res = cost[size][weight];

		//System.out.println("Cost is "+res);
		
		List<Integer> indexes = new ArrayList<>();
		
		/*for(int i = size;i>0 && res.compareTo(BigDecimal.ZERO)>0;--i) {
			if(res == cost[i-1][weight])
				continue;
			else {
				indexes.add(lineItems.get(i-1).getIndex());
				res = res.subtract(lineItems.get(i-1).getCost());
				weight -= round(lineItems.get(i-1).getWeight());
			}
		}*/
		
		int i = size;
		while (res != null && res.compareTo(BigDecimal.ZERO) > 0 && i>0) {

			if (cost[i][weight] != cost[i - 1][weight]) {
				LineItem item = lineItems.get(i - 1);

				weight -= round(item.getWeight());
				indexes.add(item.getIndex());
			}
			res = cost[i][weight];
			i--;
			
		}

		
		String result =
                indexes.stream()
                        .sorted().map(index -> String.valueOf(index))
                        .collect(Collectors.joining(","));
		result =  result.isEmpty() ? "-" : result;
		
		System.out.println(result);
		
		
	}
	
	private static int round(BigDecimal value) {
		return value.setScale(0, RoundingMode.HALF_UP).intValue();
	}

}
