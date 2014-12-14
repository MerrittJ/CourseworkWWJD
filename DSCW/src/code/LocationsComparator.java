package code;

import java.util.Comparator;
import java.util.Scanner;
import java.util.regex.Pattern;

public class LocationsComparator implements Comparator<String>{
	
	public int compare(String v1, String v2) {

		Scanner sc1 = new Scanner((String) v1);
		sc1.useDelimiter(Pattern.compile("\\s|:"));
		Scanner sc2 = new Scanner((String) v2);
		sc2.useDelimiter(Pattern.compile("\\s|:"));

		String book1 = sc1.next();
		String book2 = sc2.next();
		if (book1.compareTo(book2) > 0) {
			return 1;
		} else if (book1.compareTo(book2) < 0) {
			return -1;
		} else {
			int chap1 = sc1.nextInt();
			int chap2 = sc2.nextInt();
			
			if (chap1 < chap2) {
				return -1;
			} else if (chap1 > chap2) {
				return 1;
			} else {
				int verse1 = sc1.nextInt();
				int verse2 = sc2.nextInt();
				
				if (verse1 < verse2) {
					return -1;
				} else if (verse1 > verse2) {
					return 1;
				}
			}
		}
		return 0;
	}
}
