import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Library {
	public static int[] constants = {1, 0, 1, 1, 0, 0};
	// Librarians countId, Librarians freeIndex, Books countIdBook, Books countIdIssuedBook, Books freeIndexIssuedBook, Students freeIndex
	public static void readConstants() {
		try {
			FileInputStream fis = new FileInputStream("constants.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			constants = (int[]) ois.readObject();
			ois.close();
		}
		catch(FileNotFoundException fnfe) {
			
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), "Error reading constants file. " + e, "Alert!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void writeConstants() {
		try {
			FileOutputStream fos = new FileOutputStream("constants.ser");
		    ObjectOutputStream oos = new ObjectOutputStream(fos);
		    oos.writeObject(constants);
		    oos.close();
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), "Error writing constants file. " + e, "Alert!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	
	public static void main(String[] args) {
		Librarians.createLibrarian();
		Books.createBook();
		Books.createIssuedBook();
		Students.createStudent();
		
		readConstants();
		Librarians.readLibrarians();
		Books.readBooks();
		Students.readStudents();
		
		LibraryGui.createLibraryGui();
		
	}
}
