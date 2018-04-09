package com.hobsons.exams.calculator;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hobsons.exams.StudentScore;
import com.hobsons.exams.data.DataLoader;
import com.hobsons.exams.data.StudentDataStore;
import com.hobsons.exams.exceptions.DataException;

public class PercentileRankCalculator{
	private final static Logger logger = Logger.getLogger(PercentileRankCalculator.class);
	private DataLoader loader;
	private Sorter sorter; 
	
	public void setSorter(Sorter sorter) {
		this.sorter = sorter;
	}

	public PercentileRankCalculator(DataLoader loader){
		this.loader = loader;
	}
	
	/**
	 * Get Student percentile Ranks
	 * @return
	 */
	public String getStudentsPercentileRanks() throws DataException{
		try{
			loader.processRequest();
		}
		catch(DataException e){
			logger.error(e.getMessage(), e);
			throw e;
		}
		
		Map<StudentScore, Float> studentPercentilRanks = calculateStudentsPercentileRanks();
		
		StringBuilder output = new StringBuilder();
		output.append(String.format("%-25s%20s%20s","Student name", "GPA","Percentile Rank"));
		for(Map.Entry<StudentScore, Float> entry: studentPercentilRanks.entrySet()){
			output.append("\n");
			output.append(String.format("%-25s%20.2f%20.2f",entry.getKey().getName(),entry.getKey().getGpa(),entry.getValue()));
		}
		return output.toString();		
	}
	

	
	/**
	 * Get the student percentile by using Name
	 * @param studId
	 * @return
	 */
	public float getStudentPercentileRankByName(String studName){
		StudentDataStore store = StudentDataStore.getInstance();
		List<StudentScore> allScores = store.getAllScores();
		if(studName != null){
			for(StudentScore score : allScores){
				if(studName.equals(score.getName())){
					return store.getPercentileRank(score);
				}
			}			
		}
		return 0;
	}
	
	/**
	 * Get the student percentile by using Id
	 * @param studId
	 * @return
	 */
	public float getStudentPercentileRankById(String studId){
		StudentDataStore store = StudentDataStore.getInstance();
		List<StudentScore> allScores = store.getAllScores();
		if(studId != null){
			for(StudentScore score : allScores){
				if(studId.equals(score.getId())){
					return store.getPercentileRank(score);
				}
			}		
		}
		return 0;
	}
	

	/**
	 * Fetch All Student scores and percentile details
	 * @return
	 */
	private Map<StudentScore, Float> calculateStudentsPercentileRanks(){
		StudentDataStore store = StudentDataStore.getInstance();
		List<StudentScore> allScores = store.getAllScores();
		if(sorter!=null){
			Collections.sort(allScores,sorter);
		}
		Map<StudentScore, Float> studPercentileRanks = new LinkedHashMap<>(allScores.size());
		for(StudentScore score : allScores){
			studPercentileRanks.put(score, store.getPercentileRank(score));
		}
		return studPercentileRanks;
	}
}