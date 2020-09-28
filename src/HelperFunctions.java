import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;   
// import java.util.Scanner;
// import java.io.IOException;

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
	
	public static String SaveFile(String extensionFilter)
	{
		JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
		chooser.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter coverFilter = new FileNameExtensionFilter(extensionFilter, extensionFilter);
		chooser.setFileFilter(coverFilter);
		if ( chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
		{
			File fileToSave = chooser.getSelectedFile();
			return fileToSave.getAbsolutePath();
		}
		else
		{ 
			return "";
		}
	}
	
	public static String GetFileExtension(String path)
	{
		File file = new File(path);
	    String fileName = file.toString();
	    int index = fileName.lastIndexOf('.');
	    if (index > 0) {
	      return fileName.substring(index + 1);
	    }
	    else 
	    {
	    	return "";
	    }
	}
	
}

