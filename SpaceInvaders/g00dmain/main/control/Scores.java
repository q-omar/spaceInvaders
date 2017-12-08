package control;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/** This class handles the scores for both GUI and Text version
* @param integers is an ArrayList of scores in integer form
* @param scores are the list of scores
* @param filename is the name of the scores file  
*/

class Scores{
	
	private final ArrayList<Integer> integers = new ArrayList<>();
	private String scores;
	private String filename;
	
	/**
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
    
    /** This method sorts the scores such that only the top ten scores remain 
    */

	private void sortScores(){
		Collections.sort(integers);
		int numScores = 10;
		if (integers.size() > numScores){
			integers.remove(numScores);
		}
	}
	/** This method returns the ArrayList of scores
    */
	ArrayList<Integer> getScores() {
		return integers;
	}
    
    /**This method converts the ArrayList to a String form
    */
	private void buildScores(){
		StringBuilder sb = new StringBuilder(); //go from list to string
        for (Integer integer : integers) {
            int num = integer;
            sb.append("Your clear time/turn count was: ").append(num).append(System.lineSeparator());
        }
		scores = sb.toString();
	}
    /** This method reads the scores from the appropriate text file  
    */
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
	
    /** This method writes the top 10 scores from the game. 
    */
	private void writeScores() throws IOException{
		BufferedWriter fw = new BufferedWriter(new FileWriter(filename));
		fw.write("Top 10 clear times/turn counts"+System.lineSeparator());
		fw.write(scores);
		fw.close();
	
	}
}
