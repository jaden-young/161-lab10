package lab10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Sorts a list of Strings representing students first on class, then major,
 * then name. Uses merge sort for class, quick sort for major, and bubble sort 
 * for the names. I tried doing it radix-style, sorting from name -> major ->
 * class, but that didn't really work out. The major and name sorts are done
 * in-place on sub-arrays that are already sorted by class.
 * @author Jaden Young
 */
public class StudentSorter {

	private String[] studentList = new String[10];
	private int size = 0;
	private enum Year {
		FRESHMAN, 
		SOPHOMORE, 
		JUNIOR,
		SENIOR,
		MASTERS,
		PHD;
	}
	
	/**
	 * Default Constructor, creates an empty array to store students
	 */
	public StudentSorter() {
		// do nothing
	}
	
	/**
	 * Constructs a sorted list based off of a file
	 * @param inputFile file to sort 
	 */
	public StudentSorter(File inputFile) throws IllegalArgumentException {
		try {
			Scanner scanFile = new Scanner(inputFile);
			while(scanFile.hasNextLine()) {
				add(scanFile.nextLine());
			}
		} catch (FileNotFoundException fnfe) {
			throw new IllegalArgumentException("File was not found.");
		}
	}
	
	/**
	 * Adds a new student to the list
	 * @param newStudent student to add
	 */
	public final void add(String newStudent) {
		//increase size by 10 if list is full
		if(size == studentList.length - 1) 
			studentList = Arrays.copyOf(studentList, studentList.length + 10);
		studentList[size] = newStudent;
		size++;
	}
	
	private int compareAlphabetically(String s1, String s2) {
		if (s1.equals(s2))
			return 0;
		int max = s1.length() < s2.length() ? s1.length() : s2.length();
		//loop until every letter is checked
		for (int i = 0; i < max; i++) {
			//return the integer once we find different letters
			int diff = s1.charAt(i) - s2.charAt(i);
			if (diff != 0) {
				return diff;
			}
		}
		return 0;
	}
	
// --------------------------- Start level 1 sort -----------------------------	
	
	private void sortByYear() {
		trimToSize();
		mergeSort(studentList);
	}
	
	private void trimToSize() {
		studentList = Arrays.copyOf(studentList, size);
	}
	private void mergeSort(String[] S) {
		int currentSize = S.length;
		if (currentSize < 2) return;
		//divide
		int mid = currentSize / 2;
		String[] S1 = Arrays.copyOfRange(S, 0, mid);
		String[] S2 = Arrays.copyOfRange(S, mid, currentSize);
		//conquer
		mergeSort(S1);
		mergeSort(S2);
		//merge
		merge(S1, S2, S);
	}
	
	private void merge(String[] S1, String[] S2, String[] S) {
		int i = 0, j = 0;
		while(i + j < S.length) {
			if (j== S2.length || 
					(i < S1.length && compareYear(S1[i], S2[j]) < 0)) {
				S[i+j] = S1[i++];		//copy ith element of S1 and increment i
			} else {
				S[i+j] = S2[j++];		//copy jth element of S2 and increment j
			}
		}
	}
	
	private int compareYear(String s1, String s2) {
		Scanner scan1 = new Scanner(s1);
		Scanner scan2 = new Scanner(s2);
		Year year1 = Year.valueOf(scan1.next().toUpperCase());
		Year year2 = Year.valueOf(scan2.next().toUpperCase());
		return year1.compareTo(year2);
	}
	
// ------------------------ End Level 1 sort ---------------------------------	
// ----------------------- Start level 2 sort --------------------------------
	
	private void sortByMajor() {
		int previous = 0;
		for(int i = 1; i < size; i++) {
			if(compareYear(studentList[previous], studentList[i]) < 0) {
				quickSort(studentList, previous, i - 1);
				previous = i;
			}
		}
	}
	
	private void quickSort(String[] S, int a, int b) {
		if (a >= b) return; //already sorted
		int left = a;
		int right = b - 1;
		String pivot = S[b];
		String temp;
		while (left <= right) {
			// scan until reaching value equal or larger than pivot (or right)
			while (left <= right && compareMajor(S[left], pivot) < 0)
				left++;
			// scan until reaching value equal or smaller than pivot (or left)
			while (left <= right && compareMajor(S[right], pivot) > 0)
				right--;
			if (left <= right) {	// indices do not strictly cross
				// so swap values and shrink range
				temp = S[left];
				S[left] = S[right];
				S[right] = temp;
				left++;
				right--;
			}
		}
		// put pivot into its final place (currently marked by left index)
		temp = S[left];
		S[left] = S[b];
		S[b] = temp;
		// make recursive calls
		quickSort(S, a, left - 1);
		quickSort(S, left + 1, b);
	}
	
	private int compareMajor(String s1, String s2) {
		Scanner scan1 = new Scanner(s1);
		Scanner scan2 = new Scanner(s2);
		//we want 2nd token for each
		scan1.next();
		scan2.next();
		//Compare the first letter of each major alphabetically
		return compareAlphabetically(scan1.next(), scan2.next());
	}
	
// ----------------------------- End level 2 sort -----------------------------
// -----------------------------Start level 3 sort ----------------------------
	
	private void sortByName() {
		int previous = 0;
		for (int i = 1; i < size; i++) {
			if (compareMajor(studentList[previous], studentList[i]) != 0){
				bubbleSort(studentList, previous, i);
				previous = i;
			}
		}
	}

	private void bubbleSort(String[] S, int leftIndex, int rightIndex) {
		String temp;
		for (int i = leftIndex; i < rightIndex - 1; i++) {
			for (int j = leftIndex + 1; j < rightIndex + leftIndex - i; j++) {
				if (compareName(studentList[j-1], studentList[j]) > 0) {
					temp = studentList[j-1];
					studentList[j-1] = studentList[j];
					studentList[j] = temp;
				}
			}
		}
	}
	
	private int compareName(String s1, String s2) {
		Scanner scan1 = new Scanner(s1);
		Scanner scan2 = new Scanner(s2);
		// we want 3rd token from each
		scan1.next();
		scan1.next();
		scan2.next();
		scan2.next();
		// Compare the first letter of each name alphabetically
		return scan1.next().charAt(0) - scan2.next().charAt(0);
	}
	
// -------------------------------- End level 3 sort -------------------------
	
	public void sort() {
		sortByYear();
		sortByMajor();
		sortByName();
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		sort();
		for(int i = 0; i < size; i++) {
			result.append(studentList[i]);
			result.append("\n");
		}
		return result.toString();
	}
}