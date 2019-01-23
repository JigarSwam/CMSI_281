package hash_ex;

import java.util.HashMap;

public class HashEx {
	
	public static void main (String[] args) {
		HashMap<String, Integer> hashy = new HashMap<String,Integer>();
		hashy.put("one", 1);
		hashy.put("two", 2);
		hashy.put("three", 3);
		
		System.out.println(hashy.get("two"));
		
	}

}
