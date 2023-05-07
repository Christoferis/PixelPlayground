package main;

import components.Layer;
import components.Pixel;
import util.Union;

public class Main {
    public static void main(String[] args) {
        Core c = Core.getInstance();
        c.setSize(new Union<Integer, Integer>(1024, 1024));

        Layer test = new Layer();

        test.forEachPixel((k, v) -> {
            test.setPixel(Pixel.blue, k.getLeft(), k.getRight());
        });

        Layer g = new Layer();

        g.forEachPixel((k, v) -> {
            g.setPixel(new Pixel(255, 0, 0, 1), k.getLeft(), k.getRight());
        });

        c.addLayer(test);
        c.addLayer(g);
        try {
            c.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
