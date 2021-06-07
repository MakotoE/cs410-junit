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
		return (int) matchWhitespace.matcher(str.strip()).results().count() + 1;
	}

	@Override
	public String removeNthCharacter(int n, boolean maintainSpacing) {
		return null;
	}

	@Override
	public String[] getSubStrings(int startWord, int endWord) {
		var words = str.strip().split(matchWhitespace.pattern());
		return Arrays.copyOfRange(words, startWord - 1, endWord);
	}

	@Override
	public String restoreString(int[] indices) {
		return null;
	}
}
