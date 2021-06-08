import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
		if (s.isEmpty()) {
			return 0;
		}
		return (int) matchWhitespace.matcher(s).results().count() + 1;
	}

	@Override
	public String removeNthCharacter(int n, boolean maintainSpacing) {
		if (n <= 0) {
			throw new IllegalArgumentException("n is invalid: " + n);
		}

		var result = new StringBuilder();
		var index = new Object() {
			int n = 1;
		};

		str.codePoints().forEach(c -> {
			if (index.n % n == 0) {
				if (maintainSpacing) {
					result.append(" ");
				}
			} else {
				result.appendCodePoint(c);
			}
			++index.n;
		});

		// index.n is character length of str
		if (n >= index.n) {
			throw new IndexOutOfBoundsException("n is out of bounds: " + n);
		}

		return result.toString();
	}

	public static String[] substrings(String str) {
		var s = str.strip();
		if (s.isEmpty()) {
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
				"number of words is less than endWord: " + arr.length
			);
		}
		return Arrays.copyOfRange(arr, startWord - 1, endWord);
	}

	@Override
	public String restoreString(int[] indices) {
		var strChars = str.codePoints().toArray();

		if (indices.length != strChars.length) {
			throw new IllegalArgumentException("indices.length != str.length()");
		}

		if (IntStream.of(indices).boxed().collect(Collectors.toSet()).size() != indices.length) {
			throw new IllegalArgumentException("items in indices are not unique");
		}

		// Initializing with -1 just in case to ensure there is no uninitialized character
		var resultChars = IntStream.generate(() -> -1).limit(strChars.length).toArray();
		for (int i = 0; i < indices.length; ++i) {
			resultChars[i] = strChars[indices[i]];
		}

		var sb = new StringBuilder();
		for (var n : resultChars) {
			sb.appendCodePoint(n);
		}
		return sb.toString();
	}
}
