package control;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;


public class Scores{
	
	private ArrayList<Integer> integers = new ArrayList<Integer>();
	private int numScores = 10; // Defining max # of high scores to save
	private String scores;
	private String filename;
	
	/*
	 * This method handles all the file updating actions instead of calling each method from controller/
	 * Should first read previous scores from the file via readScores() to populate the list, then
	 * try to add the new score, then call writeScores to just write the new scores to the file.
	 * 
	 */
	void addLastScore(int aScore, String version){
		if (version.equals("GUI")) {
			filename = "ScoresGUI.txt";
		} else {
			filename = "ScoresText.txt";
		}
		readScores();
		integers.add(aScore);
		sortScores();
		buildScores();
		writeScores();
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
		for (int i = 0; i < integers.size(); i++) {
		  int num = integers.get(i);
		  sb.append("Your clear time/turn count was: "+num+ System.lineSeparator());
		}
		scores = sb.toString();
	}

	void readScores(){
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
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
			BufferedWriter fw = new BufferedWriter(new FileWriter(filename));
			fw.write("Top 10 clear times/turn counts"+System.lineSeparator());
			fw.write(scores);
			fw.close();
			
		} catch (IOException ignored) {}
	
	}
}