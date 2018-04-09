package examGardes;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hobsons.exams.calculator.PercentileRankCalculator;
import com.hobsons.exams.data.DataLoader;
import com.hobsons.exams.data.extractor.DataExtractorCommaDelimitedImpl;
import com.hobsons.exams.data.extractor.DataInputStrategyStringInputImpl;
import com.hobsons.exams.exceptions.DataException;

public class TestPercentileRankCalculator {
	
	private static String data;
	private static String expectedOutput;
	
	@BeforeClass
	public static void setUp() throws Exception {
		//Setup input and expected output.
		
		StringBuilder bld = new StringBuilder();
		bld.append("471908US,\"Randy Perez\",1.60\n");
		bld.append("957625US,\"Alice Brown\",3.50\n");
		bld.append("909401US,\"Maria Russell\",3.90\n");
		bld.append("342575US,\"Shirley Evans,3.5\n");
		bld.append("780367US,\"Daniel Bell\",2.20\n");
		bld.append("841786US,”Willie Richardson”,3.60\n");
		bld.append("881365US,\"Ruby Lee\",2.70\n");
		bld.append("848124US,\"Peter Powell\",2.30\n");
		bld.append("497579US,\"Bruce Nelson\",3.70\n");
		bld.append("756454US,\"Bonnie Murphy\",3.50\n");
		bld.append("551871US,\"Chris \"\"Mac\"\" Cooper\",2.70\n");
		bld.append("734476US,\"Christine Walker\",2.70\n");
		bld.append("138197US,\"Alan Robinson\",1.80\n");
		bld.append("744270US,\"Justin Scott\",3.80\n");
		bld.append("140419US,\"James Edwards\",2.40\n");
		bld.append("263737US,\"Ann Mitchell\",3.60\n");
		bld.append("522471US,\"Eugene Rivera\",3.50\n");
		bld.append("022169US,\"Irene Simmons\", 2.20\n");
		bld.append("690697US,\"Joshua Über\",3.60\n");
		bld.append("094778US,\"Jonathan Reed\",3.50\n");
		bld.append("73780US,\"Johnny Ross\",2.20\n");
		bld.append("256090US,\"Jessica Howard\",3.60\n");
		bld.append("775011US,\"Frank Kelly\",2.20\n");
		bld.append("333218US,\"Kathy Patterson\",3.7\n");
		data = bld.toString();
		
		StringBuilder expected = new StringBuilder();
		expected.append("	Student name                              GPA     Percentile Rank\n").
		append("\"Randy Perez\"                            1.60                2.08\n").
		append("\"Alice Brown\"                            3.50               56.25\n").
		append("\"Maria Russell\"                          3.90               97.92\n").
		append("\"Shirley Evans                           3.50               56.25\n").
		append("\"Daniel Bell\"                            2.20               16.67\n").
		append("”Willie Richardson”                      3.60               75.00\n").
		append("\"Ruby Lee\"                               2.70               39.58\n").
		append("\"Peter Powell\"                           2.30               27.08\n").
		append("\"Bruce Nelson\"                           3.70               87.50\n").
		append("\"Bonnie Murphy\"                          3.50               56.25\n").
		append("\"Chris \"\"Mac\"\" Cooper\"                   2.70               39.58\n").
		append("\"Christine Walker\"                       2.70               39.58\n").
		append("\"Alan Robinson\"                          1.80                6.25\n").
		append("\"Justin Scott\"                           3.80               93.75\n").
		append("\"James Edwards\"                          2.40               31.25\n").
		append("\"Ann Mitchell\"                           3.60               75.00\n").
		append("\"Eugene Rivera\"                          3.50               56.25\n").
		append("\"Irene Simmons\"                          2.20               16.67\n").
		append("\"Joshua Über\"                            3.60               75.00\n").
		append("\"Jonathan Reed\"                          3.50               56.25\n").
		append("\"Johnny Ross\"                            2.20               16.67\n").
		append("\"Jessica Howard\"                         3.60               75.00\n").
		append("\"Frank Kelly\"                            2.20               16.67\n").
		append("\"Kathy Patterson\"                        3.70               87.50");
		
		expectedOutput = expected.toString();
	}
	
	@Test
	public void getStudentsPercentileRanks() throws DataException{
		DataLoader loader = new DataLoader(new DataInputStrategyStringInputImpl(data), new DataExtractorCommaDelimitedImpl());
		PercentileRankCalculator calc = new PercentileRankCalculator(loader);
		String result = calc.getStudentsPercentileRanks().toString();	
		assertEquals(expectedOutput.trim(), result.trim());
	}

	@Test
	public void getStudentPercentileRankByName(){
		DataLoader loader = new DataLoader(new DataInputStrategyStringInputImpl(data), new DataExtractorCommaDelimitedImpl());
		PercentileRankCalculator calc = new PercentileRankCalculator(loader);
		float res = calc.getStudentPercentileRankByName("\"Alan Robinson\"");
		assertEquals(6.25f, res,0.0);
	}
	
	@Test
	public void getStudentPercentileRankById(){
		DataLoader loader = new DataLoader(new DataInputStrategyStringInputImpl(data), new DataExtractorCommaDelimitedImpl());
		PercentileRankCalculator calc = new PercentileRankCalculator(loader);
		float res = calc.getStudentPercentileRankById("138197US");
		assertEquals(6.25f, res,0.0);
	}
	
	@AfterClass
	public static void tearDown() throws Exception {
		data = null;
		expectedOutput = null;
	}
}
