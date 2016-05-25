import java.awt.Color;

public class Pixel {
    private int red, green, blue;
    private int[] bits; //from lsb to msb

    public Pixel(int rgb) {
        red = (rgb >> 16) & 0xFF;
        green = (rgb >> 8) & 0xFF;
        blue = (rgb >> 0) & 0xFF;

        buildBits();
    }

    public Pixel(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;

        buildBits();
    }

    public Pixel(int[] bitsArr) {
        bits = bitsArr.clone();

        for(int j = 0; j < 24; j++) {
            if(j % 3 == 0) blue += (bits[j] << (j / 3));
            else if(j % 3 == 1) green += (bits[j] << (j / 3));
            else red += (bits[j] << (j / 3));
        }
    }

    public void buildBits() {
        bits = new int[24];
        for(int j = 0; j < 24; j++) {
            if(j % 3 == 0) bits[j] = (blue >> (j / 3)) % 2;
            else if(j % 3 == 1) bits[j] = (green >> (j / 3)) % 2;
            else bits[j] = (red >> (j / 3)) % 2;
        }
        //operation shifts num j bits to the right, then masks everything except least significant
    }

    public int getBit(int dex) {
        return bits[dex];
    }

    public int getRGB() {
        return (new Color(red, green, blue)).getRGB();
    }
}
