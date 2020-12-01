/**
* date: Jan 1 2017
* purpose: This screen displays a list of tests, with options to display the selected test in a text file, 
* and to edit the selected test.
*/
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;


public class TestSelector extends JFrame{
	
	ArrayList<Test> unitTests = new ArrayList<Test>();
	public JFrame frmTSelect;

	/**
	 * purpose: To launch the screen and pass along the appropriate variable
	 * parameters passed: int Indicator, int unitIndex. Indicator dictates if the selected test is to be edited, which is then passed to the testInitializer class
	 * return type: NA
	 */
	public static void main(int Indicator, int unitIndex) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestSelector window = new TestSelector(Indicator, unitIndex);
					window.frmTSelect.setVisible(true);
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
	public TestSelector(int Indicator, int unitIndex) throws ClassNotFoundException, IOException {
		initialize(Indicator, unitIndex);
	}

	/**
	 * Initialize the contents of the frmTSelect.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	private void initialize(int Indicator, int unitIndex) throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("questions.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		ArrayList<Unit> units = (ArrayList<Unit>) ois.readObject();
		ois.close();
		
		FileInputStream fis2 = new FileInputStream("tests.txt");
		ObjectInputStream ois2 = new ObjectInputStream(fis2);
		TestQuestionDB data = (TestQuestionDB) ois2.readObject();
		ois2.close();
		
		frmTSelect = new JFrame();
		frmTSelect.setBounds(100, 100, 450, 300);
		frmTSelect.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTSelect.getContentPane().setLayout(null);
		frmTSelect.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		JLabel lblTestSelector = new JLabel("Test Selector");
		lblTestSelector.setBounds(167, 19, 98, 16);
		frmTSelect.getContentPane().add(lblTestSelector);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(148, 230, 117, 29);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
                    frmTSelect.setVisible(false);
                    TestMenu window = new TestMenu();
                    window.frmTMenu.setVisible(true);
                    
				}
				catch (Exception j) {
            			j.printStackTrace();
				}
			}
		});
		frmTSelect.getContentPane().add(btnCancel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(89, 57, 280, 134);
		frmTSelect.getContentPane().add(scrollPane);

		//----------------------------------------------------------------------------	
		
		/**
		 * purpose: To populate a list with the names of every test in a specific unit
		 * parameters passed: NA
		 * return type: NA
		 */
		
		String testName = "";
		DefaultListModel<String> model = new DefaultListModel<String>();
		JList<String> list = new JList<>(model);
		for (int i = 0; i < data.tests.size(); i++) {
			if (data.tests.get(i).getUnit() == unitIndex) {
				testName = (String) (data.tests.get(i)).getName();
				unitTests.add(data.tests.get(i));
				model.addElement(testName);
			}
		}
		scrollPane.setViewportView(list);
		
		//----------------------------------------------------------------------------
		
		/**
		 * purpose: To write a test and its various aspects in a user friendly way to a text file, to be used by the user as the final output. 
		 * parameters passed: NA
		 * return type: NA
		 */
		
		JButton btnConfirm = new JButton("Print selected test");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list.getSelectedIndex()!=-1) {
					Test selected = null;
					for (int i = 0; i < data.tests.size(); i++) {
						if (list.getSelectedValue().equals(data.tests.get(i).getName())) {
							selected = data.tests.get(i);
						}
					}
					PrintWriter writer;
					try {
						writer = new PrintWriter(selected.getName(), "UTF-8");
						if (selected.isType() == true) {
							writer.print("Summative - ");
						}
						else {
							writer.print("Formative - ");
						}
						if (selected.getUnit() == 12) {
							writer.print("Cumulative test");
						}
						else {
							writer.print(units.get(selected.getUnit()).getName());
						}
						writer.println(" ");
						writer.println(selected.getYear());
						writer.println(" ");
						writer.println("Grade "+selected.getGradeLevel());
						writer.println(" ");
						writer.println("Total marks: " + selected.getMarks());
						writer.println(" ");
						if (selected.getStandards().size() == 0) {
							writer.println("No standards assessed");
							writer.println(" ");
						}
						else {
							writer.println("Standards assessed:");
							writer.println(" ");
							for (int i = 0; i < selected.getStandards().size(); i ++) {
								writer.println(selected.getStandards().get(i));
								writer.println(" ");
							}
						}
						if (selected.getCalc() == true) {
							writer.println("A calculator is permitted on this test");
							writer.println(" ");
						}
						else {
							writer.println("A calculator is not permitted on this test");
							writer.println(" ");
						}
						for (int j = 1; j < selected.getQuestions().size() + 1; j++) {
							if (j == selected.getQuestions().size() && selected.hasChallengeQ() == true) {
								writer.println("Challenge question:");
								
							}
							writer.println(j+". "+selected.getQuestions().get(j-1).getQuestion()+"["+selected.getQuestions().get(j-1).getMarks()+"]");
							for (int i = 0; i<selected.getQuestions().get(j-1).getMarks();i++) {
								writer.println();
								writer.println();
							}
						}
						writer.close();
						data.usedTests.add(selected);
						for (int i = 0; i < 5; i++) {
							for (int j = 0; j < selected.getStandards().size(); j++) {
								if (((String) selected.getStandards().get(j)).equals(data.getPossibleStandards().get(i))){
									data.standardCount.set(i, data.standardCount.get(i)+1);
								}
							}
						}
						FileOutputStream fos2 = new FileOutputStream("tests.txt");
						ObjectOutputStream oos2;
						try {
							oos2 = new ObjectOutputStream(fos2);
							oos2.writeObject(data);
							oos2.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
	                    frmTSelect.setVisible(false);
	                    TestMenu window = new TestMenu();
	                    window.frmTMenu.setVisible(true);
	                    
					}
					catch (Exception j) {
	            			j.printStackTrace();
					}
				}
			}
		});
		btnConfirm.setBounds(245, 201, 153, 29);
		frmTSelect.getContentPane().add(btnConfirm);
		
		//----------------------------------------------------------------------------
		
		/**
		 * purpose: To locate the selected test based upon its name, and pass it to the testInitializer class
		 * parameters passed: NA
		 * return type: NA
		 */

		JButton btnEditSelectedTest = new JButton("Edit selected test");
		btnEditSelectedTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list.getSelectedIndex()!=-1) {
					try {
	                    frmTSelect.setVisible(false);
	                    int testIndex = 0;
	                    for (int i = 0; i<data.tests.size(); i++) {
		                    	if (data.tests.get(i).getName().equals(list.getSelectedValue())) {
		                    		testIndex = i;
		                    		break;
		                    	}
	                    }
	                    Test_create_edit window = new Test_create_edit(Indicator, testIndex);
	                    window.frmTInitialize.setVisible(true);
	                    
					}
					catch (Exception j) {
	            			j.printStackTrace();
					}
				}
			}
		});
		btnEditSelectedTest.setBounds(64, 201, 153, 29);
		frmTSelect.getContentPane().add(btnEditSelectedTest);
	}
}
