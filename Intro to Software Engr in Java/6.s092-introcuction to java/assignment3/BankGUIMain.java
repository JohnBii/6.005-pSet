package guis;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


@SuppressWarnings("serial")
public class BankGUIMain extends JFrame {
	
	private final JLabel label1 = new JLabel(getWelcomeMessage());
	private final JLabel USERNAME = new JLabel("Username: ");
	private final JLabel PASSWORD = new JLabel("Password: ");
	
	private final JTextField userName = new JTextField();
	private final JTextField password = new JTextField();
	
	private final JButton LOG_IN = new JButton("LOG IN");
	private final JButton CREATE_NEW_ACCOUNT = new JButton("Create A New Account");

	public BankGUIMain(){
		super("Bank of AWESOME!!!");
		setDefaults();
		//setDefaultForOpenAccount
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(
			layout.createParallelGroup()
				.addComponent(label1)
				.addGroup( layout.createSequentialGroup()
							.addGroup( layout.createParallelGroup()
										.addGroup( layout.createSequentialGroup()
													.addComponent(USERNAME)
													.addComponent(userName) )
										.addGroup( layout.createSequentialGroup()
													.addComponent(PASSWORD)
													.addComponent(password) ) )
							.addComponent(LOG_IN) )
				.addComponent(CREATE_NEW_ACCOUNT) );
		
		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addComponent(label1)
				.addGroup( layout.createParallelGroup()
							.addGroup( layout.createSequentialGroup()
										.addComponent(USERNAME)
										.addComponent(PASSWORD) )
							.addGroup( layout.createSequentialGroup()
										.addComponent(userName)
										.addComponent(password) )
							.addComponent(LOG_IN) )
				.addComponent(CREATE_NEW_ACCOUNT) );
		
		
		
		
	}
	
	private void setDefaults() {
		// TODO set the default sizes of swing elements
		label1.setMaximumSize(new Dimension(200, 50));
		USERNAME.setMaximumSize(new Dimension(100, 30));
		PASSWORD.setMaximumSize(new Dimension(100, 30));
		userName.setMaximumSize(new Dimension(100, 30));
		password.setMaximumSize(new Dimension(100, 30));
		LOG_IN.setMaximumSize(new Dimension(100, 30));
		CREATE_NEW_ACCOUNT.setMaximumSize(new Dimension(100, 30));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500,200);
		
		CREATE_NEW_ACCOUNT.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				createNewAccount();
			}
		});
		
	}
	

	private void createNewAccount() {
		// TODO Code to change GUI to "Create New Account" Look 
		// TODO Code to create new account
		String userResponse = JOptionPane.showInputDialog("You clicked on Create New Account");
		label1.setText("You are creating a new Account");
	}

	private String getWelcomeMessage() {
		return "Welcome to the Bank of Awesome." +
				"Please log in or create a new account.";
	}

	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new BankGUIMain().setVisible(true);
			}
		});
	}
	
}
