package com.packageChallenge.file.services.imp;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.packageChallenge.domain.LineItem;
import com.packageChallenge.domain.ProcessPerRow;
import com.packageChallenge.files.domain.PackageFile;
import com.packageChallenge.files.services.FileProcessorService;
import com.packageChallenge.files.services.impl.PackageFileProcessorServiceImpl;

public class PackageFileProcessorServiceTest {
	
	private static FileProcessorService  fileProcessorService;
	
	@Before
	public void initiliaze() {
		fileProcessorService =  PackageFileProcessorServiceImpl.getInstance();
	}
	
	@Test
	public void testLineItemWeightWithinPackageWeight() {
		PackageFile file = new PackageFile();
		//5 : (1,4.00,€50) (2,2.00,€40) (3,1.00,€30) (4,3.00,€45)
		ProcessPerRow processPerRow = new ProcessPerRow();
		processPerRow.setWeightLimit(new BigDecimal(5));
		List<LineItem> list = Arrays.asList(new LineItem[] {
				new LineItem(1, new BigDecimal(4), new BigDecimal(50)),
				new LineItem(2, new BigDecimal(2), new BigDecimal(45)),
				new LineItem(3, new BigDecimal(1), new BigDecimal(30)),
				new LineItem(4, new BigDecimal(3), new BigDecimal(45))
		});
		
		processPerRow.setLineItems(list);
		file.setProcessPerRow(Arrays.asList(new ProcessPerRow[] {
				processPerRow
		}));
		assertEquals("2,4",fileProcessorService.processFile(file));
	}
	
	@Test
	public void testLineItemWeightExceedingPackageWeightLimit() {
		PackageFile file = new PackageFile();
		//8 : (1,15.3,€34)
		ProcessPerRow processPerRow = new ProcessPerRow();
		processPerRow.setWeightLimit(new BigDecimal(8));
		List<LineItem> list = Arrays.asList(new LineItem[] {
				new LineItem(1, new BigDecimal(15.3), new BigDecimal(34)),
		});
		
		processPerRow.setLineItems(list);
		file.setProcessPerRow(Arrays.asList(new ProcessPerRow[] {
				processPerRow
		}));
		assertEquals("-",fileProcessorService.processFile(file));
	}

}
