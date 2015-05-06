package lab10;

import java.io.File;
import java.util.Scanner;

/**
 *
 * @author Jaden Young
 */
public class StudentTest {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		boolean validFile = false;
		do {
			try {
				System.out.print("Name of file > ");
				StudentSorter sort =
						new StudentSorter(new File(scan.nextLine()));
				System.out.println(sort.toString());
				validFile = true;
			} catch (IllegalArgumentException iae) {
				System.out.println(iae.getMessage());
			}
		} while (!validFile);
		
	}
}