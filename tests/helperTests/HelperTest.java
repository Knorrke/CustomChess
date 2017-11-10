package helperTests;

import static org.junit.Assert.*;
import static helper.Helper.*;

import org.junit.Test;

public class HelperTest {

	@Test
	public void positionTests() {
		int[] test = {4,3};
		assertArrayEquals("Should create correct array from two integers", test, pos(4,3));
		assertArrayEquals("Should create correct array from string", test, pos("e4"));
		assertEquals("Should create correct String from int array", "e4", posToString(test));
		
	}

}
