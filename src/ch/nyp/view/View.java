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
import javax.swing.JScrollPane;

public class View {

	private JFrame frame;
	private BeerAdmin beerAdmin;
	private JTextField extraInformationField;
	private static String mode;
	
	/**
	 * Buttons
	 */
	private JButton btnBeerStyles;
	private JButton btnStyleByStyle;
	private JButton btnBeerList;
	private JButton btnExtraInformation;
	private JButton btnPrintBeer;
	private JButton btnBack;
	private JButton btnPrintBeerList;

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
		beerAdmin = new BeerAdmin();
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
		
		JLabel extraInformationLabel = new JLabel("Enter");
		extraInformationLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 13));
		extraInformationLabel.setBounds(226, 113, 185, 38);
		extraInformationLabel.setVisible(false);
		frame.getContentPane().add(extraInformationLabel);
		
		JTextArea textArea = new JTextArea();
		//textArea.setBounds(30, 270, 673, 170);
		
		JScrollPane scrollArea = new JScrollPane(textArea);
        scrollArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollArea.setBounds(30, 270, 673, 170);
        frame.getContentPane().add(scrollArea);
		
		btnBeerStyles = new JButton("Print Beer Styles");
		btnBeerStyles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(beerAdmin.printBeerStyles());
			}
		});
		btnBeerStyles.setFont(new Font("Segoe UI Black", Font.BOLD, 13));
		btnBeerStyles.setBounds(162, 34, 172, 80);
		frame.getContentPane().add(btnBeerStyles);
		
		btnStyleByStyle = new JButton("Search for Beer Style");
		btnStyleByStyle.setFont(new Font("Segoe UI Black", Font.BOLD, 13));
		btnStyleByStyle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mode = "style";
				btnStyleByStyle.setVisible(false);
				btnBeerStyles.setVisible(false);
				btnBeerList.setVisible(false);
				btnPrintBeerList.setVisible(false);
				btnPrintBeer.setVisible(false);
				btnBack.setVisible(true);
				btnExtraInformation.setVisible(true);
				extraInformationField.setVisible(true);
				extraInformationLabel.setText("Enter a Search Criteria");
				extraInformationLabel.setVisible(true);
			}
		});
		btnStyleByStyle.setBounds(162, 113, 246, 80);
		frame.getContentPane().add(btnStyleByStyle);
		
		btnBeerList = new JButton("Get Beer List by StyleID");
		btnBeerList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mode = "list";
				btnStyleByStyle.setVisible(false);
				btnBeerStyles.setVisible(false);
				btnBeerList.setVisible(false);
				btnPrintBeerList.setVisible(false);
				btnPrintBeer.setVisible(false);
				btnBack.setVisible(true);
				btnExtraInformation.setVisible(true);
				extraInformationField.setVisible(true);
				extraInformationLabel.setText("Enter a StyleID");
				extraInformationLabel.setVisible(true);
			}
		});
		btnBeerList.setFont(new Font("Segoe UI Black", Font.BOLD, 13));
		btnBeerList.setBounds(334, 34, 246, 80);
		frame.getContentPane().add(btnBeerList);
		
		extraInformationField = new JTextField();
		extraInformationField.setBounds(226, 164, 185, 38);
		frame.getContentPane().add(extraInformationField);
		extraInformationField.setColumns(100);
		extraInformationField.setVisible(false);
		
		btnExtraInformation = new JButton("Search");
		btnExtraInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mode.equals("style")) {
					textArea.setText(beerAdmin.printBeerStyles(extraInformationField.getText()));
				} else if (mode.equals("list")) {
					beerAdmin.getBeerListForStyle(Integer.parseInt(extraInformationField.getText()));
					//textArea.setText(beerAdmin.printBeerList());
					textArea.setText("BeerStyle loaded");
				} else if (mode.equals("beer")) {
					textArea.setText(beerAdmin.printBeer(extraInformationField.getText()));
				}
			}
		});
		btnExtraInformation.setBounds(411, 164, 97, 38);
		btnExtraInformation.setVisible(false);
		frame.getContentPane().add(btnExtraInformation);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStyleByStyle.setVisible(true);
				btnBeerStyles.setVisible(true);
				btnBeerList.setVisible(true);
				btnPrintBeerList.setVisible(true);
				btnPrintBeer.setVisible(true);
				btnBack.setVisible(false);
				btnExtraInformation.setVisible(false);
				extraInformationField.setVisible(false);
				extraInformationField.setText("");
				extraInformationLabel.setVisible(false);
			}
		});
		btnBack.setBounds(12, 13, 97, 25);
		btnBack.setVisible(false);
		frame.getContentPane().add(btnBack);
		
		btnPrintBeerList = new JButton("Print Beer List");
		btnPrintBeerList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(beerAdmin.printBeerList());
			}
		});
		btnPrintBeerList.setFont(new Font("Segoe UI Black", Font.BOLD, 13));
		btnPrintBeerList.setBounds(408, 113, 172, 80);
		frame.getContentPane().add(btnPrintBeerList);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(635, 14, 89, 23);
		frame.getContentPane().add(btnExit);
		
		btnPrintBeer = new JButton("Print Beer");
		btnPrintBeer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mode = "beer";
				btnStyleByStyle.setVisible(false);
				btnBeerStyles.setVisible(false);
				btnBeerList.setVisible(false);
				btnPrintBeerList.setVisible(false);
				btnPrintBeer.setVisible(false);
				btnBack.setVisible(true);
				btnExtraInformation.setVisible(true);
				extraInformationField.setVisible(true);
				extraInformationLabel.setText("Enter a BeerID");
				extraInformationLabel.setVisible(true);
			}
		});
		btnPrintBeer.setFont(new Font("Segoe UI Black", Font.BOLD, 13));
		btnPrintBeer.setBounds(162, 192, 418, 58);
		frame.getContentPane().add(btnPrintBeer);
	}
}
