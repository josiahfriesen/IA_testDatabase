/**
* date: Jan. 1 2017
* purpose: Provides a hub from which one can select to edit or use a test, or create a new test. 
* There is also a report that can be accessed for the user from this screen.
*/

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.awt.event.ActionEvent;

public class TestMenu{

	public JFrame frmTMenu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestMenu window = new TestMenu();
					window.frmTMenu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public TestMenu() throws ClassNotFoundException, IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frmTMenu.
	 */
	private void initialize()  throws IOException, ClassNotFoundException{
		frmTMenu = new JFrame();
		frmTMenu.setBounds(100, 100, 450, 300);
		frmTMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTMenu.getContentPane().setLayout(null);
		frmTMenu.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		FileInputStream fis2;
		fis2 = new FileInputStream("tests.txt");
		ObjectInputStream ois2 = new ObjectInputStream(fis2);
		TestQuestionDB data = (TestQuestionDB) ois2.readObject();
		ois2.close();
		
		JButton btnCreateTest = new JButton("Create test");
		btnCreateTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
                    frmTMenu.setVisible(false);
                    UnitSelector window = new UnitSelector(0);
                    window.frmUnit.setVisible(true);
                    
				}
				catch (Exception j) {
            			j.printStackTrace();
				}
			}
		});
		btnCreateTest.setBounds(137, 107, 184, 29);
		frmTMenu.getContentPane().add(btnCreateTest);
		
		JButton btnSelectExistingTest = new JButton("Edit / Use Existing Test");
		btnSelectExistingTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
                    frmTMenu.setVisible(false);
                    UnitSelector window = new UnitSelector(1);
                    window.frmUnit.setVisible(true);
                    
				}
				catch (Exception j) {
            			j.printStackTrace();
				}
			}
		});
		btnSelectExistingTest.setBounds(137, 148, 184, 29);
		frmTMenu.getContentPane().add(btnSelectExistingTest);
		
		JButton btnStartScreen = new JButton("Start Screen");
		btnStartScreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
                    frmTMenu.setVisible(false);
                    StartScreen window = new StartScreen();
                    window.frame.setVisible(true);
                    
				}
				catch (Exception j) {
            			j.printStackTrace();
            		}
			}
		});
		btnStartScreen.setBounds(327, 243, 117, 29);
		frmTMenu.getContentPane().add(btnStartScreen);
		
		//----------------------------------------------------------------------------
		
		/**
		 * purpose: produce a report of the number of times each standard has been used (each time it has appeared in a "printed" test).
		 * parameters passed: NA
		 * return type: NA
		 */
		
		JButton btnStandardCountReport = new JButton("Standard count report");
		btnStandardCountReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Number of times each standard has been assessed to-date: \n"+
						"#1: Knowledge and Understanding: "+data.getStandardCount().get(0)+
						"\n#2: Problem Solving: "+data.getStandardCount().get(1)+
						"\n#3: Communication and Interpretation: "+data.getStandardCount().get(2)+
						"\n#4: Technology Use: "+data.getStandardCount().get(3)+
						"\n#5: Reasoning: "+data.getStandardCount().get(4)
						);
				//https://www.java-tips.org/java-se-tips-100019/15-javax-swing/1912-how-to-create-a-message-dialog-box.html
			}
		});
		btnStandardCountReport.setBounds(18, 239, 192, 29);
		frmTMenu.getContentPane().add(btnStandardCountReport);
		
		//----------------------------------------------------------------------------
	}
}
