package control;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;


public class Scores{
	
	ArrayList<Integer> integers = new ArrayList<Integer>();
	private String scores;
	
	void addLastScore(String aScore){
		int anInt = Integer.parseInt(aScore); //parse last score from controller as int
		integers.add(anInt); //add it to array 
	}

	void sortScores(){
		Collections.sort(integers); //sort array 
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
			fw.write(scores);
			fw.close();
			
			
		} catch (IOException ignored) {}
	
	}
	

}