package control;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;


public class Scores{
	
	private ArrayList<Integer> integers = new ArrayList<Integer>();
	private int numScores = 10; // Defining max # of high scores to save
	private String scores;
	
	/*
	 * This method handles all the file updating actions instead of calling each method from controller/
	 * Should first read previous scores from the file via readScores() to populate the list, then
	 * try to add the new score, then call writeScores to just write the new scores to the file.
	 * 
	 * Since we're just using integers, it might be easier to use a binary file to directly readInt and writeInt
	 * instead of converting to Strings
	 * 
	 * Read and write methods need adjustment so that we don't save every score, just the top 10 or whatever
	 * max we choose, so the file doesn't keep getting longer.
	 */
	void addLastScore(int aScore){
		
		readScores();
		integers.add(aScore);
		sortScores();
		
		buildScores();
		writeScores();
	
	}
	
	// For testing, will print the whole scores array
	public String toString() {
		String toString = "";
		for (int index = 0; index < integers.size(); index++) {
			toString += integers.get(index) + " ";
		}
		return toString;
	}
	
	private boolean contains(int anInt) {
		boolean containsInt = false;
		for (int index = 0; index < integers.size(); index++) {
			if (integers.get(index) == anInt) {
				containsInt = true;
			}
		}
		return containsInt;
	}

	void sortScores(){
		Collections.sort(integers);
		if (integers.size() > numScores){
			integers.remove(integers.indexOf(Collections.min(integers)));
		}
		
	}
	
	ArrayList<Integer> getScores() {
		return integers;
	}

	void buildScores(){
		StringBuilder sb = new StringBuilder(); //go from list to string
		for (int i = integers.size() - 1; i >= 0; i--) {
		  int num = integers.get(i);
		  sb.append("Your time was: "+num+"\n");
		}
		scores = sb.toString();
		System.out.println(scores);
	}

	void readScores(){
		try {
			File file = new File("scores.txt");
			BufferedReader reader = new BufferedReader(new FileReader("scores.txt"));
			reader.readLine();
			String line = reader.readLine();
			
			while (line != null) {
				int anInt = Integer.parseInt(line.replaceAll("[^0-9]", "")); //parse the line as an integer
				integers.add(anInt); //add the integer to a list
				line = reader.readLine(); //read next line
			}
			reader.close();

		} catch (IOException ignored) {}
	
	}
	
	void writeScores(){
		try {
			
			BufferedWriter fw = new BufferedWriter(new FileWriter("scores.txt"));
			fw.write("Top 10 Survivability Times"+"\n");
			fw.write(scores);
			fw.close();
			
		} catch (IOException ignored) {}
	
	}
	

}