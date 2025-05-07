package guis;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import objects.Bank;
import objects.BankAccount;
import objects.Date;
import objects.Person;
import enums.AccountType;



@SuppressWarnings("serial")
public class BankGUI extends JFrame {
	
	private Bank MY_BANK = new Bank("Bank of Random", 0.0, true, new HashMap<Integer, BankAccount>());;
	
	private ArrayList<JLabel> labels = new ArrayList<JLabel>();
	private ArrayList<JTextField> textFields = new ArrayList<JTextField>();
	private ArrayList<JButton> buttons = new ArrayList<JButton>();
	private ArrayList<JRadioButton> radioButtons = new ArrayList<JRadioButton>();
	
	private HashMap<JButton, ActionListener> buttonActions = new HashMap<JButton, ActionListener>();
	private HashMap<JRadioButton, ActionListener> radioButtonActions = new HashMap<JRadioButton, ActionListener>();
	private HashMap<JTextField, MouseListener> textFieldActions = new HashMap<JTextField, MouseListener>();
	
	
	private final int DEFAULT_NUM_SWING_OBJECTS = 20;
	
	public BankGUI(){
		makeDefaultNumSwingObjects();
		setUpBankFromFile();
		mainPage();
	}

	private void makeDefaultNumSwingObjects() {
		for ( @SuppressWarnings("unused") int i : new int [DEFAULT_NUM_SWING_OBJECTS] ) {
			this.labels.add(new JLabel());
			this.textFields.add(new JTextField());
			this.buttons.add(new JButton());
			this.radioButtons.add(new JRadioButton());
		}
	}

