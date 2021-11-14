import java.io.IOException;

/**
 * Represents a file that is scanned and is then read where the sum of scores of
 * characters in a word is saved until the highest scoring word is found by
 * comparing each word to the word saved and saving the highest scoring word
 * until a new highest scoring word is found. This results with the final
 * winning string, its score, and it's line number is printed out.
 * 
 * @author sagesilberman
 *
 */
public class DenseScrabble extends TextFileAccessor {
	private final String[] ALPHABET = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
			"q", "r", "s", "t", "u", "v", "w", "x", "y", "z" }; // array of the English alphabet
	private final int[] SCRABBLE_SCORES = { 1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4,
			10 }; // array of respective Scrabble scores to each letter
	private final int ASCII_CODE_a = 97;
	private double highestScore; // highest scoring word
	private int winningLineNum; // winning line number
	private int lineNum; // holds the line number
	private String winningString; // winning string of characters that form a word

	/**
	 * Initializes the code by setting the starting value for numOnes to 0 and
	 * frequency as an array with 26 values. Opens the file but throws IOException
	 * if it can't open the file.
	 * 
	 * @param filename the name of file
	 * @throws IOException if can't open file
	 */
	public DenseScrabble(String filename) throws IOException {
		openFile(filename);
		highestScore = 0;
		lineNum = 1;

	}

	@Override
	protected void processLine(String curLine) {
		String[] curLineArr = curLine.split(" ");
		for (int i = 0; i < curLineArr.length; i++) {
			double curWordScore = 0;
			char[] curWordArr = curLineArr[i].toLowerCase().toCharArray();
			for (int a = 0; a < curWordArr.length; a++) {
				if (Character.isLetter(curWordArr[a]) && getLetterPos(curWordArr[a]) < 26) {
					curWordScore = curWordScore + (SCRABBLE_SCORES[getLetterPos(curWordArr[a])]);
				}
			}
			curWordScore = curWordScore / curLineArr[i].length();
			if (highestScore < curWordScore) {
				highestScore = curWordScore;
				winningString = curLineArr[i];
				winningLineNum = lineNum;
			}

		}
		lineNum++;
	}

	/**
	 * Returns the position of a character by assigning each letter of the alphabet
	 * to a number
	 * 
	 * @param ch the character from file
	 * @return the position of a character by assigning each letter of the alphabet
	 *         to a number
	 */
	private int getLetterPos(char ch) {
		return ch - ASCII_CODE_a;
	}

	@Override
	public void printReport() {
		System.out.println("winner: " + winningString + " score: " + highestScore + " Line Number: " + winningLineNum);

	}

}
