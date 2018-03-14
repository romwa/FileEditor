package romwa.runners;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import romwa.editors.*;

/**
 * This class runs the file editors with gui
 * @author rwaisbort992
 * @version alpha
 */
public class FileEditorGui extends JFrame implements ActionListener{

	private JTextArea sFile;
	private JButton read;
	private Container c;
	private FileWriter writer;
	private FileReader reader;
	private File file;
	private JButton changeFile, rewrite, deleteLine, replaceText, concat, burn;

	/**
	 * Set up all of the elements on the gui
	 */
	public FileEditorGui() {
		this(null);
	}


	public FileEditorGui(File file) {
		writer = new FileWriter();
		reader = new FileReader();
		this.file = file;

		sFile = new JTextArea();
		sFile.setBounds(0, 0, 650, 350);
		sFile.setEditable(true);
		if(file != null) read();
		else sFile.setText("File content is gonna be shown here");

		read = new JButton("read");
		changeFile = new JButton("change file");
		replaceText = new JButton("replace");
		concat = new JButton("concat");
		deleteLine = new JButton("delete \nline");
		rewrite = new JButton("rewrite");
		for(int i = 0; i < 6; i++) {
			JButton b = (i == 0) ? read : (i == 1) ? changeFile : (i == 2) ? replaceText : (i == 3) ? concat : (i == 4) ? deleteLine : (i == 5) ? rewrite : null;
			if(b == null) continue;
			int width = 100;
			int height = 50;
			int y = 360;
			b.setBounds(110*i, y, width, height);
			b.addActionListener(this);

		}

		burn = new JButton("burn");
		burn.setBounds(660, 0, 100, 50);
		burn.addActionListener(this);
		
		c = new Container();
		c.add(sFile);
		c.add(read);
		c.add(changeFile);
		c.add(replaceText);
		c.add(concat);
		c.add(deleteLine);
		c.add(rewrite);
		c.add(burn);
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

	//	public void read(File file) {
	//		this.file = file;
	//		sFile.setText(reader.readFile(file));
	//	}

	public void read() {
		String path = JOptionPane.showInputDialog("Path:");
		
		if(path != null) {
			file = new File(path);
			sFile.setText(reader.readFile(file));
		}
	}

	public void changeFile() {
		String path = JOptionPane.showInputDialog("Enter file path");
		file = new File(path);
		read();
	}

	public void replace() {
		String oldText = JOptionPane.showInputDialog("Type old text on one line");
		String newText = JOptionPane.showInputDialog("Type new text");
		int lineNum = reader.getLineNumber(file, oldText);
		//		System.out.println(newText);
		writer.replacePhrase(file, lineNum, oldText, newText);
		read();
	}

	public void concat() {
		String content = JOptionPane.showInputDialog("Type new text");
		writer.concatToFile(file, content);
		read();
	}

	public void rewrite() {
		String content = JOptionPane.showInputDialog("Type new text");
		writer.rewriteFile(file, content);
		read();
	}

	public void deleteLine() {
		String line = JOptionPane.showInputDialog("Line to be deleted");
		writer.deleteLine(file, line);
	}
	
	public void burn() {
		writer.rewriteFile(file, sFile.getText());
	}

	/**
	 * Handles the action performed
	 */
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton)e.getSource();
		if(b == read) 
			read();
		else if(b == changeFile)
			changeFile();
		else if(b == replaceText) 
			replace();
		else if(b == concat)
			concat();
		else if(b == rewrite)
			rewrite();
		else if(b == deleteLine)
			deleteLine();
		else if(b == burn)
			burn();
	}
}
