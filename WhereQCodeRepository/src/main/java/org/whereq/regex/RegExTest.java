package org.whereq.regex;

public class RegExTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	
	public static void test() {
		String str = "";
		
		str = "ABC (123 abc), DEF (def 321), GHI (g h i 1 2 3)";
		/*
		 * replace the content in () and all of the spaces.
		 * match the content in () and with (), regex is (.*?), .*? means non-greedy, \\s* means match all of the spaces
		 * output should be ABC,DEF,GHI
		 */
		System.out.println(str.replaceAll("\\(.*?\\)|\\s*", ""));
		
	}

}
