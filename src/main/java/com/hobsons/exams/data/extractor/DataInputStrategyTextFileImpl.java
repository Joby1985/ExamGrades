package com.hobsons.exams.data.extractor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hobsons.exams.StudentScore;
import com.hobsons.exams.exceptions.DataException;

public class DataInputStrategyTextFileImpl implements DataInputStrategy{

	private String inputFilePath;
	private final static Logger logger = Logger.getLogger(DataInputStrategyTextFileImpl.class);
	
	public DataInputStrategyTextFileImpl(String inputFilePath){
		this.inputFilePath = inputFilePath;
	}
	
	public List<StudentScore> getAllScores(DataExtractor extractor) throws DataException{
		Path filePath = FileSystems.getDefault().getPath(inputFilePath);
		try(
			BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(Files.newInputStream(filePath)))){
			String line = null;
				
			List<StudentScore> scores = new ArrayList<>();
			while ((line = bufferedReader.readLine()) != null) {
				try{
					scores.add(extractor.extract(line));
				}
				catch(DataException e){
					//Individual data line in error. Just log and continue with other data.
					logger.warn(e.getMessage());
				}
			}
			return scores;
		}
		catch(Exception e){
			throw new DataException("Error occured while reading file: "+inputFilePath,e);
		}
	}
}
