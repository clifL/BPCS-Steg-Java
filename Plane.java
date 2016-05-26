import java.util.*;

public class Plane {
    private int layer;
    private int[][] cgcPlane, bpcPlane;
    private List<Segment> segments;

    public Plane(int[][] bpc, int[][] cgc, int i) {
        cgcPlane = cgc.clone();
        bpcPlane = bpc.clone();

        layer = i;

        segments = new ArrayList<Segment>();

        //initializes a bunch of segments that can access the different parts of the cgcPlane
        for(int r = 0; r < cgcPlane.length - 8; r += 8) for(int c = 0; c < cgcPlane[0].length - 8; c += 8) segments.add(new Segment(r, c, cgcPlane, layer));
    }

    public String toString() {
        String result = "Plane " + layer + "\r\n";
        for(int k = 0; k < cgcPlane.length; k++) {
            for(int j = 0; j < cgcPlane[0].length; j++) result += cgcPlane[k][j] + " ";
            result += "\r\n";
        }
        return result;
    }

    public int getCGCBit(int r, int c) {//row r, column c, not xy coordinates
        return cgcPlane[r][c];
    }

    public int getBPCBit(int r, int c) {//row r, column c, not xy coordinates
        return bpcPlane[r][c];
    }

    public int getWidth() {
        return cgcPlane[0].length;
    }

    public int getHeight() {
        return cgcPlane.length;
    }

    //number of segments
    public int size() {
        return segments.size();
    }

    public List<Segment> getAllSegments() {
        return segments;
    }

    public List<Segment> getComplexSegmentsOfPlane() {
        List<Segment> complexSegments = new ArrayList<Segment>();
        for(Segment segment : segments) if(segment.isComplex()) complexSegments.add(segment);
        return complexSegments;
    }
}
