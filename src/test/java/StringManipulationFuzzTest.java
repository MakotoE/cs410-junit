import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import java.util.stream.IntStream;

@RunWith(JUnitQuickcheck.class)
public class StringManipulationFuzzTest {
	@Property
	public void removeNthCharacter(String s) {
		if (s.length() > 0) {
			var str = new StringManipulation();
			str.setString(s);
			str.removeNthCharacter(1, false);
		}
	}

	@Property
	public void subStrings(String s) {
		StringManipulation.substrings(s);
	}

	@Property
	public void restoreString(String s) {
		var str = new StringManipulation();
		str.setString(s);
		var indices = IntStream.range(0, (int) s.codePoints().count()).toArray();
		str.restoreString(indices);
	}
}
