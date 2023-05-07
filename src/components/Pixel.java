package components;

import java.awt.Color;

import util.UtilMath;

public class Pixel {
    
    public int r, g, b;
    public double a;

    public static Pixel black = new Pixel();
    public static Pixel white = new Pixel(255, 255, 255);
    public static Pixel red = new Pixel(255, 0, 0);
    public static Pixel green = new Pixel(0, 255, 0);
    public static Pixel blue = new Pixel(0, 0, 255);
    public static Pixel teal = new Pixel(0, 255, 255);
    public static Pixel pink = new Pixel(255, 0, 255);

    //init custom
    public Pixel(int r, int g, int b, double a) {
        //clamp to between 0 and 255
        this.r = (int) UtilMath.clamp(r, 0, 255);
        this.g = (int) UtilMath.clamp(g, 0, 255);
        this.b = (int) UtilMath.clamp(b, 0, 255);

        //alpha is percentage
        this.a = UtilMath.clamp(a, 0, 1);
    }

    //init: a = 255
    public Pixel(int r, int g, int b) {
        this(r, g, b, 1);
    }

    //init: Black
    public Pixel(){
        this(0, 0, 0);
    }

    /*
     * Converts a given Pixel to Grayscale using the luminosity method (https://www.baeldung.com/cs/convert-rgb-to-grayscale)
     * weights:
     * r = 0.3
     * g = 0.59
     * b = 0.11
     */
    public Pixel toGrayscale(){
        int gray = (int) (r * 0.3 + g * 0.59 + b * 0.11);
        return new Pixel(gray, gray, gray, a);
    }

    public Color toColor(){
        // a already 0-1
        return new Color(
            (float) UtilMath.relativeScale(r, 0, 255, 0, 1),
            (float) UtilMath.relativeScale(g, 0, 255, 0, 1),
            (float) UtilMath.relativeScale(b, 0, 255, 0, 1),
            (float) a
        );
    }

    /*
     * Adds p1 and p2
     */
    public static Pixel add(Pixel p1, Pixel p2){
        return new Pixel(p1.r + p2.r, p1.g + p2.g, p1.b + p2.b, p1.a + p2.a);
    }

    /*
     * Subtracts p1 from p2
     */
    public static Pixel sub(Pixel p1, Pixel p2){
        return new Pixel(p1.r + p2.r, p1.g + p2.g, p1.b + p2.b, p1.a + p2.a);
    }
}
