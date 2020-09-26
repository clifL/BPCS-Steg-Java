import BPCS.Hider;
import BPCS.Extractor;

import java.util.*;
import java.nio.file.*;

public class Main {
    public static void main(String[] args) throws Exception {
        
        // cover image by path
        Path vessel = Paths.get("resources/Vessels/Beagle.png");
        // payload by path
        Path payload = Paths.get("resources/Payloads/payload2.txt");
        // output by path
        Path output = Paths.get("resources/StegResults/output.png");

        Hider.HidePayload(vessel, payload, output);

        // stego-ed image by path
        Path stego = output;
        // output into.. by path
        Path output2 = Paths.get("resources/ExtractedPayloads");

        List<Path> extractedPayloads = Extractor.ExtractPayload(stego, output2);

        int a = 1;
    }
}