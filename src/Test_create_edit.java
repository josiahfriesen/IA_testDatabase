/**
* date: Jan 1 2017
* purpose: This screen is the interface for editing and creating tests
*/
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;

public class Test_create_edit {
	
	ArrayList<Question> testQuestions = new ArrayList<Question>();
	ArrayList<String> standards = new ArrayList<String>();
	public JFrame frmTInitialize;
	boolean hasChallenge = false;
	
	/**
	 * purpose: launch the application and pass the appropriate variables to the next methods (which are also passed to this method from other classes)
	 * parameters passed: int editOrCreate, int unit_testIndex. editOrCreate is a value of 0 or 1 that dictates if this screen is to be used to create a test
	 * or to edit an existing test. unit_testIndex is a value that references the index of an arraylist of tests or units, depending on the value of editOrCreate
	 * return type: NA
	 */
	public static void main(int editOrCreate, int unit_testIndex) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test_create_edit window = new Test_create_edit(editOrCreate, unit_testIndex);
					window.frmTInitialize.setVisible(true);
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
	public Test_create_edit(int editOrCreate, int unit_testIndex) throws ClassNotFoundException, IOException {
		
		initialize(editOrCreate, unit_testIndex);
	}

	/**
	 * Initialize the contents of the frmTInitialize.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	private void initialize(int editOrCreate, int unit_testIndex) throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("questions.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		ArrayList<Unit> units = (ArrayList<Unit>) ois.readObject();
		ois.close();
		
		FileInputStream fis2 = new FileInputStream("tests.txt");
		ObjectInputStream ois2 = new ObjectInputStream(fis2);
		TestQuestionDB data = (TestQuestionDB) ois2.readObject();
		ois2.close();
		
		int constantUnitNum;
		int testIndex;
		if (editOrCreate == 1) {
			testIndex = unit_testIndex;
			constantUnitNum = data.tests.get(testIndex).getUnit();
		}
		else {
			constantUnitNum = unit_testIndex;
			testIndex = -1;
		}
		
		frmTInitialize = new JFrame();
		frmTInitialize.setBounds(100, 100, 775, 520);
		frmTInitialize.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTInitialize.getContentPane().setLayout(null);
		frmTInitialize.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		JLabel lblTesteInitializer = new JLabel("Test Initializer");
		lblTesteInitializer.setBounds(171, 16, 107, 16);
		frmTInitialize.getContentPane().add(lblTesteInitializer);
		
		//----------------------------------------------------------------------------
		
		/**
		 * purpose: populate a Jlist of questions with questions taken from an existing test if the screen is in edit mode (if editOrCreate = 1)
		 * parameters passed: NA
		 * return type: NA
		 */
		
		DefaultListModel<String> model2 = new DefaultListModel<String>();
		if (editOrCreate == 1) {
			for (int i = 0; i < data.tests.get(testIndex).getQuestions().size(); i++) {
				String question = (String) ((Question) data.tests.get(testIndex).getQuestions().get(i)).getQuestion();
				model2.addElement(question);
			}
		}
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(16, 255, 450, 137);
		frmTInitialize.getContentPane().add(scrollPane_1);
		JList<String>selectedBank = new JList<>(model2);
		scrollPane_1.setViewportView(selectedBank);
		
	//----------------------------------------------------------------------------
		
		/**
		 * purpose: To allow a question in the selected list to be identified as a challenge question.
		 * parameters passed: NA
		 * return type: NA
		 * specific notes: The question that is identified is then moved to the end of the selected list of questions, and a boolean value is set to true. 
		 * Only one challenge question can be identified at a time. This process can be undone as seen in the else if statement below.
		 */
		
