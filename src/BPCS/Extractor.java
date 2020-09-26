package BPCS;

import java.util.*;
import java.nio.file.*;

public class Extractor {
    public static List<Path> ExtractPayload(Path stegoPath, Path outputPath) throws Exception {
        Scanner key = new Scanner(System.in);

        System.out.print("Enter name of image file with secret payload: ");
        ImageReader extraction = new ImageReader(stegoPath.toAbsolutePath().toString());

        List<Segment> resultHiderSegments = extraction.getHiderSegments();

        System.out.println("Complex segments found: " + resultHiderSegments.size());
        System.out.println("Converted to bytes: " + resultHiderSegments.size() * 63 / 8192 + " KB");

        FileFinder secret = new FileFinder(resultHiderSegments);
        List<Path> extractedPayloadPaths = secret.extractPayload(outputPath);

        return extractedPayloadPaths;
    }
}
