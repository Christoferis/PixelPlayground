package main;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JFrame;
import components.Layer;
import components.Pixel;
import util.ScreenUtils;
import util.Union;

public class Core {
    
    private static Core instance = new Core(500, 500);

    JFrame display;

    //max Pixel thingy (not screen)
    Union<Integer, Integer> size;
    ArrayList<Layer> layers = new ArrayList<>();


    private Core(int width, int height) {
        size = new Union<>(width, height);
    }

    public Union<Integer, Integer> getSize(){
        return size;
    }

    public void addLayer(Layer l){
        layers.add(l);
    }

    public void setSize(Union<Integer, Integer> newSize){
        this.size = newSize;
    }

    private void setupJFrame(JFrame display){
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display.setBackground(Color.BLACK);
        display.setSize(ScreenUtils.getSize().getLeft(), ScreenUtils.getSize().getRight());
        display.setVisible(true);
    }

    public void show() throws Exception{
        //check integrity of all Layers
        for (Layer l : layers){
            if(!l.getSize().equals(size)){
                throw new Exception("Different Resolutions detected, Have created your layers after the Core Object?");
            }
        }

        //check for maximal PixelSize
        int pixelSize = Math.min(Math.min(ScreenUtils.getSize().getLeft() / size.getLeft(), ScreenUtils.getSize().getRight() / size.getLeft()), Math.min(ScreenUtils.getSize().getLeft() / size.getRight(), ScreenUtils.getSize().getRight() / size.getRight()));

        //create Overlay of all Pixels -> Opacity filters
        Layer fin = new Layer();

        //for every pixel
        for(int y = 0; y < size.getRight(); y++){
            for(int x = 0; x < size.getLeft(); x++){
                for(Layer l : layers){
                    //b is background, a the overlapping one https://en.wikipedia.org/wiki/Alpha_compositing
                    Pixel aPixel = l.getPixel(x, y);
                    Pixel bPixel = fin.getPixel(x, y);

                    float alpha = (float) (aPixel.a + bPixel.a * (1 - aPixel.a));
                    int r = (int)((aPixel.r * aPixel.a + bPixel.r * bPixel.a * (1 - aPixel.a)) / alpha);
                    int g = (int)((aPixel.g * aPixel.a + bPixel.g * bPixel.a * (1 - aPixel.a)) / alpha);
                    int b = (int)((aPixel.b * aPixel.a + bPixel.b * bPixel.a * (1 - aPixel.a)) / alpha);

                    fin.setPixel(new Pixel(r, g, b, alpha), x, y);
                }
            }
        }

        //translate to JFrame paint
        display = new JFrame(){
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                
                System.out.println(size);

                for(int y = 0; y < size.getRight(); y++){
                    for(int x = 0; x < size.getLeft(); x++){
                        g.setColor(fin.getPixel(x, y).toColor());
                        g.fillRect(x * pixelSize, y * pixelSize, pixelSize, pixelSize);
                    }
                }
            }
        };

        setupJFrame(display);
    }

    public static Core getInstance(){
        return Core.instance;
    }
}