		JButton btnConfirmAsChallenge = new JButton("Set selected as challenge question");
		btnConfirmAsChallenge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedBank.getSelectedIndex() !=-1 && btnConfirmAsChallenge.getText().equals("Set selected as challenge question")) {
					model2.addElement(selectedBank.getSelectedValue());
					model2.remove(selectedBank.getSelectedIndex());
					hasChallenge = true;
					btnConfirmAsChallenge.setText("undo challenge question selection");
				}
				else if (btnConfirmAsChallenge.getText().equals("undo challenge question selection")) {
					hasChallenge = false;
					btnConfirmAsChallenge.setText("Set selected as challenge question");
				}
			}
		});
		btnConfirmAsChallenge.setBounds(172, 391, 248, 29);
		frmTInitialize.getContentPane().add(btnConfirmAsChallenge);
		
		
		JComboBox gradeSelected = new JComboBox();
		gradeSelected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gradeSelected.getSelectedIndex() == 0 || gradeSelected.getSelectedIndex() == 1) {
					btnConfirmAsChallenge.setEnabled(true);
				}
				else if (gradeSelected.getSelectedIndex() == 2 || gradeSelected.getSelectedIndex() == 3) {
					btnConfirmAsChallenge.setEnabled(false);
				}
				if (btnConfirmAsChallenge.isEnabled()) {
					if (hasChallenge == false) {
						btnConfirmAsChallenge.setText("Set selected as challenge question");
					}
					else {
						btnConfirmAsChallenge.setText("undo challenge question selection");
					}
				}
			}
		});
		gradeSelected.setModel(new DefaultComboBoxModel(new String[] {"9", "10", "11", "12"}));
		gradeSelected.setBounds(189, 465, 73, 27);
		frmTInitialize.getContentPane().add(gradeSelected);
		
		JComboBox formSum = new JComboBox();
		formSum.setModel(new DefaultComboBoxModel(new String[] {"Formative", "Summative"}));
		formSum.setBounds(310, 465, 132, 27);
		frmTInitialize.getContentPane().add(formSum);
		
		JComboBox calcAllowed = new JComboBox();
		calcAllowed.setModel(new DefaultComboBoxModel(new String[] {"yes", "no"}));
		calcAllowed.setBounds(46, 465, 73, 27);
		frmTInitialize.getContentPane().add(calcAllowed);
		
		JCheckBox standard1 = new JCheckBox("#1: Knowledge and Understanding");
		standard1.setBounds(478, 278, 248, 23);
		if (editOrCreate == 1 && standards.contains("#1: Knowledge and Understanding")) {
			standard1.setSelected(true);
		}
		frmTInitialize.getContentPane().add(standard1);
		
		JCheckBox standard2 = new JCheckBox("#2: Problem Solving");
		standard2.setBounds(478, 302, 248, 23);
		if (editOrCreate == 1 && standards.contains("#2: Problem Solving")) {
			standard1.setSelected(true);
		}
		frmTInitialize.getContentPane().add(standard2);
		
		JCheckBox standard3 = new JCheckBox("#3: Communication and Interpretation");
		standard3.setBounds(478, 324, 278, 23);
		if (editOrCreate == 1 && standards.contains("#3: Communication and Interpretation")) {
			standard1.setSelected(true);
		}
		frmTInitialize.getContentPane().add(standard3);
		
		JCheckBox standard4 = new JCheckBox("#4: Technology Use");
		standard4.setBounds(478, 347, 248, 23);
		if (editOrCreate == 1 && standards.contains("#4: Technology Use")) {
			standard1.setSelected(true);
		}
		frmTInitialize.getContentPane().add(standard4);
		
		JCheckBox standard5 = new JCheckBox("#5: Reasoning");
		standard5.setBounds(478, 369, 248, 23);
		if (editOrCreate == 1 && standards.contains("#5: Reasoning")) {
			standard1.setSelected(true);
		}
		frmTInitialize.getContentPane().add(standard5);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(16, 66, 450, 135);
		frmTInitialize.getContentPane().add(scrollPane);
		
		//----------------------------------------------------------------------------
		
		/**
		 * purpose: populate a Jlist with questions from a single unit if editOrCreate = 1. 
		 * parameters passed: NA
		 * return type: NA
		 * specific notes: the first if statement, involving constantUnitNum is to obtain the unit index (because if the window is in edit mode,
		 * unit_testIndex will equal the test index, not the unit index, and both values are needed for the algorithm).
		 * The next if statement checks if the "unit" selected is the cumulative option, and if the screen is in create mode, and thus populates the
		 * question list with questions from all units instead. 
		 */
		
		DefaultListModel<String> model = new DefaultListModel<String>();
		JList<String>questionBank = new JList<>(model);
		
		if (constantUnitNum == 12 && editOrCreate == 0) {
			for (int i = 0; i<units.size(); i++) {
				for (int j = 0; j<units.get(i).getQuestions().size(); j++) {
					String question = (String) ((Question) units.get(i).getQuestions().get(j)).getQuestion();
					model.addElement(question);
				}
			}
		}
		
		else if (editOrCreate == 1 && constantUnitNum == 12) {
			for (int h = 0; h < units.size(); h++) {
				for (int i = 0; i < units.get(h).getQuestions().size(); i++) {
					int compare_count = 0;
					for (int j = 0; j < model2.size(); j++) {
						if (!((Question) (units.get(h).getQuestions().get(i))).getQuestion().equals(model2.get(j))) {
							compare_count++;
						}
						
					}
					if (compare_count == model2.size()) {
						String question = (String) ((Question) units.get(h).getQuestions().get(i)).getQuestion();
						model.addElement(question);
					}
				}
			}
		}
		
		else {
			for (int i = 0; i < units.get(constantUnitNum).getQuestions().size(); i++) {
				int compare_count = 0;
				for (int j = 0; j < model2.size(); j++) {
					if (!((Question) (units.get(constantUnitNum).getQuestions().get(i))).getQuestion().equals(model2.get(j))) {
						compare_count++;
					}
					
				}
				if (compare_count == model2.size()) {
					String question = (String) ((Question) units.get(constantUnitNum).getQuestions().get(i)).getQuestion();
					model.addElement(question);
				}
			}
			
		}
		scrollPane.setViewportView(questionBank);
		
		
		//----------------------------------------------------------------------------
		
		/**
		 * purpose: To fill in the appropriate values in all the fields on the screen if the screen is in edit mode. 
		 * This is so the user knows what the values already are, so they can alter them accordingly. 
		 * parameters passed: NA
		 * return type: NA
		 */
		
		ArrayList<JCheckBox> standardBoxes = new ArrayList<JCheckBox>();
		standardBoxes.add(standard1);
		standardBoxes.add(standard2);
		standardBoxes.add(standard3);
		standardBoxes.add(standard4);
		standardBoxes.add(standard5);
		if (editOrCreate == 1) {
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < data.tests.get(testIndex).getStandards().size(); j++) {
					if (((String) data.tests.get(testIndex).getStandards().get(j)).equals(standardBoxes.get(i).getText())){
						standardBoxes.get(i).setSelected(true);
					}
				}
			}
			gradeSelected.setSelectedIndex((9-data.tests.get(testIndex).getGradeLevel())*-1);
			if (gradeSelected.getSelectedIndex() == 0 || gradeSelected.getSelectedIndex() == 1) {
				btnConfirmAsChallenge.setEnabled(true);
			}
			if (data.tests.get(testIndex).getCalc() == true) {
				calcAllowed.setSelectedIndex(0);
			}
			else{
				calcAllowed.setSelectedIndex(1);
			}
			if (data.tests.get(testIndex).isType() == true) {
				formSum.setSelectedIndex(1);
			}
			else{
				formSum.setSelectedIndex(0);
			}
			if (data.tests.get(testIndex).hasChallengeQ() == true) {
				btnConfirmAsChallenge.setText("undo challenge question selection");
			}
			
		}
		
		//----------------------------------------------------------------------------
		
		JLabel lblStandardsAssessed = new JLabel("Standards assessed");
		lblStandardsAssessed.setBounds(478, 252, 136, 16);
		frmTInitialize.getContentPane().add(lblStandardsAssessed);
		
		JLabel lblGradeLevel = new JLabel("Grade level");
		lblGradeLevel.setBounds(189, 447, 83, 16);
		frmTInitialize.getContentPane().add(lblGradeLevel);
	
		JLabel lblFormativeOrSummative = new JLabel("Formative or summative");
		lblFormativeOrSummative.setBounds(310, 447, 156, 16);
		frmTInitialize.getContentPane().add(lblFormativeOrSummative);
		
		JLabel lblCalculatorAllowed = new JLabel("Calculator allowed?");
		lblCalculatorAllowed.setBounds(25, 447, 135, 16);
		frmTInitialize.getContentPane().add(lblCalculatorAllowed);
		
		JButton btnCancel = new JButton("Discard changes and return to menu");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
                    frmTInitialize.setVisible(false);
                    TestMenu window = new TestMenu();
                    window.frmTMenu.setVisible(true);
                    
				}
				catch (Exception j) {
            			j.printStackTrace();
				}
			}
		});
		btnCancel.setBounds(501, 463, 273, 29);
		frmTInitialize.getContentPane().add(btnCancel);
		
		//----------------------------------------------------------------------------
		
		/**
		 * purpose: save a test (different processes if its a new test entirely or simply edited).The method gathers variable values from the fields on the screen
		 * makes a new test object, either adding it to the list of tests or replacing the test it was based off of if in edit mode
		 * parameters passed: NA
		 * return type: NA
		 */
		
		JButton btnSaveTest = new JButton("Save test");
		if ((model2.size()) == 0) {
			btnSaveTest.setEnabled(false);
		}
		btnSaveTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int unit = constantUnitNum;
				int year = Calendar.getInstance().get(Calendar.YEAR);
				int testNum;
				if (editOrCreate == 0) {
					testNum = data.tests.size() + 1;
				}
				else {
					testNum = data.tests.get(testIndex).getTestNum();
				}
				int gradeLevel = Integer.parseInt((String) gradeSelected.getSelectedItem());
				boolean summative;
				if (formSum.getSelectedIndex() == 0) {
					summative = false;
				}
				else
					summative = true;
				boolean calc;
				if (calcAllowed.getSelectedIndex() == 0) {
					calc = true;
				}
				else 
					calc = false;
				String name = "";
				if (unit == 12) {
					if (editOrCreate == 1) {
						name = (String) "Cumulative test_number"+(data.tests.get(testIndex).getTestNum())+"_"+year;
					}
					else {
						name = (String) "Cumulative test_number"+(data.tests.size()+1)+"_"+year;
					}
					for (int i = 0; i < model2.size(); i++) {
						String currentQ = model2.getElementAt(i);
						for (int k = 0; k < units.size(); k ++) {
							for (int j = 0; j < units.get(k).getQuestions().size(); j++) {
								if (currentQ.equals((String) ((Question) units.get(k).getQuestions().get(j)).getQuestion())) {
									Question questionNew = (Question) units.get(k).getQuestions().get(j);
									testQuestions.add(questionNew);
								}
							}
						}
					}
				}
				
				else {
					name = (String) "Unit"+(constantUnitNum+1)+"_test"+testNum+"_"+year;
					for (int i = 0; i < model2.size(); i++) {
						String currentQ = model2.getElementAt(i);
						for (int j = 0; j < units.get(constantUnitNum).getQuestions().size(); j++) {
							if (currentQ.equals((String) ((Question) units.get(constantUnitNum).getQuestions().get(j)).getQuestion())) {
								Question questionNew = (Question) units.get(constantUnitNum).getQuestions().get(j);
								testQuestions.add(questionNew);
								j = units.get(constantUnitNum).getQuestions().size();
							}
						}
					}
				}
				int marks = 0;
				for (int k = 0; k < testQuestions.size(); k++) {
					marks += testQuestions.get(k).getMarks();
				}
				for (int i=0; i < 5; i++) {
					if (standardBoxes.get(i).isSelected()) {
						standards.add(standardBoxes.get(i).getText());
					}
				}
				Test initialized = new Test(unit, name, testNum, year, marks, gradeLevel, summative, calc, standards, testQuestions);
				if (hasChallenge == true) {
					initialized.setHasChallengeQ(true);
				}
				else {
					initialized.setHasChallengeQ(false);
				}
				if (editOrCreate == 0) {
					data.tests.add(initialized);
				}
				else if (editOrCreate == 1){
					data.tests.set(testIndex, initialized);
				}
				try {
					FileOutputStream fos2 = new FileOutputStream("tests.txt");
					ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
					oos2.writeObject(data);
					oos2.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
                    frmTInitialize.setVisible(false);
                    TestMenu window = new TestMenu();
                    window.frmTMenu.setVisible(true);
                    
				}
				catch (Exception j) {
            			j.printStackTrace();
				}
			}
		});
		
		btnSaveTest.setBounds(544, 429, 117, 29);
		frmTInitialize.getContentPane().add(btnSaveTest);
		
		//----------------------------------------------------------------------------
		
		JLabel lblQuestionBank = new JLabel("Question bank");
		lblQuestionBank.setBounds(6, 40, 100, 16);
		frmTInitialize.getContentPane().add(lblQuestionBank);
		
		JLabel lblSelectedQuestions = new JLabel("Test questions");
		lblSelectedQuestions.setBounds(6, 229, 124, 16);
		frmTInitialize.getContentPane().add(lblSelectedQuestions);
		
		JButton btnConfirmSelected = new JButton("Confirm selected");
		btnConfirmSelected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(questionBank.getSelectedIndex() != -1) {
					model2.addElement(questionBank.getSelectedValue());
					model.remove(questionBank.getSelectedIndex());
					btnSaveTest.setEnabled(true);
				}
			}
		});
		btnConfirmSelected.setBounds(16, 199, 136, 29);
		frmTInitialize.getContentPane().add(btnConfirmSelected);
		
		JButton btnRemoveSelected = new JButton("Remove selected");
		btnRemoveSelected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectedBank.getSelectedIndex() != -1) {
					model.addElement(selectedBank.getSelectedValue());
					model2.remove(selectedBank.getSelectedIndex());
					if (model2.getSize() == 0) {
						btnSaveTest.setEnabled(false);
					}
				}
			}
		});
		btnRemoveSelected.setBounds(16, 391, 135, 29);
		frmTInitialize.getContentPane().add(btnRemoveSelected);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(511, 37, 233, 164);
		scrollPane_2.setVisible(false);
		frmTInitialize.getContentPane().add(scrollPane_2);
		
		JTextArea txtrInstructions = new JTextArea();
		scrollPane_2.setViewportView(txtrInstructions);
		txtrInstructions.setLineWrap(true);
		txtrInstructions.setWrapStyleWord(true);
		txtrInstructions.setText("Welcome to the test creator and editor. To add a question to your test, select a question in the question bank and click 'Confirm selected'. To remove a question from your test, select a question in the 'Test questions' list, and click 'Remove selected'. To identify a question as a challenge question, select it and press 'Set selected as challenge question'. This question will be moved to the end of the list, and will appear as a challenge question in the test.");
		
		JButton btnDisplayInstructions = new JButton("Instructions");
		btnDisplayInstructions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnDisplayInstructions.getText().equals("Instructions")) {
					scrollPane_2.setVisible(true);
					btnDisplayInstructions.setText("Hide instructions");
					btnDisplayInstructions.setBounds(511, 199, 200, 29);
				}
				else {
					scrollPane_2.setVisible(false);
					btnDisplayInstructions.setText("Instructions");
					btnDisplayInstructions.setBounds(511, 199, 124, 29);
				}
			}
		});
		btnDisplayInstructions.setBounds(511, 199, 124, 29);
		frmTInitialize.getContentPane().add(btnDisplayInstructions);
	}
}
