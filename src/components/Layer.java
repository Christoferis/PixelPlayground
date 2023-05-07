package components;

import java.util.function.BiConsumer;

import main.Core;
import util.Union;
import util.UtilMath;

public class Layer {

    Pixel[][] matrix;
    int width, height;

    public Layer() {
        //new matrix the height of the thing
        width = Core.getInstance().getSize().getLeft();
        height = Core.getInstance().getSize().getRight();

        matrix = new Pixel[width][height];

        //fill with blank Pixels
        for (Pixel[] p : matrix) {
            for (int i = 0; i < p.length; i++) {
                p[i] = new Pixel();
            }
        }

    }

    public Pixel getPixel(int x, int y){
        return matrix[(int)UtilMath.clamp(y, 0, height - 1)][(int)UtilMath.clamp(x, 0, width - 1)];
    }

    public void setPixel(Pixel p, int x, int y){
        matrix[(int)UtilMath.clamp(y, 0, height - 1)][(int)UtilMath.clamp(x, 0, width - 1)] = p;
    }

    public Union<Integer, Integer> getSize(){
        return new Union<>(width, height);
    }
    
    public void forEachPixel(BiConsumer<Union<Integer, Integer>, Pixel> function){
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                function.accept(new Union<Integer, Integer>(x, y), getPixel(x, y));
            }
        }
    }
}
