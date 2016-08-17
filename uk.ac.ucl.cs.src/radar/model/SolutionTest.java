package radar.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SolutionTest {
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@SuppressWarnings("deprecation")
	@Test
	public void equalEmptySolutions() {
		// testing equals works on empty solutions
		Solution s1 = new Solution();
		Solution s2 = new Solution();
		Assert.assertEquals(s1, s2);
		//assert s1.equals(s2);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void equalOnSingleSolutionWithOneDecision() {
		// testing equals works on solution with single decision
		List<String> options = new ArrayList<String>();
		options.add("X");
		options.add("Y");
		Decision d = new Decision();
		d.setDecisionLabel("D");
		d.setOptions(options);

		Map<Decision, String> m3 = new LinkedHashMap<Decision, String>();
		m3.put(d, "X");
		Solution s3 = new Solution(m3);

		Map<Decision, String> m4 = new LinkedHashMap<Decision, String>();
		m4.put(d, "X");
		Solution s4 = new Solution(m4);
		Assert.assertEquals(s3,s4);
		//assert s3.equals(s4);
	}
	@SuppressWarnings("deprecation")
	@Test
	public void idempotence() {
		// Testing idempotence of add in SolutionSet:
		// If we add the same solution twice, the set should hold it only once.
		List<String> options = new ArrayList<String>();
		options.add("X");
		options.add("Y");
		Decision d = new Decision();
		d.setDecisionLabel("D");
		d.setOptions(options);

		Map<Decision, String> m3 = new LinkedHashMap<Decision, String>();
		m3.put(d, "X");
		Solution s3 = new Solution(m3);

		Map<Decision, String> m4 = new LinkedHashMap<Decision, String>();
		m4.put(d, "X");
		Solution s4 = new Solution(m4);
		
		SolutionSet slns = new SolutionSet();
		slns.add(s3);
		slns.add(s4);
		Assert.assertEquals(1, slns.size());
		//assert slns.size()==1;
	}
	
	

}
