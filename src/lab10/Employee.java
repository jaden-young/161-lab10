package lab10;

import java.util.Comparator;

/**
 *
 * @author Jaden Young
 */
public class Employee implements Comparable<Employee> {

	private String name;
	private char gender;
	private int empID;
	private int office;

	/**
	 * Default constructor
	 */
	public Employee() {
		name = "John Doe";
		gender = 'M';
		empID = 0000;
		office = 0;
	}

	/**
	 * Overloaded Constructor
	 *
	 * @param name Name of the employee
	 * @param gender Gender, either M or F
	 * @param empID ID number of the employee
	 * @param office Office number of the employee. Leading character is the
	 * floor number, second is the building wing, followed by room number
	 */
	public Employee(String name, char gender, int empID, int office) {
		this.name = name;
		this.gender = gender;
		this.empID = empID;
		this.office = office;
	}

// ----------------------------- Accessor methods ----------------------------
	public String getName() {
		return name;
	}

	public char getGender() {
		return gender;
	}

	public int getEmpID() {
		return empID;
	}

	public int getOffice() {
		return office;
	}

// ----------------------------- Mutator Methods -----------------------------
	public void setName(String name) {
		this.name = name;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public void setEmpID(int empID) {
		this.empID = empID;
	}

	public void setOffice(int office) {
		this.office = office;
	}

	/**
	 * Returns a printable string representation of the employee Ex: Name: John
	 * Doe Gender: M empID: 3024 Office: 2313
	 *
	 * @return String representing the data of the employee
	 */
	@Override
	public String toString() {
		String output = "";
		output += "Name: " + name;
		output += "\nGender: " + gender;
		output += "\nempID: " + empID;
		output += "\nOffice: " + office;
		return output;
	}

	@Override
	public int compareTo(Employee other) {
		return this.empID - other.empID;
	}

	public static final Comparator<Employee> NameComparator = new 
		Comparator<Employee>() {
		/**
		 * Compares the names of 2 employee objects. Returns negative integer
		 * if e1 comes alphabetically before e2, 0 if same name, or positive
		 * if e1 comes after e2
		 * @param e1 First employee object to compare
		 * @param e2 Second employee object to compare
		 * @return Negative integer if e1 comes alphabetically before e2, 0 
		 * if they are the same name, or positive integer if e1 comes after e2
		 */
		@Override
		public int compare(Employee e1, Employee e2) {
			return compareAlphabetically(e1.name, e2.name);
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
	};
	
	public static final Comparator<Employee> GenderComparator = new 
		Comparator<Employee>() {
			/**
			 * Compares the gender of 2 employee objects.
			 *		e1 - Female, e2 - Male = Negative integer
			 *		e1 - Female, e2 - Female = 0
			 *		e1 - Male, e2 - Male = 0
			 *		e1 - Male, e2 - Female = Positive integer
			 * @param e1 First employee to compare
			 * @param e2 Second employee to compare
			 * @return Negative integer if first employee is female and second 
			 * is male, 0 if both are the same gender, and positive integer if 
			 * first employee is male and second is female.
			 */
			public int compare(Employee e1, Employee e2) {
				return e1.gender - e2.gender;
			}
		};
	
	public static final Comparator<Employee> EmpIDComparator = new 
		Comparator<Employee>() {
			/**
			 * Compares the empID's of 2 employee objects. Returns the empID of
			 * e1 minus the empID of e2
			 * @param e1 First employee 
			 * @param e2 Second employee 
			 * @return The empID of e1 minus the empID of e2
			 */
			public int compare(Employee e1, Employee e2) {
				return e1.empID - e2.empID;
			}
		};
	
	public static final Comparator<Employee> FloorComparator = new
		Comparator<Employee>() {
			/**
			 * Compares the floor numbers of 2 employees
			 * @param e1 First employee to compare
			 * @param e2 Second employee to compare
			 * @return Negative integer if first floor is lower than second,
			 * 0 if floors are equal, and positive if first is higher
			 */
			public int compare(Employee e1, Employee e2) {
				String o1 = "" + e1.office;
				char floor1Char = o1.charAt(0);
				String floor1String = "" + floor1Char;
				int floor1 = Integer.parseInt(floor1String);
				
				String o2 = "" + e2.office;
				char floor2Char = o2.charAt(0);
				String floor2String = "" + floor2Char;
				int floor2 = Integer.parseInt(floor2String);
				
				return floor1 - floor2;
			}
		};
	
	public static final Comparator<Employee> WingComparator = new 
		Comparator<Employee>() {
			/**
			 * Compares the wing (department) numbers of 2 employee objects
			 * @param e1 First employee to compare
			 * @param e2 Second employee to compare
			 * @return Negative integer if first is smaller than second, 0 
			 * if wings are equal, positive integer if first is bigger
			 */
			public int compare(Employee e1, Employee e2) {
				String o1 = "" + e1.office;
				char wing1Char = o1.charAt(1);
				String wing1String = "" + wing1Char;
				int wing1 = Integer.parseInt(wing1String);
				
				String o2 = "" + e2.office;
				char wing2Char = o2.charAt(1);
				String wing2String = "" + wing2Char;
				int wing2 = Integer.parseInt(wing2String);
				
				return wing1 - wing2;
			}
		};
}
