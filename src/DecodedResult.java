import java.nio.file.Path;


public class DecodedResult {
	public Path stegoPath;
	public Path outputPath;

public DecodedResult(Path stegoPath,Path outputPath)
	{
		this.stegoPath = stegoPath;
		this.outputPath = outputPath;
	}
	
}
