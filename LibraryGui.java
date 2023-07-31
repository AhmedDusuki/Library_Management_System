import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class LibraryGui {
	static Font title = new Font(Font.SANS_SERIF,Font.BOLD,30);
	static Font titleButton = new Font(Font.SANS_SERIF,Font.PLAIN,18);
	
	static Font subtitle = new Font(Font.SANS_SERIF, Font.BOLD, 22);
	static Font labelButton = new Font(Font.SANS_SERIF,Font.PLAIN,14);
	
	
	private static void openLibrarianTable() {
		JFrame frameTable = new JFrame();
		frameTable.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameTable.setSize(1000,500);
		JPanel panelTable = new JPanel();
		panelTable.setSize(1000, 500);
		panelTable.setLayout(null);
		
		
		String[] columnNames = {"id", "name", "password", "email", "address", "city", "contact"};
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);
		
		for(int i = 0; i < Library.constants[1]; i++) {
			model.addRow(new Object[] {Librarians.librariansData[i].id,
					Librarians.librariansData[i].name, Librarians.librariansData[i].password, 
					Librarians.librariansData[i].email, Librarians.librariansData[i].address,
					Librarians.librariansData[i].city, Librarians.librariansData[i].contact});
		}
		
		JTable tableLibrarian_1 = new JTable(model);
		JScrollPane scrollPaneLibrarian_1 = new JScrollPane(tableLibrarian_1);
		scrollPaneLibrarian_1.setBounds(0, 0, 985, 470);
		tableLibrarian_1.setFillsViewportHeight(true);
		panelTable.add(scrollPaneLibrarian_1);
		
		frameTable.setVisible(true);
		frameTable.add(panelTable);
	}
	private static void openBooksTable() {
		JFrame frameTable = new JFrame();
		frameTable.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameTable.setSize(1000,500);
		JPanel panelTable = new JPanel();
		panelTable.setSize(1000, 500);
		panelTable.setLayout(null);
		
		
		String[] columnNames = {"id", "callno", "name", "author", "publisher", "quantity", "issued", "added_date"};
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);
		for(int i = 0; i < Library.constants[2] - 1; i++) {
			model.addRow(new Object[] {Books.booksData[i].id, Books.booksData[i].callNo, Books.booksData[i].name, 
					Books.booksData[i].author, Books.booksData[i].publisher, Books.booksData[i].quantity,
					Books.booksData[i].issuedNo, Books.booksData[i].addedDate});
		}
		
		JTable tableBooks_1 = new JTable(model);
		JScrollPane scrollPaneBooks_1 = new JScrollPane(tableBooks_1);
		scrollPaneBooks_1.setBounds(0, 0, 985, 470);
		tableBooks_1.setFillsViewportHeight(true);
		panelTable.add(scrollPaneBooks_1);
		
		frameTable.setVisible(true);
		frameTable.add(panelTable);
	}
	private static void openIssuedBooksTable() {
		JFrame frameTable = new JFrame();
		frameTable.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameTable.setSize(1000,500);
		JPanel panelTable = new JPanel();
		panelTable.setSize(1000, 500);
		panelTable.setLayout(null);
		
		
		String[] columnNames = {"id", "bookcallno", "studentid", "studentname", "studentcontact", "issueddate", "returndate"};
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);
		for(int i = 0; i < Library.constants[4]; i++) {
			model.addRow(new Object[] {Books.issuedBooksData[i].id, Books.issuedBooksData[i].callNo, Books.issuedBooksData[i].studentId, 
					Books.issuedBooksData[i].studentName, Books.issuedBooksData[i].studentContact, Books.issuedBooksData[i].issuedDate,
					Books.issuedBooksData[i].returnDate});
		}
		JTable tableIssuedBooks_1 = new JTable(model);
		JScrollPane scrollPaneIssuedBooks_1 = new JScrollPane(tableIssuedBooks_1);
		scrollPaneIssuedBooks_1.setBounds(0, 0, 985, 470);
		tableIssuedBooks_1.setFillsViewportHeight(true);
		panelTable.add(scrollPaneIssuedBooks_1);
		
		frameTable.setVisible(true);
		frameTable.add(panelTable);
	}
	
	private static int verifyNameCityAuthorPublisher(String input) {
		if (input.length() > 0) {
			if (input.matches("[a-zA-Z\s]+")) {
				return 0; // successful
			}
			else {
				return 2; // used non letters
			}
		}
		else {
			return 1; // empty input
		}
	}
	
	public static boolean isValidEmailAddress(String email) {
         String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
         java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
         java.util.regex.Matcher m = p.matcher(email);
         return m.matches();
	}
	
	private static int verifyPasswordAddressCallNo(String input) {
		if (input.length() > 0) {
			return 0; // successful
		}
		else {
			return 1; // empty input
		}
	}
	
	private static int verifyContactIdQuantity(String input) {
		if (input.length() > 0) {
			if (input.matches("[0-9]+")) {
				return 0; // successful
			}
			else {
				return 2; // used non digits
			}
		}
		else {
			return 1; // empty input
		}
	}
	
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private static int verifyDate(String input) {
		if (input.length()>0) {
			try {
				dateFormat.parse(input);
				return 0; // successful
			}
			catch(Exception e) {
				return 1; // wrong format
			}
		}
		else {
			return 2; // empty input
		}
	}
	
	public static void createLibraryGui() {
		try {
			UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
		} catch (ClassNotFoundException | InstantiationException |
                IllegalAccessException | UnsupportedLookAndFeelException ex){
			ex.printStackTrace();
		}
		JFrame messageBox = new JFrame();
		JFrame frame = new JFrame("Library Management");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,500);
		
		frame.addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent e) {
				Library.writeConstants();
				Librarians.writeLibrarians();
				Books.writeBooks();
				Students.writeStudents();
		    }
		});
		
		
		// panel 1 start
		JPanel panel1 = new JPanel();
		panel1.setSize(400, 500);
		panel1.setLayout(null);
		
		JLabel label1_1 = new JLabel("Library Management");
		label1_1.setBounds(0, 0, 400, 200);
		label1_1.setHorizontalAlignment(0);
		label1_1.setFont(title);
		panel1.add(label1_1);
		
		JButton button1_1 = new JButton("Admin Login");
		button1_1.setBounds(100,200,200,50);
		button1_1.setFont(titleButton);
		panel1.add(button1_1);
		
		JButton button1_2 = new JButton("Librarian Login");
		button1_2.setBounds(100,280,200,50);
		button1_2.setFont(titleButton);
		panel1.add(button1_2);
		
		JButton button1_3 = new JButton("Student Login");
		button1_3.setBounds(100,360,200,50);
		button1_3.setFont(titleButton);
		panel1.add(button1_3);
		
		frame.add(panel1);
		// panel 1 end
		
		// panel 2 start
		JPanel panel2 = new JPanel();
		panel2.setSize(400, 500);
		panel2.setLayout(null);
		JLabel label2_1 = new JLabel("Admin Login Form");
		label2_1.setBounds(0, 0, 400, 150);
		label2_1.setHorizontalAlignment(0);
		label2_1.setFont(subtitle);
		panel2.add(label2_1);
		
		JLabel label2_2 = new JLabel("Enter Name:");
		label2_2.setBounds(10, 150, 120, 50);
		label2_2.setFont(labelButton);
		panel2.add(label2_2);
		
		JLabel label2_3 = new JLabel("Enter Password:");
		label2_3.setBounds(10, 200, 120, 50);
		label2_3.setFont(labelButton);
		panel2.add(label2_3);
		
		JLabel label2_4 = new JLabel("Wrong username/pasword.");
		label2_4.setBounds(100, 330, 200, 50);
		label2_4.setFont(labelButton);
		label2_4.setForeground(Color.red);
		label2_4.setVisible(false);
		panel2.add(label2_4);
		
		JTextField userText2_1 = new JTextField();
		userText2_1.setBounds(150, 165, 200, 20);
		panel2.add(userText2_1);
		
		JPasswordField userPass2_1 = new JPasswordField();
		userPass2_1.setBounds(150, 215, 200, 20);
		panel2.add(userPass2_1);
		
		JButton button2_1 = new JButton("Login");
		button2_1.setBounds(150, 280, 100, 40);
		button2_1.setFont(labelButton);
		panel2.add(button2_1);
		panel2.setVisible(false);
		frame.add(panel2);
		// panel 2 end
		
		// panel 4 start
		JPanel panel4 = new JPanel();
		panel4.setSize(400, 500);
		panel4.setLayout(null);
		JLabel label4_1 = new JLabel("Admin Section");
		label4_1.setBounds(0, 0, 400, 150);
		label4_1.setHorizontalAlignment(0);
		label4_1.setFont(subtitle);
		panel4.add(label4_1);
		
		JButton button4_1 = new JButton("Add Librarian");
		button4_1.setBounds(100, 150, 200, 35);
		button4_1.setFont(labelButton);
		panel4.add(button4_1);
		
		JButton button4_2 = new JButton("View Librarian");
		button4_2.setBounds(100, 200, 200, 35);
		button4_2.setFont(labelButton);
		panel4.add(button4_2);
		
		JButton button4_3 = new JButton("Delete Librarian");
		button4_3.setBounds(100, 250, 200, 35);
		button4_3.setFont(labelButton);
		panel4.add(button4_3);
		
		JButton button4_4 = new JButton("Add Student");
		button4_4.setBounds(100, 300, 200, 35);
		button4_4.setFont(labelButton);
		panel4.add(button4_4);
		
		JButton button4_5 = new JButton("Logout");
		button4_5.setBounds(100, 350, 200, 35);
		button4_5.setFont(labelButton);
		panel4.add(button4_5);
		
		panel4.setVisible(false);
		frame.add(panel4);
		// panel 4 end
		
		// panel 5 start
		JPanel panel5 = new JPanel();
		panel5.setSize(400, 500);
		panel5.setLayout(null);
		
		JLabel label5_1 = new JLabel("Add Librarian");
		label5_1.setBounds(0, 0, 400, 50);
		label5_1.setHorizontalAlignment(0);
		label5_1.setFont(subtitle);
		panel5.add(label5_1);
		
		JLabel label5_2 = new JLabel("Name:");
		label5_2.setBounds(10, 70, 120, 35);
		label5_2.setFont(labelButton);
		panel5.add(label5_2);
		
		JTextField userText5_1 = new JTextField();
		userText5_1.setBounds(130,78,200,20);
		panel5.add(userText5_1);
		
		JLabel label5_3 = new JLabel("Password:");
		label5_3.setBounds(10, 110, 120, 35);
		label5_3.setFont(labelButton);
		panel5.add(label5_3);
		
		JPasswordField userPass5_1 = new JPasswordField();
		userPass5_1.setBounds(130,118,200,20);
		panel5.add(userPass5_1);
		
		JLabel label5_4 = new JLabel("Email:");
		label5_4.setBounds(10, 150, 120, 35);
		label5_4.setFont(labelButton);
		panel5.add(label5_4);
		
		JTextField userText5_2 = new JTextField();
		userText5_2.setBounds(130,158,200,20);
		panel5.add(userText5_2);
		
		JLabel label5_5 = new JLabel("Address:");
		label5_5.setBounds(10, 190, 120, 35);
		label5_5.setFont(labelButton);
		panel5.add(label5_5);
		
		JTextField userText5_3 = new JTextField();
		userText5_3.setBounds(130,198,200,20);
		panel5.add(userText5_3);
		
		JLabel label5_6 = new JLabel("City:");
		label5_6.setBounds(10, 230, 120, 35);
		label5_6.setFont(labelButton);
		panel5.add(label5_6);
		
		JTextField userText5_4 = new JTextField();
		userText5_4.setBounds(130,238,200,20);
		panel5.add(userText5_4);
		
		JLabel label5_7 = new JLabel("Contact No.:");
		label5_7.setBounds(10, 270, 120, 35);
		label5_7.setFont(labelButton);
		panel5.add(label5_7);
		
		JTextField userText5_5 = new JTextField();
		userText5_5.setBounds(130, 278,200,20);
		panel5.add(userText5_5);	
		
		
		JButton button5_1 = new JButton("Add Librarian");
		button5_1.setBounds(125, 350, 150, 35);
		button5_1.setFont(labelButton);
		panel5.add(button5_1);
		
		JButton button5_2 = new JButton("Back");
		button5_2.setBounds(150, 400, 100, 35);
		button5_2.setFont(labelButton);
		panel5.add(button5_2);
		
		panel5.setVisible(false);
		frame.add(panel5);
		// panel 5 end
		
		// panel 6 start
		JPanel panel6 = new JPanel();
		panel6.setSize(400, 500);
		panel6.setLayout(null);
		JLabel label6_1 = new JLabel("Delete Librarian");
		label6_1.setBounds(0, 0, 400, 50);
		label6_1.setHorizontalAlignment(0);
		label6_1.setFont(subtitle);
		panel6.add(label6_1);
		
		JLabel label6_2 = new JLabel("Id:");
		label6_2.setBounds(40, 150, 100, 35);
		label6_2.setFont(labelButton);
		panel6.add(label6_2);
		
		JTextField userText6_1 = new JTextField();
		userText6_1.setBounds(100,158,200,20);
		panel6.add(userText6_1);
		
		
		JButton button6_1 = new JButton("Delete Librarian");
		button6_1.setBounds(125, 350, 150, 35);
		button6_1.setFont(labelButton);
		panel6.add(button6_1);
		
		JButton button6_2 = new JButton("Back");
		button6_2.setBounds(150, 400, 100, 35);
		button6_2.setFont(labelButton);
		panel6.add(button6_2);
		
		panel6.setVisible(false);
		frame.add(panel6);
		// panel 6 end

		
		// panel 3 start
		JPanel panel3 = new JPanel();
		panel3.setSize(400, 500);
		panel3.setLayout(null);
		JLabel label3_1 = new JLabel("Librarian Login Form");
		label3_1.setBounds(0, 0, 400, 150);
		label3_1.setHorizontalAlignment(0);
		label3_1.setFont(subtitle);
		panel3.add(label3_1);
		
		JLabel label3_2 = new JLabel("Enter Name:");
		label3_2.setBounds(10, 150, 120, 50);
		label3_2.setFont(labelButton);
		panel3.add(label3_2);
		
		JLabel label3_3 = new JLabel("Enter Password:");
		label3_3.setBounds(10, 200, 120, 50);
		label3_3.setFont(labelButton);
		panel3.add(label3_3);
		
		JLabel label3_4 = new JLabel("Wrong username/pasword.");
		label3_4.setBounds(100, 330, 200, 50);
		label3_4.setFont(labelButton);
		label3_4.setForeground(Color.red);
		label3_4.setVisible(false);
		panel3.add(label3_4);
		
		JTextField userText3_1 = new JTextField();
		userText3_1.setBounds(150, 165, 200, 20);
		panel3.add(userText3_1);
		
		JPasswordField userPass3_1 = new JPasswordField();
		userPass3_1.setBounds(150, 215, 200, 20);
		panel3.add(userPass3_1);
		
		JButton button3_1 = new JButton("Login");
		button3_1.setBounds(150, 280, 100, 40);
		button3_1.setFont(labelButton);
		panel3.add(button3_1);
		
		panel3.setVisible(false);
		frame.add(panel3);
		// panel 3 end
		
		// panel 7 start
		
		JPanel panel7 = new JPanel();
		panel7.setSize(400, 500);
		panel7.setLayout(null);
		JLabel label7_1 = new JLabel("Librarian Section");
		label7_1.setBounds(0, 0, 400, 70);
		label7_1.setHorizontalAlignment(0);
		label7_1.setFont(subtitle);
		panel7.add(label7_1);
		
		JButton button7_1 = new JButton("Add Books");
		button7_1.setBounds(100, 100, 200, 35);
		button7_1.setFont(labelButton);
		panel7.add(button7_1);
		
		JButton button7_2 = new JButton("View Books");
		button7_2.setBounds(100, 150, 200, 35);
		button7_2.setFont(labelButton);
		panel7.add(button7_2);
		
		JButton button7_3 = new JButton("Issue Book");
		button7_3.setBounds(100, 200, 200, 35);
		button7_3.setFont(labelButton);
		panel7.add(button7_3);
		
		JButton button7_4 = new JButton("View Issued Books");
		button7_4.setBounds(100, 250, 200, 35);
		button7_4.setFont(labelButton);
		panel7.add(button7_4);
		
		JButton button7_5 = new JButton("Return Book");
		button7_5.setBounds(100, 300, 200, 35);
		button7_5.setFont(labelButton);
		panel7.add(button7_5);
		
		JButton button7_6 = new JButton("Logout");
		button7_6.setBounds(100, 350, 200, 35);
		button7_6.setFont(labelButton);
		panel7.add(button7_6);
		
		panel7.setVisible(false);
		frame.add(panel7);
		// panel 7 end
		
		// panel 8 start
		JPanel panel8 = new JPanel();
		panel8.setSize(400, 500);
		panel8.setLayout(null);
		
		JLabel label8_1 = new JLabel("Add Book");
		label8_1.setBounds(0, 0, 400, 50);
		label8_1.setHorizontalAlignment(0);
		label8_1.setFont(subtitle);
		panel8.add(label8_1);
		
		JLabel label8_2 = new JLabel("Call No.:");
		label8_2.setBounds(10, 70, 120, 35);
		label8_2.setFont(labelButton);
		panel8.add(label8_2);
		
		JTextField userText8_1 = new JTextField();
		userText8_1.setBounds(130,78,200,20);
		panel8.add(userText8_1);
		
		JLabel label8_3 = new JLabel("Name:");
		label8_3.setBounds(10, 110, 120, 35);
		label8_3.setFont(labelButton);
		panel8.add(label8_3);
		
		JTextField userText8_2 = new JTextField();
		userText8_2.setBounds(130,118,200,20);
		panel8.add(userText8_2);
		
		JLabel label8_4 = new JLabel("Author:");
		label8_4.setBounds(10, 150, 120, 35);
		label8_4.setFont(labelButton);
		panel8.add(label8_4);
		
		JTextField userText8_3 = new JTextField();
		userText8_3.setBounds(130,158,200,20);
		panel8.add(userText8_3);
		
		JLabel label8_5 = new JLabel("Publisher:");
		label8_5.setBounds(10, 190, 120, 35);
		label8_5.setFont(labelButton);
		panel8.add(label8_5);
		
		JTextField userText8_4 = new JTextField();
		userText8_4.setBounds(130,198,200,20);
		panel8.add(userText8_4);
		
		JLabel label8_6 = new JLabel("Quantity:");
		label8_6.setBounds(10, 230, 120, 35);
		label8_6.setFont(labelButton);
		panel8.add(label8_6);
		
		JTextField userText8_5 = new JTextField();
		userText8_5.setBounds(130,238,200,20);
		panel8.add(userText8_5);
		
		
		JButton button8_1 = new JButton("Add Book");
		button8_1.setBounds(100, 350, 200, 35);
		button8_1.setFont(labelButton);
		panel8.add(button8_1);
		
		JButton button8_2 = new JButton("Back");
		button8_2.setBounds(150, 400, 100, 35);
		button8_2.setFont(labelButton);
		panel8.add(button8_2);
		
		panel8.setVisible(false);
		frame.add(panel8);
		// panel 8 end
		
		// panel 9 start
		JPanel panel9 = new JPanel();
		panel9.setSize(400, 500);
		panel9.setLayout(null);
		JLabel label9_1 = new JLabel("Issue Book");
		label9_1.setBounds(0, 0, 400, 150);
		label9_1.setHorizontalAlignment(0);
		label9_1.setFont(subtitle);
		panel9.add(label9_1);
		
		JLabel label9_2 = new JLabel("Book Call No.:");
		label9_2.setBounds(10, 100, 120, 50);
		label9_2.setFont(labelButton);
		panel9.add(label9_2);
		
		JLabel label9_3 = new JLabel("Student Id:");
		label9_3.setBounds(10, 150, 120, 50);
		label9_3.setFont(labelButton);
		panel9.add(label9_3);
		
		JLabel label9_4 = new JLabel("Student Name:");
		label9_4.setBounds(10, 200, 120, 50);
		label9_4.setFont(labelButton);
		panel9.add(label9_4);
		
		JLabel label9_5 = new JLabel("Student Contact:");
		label9_5.setBounds(10, 250, 120, 50);
		label9_5.setFont(labelButton);
		panel9.add(label9_5);
		
		JLabel label9_6 = new JLabel("Return Date:");
		label9_6.setBounds(10, 300, 120, 50);
		label9_6.setFont(labelButton);
		panel9.add(label9_6);
		
		JLabel label9_7 = new JLabel("(yyyy-mm-dd)");
		label9_7.setBounds(10, 320, 120, 50);
		label9_7.setFont(labelButton);
		panel9.add(label9_7);
		
		
		JTextField userText9_1 = new JTextField();
		userText9_1.setBounds(150, 115, 200, 20);
		panel9.add(userText9_1);
		
		JTextField userText9_2 = new JTextField();
		userText9_2.setBounds(150, 165, 200, 20);
		panel9.add(userText9_2);
		
		JTextField userText9_3 = new JTextField();
		userText9_3.setBounds(150, 215, 200, 20);
		panel9.add(userText9_3);
		
		JTextField userText9_4 = new JTextField();
		userText9_4.setBounds(150, 265, 200, 20);
		panel9.add(userText9_4);
		
		JTextField userText9_5 = new JTextField();
		userText9_5.setBounds(150, 315, 200, 20);
		panel9.add(userText9_5);
		
		JButton button9_1 = new JButton("Issue Book");
		button9_1.setBounds(140, 345, 120, 40);
		button9_1.setFont(labelButton);
		panel9.add(button9_1);
		
		JButton button9_2 = new JButton("Back");
		button9_2.setBounds(150, 400, 100, 35);
		button9_2.setFont(labelButton);
		panel9.add(button9_2);
		
		panel9.setVisible(false);
		frame.add(panel9);
		// panel 9 end
		
		// panel 10 start
		JPanel panel10 = new JPanel();
		panel10.setSize(400, 500);
		panel10.setLayout(null);
		JLabel label10_1 = new JLabel("Return Book");
		label10_1.setBounds(0, 0, 400, 150);
		label10_1.setHorizontalAlignment(0);
		label10_1.setFont(subtitle);
		panel10.add(label10_1);
		
		JLabel label10_2 = new JLabel("Book Call No.:");
		label10_2.setBounds(10, 150, 120, 50);
		label10_2.setFont(labelButton);
		panel10.add(label10_2);
		
		JLabel label10_3 = new JLabel("Student Id:");
		label10_3.setBounds(10, 200, 120, 50);
		label10_3.setFont(labelButton);
		panel10.add(label10_3);
		
		JTextField userText10_1 = new JTextField();
		userText10_1.setBounds(150, 165, 200, 20);
		panel10.add(userText10_1);
		
		JTextField userText10_2 = new JTextField();
		userText10_2.setBounds(150, 215, 200, 20);
		panel10.add(userText10_2);
		

		JButton button10_1 = new JButton("Return Book");
		button10_1.setBounds(140, 300, 120, 40);
		button10_1.setFont(labelButton);
		panel10.add(button10_1);
		
		JButton button10_2 = new JButton("Back");
		button10_2.setBounds(150, 370, 100, 35);
		button10_2.setFont(labelButton);
		panel10.add(button10_2);
		
		panel10.setVisible(false);
		frame.add(panel10);
		// panel 10 end
		
		// panel 11 start
		
		JPanel panel11 = new JPanel();
		panel11.setSize(400, 500);
		panel11.setLayout(null);
		
		JLabel label11_1 = new JLabel("Add Student");
		label11_1.setBounds(0, 0, 400, 50);
		label11_1.setHorizontalAlignment(0);
		label11_1.setFont(subtitle);
		panel11.add(label11_1);
		
		JLabel label11_2 = new JLabel("Name:");
		label11_2.setBounds(10, 70, 120, 35);
		label11_2.setFont(labelButton);
		panel11.add(label11_2);
		
		JTextField userText11_1 = new JTextField();
		userText11_1.setBounds(130,78,200,20);
		panel11.add(userText11_1);
		
		JLabel label11_3 = new JLabel("Password:");
		label11_3.setBounds(10, 110, 120, 35);
		label11_3.setFont(labelButton);
		panel11.add(label11_3);
		
		JPasswordField userPass11_1 = new JPasswordField();
		userPass11_1.setBounds(130,118,200,20);
		panel11.add(userPass11_1);
		
		JLabel label11_4 = new JLabel("Email:");
		label11_4.setBounds(10, 150, 120, 35);
		label11_4.setFont(labelButton);
		panel11.add(label11_4);
		
		JTextField userText11_2 = new JTextField();
		userText11_2.setBounds(130,158,200,20);
		panel11.add(userText11_2);
		
		JLabel label11_5 = new JLabel("Address:");
		label11_5.setBounds(10, 190, 120, 35);
		label11_5.setFont(labelButton);
		panel11.add(label11_5);
		
		JTextField userText11_3 = new JTextField();
		userText11_3.setBounds(130,198,200,20);
		panel11.add(userText11_3);
		
		JLabel label11_6 = new JLabel("City:");
		label11_6.setBounds(10, 230, 120, 35);
		label11_6.setFont(labelButton);
		panel11.add(label11_6);
		
		JTextField userText11_4 = new JTextField();
		userText11_4.setBounds(130,238,200,20);
		panel11.add(userText11_4);
		
		JLabel label11_7 = new JLabel("Contact No.:");
		label11_7.setBounds(10, 270, 120, 35);
		label11_7.setFont(labelButton);
		panel11.add(label11_7);
		
		JTextField userText11_5 = new JTextField();
		userText11_5.setBounds(130, 278,200,20);
		panel11.add(userText11_5);	
		
		
		JButton button11_1 = new JButton("Add Student");
		button11_1.setBounds(125, 350, 150, 35);
		button11_1.setFont(labelButton);
		panel11.add(button11_1);
		
		JButton button11_2 = new JButton("Back");
		button11_2.setBounds(150, 400, 100, 35);
		button11_2.setFont(labelButton);
		panel11.add(button11_2);
		
		panel11.setVisible(false);
		frame.add(panel11);
		// panel 11 end
		
		// panel 12 start
		JPanel panel12 = new JPanel();
		panel12.setSize(400, 500);
		panel12.setLayout(null);
		
		JLabel label12_1 = new JLabel("Student Login Form");
		label12_1.setBounds(0, 0, 400, 150);
		label12_1.setHorizontalAlignment(0);
		label12_1.setFont(subtitle);
		panel12.add(label12_1);
		
		JLabel label12_2 = new JLabel("Enter Name:");
		label12_2.setBounds(10, 150, 120, 50);
		label12_2.setFont(labelButton);
		panel12.add(label12_2);
		
		JLabel label12_3 = new JLabel("Enter Password:");
		label12_3.setBounds(10, 200, 120, 50);
		label12_3.setFont(labelButton);
		panel12.add(label12_3);
		
		JLabel label12_4 = new JLabel("Wrong username/pasword.");
		label12_4.setBounds(100, 330, 200, 50);
		label12_4.setFont(labelButton);
		label12_4.setForeground(Color.red);
		label12_4.setVisible(false);
		panel12.add(label12_4);
		
		JTextField userText12_1 = new JTextField();
		userText12_1.setBounds(150, 165, 200, 20);
		panel12.add(userText12_1);
		
		JPasswordField userPass12_1 = new JPasswordField();
		userPass12_1.setBounds(150, 215, 200, 20);
		panel12.add(userPass12_1);
		
		JButton button12_1 = new JButton("Login");
		button12_1.setBounds(150, 280, 100, 40);
		button12_1.setFont(labelButton);
		panel12.add(button12_1);
		
		panel12.setVisible(false);
		frame.add(panel12);
		// panel 12 end
		
		// panel 13 start
		JPanel panel13 = new JPanel();
		panel13.setSize(400, 500);
		panel13.setLayout(null);
		
		JLabel label13_1 = new JLabel();
		label13_1.setBounds(0, 0, 400, 40);
		label13_1.setHorizontalAlignment(0);
		label13_1.setFont(subtitle);
		panel13.add(label13_1);
		
		JLabel label13_2 = new JLabel();
		label13_2.setBounds(0, 40, 400, 40);
		label13_2.setHorizontalAlignment(0);
		label13_2.setFont(subtitle);
		panel13.add(label13_2);
		
		String[] columnNames = {"bookcallno", "name", "issueddate", "returndate"};
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);
		
		
		JTable tableStudentIssuedBooks = new JTable(model);
		JScrollPane scrollPaneStudentIssuedBooks = new JScrollPane(tableStudentIssuedBooks);
		scrollPaneStudentIssuedBooks.setBounds(0, 80, 385, 320);
		tableStudentIssuedBooks.setFillsViewportHeight(true);
		panel13.add(scrollPaneStudentIssuedBooks);
		
		JButton button13_1 = new JButton("Logout");
		button13_1.setBounds(150, 410, 100, 40);
		button13_1.setFont(labelButton);
		panel13.add(button13_1);
		
		
		panel13.setVisible(false);
		frame.add(panel13);
		// panel 13 end
		
		ActionListener buttonListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object src = e.getSource();
				if (src == button1_1) {
					panel1.setVisible(false);
					panel2.setVisible(true);
				}
				else if(src == button1_2) {
					panel1.setVisible(false);
					panel3.setVisible(true);
				}
				else if(src == button2_1) {
					label2_4.setVisible(false);
					if(userText2_1.getText().equals("admin") && new String(userPass2_1.getPassword()).equals("password")) {
						panel2.setVisible(false);
						panel4.setVisible(true);
					}
					else {
						label2_4.setVisible(true);
					}
					userText2_1.setText(null);
					userPass2_1.setText(null);
				}
				else if(src == button4_5){
					panel4.setVisible(false);
					panel1.setVisible(true);
				}
				else if(src == button4_1) {
					panel4.setVisible(false);
					panel5.setVisible(true);
				}
				else if(src == button5_1) {
					// input verification here
					if(verifyNameCityAuthorPublisher(userText5_1.getText())==0 && verifyPasswordAddressCallNo(new String(userPass5_1.getPassword()))==0
							&& isValidEmailAddress(userText5_2.getText()) && verifyPasswordAddressCallNo(userText5_3.getText())==0
							&& verifyNameCityAuthorPublisher(userText5_4.getText())==0 && verifyContactIdQuantity(userText5_5.getText())==0) {
						Admin.addLibrarian(userText5_1.getText(), new String(userPass5_1.getPassword()), userText5_2.getText(), userText5_3.getText(), userText5_4.getText(), userText5_5.getText());
						JOptionPane.showMessageDialog(messageBox,"Librarian added successfully!");
					}
					else if (!isValidEmailAddress(userText5_2.getText())) {
						JOptionPane.showMessageDialog(messageBox, "Please check entered email.", "Alert!", JOptionPane.ERROR_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(messageBox, "Please check entered data.", "Alert!", JOptionPane.ERROR_MESSAGE);
					}
					
				}
				else if(src == button5_2) {
					panel5.setVisible(false);
					panel4.setVisible(true);
				}
				else if(src == button4_2) {
					openLibrarianTable();
				}
				else if(src == button4_3) {
					panel4.setVisible(false);
					panel6.setVisible(true);
				}
				else if(src == button6_1) {
					// input verification, search, delete here
					if(verifyContactIdQuantity(userText6_1.getText())==0) {
						if(Admin.deleteLibrarian(Integer.parseInt(userText6_1.getText()))==0) {
							JOptionPane.showMessageDialog(messageBox,"Librarian deleted successfully!");
						}
						else {
							JOptionPane.showMessageDialog(messageBox, "Librarian not found.", "Alert!", JOptionPane.ERROR_MESSAGE);
						}
					}
					else {
						JOptionPane.showMessageDialog(messageBox, "Please check entered data.", "Alert!", JOptionPane.ERROR_MESSAGE);
					}
				}
				else if(src == button6_2) {
					panel6.setVisible(false);
					panel4.setVisible(true);
				}
				else if(src == button3_1) {
					//verify correct username/password if false show label3_4.
					label3_4.setVisible(false);
					boolean foundLibrarian=false;
					String name = userText3_1.getText();
					String password = new String(userPass3_1.getPassword());
					
					for(int i = 0; i < Library.constants[1]; i++) {
						if (Librarians.librariansData[i].name.equals(name) && Librarians.librariansData[i].password.equals(password)) {
							panel3.setVisible(false);
							panel7.setVisible(true);
							foundLibrarian = true;
							break;
						}
					}
					if (!foundLibrarian) {
						label3_4.setVisible(true);
					}
					
					userText3_1.setText(null);
					userPass3_1.setText(null);
				}
				else if(src == button7_6) {
					panel7.setVisible(false);
					panel1.setVisible(true);
				}
				else if(src == button7_1) {
					panel7.setVisible(false);
					panel8.setVisible(true);
				}
				else if(src == button8_1) {
					// input verification, add book
					if(verifyPasswordAddressCallNo(userText8_1.getText())==0 && verifyNameCityAuthorPublisher(userText8_2.getText())==0
							&& verifyNameCityAuthorPublisher(userText8_3.getText())==0 && verifyNameCityAuthorPublisher(userText8_4.getText())==0
							&& verifyContactIdQuantity(userText8_5.getText())==0) {
						Librarians.addBook(userText8_1.getText(), userText8_2.getText(), userText8_3.getText(), userText8_4.getText(), Integer.parseInt(userText8_5.getText()));
						JOptionPane.showMessageDialog(messageBox,"Book added successfully!");
					}
					else {
						JOptionPane.showMessageDialog(messageBox, "Please check entered data.", "Alert!", JOptionPane.ERROR_MESSAGE);
					}
				}
				else if(src == button8_2) {
					panel8.setVisible(false);
					panel7.setVisible(true);
				}
				else if(src == button7_2) {
					openBooksTable();
				}
				else if(src == button7_4) {
					openIssuedBooksTable();
				}
				else if(src == button7_3) {
					panel7.setVisible(false);
					panel9.setVisible(true);
				}
				else if(src == button9_1) {
					// input verification, issue book
					if(verifyPasswordAddressCallNo(userText9_1.getText())==0 && verifyContactIdQuantity(userText9_2.getText())==0
							&& verifyNameCityAuthorPublisher(userText9_3.getText())==0 && verifyContactIdQuantity(userText9_4.getText())==0
							&& verifyDate(userText9_5.getText())==0) {
						int flag = Librarians.issueBook(userText9_1.getText(), Integer.parseInt(userText9_2.getText()), userText9_3.getText(), userText9_4.getText(), userText9_5.getText()+" 23:59:59");
						if (flag == 0){
							JOptionPane.showMessageDialog(messageBox,"Book issued successfully!");
						}
						else if(flag == 1) {
							JOptionPane.showMessageDialog(messageBox, "Book not found. Please check entered data.", "Alert!", JOptionPane.ERROR_MESSAGE);
						}
						else if(flag == 2) {
							JOptionPane.showMessageDialog(messageBox, "No books available.", "Alert!", JOptionPane.ERROR_MESSAGE);
						}
						else if(flag == 3) {
							JOptionPane.showMessageDialog(messageBox, "Student has maximum number (3) allowed of books borrowed.", "Alert!", JOptionPane.ERROR_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(messageBox, "Student not found. Please check entered data.", "Alert!", JOptionPane.ERROR_MESSAGE);
						}
					}
					else {
						JOptionPane.showMessageDialog(messageBox, "Please check entered data.", "Alert!", JOptionPane.ERROR_MESSAGE);
					}
					
				}
				else if(src == button9_2) {
					panel9.setVisible(false);
					panel7.setVisible(true);
				}
				else if(src == button7_5) {
					panel7.setVisible(false);
					panel10.setVisible(true);
				}
				else if(src == button10_1) {
					// input verification, return book
					if(verifyPasswordAddressCallNo(userText10_1.getText())==0 && verifyContactIdQuantity(userText10_2.getText())==0) {
						int flag = Librarians.returnBook(userText10_1.getText(), Integer.parseInt(userText10_2.getText()));
						if(flag == 0) {
							JOptionPane.showMessageDialog(messageBox,"Book returned successfully!");
						}
						else if(flag == 1) {
							JOptionPane.showMessageDialog(messageBox, "Past return date.\nBook returned successfully; student is given a penalty.", "Alert!", JOptionPane.WARNING_MESSAGE);
						}
						else if(flag == 2) {
							JOptionPane.showMessageDialog(messageBox, "Student has not borrowed that book. Please check entered data.", "Alert!", JOptionPane.ERROR_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(messageBox, "Student not found. Please check entered data.", "Alert!", JOptionPane.ERROR_MESSAGE);
						}
					}
					else {
						JOptionPane.showMessageDialog(messageBox, "Please check entered data.", "Alert!", JOptionPane.ERROR_MESSAGE);
					}
					
					
				}
				else if(src == button10_2) {
					panel10.setVisible(false);
					panel7.setVisible(true);
				}
				else if(src == button4_4) {
					panel4.setVisible(false);
					panel11.setVisible(true);
				}
				else if(src == button11_1) {
					// input verification here
					if(verifyNameCityAuthorPublisher(userText11_1.getText())==0 && verifyPasswordAddressCallNo(new String(userPass11_1.getPassword()))==0
							&& isValidEmailAddress(userText11_2.getText()) && verifyPasswordAddressCallNo(userText11_3.getText())==0
							&& verifyNameCityAuthorPublisher(userText11_4.getText())==0 && verifyContactIdQuantity(userText11_5.getText())==0) {
						Admin.addStudent(userText11_1.getText(), new String(userPass11_1.getPassword()), userText11_2.getText(), userText11_3.getText(), userText11_4.getText(), userText11_5.getText());
						JOptionPane.showMessageDialog(messageBox,"Student added successfully!");
					}
					else if(!isValidEmailAddress(userText11_2.getText())) {
						JOptionPane.showMessageDialog(messageBox, "Please check entered email.", "Alert!", JOptionPane.ERROR_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(messageBox, "Please check entered data.", "Alert!", JOptionPane.ERROR_MESSAGE);
					}
				}
				else if(src == button11_2) {
					panel11.setVisible(false);
					panel4.setVisible(true);
				}
				else if(src == button1_3) {
					panel1.setVisible(false);
					panel12.setVisible(true);
				}
				else if(src == button12_1) {

					label12_4.setVisible(false);
					int currentStudent=-1;
					String name = userText12_1.getText();
					String password = new String(userPass12_1.getPassword());
					
					for(int i = 0; i < Library.constants[5]; i++) {
						if (Students.studentsData[i].name.equals(name) && Students.studentsData[i].password.equals(password)) {
							currentStudent = i;
							break;
						}
					}
					if (currentStudent != -1) {
						for(int i = 0; i < Students.studentsData[currentStudent].noBorrowed; i++) {
							for(int j = 0; j < Library.constants[4]; j++) {
								if(Students.studentsData[currentStudent].idBorrowed[i] == Books.issuedBooksData[j].id) {
									model.addRow(new Object[] {Books.issuedBooksData[j].callNo, Books.issuedBooksData[j].name,
											Books.issuedBooksData[j].issuedDate, Books.issuedBooksData[j].returnDate});
									break;
								}
							}
							
						}
						panel12.setVisible(false);
						label13_1.setText("Name: "+Students.studentsData[currentStudent].name);
						label13_2.setText("Id: "+Students.studentsData[currentStudent].id);
						panel13.setVisible(true);
					}
					else {
						label12_4.setVisible(true);
					}
					userText12_1.setText(null);
					userPass12_1.setText(null);
				}
				else if(src == button13_1) {
					panel13.setVisible(false);
					panel1.setVisible(true);
				}

				
			}
		};
		
		button1_1.addActionListener(buttonListener);
		button1_2.addActionListener(buttonListener);
		button2_1.addActionListener(buttonListener);
		button4_1.addActionListener(buttonListener);
		button4_2.addActionListener(buttonListener);
		button4_3.addActionListener(buttonListener);
		button4_4.addActionListener(buttonListener);
		button4_5.addActionListener(buttonListener);
		button5_1.addActionListener(buttonListener);
		button5_2.addActionListener(buttonListener);
		button6_1.addActionListener(buttonListener);
		button6_2.addActionListener(buttonListener);
		button3_1.addActionListener(buttonListener);
		button7_1.addActionListener(buttonListener);
		button7_2.addActionListener(buttonListener);
		button7_3.addActionListener(buttonListener);
		button7_4.addActionListener(buttonListener);
		button7_5.addActionListener(buttonListener);
		button7_6.addActionListener(buttonListener);
		button8_1.addActionListener(buttonListener);
		button8_2.addActionListener(buttonListener);
		button9_1.addActionListener(buttonListener);
		button9_2.addActionListener(buttonListener);
		button10_1.addActionListener(buttonListener);
		button10_2.addActionListener(buttonListener);
		button11_1.addActionListener(buttonListener);
		button11_2.addActionListener(buttonListener);
		button1_3.addActionListener(buttonListener);
		button12_1.addActionListener(buttonListener);
		button13_1.addActionListener(buttonListener);
		
		frame.setVisible(true);
		
	}

}
