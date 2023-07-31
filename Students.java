import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.JOptionPane;



public class Students{

	public static class Student implements Serializable{
		private static final long serialVersionUID = 4L;
		public int id;
		public String name;
		public String password;
		public String email;
		public String address;
		public String city;
		public String contact;
		public int noBorrowed;
		public String callNoBorrowed[] = new String[3];
		public int idBorrowed [] = new int[3];
	}
	
	public static Student studentsData [] = new Student[100];
	
	public static void createStudent() {
		for(int i = 0; i < 100; i++) {
			studentsData[i] = new Student();
		}
	}
	
	public static void readStudents() {
		try {
			FileInputStream fis = new FileInputStream("students.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			studentsData = (Student[]) ois.readObject();
			ois.close();
		}
		catch(FileNotFoundException fnfe) {
			
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), "Error reading students file. " + e, "Alert!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void writeStudents() {
		try {
			FileOutputStream fos = new FileOutputStream("students.ser");
		    ObjectOutputStream oos = new ObjectOutputStream(fos);
		    oos.writeObject(studentsData);
		    oos.close();
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), "Error writing students file. " + e, "Alert!", JOptionPane.ERROR_MESSAGE);
		}
	}
}
