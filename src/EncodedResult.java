import java.nio.file.Path;

public class EncodedResult {
	public Path vesselPath;
	public Path payloadPath;
	public Path outputPath;
	
	public EncodedResult(Path vesselPath, Path payloadPath, Path outputPath)
	{
		this.vesselPath = vesselPath;
		this.payloadPath = payloadPath;
		this.outputPath = outputPath;
	}
	
	
	
}
