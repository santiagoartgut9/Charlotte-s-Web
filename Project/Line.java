import java.awt.*;

/**
 * A line that can be manipulated and that draws itself on a canvas.
 * 
 * @Author: Sebastian Galvis Briceno
 * @Author: Santiago Arteaga
 */

public class Line{
    
    public static int VERTICES=3;
    
    private double xZero;
    private double yZero;
    private double xFinal;
    private double yFinal;
    private String color;
    private boolean isVisible;
    
    /**
     * Create a line with a specific starting point and a final one
     */
    public Line(double nXZero, double nYZero, double nXFinal, double nYFinal){
        xZero = nXZero;
        yZero = nYZero;
        xFinal = nXFinal;
        yFinal = nYFinal;
        color = "black";
        isVisible = false;
    }
    
    /**
     * Make this line visible. If it was already visible, do nothing.
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * Make this line invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }
    
    /**
     * Change the color. 
     * @param color the new color. Valid colors are "red", "yellow", "blue", "green",
     * "magenta" and "black".
     */
    public void changeColor(String newColor){
        color = newColor;
        draw();
    }

    /*
     * Draw the triangle with current specifications on screen.
     */
    private void draw(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            int[] xpoints = { (int)xZero, (int)xFinal };
            int[] ypoints = { (int)yZero, (int)yFinal };
            canvas.draw(this, color, new Polygon(xpoints, ypoints, 2));
            canvas.wait(10);
        }
    }

    /*
     * Erase the triangle on screen.
     */
    private void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
}
