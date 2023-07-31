import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Books{
	
	public static class Book implements Serializable{
		private static final long serialVersionUID = 2L;
		public int id;
		public String callNo;
		public String name;
		public String author;
		public String publisher;
		public int quantity;
		public int issuedNo = 0;
		public String addedDate;
	}
	
	public static class IssuedBook implements Serializable{
		private static final long serialVersionUID = 3L;
		public int id;
		public String callNo;
		public String name;
		public int studentId;
		public String studentName;
		public String studentContact;
		public String issuedDate;
		public String returnDate;
	}
	
	public static Book booksData [] = new Book[100];
	public static IssuedBook issuedBooksData [] = new IssuedBook[100];
	
	public static void createBook() {
		for(int i = 0; i < 100; i++) {
			booksData[i] = new Book();
		}
	}
	
	public static void createIssuedBook() {
		for(int i = 0; i < 100; i++) {
			issuedBooksData[i] = new IssuedBook();
		}
	}
	
	public static void readBooks() {
		try {
			FileInputStream fis = new FileInputStream("books.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			booksData = (Book[]) ois.readObject();
			ois.close();
			
			fis = new FileInputStream("issuedBooks.ser");
			ois = new ObjectInputStream(fis);
			issuedBooksData = (IssuedBook[]) ois.readObject();
			ois.close();
		}
		catch(FileNotFoundException fnfe) {
			
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), "Error reading books files. " + e, "Alert!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void writeBooks() {
		try {
			FileOutputStream fos = new FileOutputStream("books.ser");
		    ObjectOutputStream oos = new ObjectOutputStream(fos);
		    oos.writeObject(booksData);
		    oos.close();
		    
		    fos = new FileOutputStream("issuedBooks.ser");
		    oos = new ObjectOutputStream(fos);
		    oos.writeObject(issuedBooksData);
		    oos.close();
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), "Error writing books files. " + e, "Alert!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
}
