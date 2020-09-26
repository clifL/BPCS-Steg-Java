package BPCS;

import java.util.*;
import java.nio.file.*;

public class Extractor {
    public static void ExtractPayload(Path stegoPath) throws Exception {
        Scanner key = new Scanner(System.in);

        System.out.print("Enter name of image file with secret payload: ");
        ImageReader extraction = new ImageReader(stegoPath.toAbsolutePath().toString());

        List<Segment> resultHiderSegments = extraction.getHiderSegments();

        System.out.println("Complex segments found: " + resultHiderSegments.size());
        System.out.println("Converted to bytes: " + resultHiderSegments.size() * 63 / 8192 + " KB");

        FileFinder secret = new FileFinder(resultHiderSegments);
        secret.constructFiles();
    }
}
