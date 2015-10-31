/**
 * 
 */
package com.ankit.ScrambledDataReconstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * 
 * @author Ankit Bahuguna
 * @email: ankit.bahuguna@cs.tum.edu
 */
public class TestApp {

	@Test
	public void processLineTest() {
		String filename = "inputfile.txt";
		ReadInput ri = new ReadInput(filename);
		String line = "|de|  | f|Cl|nf|ed|au| i|ti|  |ma|ha|or|nn|ou| S|on|nd|on|";
		String[] out = ri.processLine(line);
		System.out.println(out);
	}

	@Test
	public void readInputDataTest() {
		String filename = "inputfilecopy.txt";
		ReadInput ri = new ReadInput(filename);
		ri.printInputDataArray();
	}

	@Test
	public void testContextWindow() {

		HashMap<String, Double> bigramCountMap = new HashMap<String, Double>();

		String word = "magic mike";
		System.out.println("Original Word: " + word);
		String spacedword = " " + word + " ";
		char[] charArray = spacedword.toCharArray();

		// for (char c : charArray) {
		// System.out.print(c + "*");
		// }
		List<String> bigramArr = new ArrayList<String>();
		for (int i = 0, j = 1; i < charArray.length && j < charArray.length; i++, j++) {
			String bigram = String.valueOf(charArray[i]) + String.valueOf(charArray[j]);
			System.out.println(bigram);
			bigramArr.add(bigram);

		}

	}

	@Test
	public void testCandidateProbabilities() {
		CandidateProbability cp = new CandidateProbability();

		HashMap<String, Double> cpm = cp.getcProbMap();
		// System.out.println(cpm.get("the "));

		BNC bnc = new BNC();
		HashMap<String, Double> wordFrequencyMap = bnc.getwordFrequencyMap();

		// System.out.println(wordFrequencyMap.get("the"));

		int i = 0;

		for (Map.Entry<String, Double> entry : cpm.entrySet()) {
			// if (i == 10)
			// break;
			String key = entry.getKey();
			Double value = entry.getValue();
			// System.out.println(key + ":" + value);
			i++;
		}
		System.out.println("Total Candidate Probabilities in the HashMap: " + i);

	}

	public double bigramContextCount(String input, String bigram) {
		int index = input.indexOf(bigram);
		double count = 0;
		while (index != -1) {
			count++;
			input = input.substring(index + 1);
			index = input.indexOf(bigram);
		}
		return count;
	}

}
