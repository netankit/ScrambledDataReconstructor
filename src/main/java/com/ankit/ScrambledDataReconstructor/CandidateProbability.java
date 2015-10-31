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
			// System.out.println("Spaced Word: #" + spacedword + "#");
			List<String> bigramArr = new ArrayList<String>();

			char[] charArray = spacedword.toCharArray();
			int numBigramsInWord = 0;
			for (int i = 0, j = 1; i < charArray.length && j < charArray.length; i++, j++) {
				String bigram = String.valueOf(charArray[i]) + String.valueOf(charArray[j]);
				bigramArr.add(bigram);
				// bigramCountMap: Stores the individual bigram and its
				// respective corpus count
				if (bigramCountMap.containsKey(bigram)) {
					bigramCountMap.put(bigram, bigramCountMap.get(bigram) + wordFrequencyMap.get(word));
				} else {
					bigramCountMap.put(bigram, wordFrequencyMap.get(word));
				}
				numBigramsInWord++;
			}

			// Compute Total Bigrams in the Corpus using the BNC Corpus
			// Statistics
			totalBigrams = totalBigrams + (numBigramsInWord * wordFrequencyMap.get(word));
			// bigramContextCountMap: Stores how many times, given Bigrams along
			// with their immediate contexts, co-occur together
			for (int i = 0, j = i + 2; i < bigramArr.size() && j < bigramArr.size(); i++, j++) {
				String wordInContext = bigramArr.get(i) + bigramArr.get(j);

				// System.out.println(wordInContext);

				// if (wordInContext == " the") {
				// System.out.println(wordInContext);
				// Double wordInContextCount = bigramContextCount(spacedword,
				// wordInContext);
				// System.out.println(wordInContextCount);
				// System.exit(0);
				// }

				Double wordInContextCount = bigramContextCount(spacedword, wordInContext);
				// System.out.println(wordInContextCount);
				if (bigramContextCountMap.containsKey(wordInContext)) {
					bigramContextCountMap.put(wordInContext, bigramContextCountMap.get(wordInContext)
							+ (wordInContextCount * wordFrequencyMap.get(word)));
				} else {
					bigramContextCountMap.put(wordInContext, wordInContextCount * wordFrequencyMap.get(word));
				}
			}
		} // End of wordList Traversal.

		System.out.println("Total Bigrams in the Corpus:" + totalBigrams);

		/**
		 * Conditional Probability: P(A | B) = P(A ∩ B) / P(B)
		 * 
		 * Read this as follows: Probability of the current bigram (he) given
		 * the previous bigram.( t)
		 * 
		 * Iterate through all the keys in the bigramContextCountMap
		 **/
		for (String bgcontext : bigramContextCountMap.keySet()) {
			// A -> currentBigram Token
			String currentBigram = bgcontext.substring(2, 4);
			// B -> previousBigram token
			String previousBigram = bgcontext.substring(0, 2);

			double bgcontextProbability = (bigramContextCountMap.get(bgcontext) / bigramCountMap.get(previousBigram));
			if (bgcontextProbability != 0.0) {
				cProbMap.put(bgcontext, bgcontextProbability);
			}
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
