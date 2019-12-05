import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainWindow extends JFrame
{
	private static final long serialVersionUID = -3880026026104218593L;
	private Primes m_Primes;
	private JTextField tfPrimeFileName;
	private JTextField tfCrossFileName;
	private JLabel lblPrimeCount;
	private JLabel lblCrossCount;
	private JLabel lblLargestPrime;
	private JLabel lblLargestCross;
	private JLabel lblStatus;
	
	MainWindow(String name, Primes p)
	{
		m_Primes = p;
		createMainWindow(name, p);
	}
	protected void createMainWindow(String name, Primes p) {
		JFrame mainWindow = new JFrame(name);
		
		
		mainWindow.setLayout(new GridBagLayout());
		mainWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
		mainWindow.getContentPane().setBackground(Color.decode("#500000"));
		
		GridBagConstraints gbcMain = new GridBagConstraints();
		gbcMain.fill = GridBagConstraints.HORIZONTAL;
		gbcMain.anchor = GridBagConstraints.WEST;
		gbcMain.ipady = 10;
		gbcMain.weightx = .5;
		gbcMain.insets = new Insets(1,2,0,0);
		gbcMain.gridx = 0;
		gbcMain.gridy = 0;
		
		GridBagConstraints gbcPanel = new GridBagConstraints();
		gbcPanel.anchor = GridBagConstraints.WEST;
		gbcPanel.weightx = .5;
		gbcPanel.insets = new Insets(1,2,0,0);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 0;
		
		//FIRST PANEL OF MAIN WINDOW
		JPanel pnlPrimes = new JPanel();
		pnlPrimes.setLayout(new GridBagLayout());
		tfPrimeFileName = new JTextField("primes.txt");
		tfPrimeFileName.setColumns(80);
		pnlPrimes.add(tfPrimeFileName, gbcPanel);
		lblPrimeCount = new JLabel("0");
		gbcPanel.gridx = 1;
		pnlPrimes.add(lblPrimeCount, gbcPanel);
		JLabel lblPrimesFile = new JLabel("Primes File");
		lblPrimesFile.setFont(new Font("Tahoma", Font.PLAIN, 24));
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 1;
		pnlPrimes.add(lblPrimesFile, gbcPanel);
		JButton btnLoadPrimes = new JButton("Load");
		btnLoadPrimes.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		      	boolean temp = FileAccess.loadPrimes(m_Primes,  tfPrimeFileName.getText());
		      	if (temp) {
		      		lblStatus.setText("Primes successfully loaded.");
		      	}
		      	else {
		      		lblStatus.setText("Primes could not be loaded");
		      	}
		      }
		    });
		JButton btnSavePrimes = new JButton("Save");
		btnSavePrimes.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		      	boolean temp = FileAccess.savePrimes(m_Primes, tfPrimeFileName.getText());
		      	if (temp) {
		      		lblStatus.setText("Primes succesfully saved.");
		      	}
		      	else {
		      		lblStatus.setText("Primes could not be saved.");
		      	}
		      }
		    });
		gbcPanel.anchor = GridBagConstraints.EAST;
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 1;
		pnlPrimes.add(btnLoadPrimes, gbcPanel);
		gbcPanel.gridx = 1;
		pnlPrimes.add(btnSavePrimes, gbcPanel);
		mainWindow.getContentPane().add(pnlPrimes, gbcMain);
		
		//SECOND PANEL ON MAIN WINDOW
		gbcPanel.anchor = GridBagConstraints.WEST;
		JPanel pnlCross = new JPanel();
		pnlCross.setLayout(new GridBagLayout());
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 0;
		tfCrossFileName = new JTextField("crosses.txt");
		tfCrossFileName.setColumns(80);
		pnlCross.add(tfCrossFileName, gbcPanel);
		lblCrossCount = new JLabel("0");
		gbcPanel.gridx = 1;
		pnlCross.add(lblCrossCount, gbcPanel);
		JLabel lblHexagonFile = new JLabel("Hexagon Cross File");
		lblHexagonFile.setFont(new Font("Tahoma", Font.PLAIN, 24));
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 1;
		pnlCross.add(lblHexagonFile, gbcPanel);
		JButton btnLoadCrosses = new JButton("Load");
		btnLoadCrosses.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  boolean temp = FileAccess.loadCrosses(m_Primes,  tfCrossFileName.getText());
			      	if (temp) {
			      		lblStatus.setText("Crosses successfully loaded.");
			      	}
			      	else {
			      		lblStatus.setText("Crosses could not be loaded");
			      	}
		      }
		    });
		JButton btnSaveCrosses = new JButton("Save");
		btnSaveCrosses.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  boolean temp = FileAccess.saveCrosses(m_Primes, tfCrossFileName.getText());
		    	  if (temp) {
		    		  lblStatus.setText("Crosses successfully saved.");
		    	  }
		    	  else {
		    		  lblStatus.setText("Crosses could not be saved.");
		    	  }
		      }
		    });
		gbcPanel.anchor = GridBagConstraints.EAST;
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 1;
		pnlCross.add(btnLoadCrosses, gbcPanel);
		gbcPanel.gridx = 1;
		pnlCross.add(btnSaveCrosses, gbcPanel);
		gbcMain.gridy = 1;
		mainWindow.getContentPane().add(pnlCross, gbcMain);
		
		//THIRD PANEL ON MAIN WINDOW (generate buttons)
		gbcPanel.anchor = GridBagConstraints.WEST;
		JPanel pnlButtons = new JPanel();
		pnlButtons.setLayout(new GridBagLayout());
		gbcMain.gridy = 2;
		mainWindow.getContentPane().add(pnlButtons, gbcMain);
		JButton primesButton = new JButton("Generate Primes");
		primesButton.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		      	popupGeneratePrimes();
		      }
		    });
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 0;
		
		gbcPanel.fill = GridBagConstraints.VERTICAL;
		pnlButtons.add(primesButton, gbcPanel);
		
		JButton crossButton = new JButton("Generate Crosses");
		crossButton.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		      	if(m_Primes.primeCount() == 0) {
		      		lblStatus.setText("Status: Must load primes before crosses can be generated.");
		      	}
		      	else{
		      		m_Primes.generateTwinPrimes();
		      		m_Primes.generateHexPrimes();
		      		updateStats();
		      		lblStatus.setText("Status: Crosses generated successfully.");
		      	}
		      }
		    });
		gbcPanel.anchor = GridBagConstraints.EAST;
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 0;
		pnlButtons.add(crossButton, gbcPanel);
		
		
		lblLargestPrime = new JLabel("The largest prime has 0 digits.");
		gbcPanel.anchor = GridBagConstraints.CENTER;
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 0;
		pnlButtons.add(lblLargestPrime, gbcPanel);
		
		lblLargestCross = new JLabel("The largest cross has 0 and 0 digits.");
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 1;
		pnlButtons.add(lblLargestCross, gbcPanel);
		
		//STATUS PANEL ON MAIN WINDOW
		JPanel pnlStatus = new JPanel();
		pnlStatus.setLayout(new GridBagLayout());
		lblStatus = new JLabel("Status: Bored.");
		gbcPanel.fill = GridBagConstraints.HORIZONTAL;
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 0;
		pnlStatus.add(lblStatus, gbcPanel);
		gbcMain.gridx = 0;
		gbcMain.gridy = 3;
		mainWindow.getContentPane().add(pnlStatus, gbcMain);
	
		mainWindow.setSize(1000, 400);
		mainWindow.pack();
		
		mainWindow.setVisible(true);
	}

	protected void popupGeneratePrimes()
	{
		JDialog dPrimes = new JDialog(this, "Prime Number Generation");
		GridBagLayout gridLayout = new GridBagLayout();
		dPrimes.getContentPane().setBackground(new Color(52, 0, 0));
		dPrimes.getContentPane().setLayout(gridLayout);
		
		GridBagConstraints gbcDialog = new GridBagConstraints();
		gbcDialog.fill = GridBagConstraints.HORIZONTAL;
		gbcDialog.anchor = GridBagConstraints.WEST;
		gbcDialog.ipady = 10;
		gbcDialog.weightx = .5;
		gbcDialog.insets = new Insets(1,2,0,0);
		gbcDialog.gridx = 0;
		gbcDialog.gridy = 0;
		
		GridBagConstraints gbcPanel = new GridBagConstraints();
		gbcPanel.anchor = GridBagConstraints.WEST;
		gbcPanel.weightx = .5;
		gbcPanel.insets = new Insets(1,2,0,0);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 0;
		
		JPanel pnlGenerate = new JPanel();
		pnlGenerate.setLayout(new GridBagLayout());
		
		JLabel lblCount = new JLabel("Number of Primes to Generate");
		lblCount.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pnlGenerate.add(lblCount, gbcPanel);
		
		JTextField tfCount = new JTextField();
		lblCount.setLabelFor(tfCount);
		tfCount.setColumns(30);
		gbcPanel.gridx = 1;
		pnlGenerate.add(tfCount, gbcPanel);
		
		JLabel lblStart = new JLabel("Starting Number (does not have to be prime)");
		lblStart.setFont(new Font("Tahoma", Font.PLAIN, 12));
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 1;
		pnlGenerate.add(lblStart, gbcPanel);
		
		JTextField tfStart = new JTextField();
		lblStart.setLabelFor(tfStart);
		tfStart.setColumns(30);
		gbcPanel.gridx = 1;
		pnlGenerate.add(tfStart, gbcPanel);
		
		dPrimes.add(pnlGenerate, gbcDialog);
		
		JPanel pnlButtons = new JPanel();
		pnlButtons.setLayout(new GridBagLayout());
		
		JButton btnGeneratePrimes = new JButton("Generate Primes");
		btnGeneratePrimes.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      	try
      	{
      		BigInteger start = new BigInteger(tfStart.getText());
      		int count = Integer.parseInt(tfCount.getText());
       		m_Primes.generatePrimes(start, count);
       		lblStatus.setText("Status: Excited. Primes have been generated.");
       		updateStats();
      		dPrimes.dispose();
      	}
      	catch(NumberFormatException ex)
      	{
      		lblStatus.setText("You failed to type in an integer successfully. You are terrible at math. Shame.");
      		dPrimes.dispose();
      	}
      	
      }
    });
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 0;
		pnlButtons.add(btnGeneratePrimes, gbcPanel);
		
		JButton btnCancel = new JButton("Cancel Generation");
		btnCancel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      	dPrimes.dispose();
      }
    });
		gbcPanel.anchor = GridBagConstraints.EAST;
		gbcPanel.gridx = 1;
		pnlButtons.add(btnCancel, gbcPanel);		
		
		gbcDialog.gridy = 1;
		dPrimes.add(pnlButtons, gbcDialog);
		
		JPanel pnlStatus = new JPanel();
		pnlStatus.setLayout(new GridBagLayout());

		gbcPanel.anchor = GridBagConstraints.SOUTHWEST;
		gbcPanel.weightx = .5;
		gbcPanel.insets = new Insets(1,2,0,0);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 1;

		JLabel lblNotice = new JLabel("Warning: This application is single threaded, and will freeze while generating primes.");
		lblNotice.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pnlStatus.add(lblNotice, gbcPanel);
		
		gbcDialog.gridy = 2;
		dPrimes.add(pnlStatus, gbcDialog);
		
		dPrimes.setSize(200,200);
		dPrimes.pack(); // Knowing what this is and why it is needed is important. You should read the documentation on this function!
		dPrimes.setVisible(true);		
	}

	// This function updates all the GUI statistics. (# of primes, # of crosses, etc)
	private void updateStats()
	{
		lblLargestPrime.setText("The largest prime has " + m_Primes.sizeofLastPrime() + " digits.");
		lblLargestCross.setText("The largest cross has " + m_Primes.sizeofLastCross().left() + " and " +  m_Primes.sizeofLastCross().right() + " digits.");
		lblPrimeCount.setText(""+ m_Primes.primeCount());
		lblCrossCount.setText("" + m_Primes.crossesCount());
 	}

}
