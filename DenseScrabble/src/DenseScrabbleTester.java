import java.util.Scanner;

/**
 * If given a text file, run Dense Scrabble and find the word with the highest
 * score based on the Scrabble letter scores.
 * 
 * @author sagesilberman
 *
 */
public class DenseScrabbleTester {

	public static void main(String args[]) {
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("enter file name");
			String filename = scanner.next();
			DenseScrabble scrabble = new DenseScrabble(filename);
			scrabble.processFile();
			scrabble.printReport();
			scanner.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
