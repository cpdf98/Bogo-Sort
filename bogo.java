import java.util.Scanner;
import java.util.*;
class bogo{
	private static final Random generator = new Random();
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int[] array = toIntArray(getArray(in, "Insert array: "));
		int choice = getChoice(in, "Display attempts? Type 1 for yes, 0 for no");
		bogoSort(choice, array, "\n————————————————————————————————————————————————————————————————————————————————————");
		in.close();
	}
	static String[] getArray(Scanner in, String p){ //gets array of numbers
		d(2, p);
		String input = in.nextLine();
		String[] stringArray = input.split(" ");
		return stringArray;
	}
	static int getChoice(Scanner in, String p){
		d(2, p);
		return in.nextInt();
	}
	static void bogoSort(int choice, int[] array, String seperate){
		long count = 0;
		long startTime = System.currentTimeMillis(); //starts timer
		while(!orderCheck(array)){ //if the array is not in order, will shuffle again
			count++;
			shuffle(choice, array, count);
		}
		long endTime = System.currentTimeMillis();
		d(1, seperate);
		displayEnd(array, count, startTime, endTime); //ends timer
	}
	static void shuffle(int choice, int[] array, long count){
		for(int i = 0; i < array.length; i++){
			int random = generator.nextInt(array.length); //Generates random number
			swap(array, i, random);
		}
		switch(choice){ //Decides if each iteration will be displayed
			case 1:
				displayIteration(array, count);
				break;
			case 2:
				break;
		}
	}
	static void swap(int[] array, int i, int random){ //swaps based on random number from shuffle
		int temp = array[i];
		array[i] = array[random];
		array[random] = temp;
	}
	static boolean orderCheck(int[] array){
		for(int i = 1; i < array.length; i++){
			if(array[i] < array[i - 1]){
				return false;
			}
		}
		return true;
	}
	static int[] toIntArray(String[] stringArray){ //converts string array to int
		int[] array = new int[stringArray.length];
		for(int i = 0; i < stringArray.length; i++){
			array[i] = Integer.parseInt(stringArray[i]);
		}
		return array;
	}
	static void d(int a, String s){ //Displays text
		switch (a){
			case 1:
				System.out.print(s);
				break;
			case 2:
				System.out.println(s);
				break;
		}
	}
	static void displayIteration(int[] array, long count){ //Will be called after each attempt
		for(int i : array){
			d(1, (i + " "));
		}
		d(1, ("	│	Attempt: " + count + "\n"));
	}
	static void displayEnd(int[] array, long count, long startTime, long endTime){ //Will be called if the right order was found
		double time = ((double)endTime - (double)startTime); //gets the total amount of time
		String timeString = "";
		String timeUnit = "";
		if(time >= 1000 && time < 60000){
			time /= 1000;
			timeString = String.valueOf(time);
			timeUnit = "seconds";

		}else if(time >= 60000 && time < 3600000){
			time /= 60000;
			timeString = String.valueOf(time);
			timeUnit = "minutes";
		}else if(time >= 3600000){
			time /= 3600000;
			timeString = String.valueOf(time);
			timeUnit = "hours";
		}else{
			int temp = (int)time;
			timeString = String.valueOf(temp);
			timeUnit = "milliseconds";
		}
		d(2, "\nSorted Array: ");
		for(int i : array){
			d(1, (i + " "));
		}
		d(1, ("	|     Attempt: " + count + "	  |	Time: " + timeString + " " + timeUnit + "\n")); //displays completed array, the attempts, and how long it took
	}
}	
