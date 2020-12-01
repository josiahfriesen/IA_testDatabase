/**
* date: Oct 22 2017
* purpose: Initialize and/or read from text files, containing data to be used later in the application. 
* This screen serves as a hub of navigation, through which the other screens can be accessed. 
*/

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class StartScreen {
	
	public JFrame frame;
	TestQuestionDB data = new TestQuestionDB();
	
	ArrayList<Unit> units = new ArrayList<Unit>();
	ArrayList<String> unitNames = new ArrayList<String>();
	/**
	 * Launch the application.
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartScreen window = new StartScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws ClassNotFoundException 
	 */
	public StartScreen() throws ClassNotFoundException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws ClassNotFoundException 
	 */
	private void initialize() throws ClassNotFoundException {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		//https://stackoverflow.com/questions/276254/how-to-disable-or-hide-the-close-x-button-on-a-jframe
		
		/**
		 * purpose: The below method initializes two text files (after checking if they exist or not), and if they dont already exist, 
		 * populates them with arraylists to and various names to utilize in the rest of the application
		 * parameters passed: NA
		 * return type: NA
		 */

		try {
			File f = new File("questions.txt");
			
			File j = new File("tests.txt");
			
			if (f.length() <= 0) {
				unitNames.add("Unit 1: Sequences and series");
				unitNames.add("Unit 2: Functions");
				unitNames.add("Unit 3: Polynomials");
				unitNames.add("Unit 4: Differentiation");
				unitNames.add("Unit 5: Exponents and logarithms");
				unitNames.add("Unit 6: Statistics and probability");
				unitNames.add("Unit 7: Integration");
				unitNames.add("Unit 8: Triginometry");
				unitNames.add("Unit 9: Integration and triginometry");
				unitNames.add("Unit 10: Probability distributions");
				unitNames.add("Unit 11: Vectors");
				unitNames.add("Unit 12: Complex numbers");
				
				for (int i = 0; i < 12; i++) {
					Unit unitToAdd = new Unit (unitNames.get(i), i, new ArrayList<Question>());
					units.add(unitToAdd);
				}
				
				FileOutputStream fos = new FileOutputStream("questions.txt");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(units);
				oos.close();
			}
			else {
				// read object from file
				FileInputStream fis = new FileInputStream("questions.txt");
				ObjectInputStream ois = new ObjectInputStream(fis);
				units = (ArrayList<Unit>) ois.readObject();
				ois.close();
			}
			
			if (j.length() <= 0) {
				data.possibleStandards.add("#1: Knowledge and Understanding");
				data.possibleStandards.add("#2: Problem Solving");
				data.possibleStandards.add("#3: Communication and Interpretation");
				data.possibleStandards.add("#4: Technology Use");
				data.possibleStandards.add("#5: Reasoning");
				for (int i = 0; i<5; i++) {
					data.standardCount.add(0);
				}
				
				FileOutputStream fos2 = new FileOutputStream("tests.txt");
				ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
				oos2.writeObject(data);
				oos2.close();
				
			}
			else {
				// read object from file
				FileInputStream fis2 = new FileInputStream("tests.txt");
				ObjectInputStream ois2 = new ObjectInputStream(fis2);
				data = (TestQuestionDB) ois2.readObject();
				ois2.close();
				
				
			}
		} 
		
		catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//----------------------------------------------------------------------------
		
		JLabel lblTheTeachersAssistant = new JLabel("The Teacher's Assistant");
		lblTheTeachersAssistant.setBounds(144, 6, 161, 64);
		frame.getContentPane().add(lblTheTeachersAssistant);
		
		//----------------------------------------------------------------------------
		
		JButton btnQuestionMenu = new JButton("Question menu");
		btnQuestionMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
                    frame.setVisible(false);
                    QuestionMenu window = new QuestionMenu(units);
                    window.frmQMenu.setVisible(true);
                    
            		}
            		catch (Exception j) {
            			j.printStackTrace();
            		}	
			}
		});
		btnQuestionMenu.setBounds(144, 145, 144, 29);
		frame.getContentPane().add(btnQuestionMenu);
		
		//----------------------------------------------------------------------------
		
		JButton btnTestMenu = new JButton("Test menu");
		btnTestMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
                    frame.setVisible(false);
                    TestMenu window = new TestMenu();
                    window.frmTMenu.setVisible(true);
                    
            		}
				catch (Exception j) {
            			j.printStackTrace();
				}
			}
		});
		btnTestMenu.setBounds(144, 186, 144, 29);
		frame.getContentPane().add(btnTestMenu);
		
		//----------------------------------------------------------------------------
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				//https://stackoverflow.com/questions/2352727/closing-jframe-with-button-click
			}
		});
		btnQuit.setBounds(154, 223, 117, 29);
		frame.getContentPane().add(btnQuit);
	}
	
	
}