	private void mainPage() {
		resetSwingElements();
		
		/*
		 * START:	JLabels
		 * NO LISTENERS
		 */
		
		JLabel welcomeMessage = this.labels.get(0), 
			   userNameLabel = this.labels.get(1),
			   passwordLabel = this.labels.get(2);
		
		/*
		 * END:		JLabels
		 * START:	JTextFields
		 * NO LISTENERS
		 */
		
		JTextField userNameField = this.textFields.get(0),
				   passwordField = this.textFields.get(1);
		
		/*
		 * END:		JTextFields
		 * START:	JButtons
		 * 
		 * LISTENERS	- Log In
		 * 				- Create New Account
		 */
		JButton logInButton = this.buttons.get(0),
				createNewAccountButton = this.buttons.get(1);
		
		ActionListener logInAction = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logIn(userNameField, passwordField);
			}
		};
		
		ActionListener createNewAccountAction = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				createNewAccount();
			}
		};
		
		this.buttonActions.put(logInButton, logInAction);
		this.buttonActions.put(createNewAccountButton, createNewAccountAction);
		
		logInButton.addActionListener(logInAction);
		createNewAccountButton.addActionListener(createNewAccountAction);
		
		/*
		 * END:		JButtons
		 */
		
		welcomeMessage.setVisible(true);
		welcomeMessage.setText(getWelcomeMessage());
		
		userNameLabel.setVisible(true);
		userNameLabel.setText("Username: ");
		
		userNameField.setVisible(true);
		userNameField.setToolTipText("Please enter your username for your bank account.");
		
		passwordField.setVisible(true);
		passwordField.setToolTipText("Please enter your password for your bank account.");
		
		passwordLabel.setVisible(true);
		passwordLabel.setText("Password: ");
		
		logInButton.setVisible(true);
		logInButton.setText("Log In");
		
		createNewAccountButton.setVisible(true);
		createNewAccountButton.setText("Create A New Account");
		
		welcomeMessage.setMaximumSize(new Dimension(200, 50));
		userNameLabel.setMaximumSize(new Dimension(100, 30));
		passwordLabel.setMaximumSize(new Dimension(100, 30));
		userNameField.setMaximumSize(new Dimension(100, 30));
		passwordField.setMaximumSize(new Dimension(100, 30));
		logInButton.setMaximumSize(new Dimension(70, 70));
		createNewAccountButton.setMaximumSize(new Dimension(100, 30));
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500,200);
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(
			layout.createParallelGroup()
				.addComponent(welcomeMessage)
				.addGroup( layout.createSequentialGroup()
							.addGroup( layout.createParallelGroup()
										.addGroup( layout.createSequentialGroup()
													.addComponent(userNameLabel)
													.addComponent(userNameField) )
										.addGroup( layout.createSequentialGroup()
													.addComponent(passwordLabel)
													.addComponent(passwordField) ) )
							.addComponent(logInButton) )
				.addComponent(createNewAccountButton) );
		
		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addComponent(welcomeMessage)
				.addGroup( layout.createParallelGroup()
							.addGroup( layout.createSequentialGroup()
										.addComponent(userNameLabel)
										.addComponent(passwordLabel) )
							.addGroup( layout.createSequentialGroup()
										.addComponent(userNameField)
										.addComponent(passwordField) )
							.addComponent(logInButton) )
				.addComponent(createNewAccountButton) );
		
		
		
	}
	
	private void resetSwingElements() {
		
		for (JLabel label : this.labels) {
			label.setText("");
			label.setVisible(false);
		}
		
		for (JButton button : this.buttons) {
			button.setText("");
			button.setVisible(false);
			button.removeActionListener(this.buttonActions.remove(button));
		}
		
		for (JRadioButton button : this.radioButtons) {
			button.setText("");
			button.setVisible(false);
			button.setSelected(false);
			button.removeActionListener(this.radioButtonActions.remove(button));
		}
		
		for (JTextField field : this.textFields) {
			field.setText("");
			field.setToolTipText("");
			field.setVisible(false);
			field.removeMouseListener(this.textFieldActions.remove(field));
		}
		
		
	}

	protected void logIn(JTextField userNameField, JTextField passwordField) {
		String userName = userNameField.getText();
		String password = passwordField.getText();
		System.out.println(userName);
		System.out.println(password);
		clearGUI();
		
	}

	protected void createNewAccount() {
		clearGUI();
		resetSwingElements();
		
		/*
		 * START: 	JLabels
		 * NO LISTENERS
		 */
		JLabel title = this.labels.get(0),
			   messageL1 = this.labels.get(1),
			   messageL2 = this.labels.get(2),
			   messageL3 = this.labels.get(3),
			   firstNameLabel = this.labels.get(4),
			   lastNameLabel = this.labels.get(5),
			   birthdayLabel = this.labels.get(6),
			   initDepoLabel = this.labels.get(7),
			   usernameLabel = this.labels.get(8),
			   passwordLabel = this.labels.get(9),
			   accTypeLabel = this.labels.get(10);
		
		for ( int index = 0; index < 11; index++ ) {
			this.labels.get(index).setVisible(true);
			this.labels.get(index).setMaximumSize(new Dimension(100,30));
		}
		
		title.setText("Creating A New Account");
		messageL1.setText("Thank you for choosing to bank with us.");
		messageL2.setText("Please fill out the following information");
		messageL3.setText("An asterisks '*' denoted a required field");
		
		firstNameLabel.setText("First Name*: ");
		lastNameLabel.setText("Last Name*: ");
		
		birthdayLabel.setText("Date Of Birth*: ");
		
		initDepoLabel.setText("Initial Deposit: ");
		
		usernameLabel.setText("Username*: ");
		passwordLabel.setText("Password*: ");
		
		accTypeLabel.setText("Account Type: ");
		
		/*
		 * END: 	JLabels
		 * START: 	JTextFields
		 * 
		 * LISTENERS	- birthdayField
		 * 				- initDepoField
		 */
		
		JTextField firstNameField = this.textFields.get(0),
				   lastNameField = this.textFields.get(1),
				   birthdayField = this.textFields.get(2),
				   initDepoField = this.textFields.get(3),
				   usernameField = this.textFields.get(4),
				   passwordField = this.textFields.get(5);
		
		for ( int index = 0; index < 6; index++ ) {
			this.textFields.get(index).setVisible(true);
			this.textFields.get(index).setMaximumSize(new Dimension(100,30));
		}
		
		firstNameField.setToolTipText("Please enter you first/given name.");
		lastNameField.setToolTipText("Please enter your last/family name.");
		
		birthdayField.setToolTipText("Please enter your birthday in the form mm/dd/yyyy");
		birthdayField.setText("mm/dd/yyyy");
		
		initDepoField.setToolTipText("Please enter the amount at which you wish to start your bank account.");
		initDepoField.setText("0.00");
		
		usernameField.setToolTipText("Please enter your desired username for the bank account.");
		
		passwordField.setToolTipText("Please enter your desired password for the account");
		
		MouseListener birthdayFieldClick = new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				birthdayField.setText("");
			}
			@Override
			public void mousePressed(MouseEvent e) { }
			@Override
			public void mouseReleased(MouseEvent e) { }
			@Override
			public void mouseEntered(MouseEvent e) { }
			@Override
			public void mouseExited(MouseEvent e) { }
		};
		
		MouseListener initDepoFieldClick = new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				initDepoField.setText("");
			}
			@Override
			public void mousePressed(MouseEvent e) { }
			@Override
			public void mouseReleased(MouseEvent e) { }
			@Override
			public void mouseEntered(MouseEvent e) { }
			@Override
			public void mouseExited(MouseEvent e) { }
		};
		
		this.textFieldActions.put(birthdayField, birthdayFieldClick);
		birthdayField.addMouseListener(birthdayFieldClick);
		
		this.textFieldActions.put(initDepoField, initDepoFieldClick);
		initDepoField.addMouseListener(initDepoFieldClick);
		
		/*
		 * END:		JTextFields
		 * START:	JButtons
		 * 
		 * LISTENERS	- mainMenuButton
		 * 				- nextButton
		 */
		
		JButton mainMenuButton = this.buttons.get(0),
				nextButton = this.buttons.get(1);
		
		for ( int index = 0; index < 2; index ++ ) {
			this.buttons.get(index).setVisible(true);
			this.buttons.get(index).setMaximumSize(new Dimension(100,30));
		}
		
		mainMenuButton.setToolTipText("Click to return to the Main Menu.");
		mainMenuButton.setText("MAIN MENU");
		
		nextButton.setToolTipText("Click to move onto the next page of creating an account.");
		nextButton.setText("NEXT");
		
		ActionListener mainMenuButtonAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mainPage();
			}
		};
		
		ActionListener nextButtonAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		
		this.buttonActions.put(mainMenuButton, mainMenuButtonAction);
		this.buttonActions.put(nextButton, nextButtonAction);
		
		mainMenuButton.addActionListener(mainMenuButtonAction);
		nextButton.addActionListener(nextButtonAction);
		
		/*
		 * END:		JButtons
		 * START:	JRadioButtons
		 */
		
		JRadioButton personalRButton = this.radioButtons.get(0),
					 businessRButton = this.radioButtons.get(1);
		
		for ( int index = 0; index < 2; index++ ) {
			this.radioButtons.get(index).setVisible(true);
			this.radioButtons.get(index).setMaximumSize(new Dimension(100,30));
		}
		
		personalRButton.setToolTipText("Select to set your account to PERSONAL");
		personalRButton.setText("PERSONAL");
		
		businessRButton.setToolTipText("Select to set your account to BUSINESS");
		businessRButton.setText("BUSINESS");
		
		/*
		 * END:		JRadioButtons
		 */
		
		/*
		 * START:	LAYOUT FOR "CREATE NEW ACCOUNT"
		 */
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		setSize(450,300);
		
		layout.setHorizontalGroup(
				layout.createParallelGroup()
					.addComponent(title)
					.addComponent(messageL1)
					.addComponent(messageL2)
					.addComponent(messageL3)
					.addGroup(
							layout.createSequentialGroup()
								.addComponent(firstNameLabel)
								.addComponent(firstNameField)
								.addComponent(usernameLabel)
								.addComponent(usernameField))
					.addGroup(
							layout.createSequentialGroup()
								.addComponent(lastNameLabel)
								.addComponent(lastNameField)
								.addComponent(passwordLabel)
								.addComponent(passwordField))
					.addGroup(
							layout.createSequentialGroup()
								.addComponent(birthdayLabel)
								.addComponent(birthdayField)
								.addComponent(accTypeLabel)
								.addComponent(personalRButton))
					.addGroup(
							layout.createSequentialGroup()
								.addComponent(initDepoLabel)
								.addComponent(initDepoField)
								.addGap(100)
								.addComponent(businessRButton))
					.addGroup(
							layout.createSequentialGroup()
								.addComponent(mainMenuButton)
								.addGap(200)
								.addComponent(nextButton)));
		
		layout.setVerticalGroup(
				layout.createSequentialGroup()
					.addComponent(title)
					.addComponent(messageL1)
					.addComponent(messageL2)
					.addComponent(messageL3)
					.addGroup(
							layout.createParallelGroup()
								.addComponent(firstNameLabel)
								.addComponent(firstNameField)
								.addComponent(usernameLabel)
								.addComponent(usernameField))
					.addGroup(
							layout.createParallelGroup()
								.addComponent(lastNameLabel)
								.addComponent(lastNameField)
								.addComponent(passwordLabel)
								.addComponent(passwordField))
					.addGroup(
							layout.createParallelGroup()
								.addComponent(birthdayLabel)
								.addComponent(birthdayField)
								.addComponent(accTypeLabel)
								.addComponent(personalRButton))
					.addGroup(
							layout.createParallelGroup()
								.addComponent(initDepoLabel)
								.addComponent(initDepoField)
								.addGap(30)
								.addComponent(businessRButton))
					.addGroup(
							layout.createParallelGroup()
								.addComponent(mainMenuButton)
								.addComponent(nextButton)));
		
		getContentPane().repaint();
		
		/*
		 * END:		LAYOUT "CREATE NEW ACCOUNT"
		 */
	}

	private void clearGUI() {
		getContentPane().removeAll();
		getContentPane().repaint();		
	}

	private String getWelcomeMessage() {
		return "Welcome to the Bank of Awesome." +
				"Please log in or create a new account.";
	}

	private void setUpBankFromFile() {
		ArrayList<String> in = new ArrayList<String>();
		try{
			Scanner input = new Scanner(new File("bank.txt"));
			while ( input.hasNextLine() ) {
				in.add(input.nextLine());
			}
			input.close();
			doBankAccountOperation(in);
		}
		catch ( Exception e ){
			
		}
	}
	
	private void doBankAccountOperation(ArrayList<String> in) {
		
		while(!in.isEmpty()) {
			try {
				String [] inp = in.get(0).split(" ");
				String qualifier = inp[0];
				
				switch( qualifier.toLowerCase() ){
					case "/bankName"	:	this.MY_BANK.setName(getBankName(inp));
											in.remove(0);
											break;
											
					case "/bankbalance"	:	this.MY_BANK.setBalance(Double.parseDouble(inp[1]));
											in.remove(0);
											break;
											
					case "/bankfdic"	:	this.MY_BANK.setFDIC(detFDIC(inp[1]));
											in.remove(0);
											break;
											
					default				:	makeBankAccounts(in);
											break;
				}
			}
			catch ( IndexOutOfBoundsException ioobe ) {
				
			}
			catch ( NumberFormatException nfe ){
				
			}
			catch ( Exception e ) {
				
			}
		}		
	}

	private void makeBankAccounts(ArrayList<String> in) {
		try{
			int accNum = Integer.parseInt(in.get(0).split(" ")[0]);
			
			if ( this.MY_BANK.getBankAccounts().containsKey(accNum) ) {
				while ( Integer.parseInt(in.get(0).split(" ")[0]) == accNum ) {
					BankAccount current = this.MY_BANK.getBankAccounts().get(accNum);
					String [] inp = in.get(0).split(" ");
					
					switch(inp[1].toLowerCase()) {
						case "/o"	:	current.changeOwner(
												new Person(inp[2], 
														inp[3], 
														   new Date(inp[4])));
										in.remove(0);
										break;
											
						case "/a"	:	current.addPerson(
													new Person(inp[2], 
															inp[3], 
															   new Date(inp[4])));
										in.remove(0);
										break;
											
						case "/p"	:	current.changePassword(inp[2]);
										in.remove(0);
										break;
						
						case "/t"	:	current.setAt(getAccountType(inp));
										in.remove(0);
										break;
						
						case "/b"	:	current.setBalance(Double.parseDouble(inp[2]));
										in.remove(0);
										break;
						
						default		: 	in.remove(0);
										throw new Exception();
					}
				}
			}
			else {
				String firstName = "", lastName = "", password = "", username = "";
				Date birthday = null;
				double initDeposit = 0.0;
				AccountType at = AccountType.Personal;
				ArrayList<Person> auth = new ArrayList<Person>();
				
				while ( Integer.parseInt(in.get(0).split(" ")[0]) == accNum ) {
					String [] inp = in.get(0).split(" ");
					switch(inp[1].toLowerCase()) {
						case "/o"	:
										firstName = inp[2];
										lastName = inp[3];
										birthday = new Date(inp[4]);
										in.remove(0);
										break;
											
						case "/a"	:	String authFirst = inp[2];
										String authLast = inp[3];
										Date authBirth = new Date(inp[4]);
										auth.add(new Person(authFirst, authLast, authBirth));
										in.remove(0);
										break;
											
						case "/p"	:	password = getPassWord(inp);
										in.remove(0);
										break;
						
						case "/t"	:	at = getAccountType(inp);
										in.remove(0);
										break;
						
						case "/b"	:	initDeposit = Double.parseDouble(inp[2]);
										in.remove(0);
										break;
						
						case "/u"	:	username = getUsername(inp);
										in.remove(0);
										break;
						
						default		: 	in.remove(0);
										throw new Exception();
					}
				}
				
				if ( firstName.isEmpty() || lastName.isEmpty() || password.isEmpty() || username.isEmpty() || birthday == null )
					throw new Exception();
				
				this.MY_BANK.openAccount(firstName, lastName, birthday, username, password, initDeposit, at, auth, accNum);
			}
		}
		catch ( Exception e ){
			
		}
		
	}

	private String getUsername(String[] inp) {
		String userName = "";
		
		for ( int i = 2; i < inp.length; i++ )
			userName += inp[i] + " ";
		
		return userName;
	}

	private String getPassWord(String[] inp) {
		String pass = "";
		
		for ( int i = 2; i < inp.length; i++ )
			pass += inp[i] + " ";
		
		return pass;
	}

	private AccountType getAccountType(String[] input) {
		return input[2].equalsIgnoreCase("business") ? AccountType.Business : AccountType.Personal;
	}

	private boolean detFDIC(String string) {
		return string.equalsIgnoreCase("true") || string.equalsIgnoreCase("yes");
	}

	private String getBankName(String[] input) {
		String bankName = "";
		
		for ( int i = 1; i < input.length; i++ )
			bankName += input[i] + " ";
		
		return bankName;
	}

	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new BankGUI().setVisible(true);
			}
		});
	}
	
}
