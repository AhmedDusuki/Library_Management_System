import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Librarians {

	static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static class Librarian implements Serializable{
		private static final long serialVersionUID = 1L;
		public int id;
		public String name;
		public String password;
		public String email;
		public String address;
		public String city;
		public String contact;
	}
	
	public static Librarian librariansData [] = new Librarian[100];
	
	public static void createLibrarian() {
		for(int i = 0; i < 100; i++) {
			librariansData[i] = new Librarian();
		}
	}
	

	public static void addBook(String callNo, String name, String author, String publisher, int quantity) {
		Books.booksData[Library.constants[2]-1].callNo = callNo;
		Books.booksData[Library.constants[2]-1].name = name;
		Books.booksData[Library.constants[2]-1].author = author;
		Books.booksData[Library.constants[2]-1].publisher = publisher;
		Books.booksData[Library.constants[2]-1].quantity = quantity;
		Books.booksData[Library.constants[2]-1].id = Library.constants[2];
		
		Date date = new Date();
		Books.booksData[Library.constants[2]-1].addedDate = dateFormat.format(date);
		Library.constants[2]++;
	}

	public static int issueBook(String callNo, int id, String name, String contact, String returnDate) {
		for(int i = 0; i < Library.constants[2] - 1; i++) {
			if (Books.booksData[i].callNo.equals(callNo)) {
				if (Books.booksData[i].quantity != 0) {
					
					for (int j = 0; j < Library.constants[5]; j++) {
						if (Students.studentsData[j].id == id && Students.studentsData[j].name.equals(name) && Students.studentsData[j].contact.equals(contact)) {
							if (Students.studentsData[j].noBorrowed < 3) {
								Books.booksData[i].quantity--;
								Books.booksData[i].issuedNo++;
								Students.studentsData[j].noBorrowed++;
								Students.studentsData[j].callNoBorrowed[Students.studentsData[j].noBorrowed-1] = callNo;
								Books.issuedBooksData[Library.constants[4]].name = Books.booksData[i].name;
								Students.studentsData[j].idBorrowed[Students.studentsData[j].noBorrowed-1] = Library.constants[3];
								Books.issuedBooksData[Library.constants[4]].id = Library.constants[3];
								Books.issuedBooksData[Library.constants[4]].callNo = callNo;
								Books.issuedBooksData[Library.constants[4]].studentId = Students.studentsData[j].id;
								Books.issuedBooksData[Library.constants[4]].studentName = Students.studentsData[j].name;
								Books.issuedBooksData[Library.constants[4]].studentContact = Students.studentsData[j].contact;
								
								Date date = new Date();
								Books.issuedBooksData[Library.constants[4]].issuedDate = dateFormat.format(date);
								Books.issuedBooksData[Library.constants[4]].returnDate = returnDate;
								Library.constants[3]++;
								Library.constants[4]++;
								return 0; // successful
							}
							
							return 3; // borrowed max
						}
					}	return 4; // student not found
					
					
				}
				return 2; // quantity 0
			}
		}
		return 1; // book not found
	}
	
	public static int returnBook(String callNo, int id) {
		for(int i = 0; i < Library.constants[5]; i++) {
			if (Students.studentsData[i].id ==id) {
				for(int j = 0; j < Students.studentsData[i].noBorrowed; j++) {
					if (Students.studentsData[i].callNoBorrowed[j].equals(callNo)) {
						String tempDate = "";
						for (int k = 0; k < Library.constants[2] - 1; k++) {
							if(Books.booksData[k].callNo.equals(callNo)) {
								Books.booksData[k].quantity++;
								Books.booksData[k].issuedNo--;
								break;
							}
						}
						for (int k = 0; k < Library.constants[4]; k++) {
							if (Books.issuedBooksData[k].id == Students.studentsData[i].idBorrowed[j]) {
								tempDate = Books.issuedBooksData[k].returnDate;
								for (int l = k; l < Library.constants[4] - 1; l++) {
									Books.issuedBooksData[l] = Books.issuedBooksData[l+1];
								}
								Library.constants[4]--;
								break;
							}
						}
						for (int k = j; k < Students.studentsData[i].noBorrowed - 1; j++) {
							Students.studentsData[i].idBorrowed[k] = Students.studentsData[i].idBorrowed[k+1];
							Students.studentsData[i].callNoBorrowed[k] = Students.studentsData[i].callNoBorrowed[k+1];
						}
						Students.studentsData[i].noBorrowed--;
						Date currentDate = new Date();
						if (dateFormat.format(currentDate).compareTo(tempDate) > 0) {
							return 1; // successful, penalty
						}
						else {
							return 0; // successful
						}
					}
				}
				return 2; // book not found at student
			}
		}
		return 3; // student not found 
	}
	
	public static void readLibrarians() {
		try {
			FileInputStream fis = new FileInputStream("librarians.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			librariansData = (Librarian[]) ois.readObject();
			ois.close();
		}
		catch(FileNotFoundException fnfe) {
			
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), "Error reading librarians file. " + e, "Alert!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void writeLibrarians() {
		try {
			FileOutputStream fos = new FileOutputStream("librarians.ser");
		    ObjectOutputStream oos = new ObjectOutputStream(fos);
		    oos.writeObject(librariansData);
		    oos.close();
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), "Error writing librarians file. " + e, "Alert!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	
}
