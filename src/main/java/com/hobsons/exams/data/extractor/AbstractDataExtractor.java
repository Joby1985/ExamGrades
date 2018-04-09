package com.hobsons.exams.data.extractor;

import org.apache.commons.lang3.StringUtils;

import com.hobsons.exams.StudentScore;
import com.hobsons.exams.exceptions.DataException;

/**
 * Single extractor class that handles the logic to convert a given input text into the StudentScore object
 * 
 * 	if any parameters or oder in the input is changed, this class needs to be corrected.
 *
 */
public abstract class AbstractDataExtractor implements DataExtractor{
	@Override
	public StudentScore extract(String source) throws DataException{
		String data[] = StringUtils.split(source,getSplitterToken());
		
		if(data.length < 3){	
			throw new DataException("Error in input: "+source+".Insufficient Data. This will be ignored in processing...!");				
		}
		else{
			float gpa = 0;
			try {
				gpa = Float.parseFloat(data[2]);
			}
			catch(NumberFormatException ex) {
				throw new DataException("Error in input: "+source+". Invalid GPA score. This will be ignored in processing...!",ex);
			}
			StudentScore score = new StudentScore();
			score.setId(data[0]);
			score.setName(data[1]);
			score.setGpa(gpa);
			return score;
		}
	}
	
	protected abstract String getSplitterToken();
}
