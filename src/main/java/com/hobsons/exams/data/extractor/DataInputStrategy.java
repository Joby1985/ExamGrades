package com.hobsons.exams.data.extractor;

import java.util.List;

import com.hobsons.exams.StudentScore;
import com.hobsons.exams.exceptions.DataException;

/**
 * Strategy to get input data.
 *
 */
public interface DataInputStrategy {
	public List<StudentScore> getAllScores(DataExtractor extractor) throws DataException;
}
