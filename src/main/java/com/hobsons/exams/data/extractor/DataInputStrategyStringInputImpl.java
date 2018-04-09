package com.hobsons.exams.data.extractor;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.hobsons.exams.StudentScore;
import com.hobsons.exams.exceptions.DataException;

public class DataInputStrategyStringInputImpl implements DataInputStrategy {
	private String source;
	
	private final static Logger logger = Logger.getLogger(DataInputStrategyStringInputImpl.class);
	
	public DataInputStrategyStringInputImpl(String source){
		this.source = source;
	}
	
	@Override
	public List<StudentScore> getAllScores(DataExtractor extractor) throws DataException {
		String[] lines = StringUtils.split(source,'\n');
		List<StudentScore> scores = new ArrayList<>();
		for(String line: lines){
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

}
