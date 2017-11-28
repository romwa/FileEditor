package romwa.runners;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * This class runs the file editors with gui
 * @author rwaisbort992
 * @version alpha
 */
public class FileEditorRunner extends JFrame implements ActionListener{

	private JTextArea sFile;
	private JButton read;
	private Container c;

	/**
	 * Set up all of the elements on the gui
	 */
	public FileEditorRunner() {
		sFile = new JTextArea();
		sFile.setBounds(50, 50, 200, 200);
		sFile.setEditable(false);
		sFile.setText("File is gonna be shown \nhere");

		read = new JButton("read");
		read.setBounds(50, 260, 100, 50);
		read.addActionListener(this);

		c = new Container();
		c.add(sFile);
		c.add(read);
		add(c);
	}

	/**
	 * Opens a window called "File Editor" at 50, 50 with width of 800 and 
	 		height of 500
	 */
	public void openWindow() {
		this.setTitle("File Editor");
		this.setBounds(50, 50, 800, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//window.setResizable(false);
		this.setVisible(true);
	}

	/**
	 * Handles the action performed
	 */
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton)e.getSource();
		if(b == read) {
			System.out.println("read");
		}
	}
}
