package com.bhs.util;

import java.util.HashSet;

public class GainSaint {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		numberJUmper();
		fizzyBuzz();

	}

	static void numberJUmper() {

		int start = 8;
		int end = 12;

		for (int i = start; i <= end; i++) {

			String numString = Integer.toString(i);

			if (numString.length() > 1) {
				HashSet<Character> hs = new HashSet<Character>();
				boolean dup = true;
				for (int j = 0; j < numString.length(); j++) {

					if (!hs.add(numString.charAt(j))) {

						dup = false;
					}

				}

				if (dup)
					System.out.println(numString);

			}

			else
				System.out.println(i);

		}

	}

	static void fizzyBuzz() {
		
		for(int i=1;i<=15;i++){
			System.out.println("Number= "+i);
			if(i%3==0 && i%5==0)
				System.out.println("fizzyBuzz");
			else if(i%3==0)
				System.out.println("fizzy");
			else if(i%5==0)
				System.out.println("Buzz");
			System.out.println();
		}

	}

}
