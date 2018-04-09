package com.hobsons.exams.data.extractor;

public class DataExtractorCommaDelimitedImpl extends AbstractDataExtractor {	
	@Override
	protected String getSplitterToken() {
		return ",";
	}
}
