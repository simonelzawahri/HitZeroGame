package comp352_ass2_HitZeroGame;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class myMain {

	public static void main(String[] args) {
		
		System.out.println("------Welcome to Hit Zero Game------\n");
		
		//input array and index
		int[] arr = {3,4,8,2,87,4,5,5,3,7,1,0};
		int index = 2;
		
		//-----------------------------------RECURSION--------------------------------------------------------
		System.out.println("----------RECURSION METHOD----------");
		//execute game
		boolean result1 = hitZeroRecursion(arr, index);
		//display result
		if(result1) {
			System.out.println("Result found in " + printArray(arr) + ".\n");
			System.out.println("Number of recursions: " + countRecursion);
			System.out.println("Order of indexes used: " + stack1.toString());
			System.out.println();
		} else {
			System.out.println("No Result found in " + printArray(arr) + ".\n");
		}
		System.out.println("Game Over. \nThanks for playing.\n\n");
		
		//---------------------------------NON-RECURSION------------------------------------------------------
		System.out.println("--------NON-RECURSION METHOD--------");
		//execute game
		boolean result2 = hitZeroNonRec(arr, index);
		//display result
		if(result2) {
			System.out.println("Result found in " + printArray(arr) + ".\n");
			System.out.println("Order of indexes used: " + stack2.toString());
			System.out.println();
		} else {
			System.out.println("No Result found in " + printArray(arr) + ".\n");
		}
		System.out.println("Game Over. \nThanks for playing.");
		
		
		
	}
	
	
	
	
	
	
	
	
	
	public static String printArray(int[] a) {
		ArrayList<Integer> og = new ArrayList<Integer>(2);
		for(int i : a) {
			og.add(i);	
		}
		return og.toString();
	}
	
	
	//helper method to get size of array and copy contents to new array, returns new array
	public static ArrayList<Integer> list1 = new ArrayList<Integer>(2);
	public static int sizee = 0;
	public static int[] copyArray(int[] a) {
		//copy elements to ArrayList
		for(int i =0; i<a.length; i++) {
			list1.add(a[i]);
		}
		//save size
		sizee = list1.size();
		//create new array initialized to size and copy to new array
		int[] newArray = new int[sizee];
		for(int i =0; i<newArray.length; i++) {
			newArray[i] = a[i];
		}
		//clear list1 for reuse and return new array
		list1.clear();
		return newArray;
	}
	
	
	//HitZero using RECURSION and STACK
	//takes an array of elements > 0 and the last element is 0
	public static int[] arr1 = new int[sizee];
	public static int countRecursion = 0;
	public static boolean solution = false;
	public static Stack<Integer> stack1 = new Stack<Integer>();
	public static boolean hitZeroRecursion(int[] arr, int index) {
		//at first iteration:
		if (countRecursion == 0) {
			//copy array
			arr1 = copyArray(arr);			
			//check for invalid index
			if(index < 0) {
				System.out.println("Invalid Index Error.\n");
				return false;
			}
			//check for elements <=0
			for(int i=0; i <= arr1.length-2; i++) {
				if(arr1[i] <= 0) {
					System.out.println("Invalid Element Error.\n");
					return false;
				}
			}
		}
		//Stopping case --> when solution is true and reached last element
		if(solution == true && arr1[index] == 0) {
			stack1.push(index);
			return solution;
		}
		//if currently viewing number that has already been viewed, solution = false
		if(arr1[index] < 0 ) {
			solution = false;
			return solution;
		}
		//if number at index is > 0 and move is within right bounds, MOVE RIGHT
		if(arr1[index] > 0 && index + arr1[index] <= arr1.length-1) {
			//push index to stack, change number to -1, move index and recurse
			stack1.push(index);
			int tempNum = arr1[index];
			arr1[index] = -1;
			index = index + tempNum;
			countRecursion++;
			solution = true;
			solution = hitZeroRecursion(arr1, index);
		}
		//if number at index is > 0 and move is within left bounds, MOVE LEFT
		else if(arr1[index] > 0 && index - arr1[index] >= 0) {
			//push index to stack, change number to -1, move index and recurse
			stack1.push(index);
			int tempNum = arr1[index];
			arr1[index] = -1;
			index = index - tempNum;
			countRecursion++;
			solution = true;
			solution = hitZeroRecursion(arr1, index);
		}
		return solution;
	}
	
	

	//HitZero using NON-RECURSION and STACK
	//takes an array of elements > 0 and the last element is 0
	public static Stack<Integer> stack2 = new Stack<Integer>();

	public static boolean hitZeroNonRec(int[] arr, int index) {
		//copy array
		int[] arrCopy = copyArray(arr);
		//check if index passed is <0
		if(index < 0) {
			System.out.println("Invalid Index Error.\n");
			return false;
		}
		//check for elements >0 in array
		for(int i=0; i <= arrCopy.length-2; i++) {
			if(arrCopy[i] <= 0) {
				System.out.println("Invalid Element Error.\n");
				return false;
			}
		}
		int count = 0;
		boolean solution = false;
		while (arrCopy[index] != 0) {
			//if number at index is >0 and move is within right bounds, MOVE RIGHT
			if(arrCopy[index] > 0 && index + arrCopy[index] <= arrCopy.length-1) {
				//push index to stack, change number to -1, move index and recurse
				stack2.push(index);
				int tempNum = arrCopy[index];
				arrCopy[index] = -1;
				index = index + tempNum;
			}
			//if number at index is >0 and move is within left bounds, MOVE LEFT
			else if(arrCopy[index] > 0 && index - arrCopy[index] >= 0) {
				//push index to stack, change number to -1, move index and recurse
				stack2.push(index);
				int tempNum = arrCopy[index];
				arrCopy[index] = -1;
				index = index - tempNum;
			}
			//if currently viewing number that has already been viewed, solution = false
			if(arrCopy[index] < 0 ) {
				solution = false;
			}
			//Stopping case --> when solution is true and reached last element
			if(arrCopy[index] == 0) {
				stack2.push(index);
				solution = true;
				return solution;
			}
			if(count > 1000000) {
				break;
			}
			count++;
		}
		return solution;
		
	}
	
	
	
	

}
