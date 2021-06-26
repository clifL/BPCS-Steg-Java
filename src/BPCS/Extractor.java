package BPCS;

import java.util.*;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;
import java.nio.file.*;

public class Extractor {
    public static List<Path> ExtractPayload(Path stegoPath, Path outputPath) throws Exception {
        System.out.print("Enter name of image file with secret payload: ");
        ImageReader extraction = new ImageReader(stegoPath.toAbsolutePath().toString());

        List<Segment> resultHiderSegments = extraction.getHiderSegments();

        System.out.println("Complex segments found: " + resultHiderSegments.size());
        System.out.println("Converted to bytes: " + resultHiderSegments.size() * 63 / 8192 + " KB");

        FileFinder secret = new FileFinder(resultHiderSegments);
        Path decodedPath = Path.of(SaveFile());
        List<Path> extractedPayloadPaths = secret.extractPayload(decodedPath);

        return extractedPayloadPaths;
    }
    
    
    public static String SaveFile()
	{
    	JFileChooser f = new JFileChooser(System.getProperty("user.dir"));
        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
        f.showSaveDialog(null);
        String decodedPath = f.getSelectedFile().toString();
        return decodedPath;
	}
	
}
