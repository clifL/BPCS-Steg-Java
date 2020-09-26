package BPCS;

import java.nio.file.*;

public class ExtractedPayload {
    public byte[] byteForm;
    public int bitsTaken;
    public Path filePath;

    public ExtractedPayload(byte[] byteForm, int bitsTaken, Path filePath){
        this.byteForm = byteForm;
        this.bitsTaken = bitsTaken;
        this.filePath = filePath;
    }
}
