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
	public String processFile(PackageFile packageFile) {
		return packageFile.getProcessPerRow().stream().
				map(perRow ->processPackage(perRow)).
				collect(Collectors.joining("\n"));
		
	}

	private String processPackage(ProcessPerRow processPerRow) {
		int weight = roundValue(processPerRow.getWeightLimit());
		int size = processPerRow.getLineItems().size();
		
		BigDecimal[][] cost = new BigDecimal[size+1][weight+1];
		
		Collections.sort(processPerRow.getLineItems(),(i1,i2)->{
			if(i1.getCost() == i2.getCost())
				return i2.getWeight().compareTo(i1.getWeight());
			return i1.getWeight().compareTo(i2.getWeight());
		});
		
		List<LineItem> lineItems = processPerRow.getLineItems();
		
		
		for(int i = 0;i<=size;++i) {
			for(int j = 0;j<=weight;++j) {
				if(i == 0||j == 0)
					cost[i][j] = BigDecimal.ZERO;
				else {
					LineItem lineItem = lineItems.get(i-1);
					cost[i][j] = cost[i-1][j];
					if(lineItem.getWeight().compareTo(new BigDecimal(j))<=0) {
						BigDecimal currentValue = cost[i-1][j - roundValue(lineItem.getWeight())].add(lineItem.getCost());
						cost[i][j] = currentValue.compareTo(cost[i][j])>0?currentValue:cost[i][j];
					}
				}
			}
		}
		return displayLineItems(extractLineItems(processPerRow, size, cost, lineItems));
	}

	private String displayLineItems(List<Integer> indexes) {
		String result =
                indexes.stream()
                        .sorted().map(index -> String.valueOf(index))
                        .collect(Collectors.joining(","));
		result =  result.isEmpty() ? "-" : result;
		return result;
	}

	private List<Integer> extractLineItems(ProcessPerRow processPerRow, int size, BigDecimal[][] cost,
			List<LineItem> lineItems) {
		
		int weight = roundValue(processPerRow.getWeightLimit());
		
		BigDecimal res = cost[size][weight];
		
		List<Integer> indexes = new ArrayList<>();
		
		for (int i = size;res != null && res.compareTo(BigDecimal.ZERO) > 0 && i>0 ;--i) {
			if (cost[i][weight] != cost[i - 1][weight]) {
				LineItem item = lineItems.get(i - 1);

				weight -= roundValue(item.getWeight());
				indexes.add(item.getIndex());
			}
			res = cost[i][weight];
			
		}
		return indexes;
	}
	
	private static int roundValue(BigDecimal value) {
		return value.setScale(0, RoundingMode.HALF_UP).intValue();
	}

}
