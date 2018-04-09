package com.hobsons.exams.calculator;

import com.hobsons.exams.StudentScore;

public class NameSorter implements Sorter {
	
	boolean ascending;
	public NameSorter(boolean ascending){
		this.ascending = ascending;
	}

	@Override
	public int compare(StudentScore o1, StudentScore o2) {
		return (ascending?o1.getName().compareTo(o2.getName()):o2.getName().compareTo(o1.getName()));
	}

}
