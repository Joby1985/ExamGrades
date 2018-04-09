package com.hobsons.exams.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.hobsons.exams.StudentScore;

public class StudentDataStore {
	
	private static StudentDataStore instance = new StudentDataStore();
	private Map<Float,RankCounts> gradeMap = new TreeMap<>();
	private List<StudentScore> allScores;

	private StudentDataStore(){
		
	}
	
	public void initialize(List<StudentScore> allScores){
		createGradeMap(allScores);
		this.allScores = allScores;
	}
	
	public static StudentDataStore getInstance(){
		return instance;
	}
		
	public List<StudentScore> getAllScores(){
		List<StudentScore> copy = new ArrayList<>(allScores.size());
		copy.addAll(allScores);
		return copy;
	}
	
	
	/**
	 * Gets the percentile Rank for the given StudentScore object
	 * 
	 * @param score
	 * @return
	 */
	public float getPercentileRank(StudentScore score){
		Float gpaToSearch = score.getGpa();
		RankCounts rankCounts = gradeMap.get(gpaToSearch);
		int totalSample = allScores.size();
		float percentileRank = (float)((rankCounts.gpaCumulPosition + (0.5*rankCounts.sameGpaCount))/totalSample)*100;
		return percentileRank;
	}
	
	/**
	 * Creates an internal storage map (instead of DB) from where subsequent calculation can be done
	 * @param allScores
	 */
	private void createGradeMap(List<StudentScore> allScores){
		gradeMap.clear();
		
		for(StudentScore score: allScores){
			float gpa = score.getGpa();
			RankCounts gpaRankCount = gradeMap.get(gpa);
			if(gpaRankCount == null){
				gpaRankCount = new RankCounts();
				gpaRankCount.sameGpaCount = 1;
				gradeMap.put(gpa, gpaRankCount);
			}
			else{
				gpaRankCount.sameGpaCount++;
			}
		}
		// Set cumulative position of the rank - gpaCumulPosition (how many elements below this value)
		int cumulCount = 0;
		for(Map.Entry<Float,RankCounts> entry : gradeMap.entrySet()){
			RankCounts rankCount = entry.getValue();
			rankCount.gpaCumulPosition = cumulCount;
			cumulCount += rankCount.sameGpaCount;
		}
	}
	
	/**
	 * Internal class structure to store cumulative position and same GPA count
	 *
	 */
	class RankCounts{
		Integer gpaCumulPosition;
		Integer sameGpaCount;
	}
}