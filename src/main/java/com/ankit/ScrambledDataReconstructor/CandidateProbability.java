/**
 * 
 */
package com.ankit.ScrambledDataReconstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * @author Ankit Bahuguna
 * @email: ankit.bahuguna@cs.tum.edu
 */
public class CandidateProbability {

	/**
	 * @param cProbMap
	 */
	public CandidateProbability() {
		super();
		if (cProbMap.isEmpty()) {
			this.setcProbMap();
		}
	}

	// cProbMap = bigramContextProbabilityMap: Contains the bigram along with
	// their immediate contexts with their respective conditional probability
	private HashMap<String, Double> cProbMap = new HashMap<String, Double>();

	/**
	 * @return the cProbMap
	 */
	public HashMap<String, Double> getcProbMap() {
		return cProbMap;
	}

	/**
	 * @param cProbMap
	 *            the cProbMap to set
	 */
	public void setcProbMap() {
		HashMap<String, Double> cProbMap = new HashMap<String, Double>();
		BNC bnc = new BNC();
		HashMap<String, Double> wordFrequencyMap = bnc.getwordFrequencyMap();
		List<String> wordList = bnc.getWordList();
		HashMap<String, Double> bigramCountMap = new HashMap<String, Double>();
		HashMap<String, Double> bigramContextCountMap = new HashMap<String, Double>();

		double totalBigrams = 0;
		System.out.println("Total words in WordList: " + wordList.size());
		for (String word : wordList) {
			String spacedword = " " + word + " ";
			char[] charArray = spacedword.toCharArray();
			List<String> bigramArr = new ArrayList<String>();
			for (int i = 0, j = 1; i < charArray.length && j < charArray.length; i++, j++) {
				String bigram = String.valueOf(charArray[i]) + String.valueOf(charArray[j]);
				// System.out.println(bigram);
				bigramArr.add(bigram);
			}
			// Compute Total Bigrams in the Corpus using the BNC Corpus
			// Statistics
			totalBigrams += bigramArr.size() * wordFrequencyMap.get(word);

			// bigramCountMap: Stores the individual bigram and its respective
			// corpus count
			for (String bigramItem : bigramArr) {
				if (bigramCountMap.containsKey(bigramItem)) {
					bigramCountMap.put(bigramItem, bigramCountMap.get(bigramItem) + wordFrequencyMap.get(word));
				} else {
					bigramCountMap.put(bigramItem, wordFrequencyMap.get(word));
				}
			}

			// bigramContextCountMap: Stores how many times, given Bigrams along
			// with their immediate contexts, co-occur together
			for (int i = 0, j = 1; i < bigramArr.size() && j < bigramArr.size(); i++, j++) {
				String wordInContext = bigramArr.get(i) + bigramArr.get(j);
				Double wordInContextCount = bigramContextCount(spacedword, wordInContext);
				if (bigramContextCountMap.containsKey(wordInContext)) {
					bigramContextCountMap.put(wordInContext, bigramContextCountMap.get(wordInContext)
							+ (wordInContextCount * wordFrequencyMap.get(word)));
				} else {
					bigramContextCountMap.put(wordInContext, wordInContextCount * wordFrequencyMap.get(word));
				}

			}

		} // End of wordList Traversal.

		System.out.println("Total Bigrams in the Corpus:" + totalBigrams);

		// Conditional Probability: P(A | B) = P(A ∩ B) / P(B)
		// Iterate through all the keys in the bigramContextCountMap
		for (String bgcontext : bigramContextCountMap.keySet()) {
			// B -> firstBigram Token
			String firstBigram = bgcontext.substring(0, 2);

			// A -> secondBigram Token
			String secondBigram = bgcontext.substring(2);
			double bgcontextProbability = (bigramContextCountMap.get(bgcontext) / bigramCountMap.get(firstBigram));
			if (bgcontextProbability == 0.0) {
				continue;
			}
			cProbMap.put(bgcontext, bgcontextProbability);
		}

		this.cProbMap = cProbMap;
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
