/**
* date: Jan 1 2017
* purpose: This screen displays buttons that allow the user to create new questions or to edit existing questions
*/
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class QuestionMenu {

	public JFrame frmQMenu;

	/**
	 * Launch the application.
	 */
	public static void main(ArrayList units) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuestionMenu window = new QuestionMenu(units);
					window.frmQMenu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public QuestionMenu(ArrayList units) {
		initialize(units);
	}

	/**
	 * Initialize the contents of the frmQMenu.
	 */
	private void initialize(ArrayList units) {
		frmQMenu = new JFrame();
		frmQMenu.setBounds(100, 100, 450, 300);
		frmQMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmQMenu.getContentPane().setLayout(null);
		frmQMenu.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		JButton btnCreateANew = new JButton("Create a new question");
		btnCreateANew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
                    frmQMenu.setVisible(false);
                    QuestionInitializer window = new QuestionInitializer(units);
                    window.frmQInitialize.setVisible(true);
                    
            		}
            		catch (Exception j) {
            			j.printStackTrace();
            		}	
			}
		});
		btnCreateANew.setBounds(110, 75, 183, 29);
		frmQMenu.getContentPane().add(btnCreateANew);
		
		JButton btnEditAnExisting = new JButton("Edit an existing question");
		btnEditAnExisting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
                    frmQMenu.setVisible(false);
                    UnitSelector window = new UnitSelector(2);
                    window.frmUnit.setVisible(true);
                    
            		}
            		catch (Exception j) {
            			j.printStackTrace();
            		}	
			}
		});
		btnEditAnExisting.setBounds(110, 116, 183, 29);
		frmQMenu.getContentPane().add(btnEditAnExisting);
		
		JButton btnStartScreen = new JButton("Back to start screen");
		btnStartScreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
                    frmQMenu.setVisible(false);
                    StartScreen window = new StartScreen();
                    window.frame.setVisible(true);
                    
				}
				catch (Exception j) {
            			j.printStackTrace();
            		}
			}
		});
		btnStartScreen.setBounds(136, 218, 157, 29);
		frmQMenu.getContentPane().add(btnStartScreen);
	}

}
