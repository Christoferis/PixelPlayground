package util;

import java.awt.GraphicsEnvironment;

public class ScreenUtils {
 
    public static Union<Integer, Integer> getSize(){
        return new Union<Integer,Integer>(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth(), GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight());
    }
}
