import BPCS.Hider;
import BPCS.Extractor;
import java.nio.file.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Hider a = new Hider();
        Path vessel = Paths.get("resources/Vessels/Beagle.png");
        Path payload = Paths.get("resources/Payloads/payload2.txt");
        Path output = Paths.get("resources/Payloads/output.png");

        Path stego = output;

        Hider.HidePayload(vessel, payload, output);
        Extractor.ExtractPayload(stego);
    }
}