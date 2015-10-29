/**
 * 
 */
package com.ankit.ScrambledDataReconstructor;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * @author Ankit Bahuguna
 * @email: ankit.bahuguna@cs.tum.edu
 */
public class BNC {
	HashMap<String, Double> wordFrequencyMap = new HashMap<String, Double>();
	List<String> wordList = new ArrayList<String>();

	/**
	 * @return the wordList
	 */
	public List<String> getWordList() {
		return this.wordList;
	}

	/**
	 * @return the wordList
	 */
	public HashMap<String, Double> getwordFrequencyMap() {
		return this.wordFrequencyMap;
	}

	public BNC() {
		super();
		if (this.wordFrequencyMap.isEmpty()) {
			this.wordFrequencyMap = setWordFrequencyDictionary();
		}
	}

	/**
	 * @return
	 */
	private HashMap<String, Double> setWordFrequencyDictionary() {
		HashMap<String, Double> hm = new HashMap<String, Double>();
		String filename = "bnc_wordcount.txt";
		try {
			// Open File
			FileInputStream fstream = new FileInputStream(filename);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;

			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				if (strLine.startsWith("100106029")) {
					// System.out.println(strLine);
					continue;
				}
				// Process each line in the input file
				String[] tmp = strLine.split(" ");

				// System.out.println(strLine);
				String key = tmp[1];
				double value = Double.parseDouble(tmp[0]);

				if (hm.containsKey(key)) {
					Double oldValue = hm.get(key);
					// if (oldValue < value) {
					double newValue = oldValue + value;
					// System.out.println(key + ":" + newValue);
					hm.put(key, oldValue + newValue);
					// }
				} else {
					hm.put(key, value);
					this.wordList.add(key);
				}
			}

			// Close streams
			in.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}

		// TODO Auto-generated method stub
		return hm;
	}

}
