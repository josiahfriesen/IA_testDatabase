/**
* date: Jan 1 2017
* purpose: This screen is the interface to create questions
*/
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;

public class QuestionInitializer {

	public JFrame frmQInitialize;

	/**
	 * Launch the application.
	 */
	public static void main(ArrayList units) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuestionInitializer window = new QuestionInitializer(units);
					window.frmQInitialize.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public QuestionInitializer(ArrayList units) {
		initialize(units);
	}

	/**
	 * Initialize the contents of the frmQInitialize.
	 */
	private void initialize(ArrayList units) {
		frmQInitialize = new JFrame();
		frmQInitialize.setBounds(100, 100, 450, 300);
		frmQInitialize.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmQInitialize.getContentPane().setLayout(null);
		frmQInitialize.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		JLabel lblEnterAQuestion = new JLabel("Enter a question:");
		lblEnterAQuestion.setBounds(6, 6, 118, 16);
		frmQInitialize.getContentPane().add(lblEnterAQuestion);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 34, 389, 67);
		frmQInitialize.getContentPane().add(scrollPane);
		
		JTextArea questionText = new JTextArea();
		scrollPane.setViewportView(questionText);
		questionText.setLineWrap(true);
		
		JButton symbol1 = new JButton("π");
		symbol1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				questionText.insert("π", questionText.getCaretPosition());
			}
		});
		symbol1.setBounds(29, 113, 49, 29);
		frmQInitialize.getContentPane().add(symbol1);
		
		JButton symbol2 = new JButton("α");
		symbol2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				questionText.insert("α", questionText.getCaretPosition());
			}
		});
		symbol2.setBounds(90, 113, 49, 29);
		frmQInitialize.getContentPane().add(symbol2);
		
		JButton symbol3 = new JButton("β");
		symbol3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				questionText.insert("β", questionText.getCaretPosition());
			}
		});
		symbol3.setBounds(151, 113, 49, 29);
		frmQInitialize.getContentPane().add(symbol3);
		
		JButton symbol4 = new JButton("√");
		symbol4.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				questionText.insert("√", questionText.getCaretPosition());
			}
		});
		symbol4.setBounds(212, 113, 49, 29);
		frmQInitialize.getContentPane().add(symbol4);
		
		JComboBox yearSelected = new JComboBox();
		yearSelected.setModel(new DefaultComboBoxModel(new String[] {"2025", "2024", "2023", "2022", "2021", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000"}));
		yearSelected.setSelectedIndex(7);
		yearSelected.setBounds(16, 171, 151, 27);
		frmQInitialize.getContentPane().add(yearSelected);
		
		JLabel lblYearCreated = new JLabel("Year created:");
		lblYearCreated.setBounds(16, 154, 81, 16);
		frmQInitialize.getContentPane().add(lblYearCreated);
		
		JLabel lblTotalMarks = new JLabel("Total marks:");
		lblTotalMarks.setBounds(16, 210, 81, 16);
		frmQInitialize.getContentPane().add(lblTotalMarks);
		
		JComboBox marksSelected = new JComboBox();
		marksSelected.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"}));
		marksSelected.setSelectedIndex(0);
		marksSelected.setBounds(16, 226, 151, 27);
		frmQInitialize.getContentPane().add(marksSelected);
		
		JComboBox unitSelected = new JComboBox();
		unitSelected.setModel(new DefaultComboBoxModel(new String[] {"Unit 1: Sequences and series", "Unit 2: Functions", "Unit 3: Polynomials", "Unit 4: Differentiation", "Unit 5: Exponents and logarithms", "Unit 6: Statistics and probability", "Unit 7: Integration", "Unit 8: Triginometry", "Unit 9: Integration and triginometry", "Unit 10: Probability distributions", "Unit 11: Vectors", "Unit 12: Complex numbers"}));
		unitSelected.setSelectedIndex(0);
		unitSelected.setBounds(209, 171, 235, 27);
		frmQInitialize.getContentPane().add(unitSelected);
		
		JLabel lblUnit = new JLabel("Unit:");
		lblUnit.setBounds(207, 154, 61, 16);
		frmQInitialize.getContentPane().add(lblUnit);
		
		JButton btnCancel = new JButton("Question menu");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
                    frmQInitialize.setVisible(false);
                    QuestionMenu window = new QuestionMenu(units);
                    window.frmQMenu.setVisible(true);
                    
				}
				catch (Exception j) {
            			j.printStackTrace();
				}
			}
		});
		btnCancel.setBounds(308, 225, 136, 29);
		frmQInitialize.getContentPane().add(btnCancel);
		
		//----------------------------------------------------------------------------
		
		/**
		 * purpose: Collect all the values from the fields on the screen and construct a question from them. It then calls the save question method itself. 
		 * parameters passed: NA
		 * return type: NA
		 */
		
		JButton btnSave = new JButton("Save question");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int marks = Integer.valueOf((String) marksSelected.getSelectedItem());
				//https://stackoverflow.com/questions/3661413/how-to-cast-an-object-to-an-int-in-java
				String unit = (String) unitSelected.getSelectedItem();
				int year = Integer.valueOf((String) yearSelected.getSelectedItem());
				
				Question entered = new Question(unit, year, marks, questionText.getText(), false );
				
				try {
					saveQuestion(units, unitSelected.getSelectedIndex(), entered);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				yearSelected.setSelectedIndex(7);
				marksSelected.setSelectedIndex(0);
				unitSelected.setSelectedIndex(0);
				questionText.setText("");
				
			}
		});
		btnSave.setBounds(190, 225, 117, 29);
		frmQInitialize.getContentPane().add(btnSave);
	}
	//----------------------------------------------------------------------------
	
	/**
	 * purpose: Write a question object to a text file
	 * parameters passed: ArrayList units, int unitIndex, Question q. The program utilizes these parameters to find the place the question should be written to,
	 * and to obtain the question object itself. 
	 * return type: void
	 */
	
	public static void saveQuestion(ArrayList<Unit> units, int unitIndex, Question q) throws FileNotFoundException, IOException {
		for (int i = 0; i < units.size(); i++) {
			if (i == unitIndex) {		
				units.get(i).addQuestion(q);
				try {
					ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("questions.txt"));
					out.writeObject(units);
					out.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				} 
				
			}
			
		}
	}
}
