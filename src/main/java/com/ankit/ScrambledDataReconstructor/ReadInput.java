/**
 * 
 */
package com.ankit.ScrambledDataReconstructor;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Ankit Bahuguna
 * @email: ankit.bahuguna@cs.tum.edu
 */
public class ReadInput {

	/**
	 * @param inputarr
	 */
	public ReadInput(String filename) {
		super();
		if (this.inputarr.isEmpty()) {
			this.setInputDataAsArray(filename);
		}
	}

	private List<String[]> inputarr = new ArrayList<String[]>();

	public void setInputDataAsArray(String filename) {
		List<String[]> arr = new ArrayList<String[]>();
		try {
			// Open File
			FileInputStream fstream = new FileInputStream(filename);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			int rownum = 0;
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				if (!strLine.startsWith("|"))
					continue;
				// Process each line in the input file
				// System.out.println(strLine);
				String[] line_tmp = processLine(strLine);
				arr.add(line_tmp);
				rownum += 1;
				// break;
			}
			// Close streams
			in.close();
			// Set the Value of the inputarr variable
			this.inputarr = arr;
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}

	}

	/**
	 * Processes Each line in Input file and returns string arrays with strings
	 * 
	 * @param strLine
	 * @return
	 */
	public String[] processLine(String strLine) {
		// TODO Auto-generated method stub
		String linetmp = strLine.trim();
		String line = strLine.substring(1, strLine.length() - 1);
		String[] line_arr = line.split("\\|");
		return line_arr;
	}

	/**
	 * Get the Input Data in as a String array format
	 * 
	 * @return
	 */
	public List<String[]> getInputDataArray() {
		// System.out.println("Size: " + this.inputarr.size());
		return this.inputarr;
	}

	/**
	 * Print Input Data Array on the Screen.
	 */
	public void printInputDataArray() {
		List<String[]> arr = getInputDataArray();
		int i = 1;
		for (String[] item : arr) {
			System.out.print("Input Row " + i + ": ");
			for (int j = 0; j < item.length; j++) {
				System.out.print(item[j] + "#");
			}
			System.out.println();
			i++;
		}

	}

}
