package com.capgemini.placeToSplit;

/**
 * Given a non-empty array, return true if there is a place to split the array so that the sum of the numbers on one side is equal to the sum of the numbers on the other side.
 * Example:
 * {{{
 * canBalance({1, 1, 1, 2, 1}) → true
 * canBalance({2, 1, 1, 2, 1}) → false
 * canBalance({10, 10}) → true
 * }}}
 */
public final class PlaceToSplit {
    /*private PlaceToSplit() {
    }*/

    public static boolean canBalance(int[] nums) {
    	int sumUp, sumDown;
    	for (int i = 1; i < nums.length; i++){
    		sumUp = oneSideSum(nums, i, true);
    		sumDown = oneSideSum(nums, i, false);
    		if (sumUp == sumDown){
    			return true;
    		}
    	}
        return false;
    }
    //startElement index of element before which split is made
    public static int oneSideSum (int[] nums, int startElement, boolean countUp){
    	int count = 0;
    	if (countUp){
    		for (int i = startElement; i < nums.length; i++){//starts from 0 to lenght-1
    			count +=nums[i];
    		}
    	}
    	else{
    		for (int i = startElement - 1; i >= 0; i--){//starts from 0 to lenght-1
    			count +=nums[i];
    		}
    	}
    	return count;
    }
}
