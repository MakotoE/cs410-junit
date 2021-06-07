import java.util.Arrays;
import java.util.regex.Pattern;

public class StringManipulation implements StringManipulationInterface {
	private String str;
	private static final Pattern matchWhitespace = Pattern.compile("\\s+");

	@Override
	public String getString() {
		return str;
	}

	@Override
	public void setString(String string) {
		str = string;
	}

	@Override
	public int count() {
		var s = str.strip();
		if (s.length() == 0) {
			return 0;
		}
		return (int) matchWhitespace.matcher(s).results().count() + 1;
	}

	@Override
	public String removeNthCharacter(int n, boolean maintainSpacing) {
		return null;
	}

	public static String[] substrings(String str) {
		var s = str.strip();
		if (s.length() == 0) {
			return new String[]{};
		}
		return s.split(matchWhitespace.pattern());
	}

	@Override
	public String[] getSubStrings(int startWord, int endWord) {
		if (startWord - 1 < 0 || startWord > endWord) {
			throw new IllegalArgumentException();
		}
		var arr = substrings(str);
		if (arr.length < endWord) {
			throw new IndexOutOfBoundsException(
				"word array is shorter than endWord: " + arr.length
			);
		}
		return Arrays.copyOfRange(arr, startWord - 1, endWord);
	}

	@Override
	public String restoreString(int[] indices) {
		return null;
	}
}
