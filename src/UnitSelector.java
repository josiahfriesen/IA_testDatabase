/**
* date: Jan 1 2017
* purpose: This screen displays a list of units, to be selected for displaying lists that belong to that unit, 
* and for making a test that belongs to that unit. 
*/
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UnitSelector {

	public JFrame frmUnit;

	/**
	 * purpose: Launch the application.
	 * parameters passed: int argument. This argument is simply for transition. Both the test selector and question editor processes move through this screen,
	 * so the argument serves as a distinction between the various processes, which decides which screen is opened when "confirm" is pressed.
	 * return type: NA
	 */
	public static void main(int argument) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UnitSelector window = new UnitSelector(argument);
					window.frmUnit.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UnitSelector(int argument) {
		initialize(argument);
	}

	/**
	 * Initialize the contents of the frmTUnit.
	 */
	private void initialize(int argument) {
		frmUnit = new JFrame();
		frmUnit.setBounds(100, 100, 450, 300);
		frmUnit.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmUnit.getContentPane().setLayout(null);
		frmUnit.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		JLabel label = new JLabel("Unit:");
		label.setBounds(27, 41, 61, 16);
		frmUnit.getContentPane().add(label);
		
		JComboBox selectedUnit = new JComboBox();
		if (argument == 2) {
			selectedUnit.setModel(new DefaultComboBoxModel(new String[] {"Unit 1: Sequences and series", "Unit 2: Functions", "Unit 3: Polynomials", "Unit 4: Differentiation", "Unit 5: Exponents and logarithms", "Unit 6: Statistics and probability", "Unit 7: Integration", "Unit 8: Triginometry", "Unit 9: Integration and triginometry", "Unit 10: Probability distributions", "Unit 11: Vectors", "Unit 12: Complex numbers"}));
		}
		else {
			selectedUnit.setModel(new DefaultComboBoxModel(new String[] {"Unit 1: Sequences and series", "Unit 2: Functions", "Unit 3: Polynomials", "Unit 4: Differentiation", "Unit 5: Exponents and logarithms", "Unit 6: Statistics and probability", "Unit 7: Integration", "Unit 8: Triginometry", "Unit 9: Integration and triginometry", "Unit 10: Probability distributions", "Unit 11: Vectors", "Unit 12: Complex numbers", "OR cumulative test (content from multiple units)"}));
		}
		selectedUnit.setSelectedIndex(0);
		selectedUnit.setBounds(27, 57, 379, 27);
		frmUnit.getContentPane().add(selectedUnit);
		
		//----------------------------------------------------------------------------
		
		/**
		 * purpose: Open various other screens depending on what the value of int argument was
		 * parameters NA
		 * return type: NA
		 */
		
		JButton button = new JButton("Confirm");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
                    frmUnit.setVisible(false);
                    if (argument == 0) {
                    		Test_create_edit window = new Test_create_edit(argument, selectedUnit.getSelectedIndex());
                    		window.frmTInitialize.setVisible(true);
                    }
                    else if (argument == 1) {
                			TestSelector window = new TestSelector(argument, selectedUnit.getSelectedIndex());
                			window.frmTSelect.setVisible(true);
                    }
                    
                    else if (argument == 2){
                    		QuestionEditor window = new QuestionEditor(selectedUnit.getSelectedIndex());
                    		window.frmQEdit.setVisible(true);
                    }
                    
				}
				catch (Exception j) {
            			j.printStackTrace();
            		}
			}
		});
		button.setBounds(216, 243, 117, 29);
		frmUnit.getContentPane().add(button);
		
		//----------------------------------------------------------------------------
		
		JButton button_1 = new JButton("Cancel");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
                    frmUnit.setVisible(false);
                    StartScreen window = new StartScreen();
                    window.frame.setVisible(true);
                    
				}
				catch (Exception j) {
            			j.printStackTrace();
            		}
			}
		});
		button_1.setBounds(327, 243, 117, 29);
		frmUnit.getContentPane().add(button_1);
	}
}
