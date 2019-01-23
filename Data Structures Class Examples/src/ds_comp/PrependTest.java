package ds_comp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

class PrependTest {
	
	private static int TEST_SIZE = 1000000;

	@Test
	void ALPrepend() {
		ArrayList<String> arr = new ArrayList<String>();
		for (int i = 0; i < TEST_SIZE; i++) {
			arr.add(0, "" + i);
		}
	}
	
	@Test
	void LLPrepend() {
		LinkedList<String> arr = new LinkedList<String>();
		for (int i = 0; i < TEST_SIZE; i++) {
			arr.add(0, "" + i);
		}
		
	}

}
