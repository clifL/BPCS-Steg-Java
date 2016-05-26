import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.util.*;

public class ImageReader {
    private Pixel[][] pixels;
    private int width, height;
    private Plane[] planes;

    public ImageReader(String fileName) throws IOException {
        BufferedImage image = ImageIO.read(new File(fileName));

        width = image.getWidth();
        height = image.getHeight();

        pixels = new Pixel[height][width];

        System.out.println("Height: " + height + " Width: " + width);

        for(int j = 0; j < height; j++) for(int k = 0; k < width; k++) pixels[j][k] = new Pixel(image.getRGB(k, j));

        planify();
    }

    public ImageReader() throws IOException {
        this("Vessel.png");
    }

    public Plane[] getPlanes() {
        return planes;
    }

    //returns ith bit plane
    public Plane getBitPlane(int i) {
        return planes[i];
    }

    public void planify() throws IOException {
        planes = new Plane[24];

        for(int i = 0; i < 24; i++) { //24 bitplanes
            int[][] cgc = new int[height][width];
            int[][] bpc = new int[height][width];
            for(int j = 0; j < height; j++) {
                for(int k = 0; k < width; k++) {
                    bpc[j][k] = pixels[j][k].getBit(i);
                    cgc[j][k] = (i < 21)? pixels[j][k].getBit(i) ^ pixels[j][k].getBit(i + 3) : pixels[j][k].getBit(i); //convert to CGC notation
                }
            }
            planes[i] = new Plane(bpc, cgc, i);
        }
    }

    public List<Segment> getComplexSegmentsOfImage() {
        List<Segment> complexSegments = new ArrayList<Segment>();
        for(int i = 0; i < 24; i++) complexSegments.addAll(planes[i].getComplexSegmentsOfPlane());
        return complexSegments;
    }

}
