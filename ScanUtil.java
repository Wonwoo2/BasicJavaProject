package data;

import java.util.Scanner;

public class ScanUtil {
	
	private static Scanner s = new Scanner(System.in);

	
	public static String nextLine(){ // public 을 붙여주면서 다른 class에서 사용가능하게 만든것
		return s.nextLine();
	}
	
	public static int nextInt(){
		return Integer.parseInt(s.nextLine());
	}
	
	
	

}
