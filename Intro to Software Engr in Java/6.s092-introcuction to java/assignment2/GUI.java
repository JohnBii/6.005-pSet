package fileIO;

import java.awt.EventQueue;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GUI extends JFrame{
	
	/**
	 * Main method to run to activate the GUI
	 * @param args
	 */
	public static void main(String[] args){
		/*
		 * Tells java to put the GUI on an event queue to run 
		 */
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new GUI().setVisible(true);
			}
		});
	}
	
	public GUI(){
		// Sets the size of the GUI in pixels
		setSize(170,60);
		
		// Creates a new layout and tell the constructor where to put it.
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout); //  Tell the content pane your layout manager is layout
		
		// Let java determine the appropriate gaps between things
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		// Swing object that are used in the GUI
		JLabel welcome = new JLabel("Welcome"); // Label that says "Welcome"
		JButton clickMe = new JButton("Click Me!"); // Button that says "Click Me"
		
		/*
		 * Setting the layout
		 * 	Java does layouts by thinking of the x and y directions of the GUI independently of each
		 * 	other.  When setting the horizontal direction all we care about is what comes one after
		 * 	the other in sequence in terms of columns and everything in a column is parallel.  When
		 * 	setting the vertical direction all we care about is what are in each row.  Everything in
		 * 	a row is in parallel to each other.
		 */
		layout.setHorizontalGroup(
				layout.createSequentialGroup()
					.addComponent(welcome)
					.addComponent(clickMe));
		
		layout.setVerticalGroup(
				layout.createParallelGroup()
					.addComponent(welcome)
					.addComponent(clickMe));
		
		// What happens when the user presses the X button
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
}
