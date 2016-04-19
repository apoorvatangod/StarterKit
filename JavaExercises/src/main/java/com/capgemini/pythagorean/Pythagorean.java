package com.capgemini.pythagorean;

import java.lang.Math;
/**
 * A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,
 * a2 + b2 = c2
 * For example, 32 + 42 = 9 + 16 = 25 = 52.
 * There exists exactly one Pythagorean triplet for which a + b + c = 1000.
 * Find the product abc.
 */
public class Pythagorean {
	public static int[] getResult(){
		for (Double a = 1.0; a < 1000; a++){
			for (Double b = 1.0; b < 1000; b++){
				Double sum = a + b + countC(a, b);
				if (sum == 1000.0){
					int[] result = {a.intValue(), b.intValue(), countC(a, b).intValue()};
					System.out.println("a: " + a.intValue() + ", b: " + b.intValue() + ", c: " + countC(a, b).intValue());
					return result;
				}
				if (sum > 1000){
					break;
				}
			}
		}
		return null;
	}
	
	private static Double countC(Double a, Double b){
		return Math.sqrt( (a * a) + (b * b));
	}
}
