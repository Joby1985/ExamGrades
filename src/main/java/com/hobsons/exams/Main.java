package com.hobsons.exams;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;

import com.hobsons.exams.calculator.GradeSorter;
import com.hobsons.exams.calculator.NameSorter;
import com.hobsons.exams.calculator.PercentileRankCalculator;
import com.hobsons.exams.data.DataLoader;
import com.hobsons.exams.data.extractor.DataExtractorCommaDelimitedImpl;
import com.hobsons.exams.data.extractor.DataInputStrategyTextFileImpl;
import com.hobsons.exams.exceptions.DataException;

public class Main {
	private final static Logger logger = Logger.getLogger(Main.class);
	
	enum SORT_ITEM {NAME,GRADE};
	
	public static void main(String[] args) {
		
		BasicConfigurator.configure();
		
		System.out.println("Please input File path: ");
		String filePath = null;
		DataLoader loader = null;
		
		boolean asecndingOrder=true;
		SORT_ITEM sortItem = null;
		
		BufferedReader reader = null;
		
		try{
			reader = new BufferedReader(new InputStreamReader(System.in));
			filePath = reader.readLine();

			System.out.println("Sort?: Y/N");
			String option = reader.readLine();
			if("Y".equals(option) || "y".equals(option)){
				System.out.println("Sort by ?: Grade/Name(G/N) [Default - G]");
				String sortingItem = reader.readLine();
				if(sortingItem == null || "".equals(sortingItem)){
					sortItem = SORT_ITEM.GRADE;
				}
				else {
					sortingItem = sortingItem.toLowerCase();
					if("n".equals(sortingItem) || "name".equals(sortingItem)){
						sortItem = SORT_ITEM.NAME;
					}
					else{
						sortItem = SORT_ITEM.GRADE;
					}
				}
				System.out.println("Sorting Order ?: Ascending/Descending (A/D) [Default - A]");
				String sortOrder = reader.readLine();
				if(sortOrder == null || "".equals(sortOrder)){
					asecndingOrder = true;
				}
				else{
					sortOrder = sortOrder.toLowerCase();
					if("d".equals(sortOrder) || "descending".equals(sortOrder)){
						asecndingOrder = false;
					}
					else{
						asecndingOrder = true;
					}
				}
			}
					
			loader = new DataLoader(new DataInputStrategyTextFileImpl(filePath), new DataExtractorCommaDelimitedImpl());
		}
		catch(Exception e){
			logger.error(e.getMessage(), e);
			return;
		}
		
		PercentileRankCalculator calc = new PercentileRankCalculator(loader);
		
		if(sortItem == SORT_ITEM.NAME){
			calc.setSorter(new NameSorter(asecndingOrder));
		}
		else if (sortItem == SORT_ITEM.GRADE){
			calc.setSorter(new GradeSorter(asecndingOrder));
		}
		try{
			System.out.println(calc.getStudentsPercentileRanks());
		}
		catch(DataException e){
			logger.error(e.getMessage(), e);
		}
	}
}
