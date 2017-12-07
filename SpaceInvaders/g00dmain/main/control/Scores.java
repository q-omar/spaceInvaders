package control;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;


class Scores{
	
	private final ArrayList<Integer> integers = new ArrayList<>();
	private String scores;
	private String filename;
	
	/*
	 * This method handles all the file updating actions instead of calling each method from controller/
	 * Should first read previous scores from the file via readScores() to populate the list, then
	 * try to add the new score, then call writeScores to just write the new scores to the file.
	 * 
	 */
	void addLastScore(int aScore, String version) throws IOException{
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

	private void sortScores(){
		Collections.sort(integers);
		int numScores = 10;
		if (integers.size() > numScores){
			integers.remove(numScores);
		}
	}
	
	ArrayList<Integer> getScores() {
		return integers;
	}

	private void buildScores(){
		StringBuilder sb = new StringBuilder(); //go from list to string
        for (Integer integer : integers) {
            int num = integer;
            sb.append("Your clear time/turn count was: ").append(num).append(System.lineSeparator());
        }
		scores = sb.toString();
	}

	private void readScores() throws IOException {
		
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
		} catch (FileNotFoundException foe) {
			// If there is no score file, the program will just proceed and create a new file to store the scores.
		}
	}
	
	private void writeScores() throws IOException{
		BufferedWriter fw = new BufferedWriter(new FileWriter(filename));
		fw.write("Top 10 clear times/turn counts"+System.lineSeparator());
		fw.write(scores);
		fw.close();
	
	}
}