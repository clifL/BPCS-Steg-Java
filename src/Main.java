// import BPCS.Hider;
// import BPCS.Extractor;


// import java.util.*;
import java.awt.EventQueue;
import java.lang.reflect.InvocationTargetException;
// import java.nio.file.*;

public class Main {
    static GUI gui = null;

    public static void main(String[] args) throws Exception {
        
        // runnable for gui obj
        Runnable guiRunnable = new Runnable(){
            public void run(){
                try {
                    gui = new GUI();
                    gui.frameSetVisible(true);
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
        };

        // blocking invoke above runnable 
        try {
            EventQueue.invokeAndWait(guiRunnable);
        } catch (InvocationTargetException | InterruptedException e1) {
            e1.printStackTrace();
        }

        // // cover image by path
        // Path vessel = Paths.get("resources/Vessels/Beagle.png");
        // // payload by path
        // Path payload = Paths.get("resources/Payloads/payload2.txt");
        // // output by path
        // Path output = Paths.get("resources/StegResults/output.png");

        // Hider.HidePayload(vessel, payload, output);

        // // stego-ed image by path
        // Path stego = Paths.get("resources/StegResults/output.png");
        // // output into.. by path
        // Path output2 = Paths.get("resources/ExtractedPayloads");

        // List<Path> extractedPayloads = Extractor.ExtractPayload(stego, output2);

        // int a = 1;
    }
}