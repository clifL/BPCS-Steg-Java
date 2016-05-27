import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Desktop;
import java.util.*;

public class Hider {
    public static void main(String[] args) throws Exception {
        Scanner key = new Scanner(System.in);

        System.out.print("Enter name of vessel image: ");
        ImageReader vessel = new ImageReader("Vessels/" + key.next());
        System.out.println("Vessel processed.");

        List<Segment> vesselComplexSegments = vessel.getComplexSegmentsOfImage();
        //now list of complex segments from ALL planes has been constructed
        System.out.println("Complex segments available: " + vesselComplexSegments.size());
        System.out.println("Converted to bytes: " + vesselComplexSegments.size() * 63 / 8192 + " KB");

        System.out.print("Enter names of payload files (separated by semicolons): ");
        PayloadFileProcessor payload = new PayloadFileProcessor(key.next().split(";"));

        System.out.println("Payload processed.");
        System.out.println("Total # of blocks: " + payload.blockLength() + "\n" + payload.getNumOfConjugated() + " blocks needed to be conjugated.");

        if(payload.blockLength() > vesselComplexSegments.size()) throw new Exception("Payload too big!");

        for(int j = 0; j < payload.blockLength(); j++) vesselComplexSegments.get(j).replaceWith(payload.getBlock(j));
        //replace complex segments in order with data blocks that are already conjugated or left the same
        System.out.println("Data blocks hidden.");

        System.out.print("Enter name of result image: ");
        StegResultProcessor result = new StegResultProcessor(key.next());

        result.processPlanes(vessel.getPlanes()); //planes have already been modified
        result.constructImage();

        System.out.println("Result image generated!");
    }
}
