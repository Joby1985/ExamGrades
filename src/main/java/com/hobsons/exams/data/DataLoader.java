package com.hobsons.exams.data;

import java.util.List;

import com.hobsons.exams.StudentScore;
import com.hobsons.exams.data.extractor.DataExtractor;
import com.hobsons.exams.data.extractor.DataInputStrategy;
import com.hobsons.exams.exceptions.DataException;

public class DataLoader{
	
	private DataInputStrategy inpStrategy;
	private DataExtractor extractor;
	
	public DataLoader(DataInputStrategy inpStrategy,DataExtractor extractor){
		this.inpStrategy = inpStrategy;
		this.extractor = extractor;
	}
	
	public void processRequest() throws DataException{
		List<StudentScore> scores = inpStrategy.getAllScores(extractor);
		StudentDataStore dataStore = StudentDataStore.getInstance();
		dataStore.initialize(scores);
	}
}
