import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class StringManipulationTest {

	private StringManipulationInterface manipulatedstring;

	@BeforeEach
	public void setUp() {
		manipulatedstring = new StringManipulation();
	}

	@AfterEach
	public void tearDown() {
		manipulatedstring = null;
	}

	// It should get the string that was previously set in setString()
	@Test
	public void testSetString() {
		manipulatedstring.setString("a");
		assertEquals("a", manipulatedstring.getString());
	}

	// An empty string should have 0 words
	@Test
	public void testCount1() {
		manipulatedstring.setString("");
		assertEquals(0, manipulatedstring.count());
	}

	// A whitespace only string should have 0 words
	@Test
	public void testCount2() {
		manipulatedstring.setString(" ");
		assertEquals(0, manipulatedstring.count());
	}

	// A single character with a space should have 1 word
	@Test
	public void testCount3() {
		manipulatedstring.setString("a ");
		assertEquals(1, manipulatedstring.count());
	}

	// Two characters seperated by a space should have 2 words
	@Test
	public void testCount4() {
		manipulatedstring.setString("a  b");
		assertEquals(2, manipulatedstring.count());
	}

	// Two characters seperated by 2 spaces should have 2 words
	@Test
	public void testCount5() {
		manipulatedstring.setString("a  b");
		assertEquals(2, manipulatedstring.count());
	}

	// Two words seperated by a tab should have 2 words
	@Test
	public void testCount6() {
		manipulatedstring.setString("ab\tc");
		assertEquals(2, manipulatedstring.count());
	}

	// Passing 0 as n should throw an exception
	@Test
	public void testRemoveNthCharacter0() {
		manipulatedstring.setString("");
		assertThrows(
			IllegalArgumentException.class,
			() -> manipulatedstring.removeNthCharacter(0, false)
		);
	}

	// Passing -1 as n should throw an exception
	@Test
	public void testRemoveNthCharacter1() {
		manipulatedstring.setString("");
		assertThrows(
			IllegalArgumentException.class,
			() -> manipulatedstring.removeNthCharacter(-1, false)
		);
	}

	// An empty string should throw an exception
	@Test
	public void testRemoveNthCharacter2() {
		manipulatedstring.setString("");
		assertThrows(
			IndexOutOfBoundsException.class,
			() -> manipulatedstring.removeNthCharacter(1, false)
		);
	}

	// A single character with arguments 1, false should return an empty string
	@Test
	public void testRemoveNthCharacter3() {
		manipulatedstring.setString("a");
		assertEquals("", manipulatedstring.removeNthCharacter(1, false));
	}

	// A single character with arguments 1, true should return an empty string
	@Test
	public void testRemoveNthCharacter4() {
		manipulatedstring.setString("a");
		assertEquals(" ", manipulatedstring.removeNthCharacter(1, true));
	}

	// A single character with n = 2 should throw an exception
	@Test
	public void testRemoveNthCharacter5() {
		manipulatedstring.setString("a");
		assertThrows(
			IndexOutOfBoundsException.class,
			() -> manipulatedstring.removeNthCharacter(2, false)
		);
	}

	// "ab" with arguments 1, false should return an empty string
	@Test
	public void testRemoveNthCharacter6() {
		manipulatedstring.setString("ab");
		assertEquals("", manipulatedstring.removeNthCharacter(1, false));
	}

	// "ab" with arguments 1, true should return "  "
	@Test
	public void testRemoveNthCharacter7() {
		manipulatedstring.setString("ab");
		assertEquals("  ", manipulatedstring.removeNthCharacter(1, true));
	}

	// "ab" with arguments 2, false should return "a"
	@Test
	public void testRemoveNthCharacter8() {
		manipulatedstring.setString("ab");
		assertEquals("a", manipulatedstring.removeNthCharacter(2, false));
	}

	// "ab" with arguments 2, true should return "a "
	@Test
	public void testRemoveNthCharacter9() {
		manipulatedstring.setString("ab");
		assertEquals("a ", manipulatedstring.removeNthCharacter(2, true));
	}

	// "abcd" with arguments 2, false should return "ac"
	@Test
	public void testRemoveNthCharacter10() {
		manipulatedstring.setString("abcd");
		assertEquals("ac", manipulatedstring.removeNthCharacter(2, false));
	}

	// "abcd" with arguments 2, true should return "abcd"
	@Test
	public void testRemoveNthCharacter11() {
		manipulatedstring.setString("abcd");
		assertEquals("a c ", manipulatedstring.removeNthCharacter(2, true));
	}

	// Every 3rd character should be removed if n = 3 and spacing is not maintained
	@Test
	public void testRemoveNthCharacter12() {
		manipulatedstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
		assertEquals(
			"I' bttr uts0e 16tsinths trn6 rgh?",
			manipulatedstring.removeNthCharacter(3, false)
		);
	}

	// Every 3rd character should be replaced with a space if n = 3 and spacing is maintained
	@Test
	public void testRemoveNthCharacter13() {
		manipulatedstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
		assertEquals(
			"I'  b tt r  ut s0 e  16 ts in th s  tr n6  r gh ?",
			manipulatedstring.removeNthCharacter(3, true)
		);
	}

	// It should be able to remove the correct characters if the string contains non-BMP characters
	@Test
	public void testRemoveNthCharacter14() {
		manipulatedstring.setString("❤😀a");
		assertEquals(
			"❤a",
			manipulatedstring.removeNthCharacter(2, false)
		);
	}

	// An empty string should return an empty array
	@Test
	public void testSubstrings1() {
		assertArrayEquals(new String[]{}, StringManipulation.substrings(""));
	}

	// A single character string should return an array with that string
	@Test
	public void testSubstrings2() {
		assertArrayEquals(new String[]{"a"}, StringManipulation.substrings("a "));
	}

	// "a b" should return an array of length 2
	@Test
	public void testSubstrings3() {
		assertArrayEquals(
			new String[]{"a", "b"},
			StringManipulation.substrings("a b")
		);
	}

	// "a \tb  c" should return an array of length 3
	@Test
	public void testSubstrings4() {
		assertArrayEquals(
			new String[]{"a", "b", "c"},
			StringManipulation.substrings("a \tb  c")
		);
	}

	// An empty string should throw an exception with arguments 1, 1
	@Test
	public void testGeSubStrings1() {
		manipulatedstring.setString("");
		assertThrows(IndexOutOfBoundsException.class, () -> manipulatedstring.getSubStrings(1, 1));
	}

	// A space character should throw an exception with arguments 1, 1
	@Test
	public void testGeSubStrings2() {
		manipulatedstring.setString(" ");
		assertThrows(IndexOutOfBoundsException.class, () -> manipulatedstring.getSubStrings(1, 1));
	}

	// It should throw an exception with arguments 1, 0
	@Test
	public void testGeSubStrings3() {
		manipulatedstring.setString("");
		assertThrows(IllegalArgumentException.class, () -> manipulatedstring.getSubStrings(1, 0));
	}

	// An empty string with arguments 0, 1 should throw an exception
	@Test
	public void testGeSubStrings4() {
		manipulatedstring.setString("");
		assertThrows(IllegalArgumentException.class, () -> manipulatedstring.getSubStrings(0, 1));
	}

	// It should throw an exception with arguments -1, 1
	@Test
	public void testGeSubStrings5() {
		manipulatedstring.setString("");
		assertThrows(IllegalArgumentException.class, () -> manipulatedstring.getSubStrings(-1, 1));
	}

	// A string containing a space and a letter with arguments 1, 1 should return an array of
	// length 1
	@Test
	public void testGeSubStrings6() {
		manipulatedstring.setString(" a");
		assertArrayEquals(new String[]{"a"}, manipulatedstring.getSubStrings(1, 1));
	}

	// A string containing a space and a letter with arguments 1, 2 should throw an exception
	@Test
	public void testGeSubStrings7() {
		manipulatedstring.setString("a");
		assertThrows(IndexOutOfBoundsException.class, () -> manipulatedstring.getSubStrings(1, 2));
	}

	// "a b" with arguments 1, 2 should return an array of length 2
	@Test
	public void testGeSubStrings8() {
		manipulatedstring.setString("a b");
		assertArrayEquals(new String[]{"a", "b"}, manipulatedstring.getSubStrings(1, 2));
	}

	// "a b" with arguments 1, 1 should return an array with "a"
	@Test
	public void testGeSubStrings9() {
		manipulatedstring.setString("a b");
		assertArrayEquals(new String[]{"a"}, manipulatedstring.getSubStrings(1, 1));
	}

	// "a b" with arguments 2, 2 should return an array with "b"
	@Test
	public void testGeSubStrings10() {
		manipulatedstring.setString("a b");
		assertArrayEquals(new String[]{"b"}, manipulatedstring.getSubStrings(2, 2));
	}

	// A string containing 4 words with arguments 3, 4 should return the 3rd and 4th words.
	@Test
	public void testGeSubStrings11() {
		manipulatedstring.setString("This is my string");
		String[] sStings = manipulatedstring.getSubStrings(3, 4);

		assertEquals(sStings[0], "my");
		assertEquals(sStings[1], "string");
	}

	// It should be able to split a string that contains non-BMP characters
	@Test
	public void testGeSubStrings12() {
		manipulatedstring.setString("❤ 😀");
		assertArrayEquals(new String[]{"❤", "😀"}, manipulatedstring.getSubStrings(1, 2));
	}

	// An empty string with an empty indices array should return an empty string
	@Test
	public void testRestoreString1() {
		manipulatedstring.setString("");
		assertEquals("", manipulatedstring.restoreString(new int[]{}));
	}

	// If the indices array is larger than string length, it should throw an exception
	@Test
	public void testRestoreString2() {
		manipulatedstring.setString("");
		assertThrows(
			IllegalArgumentException.class,
			() -> manipulatedstring.restoreString(new int[]{0})
		);
	}

	// A single character should only accept [0] as the indices and return the same string
	@Test
	public void testRestoreString3() {
		manipulatedstring.setString("a");
		assertEquals("a", manipulatedstring.restoreString(new int[]{0}));
	}

	// Passing an out of bounds index should throw an exception
	@Test
	public void testRestoreString4() {
		manipulatedstring.setString("a");
		assertThrows(
			IndexOutOfBoundsException.class,
			() -> manipulatedstring.restoreString(new int[]{1})
		);
	}

	// Passing a negative index should throw an exception
	@Test
	public void testRestoreString5() {
		manipulatedstring.setString("a");
		assertThrows(
			IndexOutOfBoundsException.class,
			() -> manipulatedstring.restoreString(new int[]{-1})
		);
	}

	// Passing indices that are incrementing by 1 should return the same string
	@Test
	public void testRestoreString6() {
		manipulatedstring.setString("ab");
		assertEquals("ab", manipulatedstring.restoreString(new int[]{0, 1}));
	}

	// Passing indices that are decrementing by 1 should return the reversed string
	@Test
	public void testRestoreString7() {
		manipulatedstring.setString("ab");
		assertEquals("ba", manipulatedstring.restoreString(new int[]{1, 0}));
	}

	// Passing duplicate indices should throw an exception
	@Test
	public void testRestoreString8() {
		manipulatedstring.setString("ab");
		assertThrows(
			IllegalArgumentException.class,
			() -> manipulatedstring.restoreString(new int[]{0, 0})
		);
	}

	// "art" with indices [1, 0, 2] should return "rat"
	@Test
	public void testRestoreString9() {
		manipulatedstring.setString("art");
		int[] array;
		array = new int[]{1, 0, 2};
		String restoreString = manipulatedstring.restoreString(array);
		assertEquals(restoreString, "rat");
	}

	// It should be able to return the correct string if non-BMP characters are present in the
	// string
	@Test
	public void testRestoreString10() {
		manipulatedstring.setString("❤😀");
		assertEquals("😀❤", manipulatedstring.restoreString(new int[]{1, 0}));
	}
}
