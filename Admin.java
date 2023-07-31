
public class Admin {
	
	public static void addLibrarian(String name, String password, String email, String address, String city, String contact) {
		Librarians.librariansData[Library.constants[1]].id = Library.constants[0]++;
		Librarians.librariansData[Library.constants[1]].name = name;
		Librarians.librariansData[Library.constants[1]].password = password;
		Librarians.librariansData[Library.constants[1]].email = email;
		Librarians.librariansData[Library.constants[1]].address = address;
		Librarians.librariansData[Library.constants[1]].city = city;
		Librarians.librariansData[Library.constants[1]].contact = contact;
		Library.constants[1]++;
	}
	
	
	public static int deleteLibrarian(int id) {
		for (int i = 0; i < Library.constants[1]; i++) {
			if (Librarians.librariansData[i].id == id) {
				for (int j = i; j < Library.constants[1]; j++) {
					Librarians.librariansData[j] = Librarians.librariansData[j+1];
				}
				Library.constants[1]--;
				return 0;
			}
		}
		return 1;
	}
	
	public static void addStudent(String name, String password, String email, String address, String city, String contact) {
		Students.studentsData[Library.constants[5]].id = Library.constants[5] + 1;
		Students.studentsData[Library.constants[5]].name = name;
		Students.studentsData[Library.constants[5]].password = password;
		Students.studentsData[Library.constants[5]].email = email;
		Students.studentsData[Library.constants[5]].address = address;
		Students.studentsData[Library.constants[5]].city = city;
		Students.studentsData[Library.constants[5]].contact = contact;
		
		Library.constants[5]++;

	}
	
	
}
