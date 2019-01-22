package ch.nyp.view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import ch.nyp.BeerAdmin;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class View {

	private JFrame frame;
	private BeerAdmin beerAdmin;
	private JTextField extraInformationField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View window = new View();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public View() {
		initializeBeer();
		initializeFrame();
	}
	
	/**
	 * Initialize the beer data
	 */
	private void initializeBeer() {
		beerAdmin.loadBeerStyles();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initializeFrame() {
		frame = new JFrame();
		frame.setBounds(100, 100, 750, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(30, 270, 673, 170);
		frame.getContentPane().add(textArea);
		
		JButton btnBeerStyles = new JButton("Print Beer Styles");
		btnBeerStyles.setFont(new Font("Segoe UI Black", Font.BOLD, 13));
		btnBeerStyles.setBounds(30, 34, 172, 100);
		frame.getContentPane().add(btnBeerStyles);
		
		JButton btnStyleByStyle = new JButton("Print Beer Styles by Style");
		btnStyleByStyle.setFont(new Font("Segoe UI Black", Font.BOLD, 13));
		btnStyleByStyle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnStyleByStyle.setBounds(30, 133, 246, 100);
		frame.getContentPane().add(btnStyleByStyle);
		
		JButton btnBeerList = new JButton("Print Beer List by Styles");
		btnBeerList.setFont(new Font("Segoe UI Black", Font.BOLD, 13));
		btnBeerList.setBounds(202, 34, 246, 100);
		frame.getContentPane().add(btnBeerList);
		
		extraInformationField = new JTextField();
		extraInformationField.setBounds(226, 164, 185, 38);
		frame.getContentPane().add(extraInformationField);
		extraInformationField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Search");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(411, 164, 97, 38);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel extraInformationLabel = new JLabel("Enter");
		extraInformationLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 13));
		extraInformationLabel.setBounds(226, 113, 185, 38);
		frame.getContentPane().add(extraInformationLabel);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(12, 13, 97, 25);
		frame.getContentPane().add(btnBack);
	}
}
