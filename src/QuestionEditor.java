/**
* date: Jan 1 2017
* purpose: This screen is the interface for editing questions
*/
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

public class QuestionEditor {

	public JFrame frmQEdit;

	/**
	 * purpose: Launch the application.
	 * parameters passed: int unitIndex. This argument dictates which unit object the questions to be displayed are accessed from
	 * return type: NA
	 */
	
	public static void main(int unitIndex) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuestionEditor window = new QuestionEditor(unitIndex);
					window.frmQEdit.setVisible(true);
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
	
	public QuestionEditor(int unitIndex) throws ClassNotFoundException, IOException {
		initialize(unitIndex);
	}

	/**
	 * Initialize the contents of the frmQEdit.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	private void initialize(int unitIndex) throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("questions.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		ArrayList<Unit> units = (ArrayList<Unit>) ois.readObject();
		ois.close();
		
		frmQEdit = new JFrame();
		frmQEdit.setBounds(100, 100, 733, 494);
		frmQEdit.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmQEdit.getContentPane().setLayout(null);
		frmQEdit.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(19, 18, 267, 201);
		frmQEdit.getContentPane().add(scrollPane);
		
		//----------------------------------------------------------------------------
		
		/**
		 * purpose: Display the questions belonging to the appropriate unit 
		 * parameters passed: NA
		 * return type: NA
		 */
		
		DefaultListModel<String> model = new DefaultListModel<String>();
		JList<String>questionList = new JList<>(model);
		if (units.get(unitIndex).getQuestions().size() != 0) {
			for (int i = 0; i < units.get(unitIndex).getQuestions().size(); i++) {
				String question = (String) ((Question) units.get(unitIndex).getQuestions().get(i)).getQuestion();
				model.addElement(question);
			}
		}
		scrollPane.setViewportView(questionList);
		
		//----------------------------------------------------------------------------
		
		JButton button = new JButton("Done");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					frmQEdit.setVisible(false);
					questionList.removeAll();
                    	QuestionMenu window = new QuestionMenu(units);
                    	window.frmQMenu.setVisible(true);
                    
				}
				catch (Exception j) {
            			j.printStackTrace();
				}
			}
		});
		button.setBounds(252, 358, 117, 29);
		frmQEdit.getContentPane().add(button);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(347, 96, 363, 103);
		frmQEdit.getContentPane().add(scrollPane_1);
		
		JTextArea editArea = new JTextArea();
		editArea.setLineWrap(true);
		scrollPane_1.setViewportView(editArea);
		editArea.setEditable(false);
		
		JComboBox yearModified = new JComboBox();
		yearModified.setEnabled(false);
		yearModified.setModel(new DefaultComboBoxModel(new String[] {"2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030"}));
		yearModified.setSelectedIndex(0);
		yearModified.setBounds(382, 309, 151, 27);
		frmQEdit.getContentPane().add(yearModified);
		
		JComboBox marksSelected = new JComboBox();
		marksSelected.setEnabled(false);
		marksSelected.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"}));
		marksSelected.setSelectedIndex(0);
		marksSelected.setBounds(537, 309, 151, 27);
		frmQEdit.getContentPane().add(marksSelected);
		
		//----------------------------------------------------------------------------
		
		JButton btnEditSelected = new JButton("Edit selected");
		btnEditSelected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (questionList.getSelectedIndex() != -1) {
					editArea.setEditable(true);
					marksSelected.setEnabled(true);
					yearModified.setEnabled(true);
					marksSelected.setSelectedIndex((((Question)units.get(unitIndex).getQuestions().get(questionList.getSelectedIndex())).getMarks())-1);
					editArea.setText(questionList.getSelectedValue());
				}
			}
		});
		btnEditSelected.setBounds(98, 231, 117, 29);
		frmQEdit.getContentPane().add(btnEditSelected);
		
		//----------------------------------------------------------------------------
		
		/**
		 * purpose: Once the save button is pressed, the below method collects all the values from the various fields on the screen,
		 * constructing a question object with the values, and replacing it at the same index as the question that was selected to 
		 * be edited
		 * parameters passed: NA
		 * return type: NA
		 */
		
		JButton save = new JButton("Save question");
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (editArea.isEditable()) {
					int marks = Integer.valueOf((String) marksSelected.getSelectedItem());
					//https://stackoverflow.com/questions/3661413/how-to-cast-an-object-to-an-int-in-java
					int year = Integer.valueOf((String) yearModified.getSelectedItem());
					Question qToAdd = new Question(units.get(unitIndex).getName(), year, marks, editArea.getText(), false);
					units.get(unitIndex).getQuestions().set(questionList.getSelectedIndex(), qToAdd);
					
					try {
						FileOutputStream fos = new FileOutputStream("questions.txt");
						ObjectOutputStream oos = new ObjectOutputStream(fos);
						oos.writeObject(units);
						oos.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					editArea.setText("");
					editArea.setEditable(false);
					model.clear();
					yearModified.setSelectedIndex(0);
					yearModified.setEnabled(false);
					marksSelected.setSelectedIndex(0);
					marksSelected.setEnabled(false);
					
					for (int i = 0; i < units.get(unitIndex).getQuestions().size(); i++) {
						String question = (String) ((Question) units.get(unitIndex).getQuestions().get(i)).getQuestion();
						model.addElement(question);
					}
				}
			}
		});
		save.setBounds(499, 358, 117, 29);
		frmQEdit.getContentPane().add(save);
		
		//----------------------------------------------------------------------------
		
		JLabel label = new JLabel("Editing field:");
		label.setBounds(347, 68, 91, 16);
		frmQEdit.getContentPane().add(label);
		
		JButton symbol1 = new JButton("π");
		symbol1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				editArea.insert("π", editArea.getCaretPosition());
			}
		});
		symbol1.setBounds(362, 211, 49, 29);
		frmQEdit.getContentPane().add(symbol1);
		
		JButton symbol2 = new JButton("α");
		symbol2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				editArea.insert("α", editArea.getCaretPosition());
			}
		});
		symbol2.setBounds(423, 211, 49, 29);
		frmQEdit.getContentPane().add(symbol2);
		
		JButton symbol3 = new JButton("β");
		symbol3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				editArea.insert("β", editArea.getCaretPosition());
			}
		});
		symbol3.setBounds(484, 211, 49, 29);
		frmQEdit.getContentPane().add(symbol3);
		
		JButton symbol4 = new JButton("√");
		symbol4.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				editArea.insert("√", editArea.getCaretPosition());
			}
		});
		symbol4.setBounds(545, 211, 49, 29);
		frmQEdit.getContentPane().add(symbol4);
		
		JLabel lblUpdatedYear = new JLabel("Updated year:");
		lblUpdatedYear.setBounds(375, 293, 97, 16);
		frmQEdit.getContentPane().add(lblUpdatedYear);
		
		JLabel label_2 = new JLabel("Total marks:");
		label_2.setBounds(537, 293, 81, 16);
		frmQEdit.getContentPane().add(label_2);
		
		JLabel lblQuestionEditor = new JLabel("Question Editor");
		lblQuestionEditor.setBounds(320, 18, 118, 16);
		frmQEdit.getContentPane().add(lblQuestionEditor);
	}
}
