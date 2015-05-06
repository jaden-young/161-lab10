package lab10;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 *
 * @author Jaden Young
 */
public class EmployeeTest {
	
	private static ArrayList<Employee> empList = new ArrayList<>();
		
	public static void main(String[] args) {
		
		empList.add(new Employee("Snowball", 'M', 1000, 111));
		empList.add(new Employee("Johns, Jimmy", 'M', 1001, 112));
		empList.add(new Employee("Cassius Clay", 'M', 1002, 221));
		empList.add(new Employee("Jardir, Inevera", 'F', 1003, 223));
		empList.add(new Employee("Raskalnikov", 'F', 1004, 311));
		empList.add(new Employee("Mallow, Hober", 'M', 1005, 341));
		empList.add(new Employee("Ancrath, Jorg", 'M', 1006, 333));
		empList.add(new Employee("Paper, Leesha", 'F', 1007, 444));
		empList.add(new Employee("Ivanova, Alyona", 'F', 1008, 423));
		empList.add(new Employee("Duarte, Matias", 'M', 1009, 999));
		
		System.out.println("Current list:");
		for (Employee empList1 : empList) {
			System.out.println("\n" + empList1.toString());
		}
		
		System.out.println("How would you like to sort the list?");
		System.out.println("	1: By name");
		System.out.println("	2: By gender");
		System.out.println("	3: By ID number");
		System.out.println("	4: By floor number");
		System.out.println("	5: By wing number");
		Scanner scan = new Scanner(System.in);
		
		int choice = 0;
		boolean valid = false;
		while(!valid) {
			System.out.print("Choose 1-5. > ");
			if(!scan.hasNextInt()) {
				scan.nextLine();
			} else {
				choice = scan.nextInt();
				if (choice >= 1 && choice <= 5)
					valid = true;
			}
		}
		switch(choice) {
			case 1:
				quickSort(empList, Employee.NameComparator, 0, 
						empList.size() - 1);
				break;
			case 2:
				quickSort(empList, Employee.GenderComparator, 0, 
						empList.size() - 1);
				break;
			case 3:
				quickSort(empList, Employee.EmpIDComparator, 0, 
						empList.size() - 1);
				break;
			case 4:
				quickSort(empList, Employee.FloorComparator, 0, 
						empList.size() - 1);
				break;
			case 5:
				quickSort(empList, Employee.WingComparator, 0,
						empList.size() - 1);
				break;
			default:
				System.out.println("Something went horribly wrong.");
		}
		System.out.println("After sorting:");
		for (Employee empList1 : empList) {
			System.out.println("\n" + empList1.toString());
		}
	}
	
	private static void quickSort(ArrayList<Employee> S, 
			Comparator<Employee> comp, int a, int b) {
		if (a >= b) return;
		int left = a;
		int right = b-1;
		Employee pivot = S.get(b);
		Employee temp;
		while (left <= right) {
			while (left <= right && comp.compare(S.get(left), pivot) < 0)
				left++;
			while (left <= right && comp.compare(S.get(right), pivot) > 0)
				right--;
			if (left <= right) {
				temp = S.get(left);
				S.set(left, S.get(right));
				S.set(right, temp);
				left++;
				right--;
			}
		}
		temp = S.get(left);
		S.set(left, S.get(b));
		S.set(b, temp);
		quickSort(S, comp, a, left - 1);
		quickSort(S, comp, left + 1, b);
	}
}
