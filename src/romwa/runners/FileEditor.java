package romwa.runners;

import java.io.File;

public class FileEditor {

	public static void main(String[] args) {
		File file = new File("files\\test.txt");
		FileEditorGui runner = new FileEditorGui();
		runner.openWindow();
		
	}

}
