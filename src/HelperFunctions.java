import javax.swing.JFileChooser;
import java.io.File;   
import java.util.Scanner;
import java.io.IOException;

public class HelperFunctions {
	
	public static String SelectFile()
	{
		JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
		{
			File selectedFile = chooser.getSelectedFile();
			return selectedFile.getAbsolutePath();
			
		}
		else
		{ 
			return "";
		}
		
	}

	
}
