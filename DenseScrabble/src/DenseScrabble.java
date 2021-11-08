import java.io.IOException;

public class DenseScrabble extends TextFileAccessor {
	private final String[] ALPHABET = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
			"q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
	private final int[] SCRABBLE_SCORES = { 1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4,
			10 };
	private final int ASCII_CODE_a = 97;
	private int highestScore;
	private int lineNum;

	public DenseScrabble(String filename) throws IOException {
		openFile(filename);

	}

	@Override
	protected void processLine(String curLine) {
		String[] curLineArr = curLine.split(" ");
		int curWordScore = 0;
		for (int i = 0; i < curLineArr.length; i++) {
			char[] curWordArr = curLineArr[i].toLowerCase().toCharArray();
			for (int a = 0; a < curWordArr.length; a++) {
				if (Character.isLetter(curWordArr[a]) && getLetterPos(curWordArr[a]) < 26) {
					curWordScore = curWordScore + (SCRABBLE_SCORES[getLetterPos(curWordArr[a])]);
					// winning string
				}

			}
			// calculate density
			if (highestScore < curWordScore) {
				highestScore = curWordScore;
			}
			lineNum++;

		}

	}

	private int getLetterPos(char ch) {
		return ch - ASCII_CODE_a;
	}

	@Override
	public void printReport() {
		System.out.println("winner: " + "/winningstring/" + " score: " + highestScore + " Line Number: " + lineNum);

	}

}
