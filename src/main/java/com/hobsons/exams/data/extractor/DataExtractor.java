package com.hobsons.exams.data.extractor;

import com.hobsons.exams.StudentScore;
import com.hobsons.exams.exceptions.DataException;

public interface DataExtractor {
	public StudentScore extract(String source) throws DataException;
}
