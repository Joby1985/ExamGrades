package com.hobsons.exams.calculator;


import com.hobsons.exams.StudentScore;

public class GradeSorter implements Sorter {
	
	boolean ascending;
	public GradeSorter(boolean ascending){
		this.ascending = ascending;
	}

	@Override
	public int compare(StudentScore o1, StudentScore o2) {
		return o1.getGpa() < o2.getGpa() ? (ascending?-1:1) 
			     : o1.getGpa() > o2.getGpa() ? (ascending?1:-1) 
			     : 0;
	}

}
